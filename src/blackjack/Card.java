/**
 * 
 */
package blackjack;

/**
 * A card object indicating rank and suit.
 * 
 * @author Riya Modi
 * @version 1.0
 */
public class Card {
	
	/**
	 * Stores the rank of the card.
	 */
	private Rank cardRank;
	
	/**
	 * Stores the suit of the card.
	 */
	private Suit cardSuit;

	/**
	 * Default constructor, sets to ace of clubs.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public Card() {
		
		// Defaults rank and suit to ace of clubs
		cardRank = Rank.ACE;
		cardSuit = Suit.CLUBS;
	}
	
	/**
	 *Explicit constructor, allows manual declaration of rank and suit.
	 * 
	 * @param cardRank the rank of the new card
	 * @param cardSuit the suit of the new card
	 * @return none
	 * @since 1.0
	 */
	public Card (Rank cardRank, Suit cardSuit){
		
		// set the rank and the suit
		this.cardRank=cardRank;
		this.cardSuit=cardSuit;
	}
	
	/**
	 * Sets the rank of the card.
	 * 
	 * @param cardRank the new rank
	 * @return none
	 * @since 1.0
	 */
	public void setCardRank(Rank cardRank){
		
		// set the new rank
		this.cardRank=cardRank;
	}
	
	/**
	 * Sets the suit of the card.
	 * 
	 * @param cardSuit the new suit of the card.
	 * @return none
	 * @since 1.0
	 */
	public void setCardSuit(Suit cardSuit){
		
		// set the new suit
		this.cardSuit=cardSuit;
	}

	/**
	 * Returns the rank of the card.
	 * 
	 * @param none
	 * @return cardRank the rank of the card.
	 * @since 1.0
	 */
	public Rank getCardRank(){
		
		// returns the rank of the card
		return cardRank;
	}
	
	/**
	 * Returns the suit of the card.
	 * 
	 * @param none
	 * @return cardSuit
	 * @since 1.0
	 */
	public Suit getCardSuit(){
		
		// returns the suit of the card.
		return cardSuit;
	}
	

}
