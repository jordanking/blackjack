/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * The player model for the game. The dealer can be represented with this class as well, just
 * don't use the split hand for the dealer.
 * 
 * @author Riya Modi
 * @version 1.0
 */
public class Player {
	
	/**
	 * ArrayList of cards for the player hand.
	 */
	ArrayList<Card> playerHand;	
	
	/**
	 * ArrayList of cards of the split hand.
	 */
	ArrayList<Card> playerHandSplit;

	
	/**
	 * Constructor - initializes player class variables.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public Player() {
		
		// init the player's hand
		playerHand = new ArrayList<Card>();
		
		// init the player's hand
		playerHandSplit = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card to the player's hand.
	 * 
	 * @param drawCard Card object to add to the hand.
	 * @return none
	 * @since 1.0
	 */
	public void addCard(Card drawCard) {

		//adds a card to the ArrayList of the player's hand
		playerHand.add(drawCard);
		
	}

	/**
	 * adds a card to the player's split hand
	 * 
	 * @param drawCard Card object to add to the split hand.
	 * @return none
	 * @since 1.0
	 */
	public void addCardSplit(Card drawCard) {

		//adds a card to the ArrayList of the player's hand
		playerHandSplit.add(drawCard);
		
	}
	
	/**
	 * returns the value of the player's hand.
	 * 
	 * @param none
	 * @return points the int value of the hand.
	 * @since 1.0
	 */
	public int getPoints() {
		
		// the return counter
		int playerHandValue = 0;
		
		// count all points
		for(Card card: playerHand){
			playerHandValue += card.getCardRank().getCardPoints();
		}
		
		// adjust for aces
		for(Card card: playerHand) {
			if (playerHandValue > 21 && card.getCardRank() == Rank.ACE) {
				playerHandValue -= 10;
			}
		}
		
		return playerHandValue;
	}
	
	/**
	 * returns the value of the player's split hand
	 * 
	 * @param none
	 * @return points the int points for the value of the split hand.
	 * @since 1.0
	 */
	public int getPointsSplit() {
		
		// the return counter.
		int playerHandValue = 0;
		
		// count points
		for(Card card: playerHandSplit){
			playerHandValue += card.getCardRank().getCardPoints();
		}
		
		// handle aces
		for(Card card: playerHandSplit) {
			if (playerHandValue > 21 && card.getCardRank() == Rank.ACE) {
				playerHandValue -= 10;
			}
		}
		return playerHandValue;
	}
	
	/**
	 * returns the number of cards in the player's hand
	 * 
	 * @param none
	 * @return size the int size of the hand.
	 * @since 1.0
	 */
	public int getNumOfCards(){
		return playerHand.size();
	}
	
	/**
	 * returns the number of cards in the player's split hand
	 * 
	 * @param none
	 * @return size the size of the split hand
	 * @param 1.0
	 */
	public int getNumOfCardsSplit(){
		return playerHandSplit.size();
	}

	/**
	 * returns the ArrayList representing the player's hand of Card objects
	 * 
	 * @param none
	 * @return hand the ArrayList<Card> of the hand.
	 * @since 1.0
	 */
	public ArrayList<Card> getHand() {
		
		// the whole hand
		return playerHand;
	}
	
	/**
	 * returns the ArrayList representing the player's split hand of Card objects
	 * 
	 * @param none
	 * @return hand the ArrayList<Card> of the hand
	 * @since 1.0
	 */
	public ArrayList<Card> getHandSplit() {
		
		// the whole hand
		return playerHandSplit;
	}
	
	/**
	 * returns whether or not the player has a splittable pair.
	 * 
	 * @param canOnlySplitOnSameRank false if you can split based on value, true if rank must be same !=(king/10)
	 * @return hasPair true if they have a pair
	 * @since 1.0
	 */
	public boolean hasPair(boolean canOnlySplitOnSameRank) {
		if (canOnlySplitOnSameRank) {

			// true if they have 2 cards, and they have the same rank ( 10 / 10 or Jack/Jack)
			return (playerHand.get(0).getCardRank() == playerHand.get(1).getCardRank()
					&& playerHand.size() == 2);
		} else {

			// true if they have 2 cards, and they have the same value ( 10 / 10 or 10/Jack)
			return (playerHand.get(0).getCardRank().getCardPoints() == 
					playerHand.get(1).getCardRank().getCardPoints()
					&& playerHand.size() == 2);
		}

	}

	/**
	 * returns whether or not the player has a blackjack (or dealer!)
	 * 
	 * @param none
	 * @return hasBlackjack whether or not they have a blackjack
	 * @since 1.0
	 */
	public boolean hasBlackjack() {
		
		// make sure there is a blackjack
		return (playerHand.size() == 2 && getPoints() == 21);
	}
	
	/**
	 * reset()
	 * resets the player's hands
	 * @return ArrayList<Card>
	 */
	public void reset() {
		
		// empty both hands
		playerHand.clear();
		playerHandSplit.clear();
	}


}
