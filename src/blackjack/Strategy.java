/**
 * 
 */
package blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author alyssamiller
 *
 *Object to hold strategy for a given hand. Contains
 *all game actions to take for all possible dealer's face up 
 *card value. 
 *
 */
public class Strategy {
/**
	// might not need
	public static final ArrayList<Integer> dealerFaceUpCardValue = (ArrayList<Integer>) 
			Collections.unmodifiableList(new ArrayList<Integer>() {{
		    	//populate possible dealer's face up card value
		    	for (int i = 1; i <= 10; i++)
		    		add(i);
		    }}); 
	*/
	private int totalPlayerHand; // total card value of player's hand
	
	// the action to take with the dealt hand 
	//and all corresponding dealer face up card values
	//first index corresponds to ace; second to 
	//"two", third to "three", etc. tenth corresponds
	// to "ten" and other face cards
	private ArrayList<GameAction> desiredActions; 
	
	/**
	 * Constructor. Sets totalPlayerHand and desiredGameAction
	 * @param totalPlayerHand
	 * @param desiredActions
	 */
	public Strategy(int totalPlayerHand, ArrayList<GameAction> desiredActions) {
		desiredActions = new ArrayList<GameAction>();
		
		this.setTotalPlayerHand(totalPlayerHand);
		this.setDesiredActions(desiredActions);
	}

	/**
	 * @return the desiredActions
	 */
	public ArrayList<GameAction> getDesiredActions() {
		return desiredActions;
	}

	/**
	 * @param desiredActions the desiredActions to set
	 */
	public void setDesiredActions(ArrayList<GameAction> desiredActions) {
		this.desiredActions = desiredActions;
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
}
