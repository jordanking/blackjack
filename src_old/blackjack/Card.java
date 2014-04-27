/**
 * 
 */
package blackjack;

/**
 * Card
 * 
 * A card object indicating rank and suit.
 * 
 * @author Riya Modi
 *
 */
public class Card {
	
	/**
	 * Declare private variables storing rank and suit.
	 */
	private Rank cardRank;
	private Suit cardSuit;

	/**
	 * Card()
	 * 
	 * Default constructor.
	 */
	public Card() {
		// Defaults rank and suit
		cardRank = Rank.ACE;
		cardSuit = Suit.CLUBS;
	}
	
	/**
	 * Card()
	 * 
	 * Explicit constructor. 
	 * 
	 * @param cardRank 
	 * @param cardSuit
	 */
	public Card (Rank cardRank, Suit cardSuit){
		this.cardRank=cardRank;
		this.cardSuit=cardSuit;
	}
	
	/**
	 * setCardRank()
	 * 
	 * cardRank setter.
	 * 
	 * @param cardRank
	 */
	public void setCardRank(Rank cardRank){
		this.cardRank=cardRank;
	}
	
	/**
	 * setCardSuit()
	 * 
	 * cardSuit setter.
	 * 
	 * @param cardSuit
	 */
	public void setCardSuit(Suit cardSuit){
		this.cardSuit=cardSuit;
	}

	/**
	 * getCardRank()
	 * 
	 * cardRank getter.
	 * 
	 * @return cardRank
	 */
	public Rank getCardRank(){
		return cardRank;
	}
	
	/**
	 * getCardSuit()
	 * 
	 * cardSuit getter.
	 * 
	 * @return cardSuit
	 */
	public Suit getCardSuit(){
		return cardSuit;
	}
	

}
