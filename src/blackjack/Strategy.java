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
	private int dealerFaceUpCard; // value of dealer's card that is face up
	
	private GameAction desiredAction; // the action to take with the dealt hand 
	
	
	/**
	 * Constructor. Sets totalPlayerHand and desiredGameAction
	 * @param totalPlayerHand
	 * @param desiredAction
	 * @param dealerFaceUpCard
	 */
	public Strategy(int totalPlayerHand, int dealerFaceUpCard, GameAction desiredAction) {
		
		this.setTotalPlayerHand(totalPlayerHand);
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
	public int getDealerFaceUpCard() {
		return dealerFaceUpCard;
	}

	/**
	 * @param dealerFaceUpCard the dealerFaceUpCard to set
	 */
	public void setDealerFaceUpCard(int dealerFaceUpCard) {
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
}
