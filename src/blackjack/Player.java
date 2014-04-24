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
	
	ArrayList<Card> playerHand;			//ArrayList of Card Objects for player's hand
	ArrayList<Card> playerHandSplit;	//ArrayList of Card Objects for player's split hand

	
	/**
	 * player()
	 * initializes player class variables
	 */
	public Player() {
		
		// the player's hand
		playerHand = new ArrayList<Card>();
		
		// the player's hand
		playerHandSplit = new ArrayList<Card>();
	}
	
	/**
	 * addCard(Card drawCard)
	 * adds a card to the player's hand when the player hits
	 * @param drawCard Card object 
	 */
	public void addCard(Card drawCard) {

		//adds a card to the ArrayList of the player's hand
		playerHand.add(drawCard);
		
	}

	/**
	 * addCardSplit(Card drawCard)
	 * adds a card to the player's split hand when the player hits on it
	 * @param drawCard Card object 
	 */
	public void addCardSplit(Card drawCard) {

		//adds a card to the ArrayList of the player's hand
		playerHandSplit.add(drawCard);
		
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
	 * getPointsSplit()
	 * returns the value of the player's split hand
	 */
	public int getPointsSplit() {
		int playerHandValue = 0;
		for(Card card: playerHandSplit){
			playerHandValue += card.getCardRank().getCardPoints();
		}
		for(Card card: playerHandSplit) {
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
	 * getNumOfCardsSplit()
	 * returns the number of cards in the player's split hand
	 */
	public int getNumOfCardsSplit(){
		return playerHandSplit.size();
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
	 * getHandSplit()
	 * returns the ArrayList representing the player's split hand of Card objects
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getHandSplit() {
		
		return playerHandSplit;
	}
	
	/**
	 * reset()
	 * resets the player's hands
	 * @return ArrayList<Card>
	 */
	public void reset() {
		playerHand.clear();
		playerHandSplit.clear();
	}


}
