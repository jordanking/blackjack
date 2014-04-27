/**
 * 
 */
package blackjack;

import java.util.HashMap;
import java.util.Map;


/**
 * @author alyssamiller
 *
 *Object to hold strategy (GameAction) for all player and dealer hand
 *combinations
 *
 */
public class Strategy {
	/** 
	 * Holds all the values in the strategy table, including the 
	 * player and dealer hand combinations and the GameAction
	 * to take with that combination
	 */
	public Map<String, Map<Integer, GameAction>> strategyTable;
	
	/**
	 * Constructor. Initializes the strategy with
	 * the optimal strategy found on Wikipedia
	 */
	public Strategy() {
		// populate strategyTable with optimal strategy (according to Wikipedia)

		strategyTable = new HashMap<String, Map<Integer,GameAction>>();
		
		//Combination of dealer face-up cards and the GameAction to take
		// to add to the strategyTable
		Map<Integer, GameAction> currentDealerGameActionCombinations = 
				new HashMap<Integer, GameAction>();
		
		//For all of the hard totals:
		
		//GameActions for all dealer cards and player's hand total of 17-20
		currentDealerGameActionCombinations.put(1, GameAction.STAND);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.STAND);
		currentDealerGameActionCombinations.put(8, GameAction.STAND);
		currentDealerGameActionCombinations.put(9, GameAction.STAND);
		currentDealerGameActionCombinations.put(10, GameAction.STAND);
		
		strategyTable.put("17-20", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 16
		currentDealerGameActionCombinations.put(1, GameAction.SURRENDER);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.SURRENDER);
		currentDealerGameActionCombinations.put(10, GameAction.SURRENDER);
		
		strategyTable.put("16", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 15
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.SURRENDER);
		
		strategyTable.put("15", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 13-14
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("13-14", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 12
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.HIT);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("12", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 11
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(8, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(9, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(10, GameAction.DOUBLE_HIT);
		
		strategyTable.put("11", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 10
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(8, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(9, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("10", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 9
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(8, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(9, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(10, GameAction.DOUBLE_HIT);
		
		strategyTable.put("9", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of 5-8
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.HIT);
		currentDealerGameActionCombinations.put(4, GameAction.HIT);
		currentDealerGameActionCombinations.put(5, GameAction.HIT);
		currentDealerGameActionCombinations.put(6, GameAction.HIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("5-8", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//For Soft Totals:
		
		//GameActions for all dealer cards and player's hand of A,8-A,9
		currentDealerGameActionCombinations.put(1, GameAction.STAND);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.STAND);
		currentDealerGameActionCombinations.put(8, GameAction.STAND);
		currentDealerGameActionCombinations.put(9, GameAction.STAND);
		currentDealerGameActionCombinations.put(10, GameAction.STAND);
		
		strategyTable.put("A,8-A,9", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of A,7
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_STAND);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_STAND);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_STAND);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_STAND);
		currentDealerGameActionCombinations.put(7, GameAction.STAND);
		currentDealerGameActionCombinations.put(8, GameAction.STAND);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("A,7", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of A,6
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("A,6", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand total of A,4-A,5
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("A,4-A,5", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of A,2-A,3
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.HIT);
		currentDealerGameActionCombinations.put(4, GameAction.HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("A,2-A,3", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//For pairs:
		
		//GameActions for all dealer cards and player's hand of A,A
		currentDealerGameActionCombinations.put(1, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(8, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(9, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(10, GameAction.SPLIT);
		
		strategyTable.put("A,A", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 10,10
		currentDealerGameActionCombinations.put(1, GameAction.STAND);
		currentDealerGameActionCombinations.put(2, GameAction.STAND);
		currentDealerGameActionCombinations.put(3, GameAction.STAND);
		currentDealerGameActionCombinations.put(4, GameAction.STAND);
		currentDealerGameActionCombinations.put(5, GameAction.STAND);
		currentDealerGameActionCombinations.put(6, GameAction.STAND);
		currentDealerGameActionCombinations.put(7, GameAction.STAND);
		currentDealerGameActionCombinations.put(8, GameAction.STAND);
		currentDealerGameActionCombinations.put(9, GameAction.STAND);
		currentDealerGameActionCombinations.put(10, GameAction.STAND);
		
		strategyTable.put("10,10", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 9,9
		currentDealerGameActionCombinations.put(1, GameAction.STAND);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.STAND);
		currentDealerGameActionCombinations.put(8, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(9, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(10, GameAction.STAND);
		
		strategyTable.put("9,9", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 8,8
		currentDealerGameActionCombinations.put(1, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(8, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(9, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(10, GameAction.SPLIT);
		
		strategyTable.put("8,8", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 7,7
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("7,7", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 6,6
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("6,6", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 5,5
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(3, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(4, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(5, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(6, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(7, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(8, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(9, GameAction.DOUBLE_HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("5,5", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 4,4
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.HIT);
		currentDealerGameActionCombinations.put(3, GameAction.HIT);
		currentDealerGameActionCombinations.put(4, GameAction.HIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.HIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("4,4", currentDealerGameActionCombinations);
		
		//clear this combination and move to next row of combinations
		currentDealerGameActionCombinations.clear();
		
		//GameActions for all dealer cards and player's hand of 2,2-3,3
		currentDealerGameActionCombinations.put(1, GameAction.HIT);
		currentDealerGameActionCombinations.put(2, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(3, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(4, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(5, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(6, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(7, GameAction.SPLIT);
		currentDealerGameActionCombinations.put(8, GameAction.HIT);
		currentDealerGameActionCombinations.put(9, GameAction.HIT);
		currentDealerGameActionCombinations.put(10, GameAction.HIT);
		
		strategyTable.put("2,2-3,3", currentDealerGameActionCombinations);
		
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
	/**
	 * getGameActionForHands()
	 * 
	 * returns the GameAction in the Strategy that corresponds
	 * to a given player hand and dealer face up card combination
	 * 
	 * @param playerHand
	 * @param dealerFaceUpCard
	 * @return
	 */
	public GameAction getGameActionForHands(String playerHand, Integer dealerFaceUpCard) {
		GameAction desiredAction = null;	//the action to take
		
		// Map of the dealer/GameAction combinations
		Map<Integer, GameAction> dealerGameActionCombinations = 
				new HashMap<Integer, GameAction>();
		
		try {
			//get the dealer face up card/game action combinations for the player's hand
			dealerGameActionCombinations = this.strategyTable.get(playerHand);
			//get the desired GameAction associated with this particular dealer
			//face up card/player's hand combination
			desiredAction = dealerGameActionCombinations.get(dealerFaceUpCard);
			
		} catch (NullPointerException nullExcept) {
			System.out.println("Exception thrown  :" + nullExcept);
		}
		return desiredAction;
	}
	
	public void setGameActionForHands(String playerHand, Integer dealerFaceUpCard, GameAction desiredAction) {
		// Map of the dealer/GameAction combinations
		Map<Integer, GameAction> dealerGameActionCombinations = 
				new HashMap<Integer, GameAction>();
		
		try {
			//get the dealer face up card/game action combinations for the player's hand
			dealerGameActionCombinations = this.strategyTable.get(playerHand);
			//set the desired GameAction associated with this particular dealer
			//face up card/player's hand combination
			desiredAction = dealerGameActionCombinations.put(dealerFaceUpCard, desiredAction);
			
		} catch (NullPointerException nullExcept) {
			System.out.println("Exception thrown  :" + nullExcept);
		}
		
	}
}
