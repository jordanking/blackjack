/**
 * 
 */
package blackjack;

/**
 * @author Riya Modi
 *
 */
public enum Rank {
	//NEED TO change this so ACE can be a 1 or a 11
	ACE(1),
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
	
	private int cardPoints;
	
	private Rank (int points){
		this.cardPoints = points;
	}
	
	public int getCardPoints(){
		return cardPoints;
	}
}
