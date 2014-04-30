/**
 * 
 */
package model;

/**
 * Use enums to represent the different point-values of cards in a deck
 * @author Riya Modi
 *
 */
public enum Rank {

	ACE(11),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);
	
	/**
	 * The value of the card.
	 */
	private int cardPoints;
	
	/**
	 * Rank(int points)
	 * Constructor sets the points-value to an object
	 * @param points
	 */
	private Rank (int points){
		this.cardPoints = points;
	}
	
	/**
	 * getCardPoints()
	 * returns the point-value of the card
	 * @return cardPoints
	 */
	public int getCardPoints(){
		return cardPoints;
	}
}
