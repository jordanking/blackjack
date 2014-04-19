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
	
	ArrayList<Card> playerHand = new ArrayList<Card>();	//ArrayList of Card Objects for player's hand
	private int playerHandValue;						//value of the player's hand
	private int numOfCards;								//number of card's the player has

	/**
	 * Player()
	 * constructor initializes Player class variables
	 */
	public Player() {
		// TODO Auto-generated constructor stub
		playerHandValue = 0;	//initialize the value of the player's hand
		numOfCards = 0;			//initialize the number of cards the player has
	}

	/*
	 * addCard(Card drawCard)
	 * adds a card to the player's hand when player hits
	 * updates the value of the player's hand
	 * updates the number of cards the player has
	 * @param drawCard Card object 
	 */
	public void addCard(Card drawCard) {
		// TODO Auto-generated method stub
		
		//adds a card to the ArrayList of the player's hand
		playerHand.add(drawCard);
		
		//update the value of the player's hand
		int newCardRank = drawCard.getCardRank().getCardPoints();
		playerHandValue+=newCardRank;
		
		//update the number of cards the player has
		numOfCards++;
	}

	/*
	 * getPoints()
	 * returns the value of the player's hand
	 */
	public int getPoints() {
		// TODO Auto-generated method stub
		return playerHandValue;
	}
	
	/*
	 * getNumOfCards()
	 * returns the number of cards in the player's hand
	 */
	public int getNumOfCards(){
		return numOfCards;
	}

	/*
	 * getHand()
	 * returns the ArrayList representing the player's hand of Card objects
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getHand() {
		// TODO Auto-generated method stub
		return playerHand;
	}

}
