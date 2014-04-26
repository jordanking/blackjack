/**
 * 
 */
package blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author alyssamiller
 *
 *Object to hold strategy (GameAction) for a given player and dealer hand
 *
 */
public class Strategy {

	private int totalPlayerHand; // total card value of player's hand
	private Card firstPlayerCard; // first card player is dealt
	private Card secondPlayerCard; // second card player is dealt
	private Card dealerFaceUpCard; // value of dealer's card that is face up
	
	private GameAction desiredAction; // the action to take with the dealt hand 
	
	
	/**
	 * Constructor. Sets totalPlayerHand and desiredGameAction
	 * @param totalPlayerHand
	 * @param desiredAction
	 * @param dealerFaceUpCard
	 */
	public Strategy(Card firstPlayerCard, Card secondPlayerCard, Card dealerFaceUpCard, GameAction desiredAction) {
		//total the number of points the player has and set totalPlayerHand to that amount
		this.setTotalPlayerHand(firstPlayerCard.getCardRank().getCardPoints() + secondPlayerCard.getCardRank().getCardPoints());
		
		this.setFirstPlayerCard(firstPlayerCard);
		this.setSecondPlayerCard(secondPlayerCard);
		this.setDealerFaceUpCard(dealerFaceUpCard);
		this.setDesiredAction(desiredAction);
	}

	/**
	 * @return the totalPlayerHand
	 */
	public int getTotalPlayerHand() {
		return totalPlayerHand;
	}

	/**
	 * @param totalPlayerHand the totalPlayerHand to set
	 */
	public void setTotalPlayerHand(int totalPlayerHand) {
		this.totalPlayerHand = totalPlayerHand;
	}

	/**
	 * @return the dealerFaceUpCard
	 */
	public Card getDealerFaceUpCard() {
		return dealerFaceUpCard;
	}

	/**
	 * @param dealerFaceUpCard the dealerFaceUpCard to set
	 */
	public void setDealerFaceUpCard(Card dealerFaceUpCard) {
		this.dealerFaceUpCard = dealerFaceUpCard;
	}

	/**
	 * @return the desiredAction
	 */
	public GameAction getDesiredAction() {
		return desiredAction;
	}

	/**
	 * @param desiredAction the desiredAction to set
	 */
	public void setDesiredAction(GameAction desiredAction) {
		this.desiredAction = desiredAction;
	}

	/**
	 * @return the firstPlayerCard
	 */
	public Card getFirstPlayerCard() {
		return firstPlayerCard;
	}

	/**
	 * @param firstPlayerCard the firstPlayerCard to set
	 */
	public void setFirstPlayerCard(Card firstPlayerCard) {
		this.firstPlayerCard = firstPlayerCard;
	}

	/**
	 * @return the secondPlayerCard
	 */
	public Card getSecondPlayerCard() {
		return secondPlayerCard;
	}

	/**
	 * @param secondPlayerCard the secondPlayerCard to set
	 */
	public void setSecondPlayerCard(Card secondPlayerCard) {
		this.secondPlayerCard = secondPlayerCard;
	}
}
