package blackjack;

import java.awt.event.ActionEvent;
import java.util.ArrayList;



/**
 * @author Jordan King
 * @version 1.0
 * 
 * The panel that is used in both the PlayPanel and the AutoPanel to display and model a
 * game of blackjack. It contains the complete model of a game of blackjack, for an arbitrary
 * number of hands played between one player and a dealer. It tracks losses and finances, and draws
 * the game on a panel.
 */
public class GameBoard  {
	
	/**
	 * A constant for the default amount of starting cash.
	 */
	static private final int DEFAULT_CASH = 1000;
	
	/**
	 * A constant for the default amount to bet.
	 */
	static private final int DEFAULT_BET = 10;
	
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
	
	/**
	 * The readyForInput bool is a marker of whether or not the game is ready to receive the player
	 * choice of hit, stand, etc... (not the bet!)
	 */
	private boolean readyForInput;
	
	/**
	 * The readyForBet bool is a marker of whether or not the game is ready to receive the player
	 * bet.
	 */
	private boolean readyForBet;
	
	/**
	 * The readyForBet bool is a marker of whether or not the game is ready to receive the player
	 * bet.
	 */
	private boolean handInProgress;

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
	 * Sets the player bet to the parameter value.
	 * 
	 * @param newBet the new value to set as the player's bet.
	 * @return none
	 * @since 1.0
	 */
	public void setBet(int newBet) {
		if(!isReadyForBet()){
			return;
		}
		bet = newBet;
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
	 * Returns the current value of the hand of the player.
	 * 
	 * @param none
	 * @return handValue the player's hand value.
	 * @since 1.0
	 */
	public int getPlayerHandValue() {
		return player.getPoints();
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

	/**
	 * Initializes the game model to a game-start configuration. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void initModel() {
		
		// get new models
		dealer = new Player();
		player = new Player();
		deck = new Deck();
		
		// set the numbers to a new game default
		cash = DEFAULT_CASH;
		losses = 0;
		bet = DEFAULT_BET;
		handNumber = 0;
		
		// set to a state that does not accept input
		readyForInput = false;
		readyForBet = false;
	}
	
	/**
	 * Starts the game loop for one hand of blackjack. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void playHand() {
		
		if (handInProgress) {
			return;
		}
		
		// See if the player has money to play
		if (cash < bet) {
			return;
		}

		// the hand begins!
		handInProgress = true;

		// reset player
		player.reset();
		
		// reset dealer
		dealer.reset();
		
		// We are now on the next hand.
		handNumber++;
		
		// shuffle and deal
		deal();
		
		// ready for bet and input
		readyForInput = true;
		readyForBet = true;
		
	}

	/**
	 * 
	 */
	private void deal() {
		
		// get a fresh deck
		deck = new Deck();
		
		// Shuffle deck up front
		deck.shuffle();
		
		// deal the first card to the player
		player.addCard(deck.drawCard());
		
		// deal the second card to the dealer
		dealer.addCard(deck.drawCard());
		
		// deal the third card to the player
		player.addCard(deck.drawCard());
	}
	
	/**
	 * Tells the model that the player would like to hit. Only registers if the 'readyForInput' bool
	 * is set to true. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void hit() {
		
		// Make sure that the model is ready for input
		if (!readyForInput || !handInProgress) {
			return;
		}
		
		// Stop the player from changing their bet from here on out!
		readyForBet = false;
		
		// don't allow input while processing input
		readyForInput = false;
		
		// Add a card to the player
		player.addCard(deck.drawCard());
		
		// check for bust
		if (player.getPoints() > 21) {
			
			// the player loses!
			lose();
			return;
		}
		
		// now we are ready for more input
		readyForInput = true;
		
	}
	
	/**
	 * Tells the model that the player would like to stand. Only registers if the 'readyForInput' 
	 * bool is set to true.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void stand() {
		
		// Make sure that the model is ready for input
		if (!readyForInput || !handInProgress) {
			return;
		}
		
		// Stop the player from changing their bet from here on out!
		readyForBet = false;
		
		// don't allow input while processing input
		readyForInput = false;
		
		// dealer must take hits until they have < 17 points
		while (dealer.getPoints() < 17) {
			
			// take a hit
			dealer.addCard(deck.drawCard());
			
			// check for bust
			if (dealer.getPoints() > 21) {
				
				// the player wins!
				win();
				return;
				
			}
			
		}
		
		// see who won
		checkScores();		
	}

	/**
	 * 
	 */
	private void checkScores() {
		// check the scores for the winner
		if (dealer.getPoints() > player.getPoints()) {
			
			// the player loses!
			lose();
			return;
			
		} else if (dealer.getPoints() < player.getPoints()) {
			
			// the player wins!
			win();
			return;
			
		} else {
			
			push();
			return;
			
		}
	}
	
	/**
	 * Returns a boolean representing whether or not the game is ready for the player's input
	 * behavior. 
	 * 
	 * @param none
	 * @return readyForInput a boolean value of whether the game is ready for input or not.
	 * @since 1.0
	 */
	public boolean isReadyForInput() {
		return readyForInput;
	}
	
	/**
	 * Returns a boolean representing whether or not the game is ready for the player's input
	 * behavior. 
	 * 
	 * @param none
	 * @return readyForBet a boolean value of whether the game is ready for a bet or not.
	 * @since 1.0
	 */
	public boolean isReadyForBet() {
		return readyForBet;
	}
	
	/**
	 * Returns a boolean representing whether or not the game is playing a hand. 
	 * 
	 * @param none
	 * @return handInProgress a boolean value of whether the game playing a hand.
	 * @since 1.0
	 */
	public boolean isHandInProgress() {
		return handInProgress;
	}
	
	/**
	 * Updates for a loss. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void lose() {
		cash -= bet;
		losses += bet;
		handInProgress = false;
		System.out.println("Player loses: ");
		System.out.println("Player has " + player.getPoints() + " points");
		System.out.println("Dealer has " + dealer.getPoints() + " points");

	}
	
	/**
	 * Updates for a win.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void win() {
		cash += bet;
		losses -= bet;
		handInProgress = false;
		System.out.println("Player wins: ");
		System.out.println("Player has " + player.getPoints() + " points");
		System.out.println("Dealer has " + dealer.getPoints() + " points");
	}
	
	/**
	 * Updates for a push.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void push() {
		handInProgress = false;
		System.out.println("Push: ");
		System.out.println("Player has " + player.getPoints() + " points");
		System.out.println("Dealer has " + dealer.getPoints() + " points");
	}


}
