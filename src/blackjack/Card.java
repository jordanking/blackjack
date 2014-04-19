/**
 * 
 */
package blackjack;

/**
 * @author Riya Modi
 *
 */
public class Card {
	
	private Rank cardRank;
	private Suit cardSuit;

	/**
	 * 
	 */
	public Card() {
		// TODO Auto-generated constructor stub
	}
	
	public Card (Rank cardRank, Suit cardSuit){
		this.cardRank=cardRank;
		this.cardSuit=cardSuit;
	}
	
	public void setCardRank(Rank cardRank){
		this.cardRank=cardRank;
	}
	public void setCardSuit(Suit cardSuit){
		this.cardSuit=cardSuit;
	}
	
	public Rank getCardRank(){
		return cardRank;
	}
	
	public Suit getCardSuit(){
		return cardSuit;
	}
	

}
