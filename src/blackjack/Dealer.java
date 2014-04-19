/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * @author Riya Modi
 *
 */
public class Dealer {
	
	ArrayList<Card> dealerHand = new ArrayList<Card>();	//ArrayList of Card Objects for dealer's hand
	private int dealerHandValue;						//value of the dealer's hand
	private int numOfCards;								//number of cards the dealer has

	/**
	 * Dealer()
	 * initializes Dealer class variables
	 */
	public Dealer() {
		// TODO Auto-generated constructor stub
		dealerHandValue = 0;	//initialize the value of the dealer's hand
		numOfCards = 0;			//initialize the number of cards the dealer has
	}

	/*
	 * addCard(Card drawCard)
	 * adds a card to the dealer's hand when the dealer hits
	 * updates the value of the dealer's hand
	 * updates the number of cards the dealer's has
	 * @param drawCard Card object 
	 */
	public void addCard(Card drawCard) {
		// TODO Auto-generated method stub
		
		//adds a card to the ArrayList of the dealer's hand
		dealerHand.add(drawCard);
				
		//update the value of the dealer's hand
		int newCardRank = drawCard.getCardRank().getCardPoints();
		dealerHandValue+=newCardRank;
				
		//update the number of cards the dealer has
		numOfCards++;
	}

	/*
	 * getPoints()
	 * returns the value of the dealer's hand
	 */
	public int getPoints() {
		// TODO Auto-generated method stub
		return dealerHandValue;
	}
	
	/*
	 * getNumOfCards()
	 * returns the number of cards in the dealer's hand
	 */
	public int getNumOfCards(){
		return numOfCards;
	}

	/*
	 * getHand()
	 * returns the ArrayList representing the dealer's hand of Card objects
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getHand() {
		// TODO Auto-generated method stub
		return dealerHand;
	}

}
