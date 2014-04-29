/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.GameAction;
import controller.Strategy;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

/**
 * @author alyssamiller
 *
 *Test for the setGameActionFor Hand and getGameActionForHand
 *methods of the Strategy class.
 *
 *Values are changed from
 * optimal (default) strategy so that the GameAction should 
 * change from default value. These values are set with
 * setGameActionForHand and the GameAction is returned to 
 * check to see if it updated the strategy using the
 * getGameActionForHand method of the Strategy class
 */
@RunWith(value = Parameterized.class)
public class StrategyTest {

	Strategy strategy;
	
	private GameAction expected;	// The expected result and action to set
	private String playerHand;		//playerHand
	private Integer dealerFaceUpCard;// Dealer face-up card value
	
	/**
	 * StrategyTest()
	 * Constructor. Each test will have 3 values
	 * 
	 * @param expected
	 * @param actionToSet
	 * @param playerHand
	 * @param dealerFaceUpCard
	 */
	public StrategyTest
		(GameAction expected, String playerHand, Integer dealerFaceUpCard) {
			this.expected = expected;
			this.playerHand = playerHand;
			this.dealerFaceUpCard = dealerFaceUpCard;
	}
	
	/**
	 * getTestParameters()
	 * 
	 * A method for specifying all of the parameters to test. 
	 * There are 6 tests, two for each table (pairs, hard, soft totals)
	 * that tests all GameActions for each test. The GameAction to 
	 * set for a dealerFaceUpCard value and a given playerHand
	 * and the expected result are given. 
	 * 
	 * @param none
	 * @return a list of parameters
	 * 
	 */
	@Parameters
	public static Collection<Object[]> getTestParameters() {
		
		// The parameters are initially stored in a two dimensional object
		Object [][] myTestParameters = new Object [][] {
				{GameAction.HIT, "17-20", 2},//expected (Action to set), playerHand, dealerFaceUpCard
				{GameAction.STAND, "12", 3},//expected (Action to set), playerHand, dealerFaceUpCard
				{GameAction.DOUBLE, "A,8-A,9", 7},//expected (Action to set), playerHand, dealerFaceUpCard
				{GameAction.SURRENDER, "A,6", 1},//expected (Action to set), playerHand, dealerFaceUpCard
				{GameAction.SPLIT, "5,5", 6},//expected (Action to set), playerHand, dealerFaceUpCard
				{GameAction.STAND, "8,8", 10},//expected (Action to set), playerHand, dealerFaceUpCard
				
		};
		
		// Returning a single dimension list
		return Arrays.asList(myTestParameters);
			
	}


	/**
	 * 
	 * Test method for {@link controller.Strategy#setGameActionForHands(java.lang.String, java.lang.Integer, controller.GameAction)}.
	 * Calls the set and get method required for updating and returning the strategy
	 */
	@Test
	public void testSetGameActionForHands() {
		
		strategy = new Strategy();
	
		//updates the strategy using parameters. Expected is both the expected
		//result and the action to set
		strategy.setGameActionForHands(playerHand, dealerFaceUpCard, expected);
		// Test for expected value
		assertEquals(expected, strategy.getGameActionForHands(playerHand, dealerFaceUpCard));
	}

}
