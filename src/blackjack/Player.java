/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * @author Riya Modi
 *
 */
public class Player {
	
	ArrayList<Card> playerHand;	//ArrayList of Card Objects for player's hand

	/**
	 * player()
	 * initializes player class variables
	 */
	public Player() {
		
		// the player's hand
		playerHand = new ArrayList<Card>();
	}

	/**
	 * addCard(Card drawCard)
	 * adds a card to the player's hand when the player hits
	 * updates the value of the player's hand
	 * updates the number of cards the player's has
	 * @param drawCard Card object 
	 */
	public void addCard(Card drawCard) {

		//adds a card to the ArrayList of the player's hand
		playerHand.add(drawCard);
		
	}

	/**
	 * getPoints()
	 * returns the value of the player's hand
	 */
	public int getPoints() {
		int playerHandValue = 0;
		for(Card card: playerHand){
			playerHandValue += card.getCardRank().getCardPoints();
		}
		for(Card card: playerHand) {
			if (playerHandValue > 21 && card.getCardRank() == Rank.ACE) {
				playerHandValue -= 10;
			}
		}
		return playerHandValue;
	}
	
	/**
	 * getNumOfCards()
	 * returns the number of cards in the player's hand
	 */
	public int getNumOfCards(){
		return playerHand.size();
	}

	/**
	 * getHand()
	 * returns the ArrayList representing the player's hand of Card objects
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getHand() {
		
		return playerHand;
	}
	
	/**
	 * clear()
	 * clears the ArrayList representing the player's hand of Card objects
	 * @return ArrayList<Card>
	 */
	public void reset() {
		playerHand.clear();
	}


}
