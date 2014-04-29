/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.*;
import model.GameState;

import org.junit.Before;
import org.junit.Test;

import controller.GameBoard;

/**
 * Tests all of the public methods of the gameboard class. THERE ARE FOURTY-FOUR
 * 
 * @author Jordan King
 * @version 1.0
 */
public class GameBoardTest {
	
	/**
	 * A gameBoard that we will test with
	 */
	GameBoard game;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		game = new GameBoard();
	}

	/**
	 * Test method for {@link controller.GameBoard#GameBoard()}.
	 */
	@Test
	public final void testGameBoard() {
		
		// check all of the default values
		assertEquals(0, game.getDealer().getNumOfCards());
		assertEquals(0, game.getPlayer().getNumOfCards());

	
		// check what defaults we can that wouldn't change.
		assertEquals(0, game.getLosses());
		assertEquals(0, game.getHandNumber());
		assertEquals(GameState.END, game.getMainHandState());
		assertEquals(GameState.END, game.getSplitHandState());
	}

	/**
	 * Test method for {@link controller.GameBoard#playHand()}.
	 */
	@Test
	public final void testPlayHand() {
		// start a hand
		game.playHand();
		
		// make sure both hands are in the right state
		assertEquals(GameState.BET, game.getMainHandState());
		assertEquals(GameState.END, game.getSplitHandState());
	}

	/**
	 * Test method for {@link controller.GameBoard#bet(int)}.
	 */
	@Test
	public final void testBet() {
		// start a hand
		game.playHand();
		
		// set a bet
		game.bet(400);
		
		// make sure both hands are in the right state
		assertEquals(GameState.BET, game.getMainHandState());
		assertEquals(GameState.END, game.getSplitHandState());	
	
		// make sure the bet is right
		assertEquals(400, game.getBet());
	}

	/**
	 * Test method for {@link controller.GameBoard#deal()}.
	 */
	@Test
	public final void testDeal() {
		// start a hand
		game.playHand();
		
		// set a bet
		game.deal();
		
		// make sure both hands are in the right state
		assertEquals(GameState.DEAL, game.getMainHandState());
		assertEquals(GameState.END, game.getSplitHandState());	
	
		// make sure the hands are dealt correctly
		assertEquals(2, game.getPlayerHand().size());
		assertEquals(1, game.getDealerHand().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#hit()}.
	 */
	@Test
	public final void testHit() {
		// start a hand
		game.playHand();
		
		// set a bet
		game.deal();
		
		game.hit();
		
		// make sure the split hand is in the right state
		assertEquals(GameState.END, game.getSplitHandState());	
	
		// make sure the hand is hit correctly
		assertEquals(3, game.getPlayerHand().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#stand()}.
	 */
	@Test
	public final void testStand() {
		// start a hand
		game.playHand();
		
		// deal
		game.deal();
		
		game.stand();
		
		// make sure the hands are in the right state
		assertEquals(GameState.END, game.getMainHandState());	
		assertEquals(GameState.END, game.getSplitHandState());	
	
		// make sure the hand is hit correctly
		assertEquals(2, game.getPlayerHand().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#split()}.
	 */
	@Test
	public final void testSplit() {
		
		// cycle til we get splittable hands
		for (int i = 0; i < 20; i++) {

			// new game
			game = new GameBoard();
			
			// start a hand
			game.playHand();

			// deal
			game.deal();
			
			if (game.getPlayer().hasPair(game.isOnlySplitOnSameRank()) ) {
				
				// split hand
				game.split();

				// make sure the hands are in the right state
				assertEquals(GameState.SPLIT, game.getMainHandState());	
				assertEquals(GameState.SPLIT, game.getSplitHandState());
				
				// make sure the hand is hit correctly
				assertEquals(2, game.getPlayerHand().size());
				
				// make sure the split hand is hit correctly
				assertEquals(2, game.getPlayerHandSplit().size());
			}
		}
		
		
	}

	/**
	 * Test method for {@link controller.GameBoard#doubleDown()}.
	 */
	@Test
	public final void testDoubleDown() {
		// start a hand
		game.playHand();
		
		// deal
		game.deal();
		
		game.doubleDown();
		
		// make sure the hands are in the right state
		assertEquals(GameState.END, game.getMainHandState());	
		assertEquals(GameState.END, game.getSplitHandState());	
	}

	/**
	 * Test method for {@link controller.GameBoard#surrender()}.
	 */
	@Test
	public final void testSurrender() {
		// start a hand
		game.playHand();
		
		// deal
		game.deal();
		
		game.surrender();
		
		// make sure the hands are in the right state
		assertEquals(GameState.END, game.getMainHandState());	
		assertEquals(GameState.END, game.getSplitHandState());	
	}

	/**
	 * Test method for {@link controller.GameBoard#hitSplit()}.
	 */
	@Test
	public final void testHitSplit() {
		
		// cycle so we get some splittable hands
		for (int i = 0; i < 20; i++) {

			// new game
			game = new GameBoard();
			
			game.setSplittingAcesEndsControl(false);
			
			// start a hand
			game.playHand();

			// deal
			game.deal();
			
			if (game.getPlayer().hasPair(game.isOnlySplitOnSameRank()) ) {
				
				// split hand
				game.split();
				
				// the test!
				game.hitSplit();

				// make sure the hands are in the right state
				assertEquals(GameState.SPLIT, game.getMainHandState());	
				
				// make sure the hand is hit correctly
				assertEquals(2, game.getPlayerHand().size());
				
				// make sure the split hand is hit correctly
				assertEquals(3, game.getPlayerHandSplit().size());
			}
		}
	}

	/**
	 * Test method for {@link controller.GameBoard#standSplit()}.
	 */
	@Test
	public final void testStandSplit() {
		// cycle so we get some splittable hands
		for (int i = 0; i < 20; i++) {

			// new game
			game = new GameBoard();

			game.setSplittingAcesEndsControl(false);

			// start a hand
			game.playHand();

			// deal
			game.deal();

			if (game.getPlayer().hasPair(game.isOnlySplitOnSameRank()) ) {

				// split hand
				game.split();

				// the test!
				game.standSplit();

				// make sure the hands are in the right state
				assertEquals(GameState.SPLIT, game.getMainHandState());	

				// make sure the hand is hit correctly
				assertEquals(2, game.getPlayerHand().size());

				// make sure the split hand is hit correctly
				assertEquals(2, game.getPlayerHandSplit().size());
			}
		}
	}

	/**
	 * Test method for {@link controller.GameBoard#doubleDownSplit()}.
	 */
	@Test
	public final void testDoubleDownSplit() {
		// cycle so we get some splittable hands
		for (int i = 0; i < 20; i++) {

			// new game
			game = new GameBoard();

			game.setSplittingAcesEndsControl(false);
			game.setDoubleAllowedAfterSplit(true);

			// start a hand
			game.playHand();

			// deal
			game.deal();

			if (game.getPlayer().hasPair(game.isOnlySplitOnSameRank()) ) {

				// split hand
				game.split();

				// the test!
				game.doubleDownSplit();

				// make sure the hands are in the right state
				assertEquals(GameState.SPLIT, game.getMainHandState());	

				// make sure the hand is hit correctly
				assertEquals(2, game.getPlayerHand().size());

				// make sure the split hand is hit correctly
				assertEquals(3, game.getPlayerHandSplit().size());
			}
		}	
	}

	/**
	 * Test method for {@link controller.GameBoard#getMainHandState()}.
	 */
	@Test
	public final void testGetMainHandState() {
		
		// make sure the hand is in the right state
		assertEquals(GameState.END, game.getMainHandState());	
	}

	/**
	 * Test method for {@link controller.GameBoard#getSplitHandState()}.
	 */
	@Test
	public final void testGetSplitHandState() {
		
		// make sure the split hand is in the right state
		assertEquals(GameState.END, game.getSplitHandState());
	}

	/**
	 * Test method for {@link controller.GameBoard#handHasSplit()}.
	 */
	@Test
	public final void testHandHasSplit() {
		
		// make sure the value is correct with a new board
		assertEquals(false, game.handHasSplit());
	}

	/**
	 * Test method for {@link controller.GameBoard#setDealerWinsPush(boolean)}.
	 */
	@Test
	public final void testSetDealerWinsPush() {
		
		// make sure the value is correct with a new board
		assertEquals(false, game.isDealerWinsPush());
		
		// set the dealer winning
		game.setDealerWinsPush(true);
		
		// make sure the value is correct now
		assertEquals(true, game.isDealerWinsPush());
	}

	/**
	 * Test method for {@link controller.GameBoard#isDealerWinsPush()}.
	 */
	@Test
	public final void testIsDealerWinsPush() {
		
		// make sure the value is correct with a new board
		assertEquals(false, game.isDealerWinsPush());
		
		// set the dealer winning
		game.setDealerWinsPush(true);
		
		// make sure the value is correct now
		assertEquals(true, game.isDealerWinsPush());
	}

	/**
	 * Test method for {@link controller.GameBoard#setSoftSeventeenRule(boolean)}.
	 */
	@Test
	public final void testSetSoftSeventeenRule() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSoftSeventeenRule());
		
		// set the new value
		game.setSoftSeventeenRule(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSoftSeventeenRule());
	}

	/**
	 * Test method for {@link controller.GameBoard#isSoftSeventeenRule()}.
	 */
	@Test
	public final void testIsSoftSeventeenRule() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSoftSeventeenRule());
		
		// set the new value
		game.setSoftSeventeenRule(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSoftSeventeenRule());
	}

	/**
	 * Test method for {@link controller.GameBoard#setSurrenderAllowed(boolean)}.
	 */
	@Test
	public final void testSetSurrenderAllowed() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSurrenderAllowed());
		
		// set the new value
		game.setSurrenderAllowed(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSurrenderAllowed());
	}

	/**
	 * Test method for {@link controller.GameBoard#isSurrenderAllowed()}.
	 */
	@Test
	public final void testIsSurrenderAllowed() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSurrenderAllowed());
		
		// set the new value
		game.setSurrenderAllowed(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSurrenderAllowed());	
	}

	/**
	 * Test method for {@link controller.GameBoard#setSplittingAcesEndsControl(boolean)}.
	 */
	@Test
	public final void testSetSplittingAcesEndsControl() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSplittingAcesEndsControl());
		
		// set the new value
		game.setSplittingAcesEndsControl(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSplittingAcesEndsControl());	
	}

	/**
	 * Test method for {@link controller.GameBoard#isSplittingAcesEndsControl()}.
	 */
	@Test
	public final void testIsSplittingAcesEndsControl() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isSplittingAcesEndsControl());
		
		// set the new value
		game.setSplittingAcesEndsControl(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isSplittingAcesEndsControl());	
	}

	/**
	 * Test method for {@link controller.GameBoard#setOnlySplitOnSameRank(boolean)}.
	 */
	@Test
	public final void testSetOnlySplitOnSameRank() {
		
		// make sure the value is correct with a new board
		assertEquals(false, game.isOnlySplitOnSameRank());
		
		// set the new value
		game.setOnlySplitOnSameRank(true);
		
		// make sure the value is correct now
		assertEquals(true, game.isOnlySplitOnSameRank());	
	}

	/**
	 * Test method for {@link controller.GameBoard#isOnlySplitOnSameRank()}.
	 */
	@Test
	public final void testIsOnlySplitOnSameRank() {
		
		// make sure the value is correct with a new board
		assertEquals(false, game.isOnlySplitOnSameRank());
		
		// set the new value
		game.setOnlySplitOnSameRank(true);
		
		// make sure the value is correct now
		assertEquals(true, game.isOnlySplitOnSameRank());
	}

	/**
	 * Test method for {@link controller.GameBoard#setDoubleAllowedAfterSplit(boolean)}.
	 */
	@Test
	public final void testSetDoubleAllowedAfterSplit() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isDoubleAllowedAfterSplit());
		
		// set the new value
		game.setDoubleAllowedAfterSplit(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isDoubleAllowedAfterSplit());
	}

	/**
	 * Test method for {@link controller.GameBoard#isDoubleAllowedAfterSplit()}.
	 */
	@Test
	public final void testIsDoubleAllowedAfterSplit() {
		
		// make sure the value is correct with a new board
		assertEquals(true, game.isDoubleAllowedAfterSplit());
		
		// set the new value
		game.setDoubleAllowedAfterSplit(false);
		
		// make sure the value is correct now
		assertEquals(false, game.isDoubleAllowedAfterSplit());	
	}

	/**
	 * Test method for {@link controller.GameBoard#setNumberOfDecks(int)}.
	 */
	@Test
	public final void testSetNumberOfDecks() {
		
		// make sure the value is correct with a new board
		assertEquals(8, game.getNumberOfDecks());
		
		// set the new value
		game.setNumberOfDecks(2);
		
		// make sure the value is correct now
		assertEquals(2, game.getNumberOfDecks());
	}

	/**
	 * Test method for {@link controller.GameBoard#getNumberOfDecks()}.
	 */
	@Test
	public final void testGetNumberOfDecks() {
		
		// make sure the value is correct with a new board
		assertEquals(8, game.getNumberOfDecks());
		
		// set the new value
		game.setNumberOfDecks(2);
		
		// make sure the value is correct now
		assertEquals(2, game.getNumberOfDecks());
	}

	/**
	 * Test method for {@link controller.GameBoard#setBlackjackPayRatio(double)}.
	 */
	@Test
	public final void testSetBlackjackPayRatio() {
		
		// make sure the value is correct with a new board
		assertEquals(1.2, game.getBlackjackPayRatio(), 0);
		
		// set the new value
		game.setBlackjackPayRatio(2.5);
		
		// make sure the value is correct now
		assertEquals(2.5, game.getBlackjackPayRatio(), 0);
	}

	/**
	 * Test method for {@link controller.GameBoard#getBlackjackPayRatio()}.
	 */
	@Test
	public final void testGetBlackjackPayRatio() {
		
		// make sure the value is correct with a new board
		assertEquals(1.2, game.getBlackjackPayRatio(), 0);
		
		// set the new value
		game.setBlackjackPayRatio(2.5);
		
		// make sure the value is correct now
		assertEquals(2.5, game.getBlackjackPayRatio(), 0);
	}

	/**
	 * Test method for {@link controller.GameBoard#getPlayer()}.
	 */
	@Test
	public final void testGetPlayer() {
		
		assertEquals(0, game.getPlayer().getNumOfCards());
		
	}

	/**
	 * Test method for {@link controller.GameBoard#getDealer()}.
	 */
	@Test
	public final void testGetDealer() {
		assertEquals(0, game.getDealer().getNumOfCards());
	}

	/**
	 * Test method for {@link controller.GameBoard#setCash(int)}.
	 */
	@Test
	public final void testSetCash() {
		
		// make sure the value is correct with a new board
		assertEquals(1000, game.getCash());
		
		// set the new value
		game.setCash(2000);
		
		// make sure the value is correct now
		assertEquals(2000, game.getCash());
	}

	/**
	 * Test method for {@link controller.GameBoard#getCash()}.
	 */
	@Test
	public final void testGetCash() {
		
		// make sure the value is correct with a new board
		assertEquals(1000, game.getCash());
		
		// set the new value
		game.setCash(2000);
		
		// make sure the value is correct now
		assertEquals(2000, game.getCash());
	}

	/**
	 * Test method for {@link controller.GameBoard#getLosses()}.
	 */
	@Test
	public final void testGetLosses() {
		
		// make sure the value is correct with a new board
		assertEquals(0, game.getLosses());
	}

	/**
	 * Test method for {@link controller.GameBoard#getBet()}.
	 */
	@Test
	public final void testGetBet() {
		
		// make sure the value is correct with a new board
		assertEquals(10, game.getBet());
	}

	/**
	 * Test method for {@link controller.GameBoard#getDealerHand()}.
	 */
	@Test
	public final void testGetDealerHand() {
		
		// starts with no cards
		assertEquals(0, game.getDealerHand().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#getPlayerHand()}.
	 */
	@Test
	public final void testGetPlayerHand() {
		
		// starts with no cards
		assertEquals(0, game.getPlayerHand().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#getPlayerHandSplit()}.
	 */
	@Test
	public final void testGetPlayerHandSplit() {
		
		// should be zero
		assertEquals(0, game.getPlayerHandSplit().size());
	}

	/**
	 * Test method for {@link controller.GameBoard#getPlayerHandValue()}.
	 */
	@Test
	public final void testGetPlayerHandValue() {
		
		// should be zero
		assertEquals(0, game.getPlayerHandValue());
	}

	/**
	 * Test method for {@link controller.GameBoard#getPlayerHandValueSplit()}.
	 */
	@Test
	public final void testGetPlayerHandValueSplit() {
		
		// should be zero
		assertEquals(0, game.getPlayerHandValueSplit());
	}


	/**
	 * Test method for {@link controller.GameBoard#getTotalLosses()}.
	 */
	@Test
	public final void testGetTotalLosses() {
		
		// should be zero
		assertEquals(0, game.getTotalLosses());
	}

	/**
	 * Test method for {@link controller.GameBoard#getHandNumber()}.
	 */
	@Test
	public final void testGetHandNumber() {
		// should be one
		assertEquals(0, game.getHandNumber());
		
		// start a hand
		game.playHand();
		
		// should be one
		assertEquals(1, game.getHandNumber());
	}

}
