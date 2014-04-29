/**
 * 
 */
package model;

/**
 * @author Riya Modi
 *
 */
public enum Rank {
	// ACE can be a 1 or a 11 in context
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
	
	private Rank (int points){
		this.cardPoints = points;
	}
	
	public int getCardPoints(){
		return cardPoints;
	}
}
