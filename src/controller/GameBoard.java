package controller;

import java.util.ArrayList;

import model.Card;
import model.Deck;
import model.GameState;
import model.Player;
import model.Rank;



/**
 * @author Jordan King
 * @version 1.0
 * 
 * The panel that is used in both the PlayPanel and the AutoPanel to display and model a
 * game of blackjack. It contains the complete model of a game of blackjack, for an arbitrary
 * number of hands played between one player and a dealer. It tracks losses and finances.
 * 
 */
	
	/////////////////////////////////Constants/////////////////////////////////////////////////////


public class GameBoard  {

	
	/**
	 * A constant for the default amount of starting cash.
	 */
	static private final int DEFAULT_CASH = 1000;

	/**
	 * A constant for the default amount to bet.
	 */
	static private final int MINIMUM_BET = 10;
	
	/**
	 * A constant for the default amount to bet.
	 */
	static private final boolean INFINITEMONEY = true;
	
	/////////////////////////////////Data Model////////////////////////////////////////////////////


	/**
	 * The dealer is a private member that models the current state of the dealer in the game.
	 */
	private Player dealer;

	/**
	 * The player is a private member that models the current state of the player in the game.
	 */
	private Player player;

	/**
	 * The deck is a private member that enables the game model to accurately draw cards from
	 * randomized decks.
	 */
	private Deck deck;

	/**
	 * The cash is a private member that represents the current amount of money that the player has
	 * to bet with.
	 */
	private int cash;
	
	/**
	 * The totalWins is a private member that tracks the total wins the player has.
	 */
	private int totalHandWins;
	
	/**
	 * The totalWins is a private member that tracks the total lost hands the player has.
	 */
	private int totalHandLosses;
	
	/**
	 * The totalWins is a private member that tracks the total pushed hands the player has.
	 */
	private int totalHandTies;
	
	/**
	 * The losses is a private member that tracks the total amount of money lost by the player over
	 * the course of this game's last initialization (or creation).
	 */
	private int losses;

	/**
	 * The bet is a private member that represents the amount of money that the player is betting on
	 * the current hand.
	 */
	private int bet;

	/**
	 * The handNumber is the number of hands of blackjack that have been played since this game's 
	 * last initialization (or creation).
	 */
	private int handNumber;

	/////////////////////////////////States////////////////////////////////////////////////////////

	
	/**
	 * The current state of the main hand, used to determine the game state.
	 */
	private GameState mainHandState;

	/**
	 * The current state of the split hand.
	 */
	private GameState splitHandState;

	/**
	 * A boolean indicating that the player has split their hand!
	 */
	private boolean handHasSplit;
	


	/////////////////////////////////Rules////////////////////////////////////////////////////////
	
	/**
	 * This rule adds a huge house edge.
	 */
	private boolean dealerWinsPush;
	
	/**
	 * The soft seventeen is a rule where the dealer must hit if they have an ace and 17 points.
	 */
	private boolean softSeventeenRule;
	
	/**
	 * Toggles whether surrendering is allowed.
	 */
	private boolean surrenderAllowed;
	
	/**
	 * If the player splits aces, they usually are not allowed to do anything more with them.
	 */
	private boolean splittingAcesEndsControl;
	
	/**
	 * True only lets players split cards with the same rank.
	 */
	private boolean onlySplitOnSameRank;
	
	/**
	 * Toggles allowing the player to double on either hand after splitting.
	 */
	private boolean doubleAllowedAfterSplit;
	
	/**
	 * The number of the decks to play with!
	 */
	private int numberOfDecks;
	
	/**
	 * The ratio to pay blackjacks at.
	 */
	private double blackjackPayRatio;

	/////////////////////////////////Methods/////////////////////////////////////////////////////

	
	/**
	 * The default constructor just initializes the data model. I am not sure how this will interact
	 * with the rest of the panels, as the layout has not been addressed.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public GameBoard() {

		// initialize the game model
		initModel();

	}

	/**
	 * Starts the game loop for one hand of blackjack. 
	 * 
	 * @param none
	 * @return success a boolean indicating success.
	 * @since 1.0
	 */
	public boolean playHand() {

		// make sure there is no active hand
		if (mainHandState != GameState.END || splitHandState != GameState.END) {
			System.out.println("Can't begin new hand because the previous hand has not ended.");
			return false;
		}

		// See if the player has money to play
		if (cash < 0 && !INFINITEMONEY) {
			System.out.println("There was not enough cash to play a hand.");
			return false;
		}
		
		// reset states
		resetHandStates();

		// reset player
		player.reset();

		// reset dealer
		dealer.reset();

		// We are now on the next hand.
		handNumber++;

		// set the state
		mainHandState = GameState.BET;

		// set the bet to the minimum
		bet = MINIMUM_BET;

		// successfully began hand
		return true;

	}

	/**
	 * Sets the player bet to the parameter value.
	 * 
	 * Called from state BET.
	 * 
	 * @param newBet the new value to set as the player's bet.
	 * @return success a boolean indicating success
	 * @since 1.0
	 */
	public boolean bet(int newBet) {

		// check state.
		if(mainHandState != GameState.BET){

			// errors
			System.out.println("Can't bet from a state other than the bet state.");
			return false;
		}


		// set the new bet
		bet = newBet;

		// success
		return true;
	}

	/**
	 * Gets a new deck (or more), shuffles the deck(s), deals 2 cards to player 
	 * and 1 card to dealer.
	 * 
	 * Called from state BET.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public boolean deal() {
		if (mainHandState != GameState.BET) {

			// errors!
			System.out.println("Could not deal a hand from a state other than BET.");
			return false;

		}

		// get a fresh deck
		deck = new Deck(numberOfDecks);

		// Shuffle deck up front
		deck.shuffle();

		// deal the first card to the player
		player.addCard(deck.drawCard());

		// deal the second card to the dealer
		dealer.addCard(deck.drawCard());

		// deal the third card to the player
		player.addCard(deck.drawCard());

		// change the game state
		mainHandState = GameState.DEAL;

		// success
		return true;

	}

	/**
	 * Tells the model that the player would like to hit. 
	 * 
	 * Called from states deal, hit, or split
	 * 
	 * @param none
	 * @return success a boolean indicating a successful hit
	 * @since 1.0
	 */
	public boolean hit() {

		// Make sure that the model is in a good state
		if (mainHandState != GameState.DEAL &&
				mainHandState != GameState.HIT && 
				mainHandState != GameState.SPLIT) {

			// print errors!
			System.out.println("Can only hit from states DEAL, HIT, or SPLIT.");
			return false;

		}

		// Add a card to the player
		player.addCard(deck.drawCard());

		// check for bust
		if (player.getPoints() > 21) {

			// resolved now.
			resolveMain();

			// still a successful hit
			System.out.println("BUST");
			return true;

		}

		// if we did not bust, we are in the hit state.
		mainHandState = GameState.HIT;

		// successfully hit
		return true;

	}

	/**
	 * Tells the model that the player would like to stand.
	 * 
	 * Called from states deal, hit, or split
	 * 
	 * @param none
	 * @return success a boolean indicating a successful stand.
	 * @since 1.0
	 */
	public boolean stand() {

		// Make sure that the model is in a good state
		if (mainHandState != GameState.DEAL &&
				mainHandState != GameState.HIT && 
				mainHandState != GameState.SPLIT) {

			// errors!
			System.out.println("Can only stand from states DEAL, HIT, or SPLIT.");
			return false;

		}

		// this hand is now done
		resolveMain();

		// successful stand
		return true;

	}

	/**
	 * Tells the model that the player would like to split.
	 * 
	 * Called from state deal.
	 * 
	 * @param none
	 * @return success a boolean indicating a successful split
	 * @since 1.0
	 */
	public boolean split() {


		// Make sure that the model is in a good state
		if (mainHandState != GameState.DEAL) {

			// errors!
			System.out.println("Can only split from state DEAL.");
			return false;

		}

		// no splitting after a split
		if (handHasSplit) {

			System.out.println("You can only split once.");
			return false;
		}
		
		// make sure the cards are the same rank
		if (!player.hasPair(onlySplitOnSameRank)){

			System.out.println("The hand may only be split when the player has a pair.");
			return false;
		}

		// move one card from the main hand to the split hand
		player.addCardSplit( player.getHand().remove(1) );

		// hit on both hands
		player.addCard(deck.drawCard());
		player.addCardSplit( deck.drawCard() );
		
		// update the game states
		mainHandState = GameState.SPLIT;
		splitHandState = GameState.SPLIT;
		handHasSplit = true;
		
		// check for rules on splitting aces
		if (player.getHand().get(0).getCardRank() == Rank.ACE && splittingAcesEndsControl) {
			
			// force the player to stand now on both hands
			stand();
			standSplit();
		}



		// successful split
		return true;
	}

	/**
	 * Tells the model that the player would like to double down on the main hand.
	 * 
	 * Called from states deal or split.
	 * 
	 * @param none
	 * @return success a boolean indicating a successful double!
	 * @since 1.0
	 */
	public boolean doubleDown() {


		// Make sure that the model is in a good state
		if (mainHandState != GameState.DEAL && mainHandState != GameState.SPLIT) {

			// errors!
			System.out.println("Can only double down from states DEAL or SPLIT.");
			return false;

		}

		// check if doubling is allowed after a split
		if (!doubleAllowedAfterSplit && handHasSplit) {

			// errors!
			System.out.println("Doubling after a split has been disallowed.");
			return false;
		}

		// this hand has doubled
		mainHandState = GameState.DOUBLE;

		// update the game states with a boolean indicating that they doubled down
		resolveMain();

		// successful double
		return true;
	}

	/**
	 * Tells the model that the player would like to surrender their hand.
	 * 
	 * Called from state deal.
	 * 
	 * @param none
	 * @return success a boolean indicating a successful surrender!
	 * @since 1.0
	 */
	public boolean surrender() {


		// Make sure that the model is in a good state
		if (mainHandState != GameState.DEAL) {

			// errors!
			System.out.println("Can only surrender from state DEAL.");
			return false;

		}
		
		// check if surrendering is allowed
		if (!surrenderAllowed) {

			// errors!
			System.out.println("Surrendering is set to not be allowed.");
			return false;

		}


		//set value of cash and losses for the player
		cash -= Math.ceil(bet/2);
		losses += Math.ceil(bet/2);
		
		// update wins
		totalHandLosses++;

		// set this so the hand is over, because it is.
		mainHandState = GameState.END;

		// reset the states as the hand is over.
		resetHandStates();		

		// successful surrender
		return true;
	}

	/////////////////////////////////splitting stuff//////////////////////////////////////////////

	/**
	 * Tells the model that the player would like to hit on the split hand. 
	 * 
	 * Called from states deal, hit, or split
	 * 
	 * @param none
	 * @return success a boolean indicating a successful hit
	 * @since 1.0
	 */
	public boolean hitSplit() {

		// Make sure that the model is in a good state
		if (splitHandState != GameState.DEAL &&
				splitHandState != GameState.HIT && 
				splitHandState != GameState.SPLIT) {

			// errors!
			System.out.println("Can only hit from states DEAL, HIT, or SPLIT.");
			return false;

		}

		// Add a card to the player
		player.addCardSplit(deck.drawCard());

		// check for bust
		if (player.getPointsSplit() > 21) {

			// resolved now.
			resolveSplit();

			// still a successful hit
			return true;

		}

		// if we did not bust, we are in the hit state.
		splitHandState = GameState.HIT;

		// successfully hit
		return true;
	}

	/**
	 * Tells the model that the player would like to stand on the split hand.
	 * 
	 * Called from states deal, hit, or split
	 * 
	 * @param none
	 * @return success a boolean indicating a successful stand.
	 * @since 1.0
	 */
	public boolean standSplit() {

		// Make sure that the model is in a good state
		if (splitHandState != GameState.DEAL &&
				splitHandState != GameState.HIT && 
				splitHandState != GameState.SPLIT) {

			// errors!
			System.out.println("Can only stand from states DEAL, HIT, or SPLIT.");
			return false;

		}

		// this hand is now done
		resolveSplit();

		// successful stand
		return true;

	}

	/**
	 * Tells the model that the player would like to double down on the split hand.
	 * 
	 * Called from states deal or split.
	 * 
	 * @param none
	 * @return success a boolean indicating a successful double!
	 * @since 1.0
	 */
	public boolean doubleDownSplit() {


		// Make sure that the model is in a good state
		if (splitHandState != GameState.DEAL && splitHandState != GameState.SPLIT) {

			// errors!
			System.out.println("Can only double down from states DEAL or SPLIT.");
			return false;

		}
		
		// check if doubling is allowed after a split
		if (!doubleAllowedAfterSplit) {

			// errors!
			System.out.println("Doubling after a split has been disallowed.");
			return false;
		}

		// this hand has doubled.
		splitHandState = GameState.DOUBLE;

		// update the game states with a boolean indicating that they doubled down
		resolveSplit();

		// successful double
		return true;
	}

	/**
	 * Initializes the game model to a game-start configuration. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void initModel() {
	
		// get new models
		dealer = new Player();
		player = new Player();
		deck = new Deck();
	
		// set the numbers to a new game default
		cash = DEFAULT_CASH;
		losses = 0;
		bet = MINIMUM_BET;
		handNumber = 0;
		totalHandWins = 0;
		totalHandLosses = 0;
	
		// set to a default state that does not accept input
		resetHandStates();
	
		
		// init the rules
		initRules();
	}

	/**
	 * Initializes all of the rules to some default values
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void initRules() {
		
		// this rule is mean, so we don't default to it
		dealerWinsPush = false;
		
		// like in vegas
		softSeventeenRule = true;
		
		// allow surrender
		surrenderAllowed = true;
		
		// disallow control after splitting aces
		splittingAcesEndsControl = true;
		
		// allow splitting regardless of rank
		onlySplitOnSameRank = false;
		
		// less house edge
		doubleAllowedAfterSplit = true;
		
		// best house edge
		numberOfDecks = 8;
		
		// bestHouseEdge
		blackjackPayRatio = 1.2;
	}

	/**
	 * Resolves the main hand and checks to see if the hand can end.
	 * 
	 * @param doubledDown, a boolean indicating that they doubled down
	 * @return none
	 * @since 1.0
	 */
	private void resolveMain() {

		// handle doubling hit
		if (mainHandState == GameState.DOUBLE) {
			player.addCard(deck.drawCard());
		} else {
			mainHandState = GameState.RESOLVED;
		}

		// check for bust
		if (player.getPoints() > 21) {
			loseMain();
		}

		// try to end
		checkScores();
	}


	/**
	 * Resolves the split hand and checks to see if the hand can end.
	 * 
	 * @param doubledDown, a boolean indicating that they doubled down
	 * @return none
	 * @since 1.0
	 */
	private void resolveSplit() {
		// handle doubling hit
		if (splitHandState == GameState.DOUBLE) {
			player.addCardSplit(deck.drawCard());
		} else {
			splitHandState = GameState.RESOLVED;
		}

		// check for bust
		if (player.getPointsSplit() > 21) {
			loseSplit();
		}

		// try to end
		checkScores();
	}

	/**
	 * Checks the scores of the player's hand and split hand (if exists) and ends the hand.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void checkScores() {

		// make sure both hands are ready to end
		if ( !bothHandsResolved() ) {
			return;
		}

		// see if they have already busted
		if (mainHandState == GameState.END && splitHandState == GameState.END) {

			System.out.println("Hand is over, both have busted");
			return;
		}

		// resolve the dealer
		resolveDealerHand();

		// find the winner on the main hand.
		if (dealer.getPoints() > 21) {

			// dealer busted
			if (mainHandState != GameState.END) {
				winMain();
			}
			if (splitHandState != GameState.END) {
				winSplit();
			}
			return;

		} 
		
		// finish the main hand
		if (mainHandState != GameState.END) {
			endMain();
		}
		
		// finish the split hand
		if (splitHandState != GameState.END) {
			endSplit();
		}
	}

	/**
	 * resolveDealerHand()
	 * makes sure the dealer is following the rules: 
	 * hits if under 17 and calls function to check soft seventeen rule
	 * @param none
	 * @return none
	 */
	private void resolveDealerHand() {
		// dealer must take hits until they have < 17 points
		while (dealer.getPoints() < 17) {
	
			// take a hit
			dealer.addCard(deck.drawCard());
	
		}
	
		// check for the soft seventeen rule
		checkSoftSeventeenRule();
	}

	/**
	 * Checks all of the scores on the main hand and decides who wins.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void endMain() {
		
		if (dealer.getPoints() > player.getPoints()) {

			// the player loses!
			loseMain();

		} else if (dealer.getPoints() < player.getPoints()) {

			// the player wins!
			winMain();

		} else if (player.hasBlackjack() && !dealer.hasBlackjack() && !handHasSplit) {
			
			// player wins!
			winMain();
			
		} else if (!player.hasBlackjack() && dealer.hasBlackjack() && !handHasSplit) {
			
			// player loses!
			loseMain();
			
		} else {

			pushMain();

		}
	}
	
	/**
	 * Checks all of the scores on the split hand and decides who wins.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void endSplit() {
		if (dealer.getPoints() > player.getPointsSplit()) {

			// the player loses!
			loseSplit();

		} else if (dealer.getPoints() < player.getPoints()) {

			// the player wins!
			winSplit();

		} else {

			pushSplit();

		}
	}

	/**
	 * Returns whether or not both hands have reached states that allow them to be resolved.
	 * 
	 * @param none
	 * @return bothHandsResolved a boolean
	 * @since 1.0
	 */
	private boolean bothHandsResolved() {
		
		// check for the three applicable states
		return (mainHandState == GameState.RESOLVED 
				|| mainHandState == GameState.DOUBLE
				|| mainHandState == GameState.END ) 
				&& 
				(splitHandState == GameState.RESOLVED 
				|| splitHandState == GameState.DOUBLE
				|| splitHandState == GameState.END);
	}

	/**
	 * Updates for a loss. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void loseMain() {

		// update money
		if (mainHandState == GameState.DOUBLE) {
			cash -= bet*2;
			losses += bet*2;
		} else {
			cash -= bet;
			losses += bet;
		}
		
		// update losses
		totalHandLosses++;

		// update state
		mainHandState = GameState.END;

		System.out.println("Player loses main hand.");

	}

	/**
	 * Updates for a loss on the split. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void loseSplit() {

		// update money
		if (splitHandState == GameState.DOUBLE) {
			cash -= bet*2;
			losses += bet*2;
		} else {
			cash -= bet;
			losses += bet;
		}

		// update losses
		totalHandLosses++;
		
		// update state
		splitHandState = GameState.END;

		System.out.println("Player loses split hand.");

	}

	/**
	 * Updates for a win.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void winMain() {

		// update money
		if (mainHandState == GameState.DOUBLE) {
			cash += bet*2;
			losses -= bet*2;
		} else if (player.hasBlackjack() && !handHasSplit) {
		
			// blackjacks pay differently
			cash += bet * blackjackPayRatio;
			losses -= bet * blackjackPayRatio;
		} else {
			
			// no change
			cash += bet;
			losses -= bet;
		}

		// update wins
		totalHandWins++;
		
		// update state
		mainHandState = GameState.END;

		System.out.println("Player wins main hand.");
	}

	/**
	 * Updates for a win on the split.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void winSplit() {

		// update money
		if (splitHandState == GameState.DOUBLE) {
			cash += bet*2;
			losses -= bet*2;
		} else {
			cash += bet;
			losses -= bet;
		}

		// update wins
		totalHandWins++;
		
		// update state
		splitHandState = GameState.END;

		System.out.println("Player wins split hand.");
	}

	/**
	 * Updates for a push.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void pushMain() {
		
		// end the hand and do nothing
		mainHandState = GameState.END;
		System.out.println("Push on main hand.");

	
		// bad rule
		if (dealerWinsPush) {
			// update money
			cash -= bet;
			losses += bet;
			
			// update losses
			totalHandLosses++;
		}
		totalHandTies++;

	}

	/**
	 * Updates for a push on the split hand.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void pushSplit() {
		
		// end the hand and do nothing
		splitHandState = GameState.END;
		System.out.println("Push on split hand.");
		
		// bad rule
		if (dealerWinsPush) {
			
			// update money
			cash -= bet;
			losses += bet;
		
			// update losses
			totalHandLosses++;
		}
		totalHandTies++;
	}

	/**
	 * Resets both hand states and the handHasSplit to end, end, false.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void resetHandStates() {
		mainHandState = GameState.END;
		splitHandState = GameState.END;
		handHasSplit = false;
	}
		
	/**
	 * Checks for the soft seventeen rule, where the dealer hits on 17 when they have an ace.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private void checkSoftSeventeenRule() {

		// check for the soft seventeen rule
		if (!softSeventeenRule) {
			return;
		}

		
		// some complicated processing
		while(dealer.getPoints() <= 17) {
			
			System.out.println("Here");
			
			// reset
			int dealerValue = 0;
			
			// count all points to the max
			for(Card card: dealer.getHand()){
				dealerValue += card.getCardRank().getCardPoints();
			}
			
			// adjust for aces until we have the lowest corrected hand
			for(Card card: dealer.getHand()) {
				if (dealerValue >= 17 && card.getCardRank() == Rank.ACE) {
					dealerValue -= 10;
				}
			}
			
			// if the lowest soft hand is less than 7, hit
			if (dealerValue <= 7) {
				dealer.addCard(deck.drawCard());
			} else if (dealer.getPoints() < 17) {
				dealer.addCard(deck.drawCard());
			} else {
				break;
			}
		}


	}

	/**
	 * Returns a GameState representing the state of the main hand.
	 * 
	 * @param none
	 * @return mainHandState a GameState representing the state of the main hand.
	 * @since 1.0
	 */
	public GameState getMainHandState() {
		return mainHandState;
	}

	/**
	 * Returns a GameState representing the state of the split hand.
	 * 
	 * @param none
	 * @return splitHandState a GameState representing the state of the split hand.
	 * @since 1.0
	 */
	public GameState getSplitHandState() {
		return splitHandState;
	}

	/**
	 * Returns a boolean representing whether or not the game has split hands.
	 * 
	 * @param none
	 * @return handHasSplit a boolean value of whether the game has split.
	 * @since 1.0
	 */
	public boolean handHasSplit() {
		return handHasSplit;
	}
	
	/**
	 * Sets whether the dealer wins the push or not
	 * 
	 * @param newDealerWinsPush the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setDealerWinsPush(boolean newDealerWinsPush) {
		dealerWinsPush = newDealerWinsPush;
	}

	/**
	 * Returns a boolean representing whether or not the dealer wins the push. 
	 * 
	 * @param none
	 * @return dealerWinsPush a boolean value of whether the dealer wins the push
	 * @since 1.0
	 */
	public boolean isDealerWinsPush() {
		return dealerWinsPush;
	}

	/**
	 * Sets the soft seventeen rule.
	 * 
	 * @param ruleBule the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setSoftSeventeenRule(boolean ruleBule) {
		softSeventeenRule = ruleBule;
	}

	/**
	 * Returns a boolean representing whether or not the game uses the soft 17 rule
	 * 
	 * @param none
	 * @return softSeventeenRule a boolean value of whether the game uses the rule
	 * @since 1.0
	 */
	public boolean isSoftSeventeenRule() {
		return softSeventeenRule;
	}
	
	/**
	 * Sets whether the player may surrender or not
	 * 
	 * @param newSurrenderAllowed the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setSurrenderAllowed(boolean newSurrenderAllowed) {
		surrenderAllowed = newSurrenderAllowed;
	}

	/**
	 * Returns a boolean representing whether or not the game allows surrenders
	 * 
	 * @param none
	 * @return surrenderAllowed a boolean value of whether the player may surrender
	 * @since 1.0
	 */
	public boolean isSurrenderAllowed() {
		return surrenderAllowed;
	}
	
	/**
	 * Sets whether the player may control split hands from split aces
	 * 
	 * @param newSplittingAcesEndsControl the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setSplittingAcesEndsControl(boolean newSplittingAcesEndsControl) {
		splittingAcesEndsControl = newSplittingAcesEndsControl;
	}

	/**
	 * Returns a boolean representing whether or not the game disallows the player to control split
	 * aces.
	 * 
	 * @param none
	 * @return splittingAcesEndsControl a boolean value of whether the game ends control
	 * @since 1.0
	 */
	public boolean isSplittingAcesEndsControl() {
		return splittingAcesEndsControl;
	}
	/**
	 * Sets whether the player may only split on the same rank
	 * 
	 * @param newOnlySplitOnSameRank the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setOnlySplitOnSameRank(boolean newOnlySplitOnSameRank) {
		onlySplitOnSameRank = newOnlySplitOnSameRank;
	}

	/**
	 * Returns a boolean representing whether or not the player only may split on the same rank.
	 * 
	 * @param none
	 * @return onlySplitOnSameRank a boolean value of whether the player may only split on same rank
	 * @since 1.0
	 */
	public boolean isOnlySplitOnSameRank() {
		return onlySplitOnSameRank;
	}
	
	/**
	 * Sets whether the player may double after a split
	 * 
	 * @param newDoubleAllowedAfterSplit the new value for the rule.
	 * @return none
	 * @since 1.0
	 */
	public void setDoubleAllowedAfterSplit(boolean newDoubleAllowedAfterSplit) {
		doubleAllowedAfterSplit = newDoubleAllowedAfterSplit;
	}

	/**
	 * Returns a boolean representing whether or not the game allows the player to double after the
	 * split
	 * 
	 * @param none
	 * @return doubleAllowedAfterSplit a boolean value of whether the game allows doubling after
	 * a split.
	 * @since 1.0
	 */
	public boolean isDoubleAllowedAfterSplit() {
		return doubleAllowedAfterSplit;
	}
	
	/**
	 * Sets the number of decks.
	 * 
	 * @param numOfDecks the new number of decks.
	 * @return none
	 * @since 1.0
	 */
	public void setNumberOfDecks(int numOfDecks) {
		
		// no restrictions!
		numberOfDecks = numOfDecks;
	}

	/**
	 * Returns a boolean representing whether or not the game is using the soft 17 rule. 
	 * 
	 * @param none
	 * @return numberOfDecks the integer number of decks
	 * @since 1.0
	 */
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	/**
	 * Sets the blackjack pay ratio.
	 * 
	 * @param newBlackjackPayRatio the new blackjackPayRatio
	 * @return none
	 * @since 1.0
	 */
	public void setBlackjackPayRatio(double newBlackjackPayRatio) {
		
		// no restrictions!
		blackjackPayRatio = newBlackjackPayRatio;
	}

	/**
	 * Returns a boolean representing whether or not the game is using the soft 17 rule. 
	 * 
	 * @param none
	 * @return blackjackPayRatio the double for the pay ratio
	 * @since 1.0
	 */
	public double getBlackjackPayRatio() {
		return blackjackPayRatio;
	}

	/**
	 * Returns the entire player object just for fun.
	 * 
	 * @param none
	 * @return player the whole player!
	 * @since 1.0
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the entire dealer object just for fun.
	 * 
	 * @param none
	 * @return dealer the whole dealer!
	 * @since 1.0
	 */
	public Player getDealer() {
		return dealer;
	}

	/**
	 * Sets the cash to the parameter value.
	 * 
	 * @param newCash the new value to set as the player's bet.
	 * @return none
	 * @since 1.0
	 */
	public void setCash(int newCash) {
		cash = newCash;
	}

	/**
	 * Returns the current amount of cash that the player can bet with.
	 * 
	 * @param none
	 * @return cash the integer value representing the amount of cash.
	 * @since 1.0
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * Returns the current amount of cash that the player has lost in the game.
	 * 
	 * @param none
	 * @return losses the integer value representing the amount of cash lost.
	 * @since 1.0
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Returns the current amount of cash that the player is betting in the game.
	 * 
	 * @param none
	 * @return bet the integer value representing the amount of cash being bet.
	 * @since 1.0
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Returns the current hand of the dealer.
	 * 
	 * @param none
	 * @return dealerHand the player's hand.
	 * @since 1.0
	 */
	public ArrayList<Card> getDealerHand() {
		return dealer.getHand();
	}
	
	/**
	 * Returns the current value of the hand of the dealer.
	 * 
	 * @param none
	 * @return handValue the dealer's hand value.
	 * @since 1.0
	 */
	public int getDealerHandValue() {
		return dealer.getPoints();
	}

	/**
	 * Returns the current hand of the player.
	 * 
	 * @param none
	 * @return playerHand the player's hand.
	 * @since 1.0
	 */
	public ArrayList<Card> getPlayerHand() {
		return player.getHand();
	}

	/**
	 * Returns the current split hand of the player.
	 * 
	 * @param none
	 * @return playerHandSplit the player's split hand.
	 * @since 1.0
	 */
	public ArrayList<Card> getPlayerHandSplit() {
		
		// return it
		return player.getHandSplit();
	}

	/**
	 * Returns the current value of the hand of the player.
	 * 
	 * @param none
	 * @return handValue the player's hand value.
	 * @since 1.0
	 */
	public int getPlayerHandValue() {
		
		// return it.
		return player.getPoints();
	}

	/**
	 * Returns the current value of the split hand of the player.
	 * 
	 * @param none
	 * @return handValueSplit the player's split hand value.
	 * @since 1.0
	 */
	public int getPlayerHandValueSplit() {
		
		// return it
		return player.getPointsSplit();
	}
	
	/**
	 * getTotalHandLosses()
	 * 
	 * Return total hand losses for player.
	 * 
	 * @param none
	 * @return totalHandLosses the number of hands lost
	 * @since 1.0
	 */
	public int getTotalHandLosses() {
		return totalHandLosses;
	}
	
	/**
	 * getTotalHandWins()
	 * 
	 * Return total hand wins for the player.
	 * 
	 * @param none
	 * @return totalHandWins the number of hands won
	 * @since 1.0
	 */
	public int getTotalHandWins() {
		return totalHandWins;
	}
	
	/**
	 * getTotalHandTies()
	 * 
	 * Return total hand ties for the player.
	 * 
	 * @param none
	 * @return totalHandTies the number of hands pushed
	 * @since 1.0
	 */
	public int getTotalHandTies() {
		return totalHandTies;
	}

	/**
	 * Returns the current number of hands that have been played since the last initialization.
	 * 
	 * @param none
	 * @return handNumber the integer value representing the number of hands played.
	 * @since 1.0
	 */
	public int getHandNumber() {
		return handNumber;
	}

}
