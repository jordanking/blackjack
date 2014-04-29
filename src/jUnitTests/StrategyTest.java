/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackjack.GameAction;
import blackjack.Strategy;

/**
 * @author alyssamiller
 *
 */
public class StrategyTest {

	Strategy strategy;
	
	public StrategyTest() {
		
	}


	/**
	 * 
	 * Test method for {@link blackjack.Strategy#setGameActionForHands(java.lang.String, java.lang.Integer, blackjack.GameAction)}.
	 * Calls the set and get method for updating the strategy
	 */
	@Test
	public void testSetGameActionForHands() {
		
		strategy = new Strategy();
		
		strategy.setGameActionForHands("13-14", 3, GameAction.STAND);
		assertEquals(GameAction.STAND, strategy.getGameActionForHands("13-14", 3));
	}

}
