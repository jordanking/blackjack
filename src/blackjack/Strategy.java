/**
 * 
 */
package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * @author alyssamiller
 *
 *Object to hold strategy (GameAction) for a given player and dealer hand
 *
 */
public class Strategy {

	private Map<String, Map<Integer, GameAction>> strategyTable; //combinations of player and dealer hands
	
	private GameAction desiredAction; // the action to take with the dealt hand 
	
	/**
	 * Default Constructor. Sets the optimal strategy.
	 */
	public Strategy() {
		
		
		
		
		
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
	 * @return the strategyTable
	 */
	public Map<String, Map<Integer, GameAction>> getStrategyTable() {
		return strategyTable;
	}


	/**
	 * @param strategyTable the strategyTable to set
	 */
	public void setStrategyTable(Map<String, Map<Integer, GameAction>> strategyTable) {
		this.strategyTable = strategyTable;
	}
}
