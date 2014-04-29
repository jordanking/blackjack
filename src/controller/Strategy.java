/**
 * 
 */
package controller;

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
		
		//Maps for hard totals:
		Map<Integer, GameAction> hardTotalRowOne = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowTwo = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowThree = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowFour = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowFive = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowSix = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowSeven = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowEight = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> hardTotalRowNine = 
				new HashMap<Integer, GameAction>();
		
		//Maps for soft totals:
		Map<Integer, GameAction> softTotalRowOne = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> softTotalRowTwo = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> softTotalRowThree = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> softTotalRowFour = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> softTotalRowFive = 
				new HashMap<Integer, GameAction>();

		//Maps for pairs:
		Map<Integer, GameAction> pairRowOne = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowTwo = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowThree = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowFour = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowFive = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowSix = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowSeven = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowEight = 
				new HashMap<Integer, GameAction>();
		Map<Integer, GameAction> pairRowNine = 
				new HashMap<Integer, GameAction>();
		
		//For all of the hard totals:
		
		//GameActions for all dealer cards and player's hand total of 17-20
		hardTotalRowOne.put(1, GameAction.STAND);
		hardTotalRowOne.put(2, GameAction.STAND);
		hardTotalRowOne.put(3, GameAction.STAND);
		hardTotalRowOne.put(4, GameAction.STAND);
		hardTotalRowOne.put(5, GameAction.STAND);
		hardTotalRowOne.put(6, GameAction.STAND);
		hardTotalRowOne.put(7, GameAction.STAND);
		hardTotalRowOne.put(8, GameAction.STAND);
		hardTotalRowOne.put(9, GameAction.STAND);
		hardTotalRowOne.put(10, GameAction.STAND);
		
		strategyTable.put("17-20", hardTotalRowOne);
		
		
		//GameActions for all dealer cards and player's hand total of 16
		hardTotalRowTwo.put(1, GameAction.SURRENDER);
		hardTotalRowTwo.put(2, GameAction.STAND);
		hardTotalRowTwo.put(3, GameAction.STAND);
		hardTotalRowTwo.put(4, GameAction.STAND);
		hardTotalRowTwo.put(5, GameAction.STAND);
		hardTotalRowTwo.put(6, GameAction.STAND);
		hardTotalRowTwo.put(7, GameAction.HIT);
		hardTotalRowTwo.put(8, GameAction.HIT);
		hardTotalRowTwo.put(9, GameAction.SURRENDER);
		hardTotalRowTwo.put(10, GameAction.SURRENDER);
		
		strategyTable.put("16", hardTotalRowTwo);
		
		//GameActions for all dealer cards and player's hand total of 15
		hardTotalRowThree.put(1, GameAction.HIT);
		hardTotalRowThree.put(2, GameAction.STAND);
		hardTotalRowThree.put(3, GameAction.STAND);
		hardTotalRowThree.put(4, GameAction.STAND);
		hardTotalRowThree.put(5, GameAction.STAND);
		hardTotalRowThree.put(6, GameAction.STAND);
		hardTotalRowThree.put(7, GameAction.HIT);
		hardTotalRowThree.put(8, GameAction.HIT);
		hardTotalRowThree.put(9, GameAction.HIT);
		hardTotalRowThree.put(10, GameAction.SURRENDER);
		
		strategyTable.put("15", hardTotalRowThree);
		
		//GameActions for all dealer cards and player's hand total of 13-14
		hardTotalRowFour.put(1, GameAction.HIT);
		hardTotalRowFour.put(2, GameAction.STAND);
		hardTotalRowFour.put(3, GameAction.STAND);
		hardTotalRowFour.put(4, GameAction.STAND);
		hardTotalRowFour.put(5, GameAction.STAND);
		hardTotalRowFour.put(6, GameAction.STAND);
		hardTotalRowFour.put(7, GameAction.HIT);
		hardTotalRowFour.put(8, GameAction.HIT);
		hardTotalRowFour.put(9, GameAction.HIT);
		hardTotalRowFour.put(10, GameAction.HIT);
		
		strategyTable.put("13-14", hardTotalRowFour);
		
		//GameActions for all dealer cards and player's hand total of 12
		hardTotalRowFive.put(1, GameAction.HIT);
		hardTotalRowFive.put(2, GameAction.HIT);
		hardTotalRowFive.put(3, GameAction.HIT);
		hardTotalRowFive.put(4, GameAction.STAND);
		hardTotalRowFive.put(5, GameAction.STAND);
		hardTotalRowFive.put(6, GameAction.STAND);
		hardTotalRowFive.put(7, GameAction.HIT);
		hardTotalRowFive.put(8, GameAction.HIT);
		hardTotalRowFive.put(9, GameAction.HIT);
		hardTotalRowFive.put(10, GameAction.HIT);
		
		strategyTable.put("12", hardTotalRowFive);
		
		//GameActions for all dealer cards and player's hand total of 11
		hardTotalRowSix.put(1, GameAction.HIT);
		hardTotalRowSix.put(2, GameAction.DOUBLE);
		hardTotalRowSix.put(3, GameAction.DOUBLE);
		hardTotalRowSix.put(4, GameAction.DOUBLE);
		hardTotalRowSix.put(5, GameAction.DOUBLE);
		hardTotalRowSix.put(6, GameAction.DOUBLE);
		hardTotalRowSix.put(7, GameAction.DOUBLE);
		hardTotalRowSix.put(8, GameAction.DOUBLE);
		hardTotalRowSix.put(9, GameAction.DOUBLE);
		hardTotalRowSix.put(10, GameAction.DOUBLE);
		
		strategyTable.put("11", hardTotalRowSix);
		
		//GameActions for all dealer cards and player's hand total of 10
		hardTotalRowSeven.put(1, GameAction.HIT);
		hardTotalRowSeven.put(2, GameAction.DOUBLE);
		hardTotalRowSeven.put(3, GameAction.DOUBLE);
		hardTotalRowSeven.put(4, GameAction.DOUBLE);
		hardTotalRowSeven.put(5, GameAction.DOUBLE);
		hardTotalRowSeven.put(6, GameAction.DOUBLE);
		hardTotalRowSeven.put(7, GameAction.DOUBLE);
		hardTotalRowSeven.put(8, GameAction.DOUBLE);
		hardTotalRowSeven.put(9, GameAction.DOUBLE);
		hardTotalRowSeven.put(10, GameAction.HIT);
		
		strategyTable.put("10", hardTotalRowSeven);
		
		//GameActions for all dealer cards and player's hand total of 9
		hardTotalRowEight.put(1, GameAction.HIT);
		hardTotalRowEight.put(2, GameAction.DOUBLE);
		hardTotalRowEight.put(3, GameAction.DOUBLE);
		hardTotalRowEight.put(4, GameAction.DOUBLE);
		hardTotalRowEight.put(5, GameAction.DOUBLE);
		hardTotalRowEight.put(6, GameAction.DOUBLE);
		hardTotalRowEight.put(7, GameAction.DOUBLE);
		hardTotalRowEight.put(8, GameAction.DOUBLE);
		hardTotalRowEight.put(9, GameAction.DOUBLE);
		hardTotalRowEight.put(10, GameAction.DOUBLE);
		
		strategyTable.put("9", hardTotalRowEight);
		
		//GameActions for all dealer cards and player's hand total of 5-8
		hardTotalRowNine.put(1, GameAction.HIT);
		hardTotalRowNine.put(2, GameAction.HIT);
		hardTotalRowNine.put(3, GameAction.HIT);
		hardTotalRowNine.put(4, GameAction.HIT);
		hardTotalRowNine.put(5, GameAction.HIT);
		hardTotalRowNine.put(6, GameAction.HIT);
		hardTotalRowNine.put(7, GameAction.HIT);
		hardTotalRowNine.put(8, GameAction.HIT);
		hardTotalRowNine.put(9, GameAction.HIT);
		hardTotalRowNine.put(10, GameAction.HIT);
		
		strategyTable.put("5-8", hardTotalRowNine);
		
		//For Soft Totals:
		
		//GameActions for all dealer cards and player's hand of A,8-A,9
		softTotalRowOne.put(1, GameAction.STAND);
		softTotalRowOne.put(2, GameAction.STAND);
		softTotalRowOne.put(3, GameAction.STAND);
		softTotalRowOne.put(4, GameAction.STAND);
		softTotalRowOne.put(5, GameAction.STAND);
		softTotalRowOne.put(6, GameAction.STAND);
		softTotalRowOne.put(7, GameAction.STAND);
		softTotalRowOne.put(8, GameAction.STAND);
		softTotalRowOne.put(9, GameAction.STAND);
		softTotalRowOne.put(10, GameAction.STAND);
		
		strategyTable.put("A,8-A,9", softTotalRowOne);
		
		//GameActions for all dealer cards and player's hand of A,7
		softTotalRowTwo.put(1, GameAction.HIT);
		softTotalRowTwo.put(2, GameAction.STAND);
		softTotalRowTwo.put(3, GameAction.DOUBLE);
		softTotalRowTwo.put(4, GameAction.DOUBLE);
		softTotalRowTwo.put(5, GameAction.DOUBLE);
		softTotalRowTwo.put(6, GameAction.DOUBLE);
		softTotalRowTwo.put(7, GameAction.STAND);
		softTotalRowTwo.put(8, GameAction.STAND);
		softTotalRowTwo.put(9, GameAction.HIT);
		softTotalRowTwo.put(10, GameAction.HIT);
		
		strategyTable.put("A,7", softTotalRowTwo);
		
		//GameActions for all dealer cards and player's hand of A,6
		softTotalRowThree.put(1, GameAction.HIT);
		softTotalRowThree.put(2, GameAction.HIT);
		softTotalRowThree.put(3, GameAction.DOUBLE);
		softTotalRowThree.put(4, GameAction.DOUBLE);
		softTotalRowThree.put(5, GameAction.DOUBLE);
		softTotalRowThree.put(6, GameAction.DOUBLE);
		softTotalRowThree.put(7, GameAction.HIT);
		softTotalRowThree.put(8, GameAction.HIT);
		softTotalRowThree.put(9, GameAction.HIT);
		softTotalRowThree.put(10, GameAction.HIT);
		
		strategyTable.put("A,6", softTotalRowThree);
		
		//GameActions for all dealer cards and player's hand total of A,4-A,5
		softTotalRowFour.put(1, GameAction.HIT);
		softTotalRowFour.put(2, GameAction.HIT);
		softTotalRowFour.put(3, GameAction.HIT);
		softTotalRowFour.put(4, GameAction.DOUBLE);
		softTotalRowFour.put(5, GameAction.DOUBLE);
		softTotalRowFour.put(6, GameAction.DOUBLE);
		softTotalRowFour.put(7, GameAction.HIT);
		softTotalRowFour.put(8, GameAction.HIT);
		softTotalRowFour.put(9, GameAction.HIT);
		softTotalRowFour.put(10, GameAction.HIT);
		
		strategyTable.put("A,4-A,5", softTotalRowFour);
		
		//GameActions for all dealer cards and player's hand of A,2-A,3
		softTotalRowFive.put(1, GameAction.HIT);
		softTotalRowFive.put(2, GameAction.HIT);
		softTotalRowFive.put(3, GameAction.HIT);
		softTotalRowFive.put(4, GameAction.HIT);
		softTotalRowFive.put(5, GameAction.DOUBLE);
		softTotalRowFive.put(6, GameAction.DOUBLE);
		softTotalRowFive.put(7, GameAction.HIT);
		softTotalRowFive.put(8, GameAction.HIT);
		softTotalRowFive.put(9, GameAction.HIT);
		softTotalRowFive.put(10, GameAction.HIT);
		
		strategyTable.put("A,2-A,3", softTotalRowFive);
		
		//For pairs:
		
		//GameActions for all dealer cards and player's hand of A,A
		pairRowOne.put(1, GameAction.SPLIT);
		pairRowOne.put(2, GameAction.SPLIT);
		pairRowOne.put(3, GameAction.SPLIT);
		pairRowOne.put(4, GameAction.SPLIT);
		pairRowOne.put(5, GameAction.SPLIT);
		pairRowOne.put(6, GameAction.SPLIT);
		pairRowOne.put(7, GameAction.SPLIT);
		pairRowOne.put(8, GameAction.SPLIT);
		pairRowOne.put(9, GameAction.SPLIT);
		pairRowOne.put(10, GameAction.SPLIT);
		
		strategyTable.put("A,A", pairRowOne);
		
		//GameActions for all dealer cards and player's hand of 10,10
		pairRowTwo.put(1, GameAction.STAND);
		pairRowTwo.put(2, GameAction.STAND);
		pairRowTwo.put(3, GameAction.STAND);
		pairRowTwo.put(4, GameAction.STAND);
		pairRowTwo.put(5, GameAction.STAND);
		pairRowTwo.put(6, GameAction.STAND);
		pairRowTwo.put(7, GameAction.STAND);
		pairRowTwo.put(8, GameAction.STAND);
		pairRowTwo.put(9, GameAction.STAND);
		pairRowTwo.put(10, GameAction.STAND);
		
		strategyTable.put("10,10", pairRowTwo);
		
		//GameActions for all dealer cards and player's hand of 9,9
		pairRowThree.put(1, GameAction.STAND);
		pairRowThree.put(2, GameAction.SPLIT);
		pairRowThree.put(3, GameAction.SPLIT);
		pairRowThree.put(4, GameAction.SPLIT);
		pairRowThree.put(5, GameAction.SPLIT);
		pairRowThree.put(6, GameAction.SPLIT);
		pairRowThree.put(7, GameAction.STAND);
		pairRowThree.put(8, GameAction.SPLIT);
		pairRowThree.put(9, GameAction.SPLIT);
		pairRowThree.put(10, GameAction.STAND);
		
		strategyTable.put("9,9", pairRowThree);
		
		//GameActions for all dealer cards and player's hand of 8,8
		pairRowFour.put(1, GameAction.SPLIT);
		pairRowFour.put(2, GameAction.SPLIT);
		pairRowFour.put(3, GameAction.SPLIT);
		pairRowFour.put(4, GameAction.SPLIT);
		pairRowFour.put(5, GameAction.SPLIT);
		pairRowFour.put(6, GameAction.SPLIT);
		pairRowFour.put(7, GameAction.SPLIT);
		pairRowFour.put(8, GameAction.SPLIT);
		pairRowFour.put(9, GameAction.SPLIT);
		pairRowFour.put(10, GameAction.SPLIT);
		
		strategyTable.put("8,8", pairRowFour);
		
		//GameActions for all dealer cards and player's hand of 7,7
		pairRowFive.put(1, GameAction.HIT);
		pairRowFive.put(2, GameAction.SPLIT);
		pairRowFive.put(3, GameAction.SPLIT);
		pairRowFive.put(4, GameAction.SPLIT);
		pairRowFive.put(5, GameAction.SPLIT);
		pairRowFive.put(6, GameAction.SPLIT);
		pairRowFive.put(7, GameAction.SPLIT);
		pairRowFive.put(8, GameAction.HIT);
		pairRowFive.put(9, GameAction.HIT);
		pairRowFive.put(10, GameAction.HIT);
		
		strategyTable.put("7,7", pairRowFive);
		
		//GameActions for all dealer cards and player's hand of 6,6
		pairRowSix.put(1, GameAction.HIT);
		pairRowSix.put(2, GameAction.SPLIT);
		pairRowSix.put(3, GameAction.SPLIT);
		pairRowSix.put(4, GameAction.SPLIT);
		pairRowSix.put(5, GameAction.SPLIT);
		pairRowSix.put(6, GameAction.SPLIT);
		pairRowSix.put(7, GameAction.HIT);
		pairRowSix.put(8, GameAction.HIT);
		pairRowSix.put(9, GameAction.HIT);
		pairRowSix.put(10, GameAction.HIT);
		
		strategyTable.put("6,6", pairRowSix);
		
		//GameActions for all dealer cards and player's hand of 5,5
		pairRowSeven.put(1, GameAction.HIT);
		pairRowSeven.put(2, GameAction.DOUBLE);
		pairRowSeven.put(3, GameAction.DOUBLE);
		pairRowSeven.put(4, GameAction.DOUBLE);
		pairRowSeven.put(5, GameAction.DOUBLE);
		pairRowSeven.put(6, GameAction.DOUBLE);
		pairRowSeven.put(7, GameAction.DOUBLE);
		pairRowSeven.put(8, GameAction.DOUBLE);
		pairRowSeven.put(9, GameAction.DOUBLE);
		pairRowSeven.put(10, GameAction.HIT);
		
		strategyTable.put("5,5", pairRowSeven);
		
		//GameActions for all dealer cards and player's hand of 4,4
		pairRowEight.put(1, GameAction.HIT);
		pairRowEight.put(2, GameAction.HIT);
		pairRowEight.put(3, GameAction.HIT);
		pairRowEight.put(4, GameAction.HIT);
		pairRowEight.put(5, GameAction.SPLIT);
		pairRowEight.put(6, GameAction.SPLIT);
		pairRowEight.put(7, GameAction.HIT);
		pairRowEight.put(8, GameAction.HIT);
		pairRowEight.put(9, GameAction.HIT);
		pairRowEight.put(10, GameAction.HIT);
		
		strategyTable.put("4,4", pairRowEight);
		
		//GameActions for all dealer cards and player's hand of 2,2-3,3
		pairRowNine.put(1, GameAction.HIT);
		pairRowNine.put(2, GameAction.SPLIT);
		pairRowNine.put(3, GameAction.SPLIT);
		pairRowNine.put(4, GameAction.SPLIT);
		pairRowNine.put(5, GameAction.SPLIT);
		pairRowNine.put(6, GameAction.SPLIT);
		pairRowNine.put(7, GameAction.SPLIT);
		pairRowNine.put(8, GameAction.HIT);
		pairRowNine.put(9, GameAction.HIT);
		pairRowNine.put(10, GameAction.HIT);
		
		strategyTable.put("2,2-3,3", pairRowNine);
		
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
