/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import blackjack.Card;
import blackjack.Player;
import blackjack.Rank;
import blackjack.Suit;

/**
 * Tests all of the public methods of the player class.
 * 
 * @author Jordan King
 * @version 1.0
 */
public class PlayerTest {

	/**
	 * Test method for {@link blackjack.Player#Player()}.
	 */
	@Test
	public final void testPlayer() {

		// get a new player
		Player player = new Player();
		
		// make sure both hands are empty
		assertEquals(0, player.getNumOfCards());
		assertEquals(0, player.getNumOfCardsSplit());
		
	}

	/**
	 * Test method for {@link blackjack.Player#addCard(blackjack.Card)}.
	 */
	@Test
	public final void testAddCard() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(Rank.ACE, player.getHand().get(0).getCardRank());
	}

	/**
	 * Test method for {@link blackjack.Player#addCardSplit(blackjack.Card)}.
	 */
	@Test
	public final void testAddCardSplit() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(Rank.ACE, player.getHandSplit().get(0).getCardRank());
	}

	/**
	 * Test method for {@link blackjack.Player#getPoints()}.
	 */
	@Test
	public final void testGetPoints() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(11, player.getPoints());
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the ace is handled
		assertEquals(12, player.getPoints());
	}

	/**
	 * Test method for {@link blackjack.Player#getPointsSplit()}.
	 */
	@Test
	public final void testGetPointsSplit() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(11, player.getPointsSplit());
		
		// add a card
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the ace is handled
		assertEquals(12, player.getPointsSplit());
	}

	/**
	 * Test method for {@link blackjack.Player#getNumOfCards()}.
	 */
	@Test
	public final void testGetNumOfCards() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(1, player.getNumOfCards());
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the ace is handled
		assertEquals(2, player.getNumOfCards());
	}

	/**
	 * Test method for {@link blackjack.Player#getNumOfCardsSplit()}.
	 */
	@Test
	public final void testGetNumOfCardsSplit() {
		// get a new player
		Player player = new Player();
		
		// add a card
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the card is there
		assertEquals(1, player.getNumOfCardsSplit());
		
		// add a card
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the ace is handled
		assertEquals(2, player.getNumOfCardsSplit());
	}

	/**
	 * Test method for {@link blackjack.Player#getHand()}.
	 */
	@Test
	public final void testGetHand() {
		// get a new player
		Player player = new Player();

		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// add a card
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the aces are there
		assertEquals(Rank.ACE, player.getHand().get(0).getCardRank());
		
	}

	/**
	 * Test method for {@link blackjack.Player#getHandSplit()}.
	 */
	@Test
	public final void testGetHandSplit() {
		// get a new player
		Player player = new Player();
		
		// add a card to split
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// add a card to split
		player.addCardSplit(new Card(Rank.ACE, Suit.HEARTS));
		
		// make sure the aces are there
		assertEquals(Rank.ACE, player.getHandSplit().get(0).getCardRank());
	}

	/**
	 * Test method for {@link blackjack.Player#hasPair(boolean)}.
	 */
	@Test
	public final void testHasPair() {
		// get a new player
		Player player = new Player();
		
		// add a card to split
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// add a card to split
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// check for pair
		assertEquals(true, player.hasPair(false));
	}

	/**
	 * Test method for {@link blackjack.Player#hasBlackjack()}.
	 */
	@Test
	public final void testHasBlackjack() {
		// get a new player
		Player player = new Player();
		
		// add a card to split
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// add a card to split
		player.addCard(new Card(Rank.JACK, Suit.HEARTS));
		
		// check for blackjack
		assertEquals(true, player.hasBlackjack());
	}

	/**
	 * Test method for {@link blackjack.Player#reset()}.
	 */
	@Test
	public final void testReset() {
		// get a new player
		Player player = new Player();
		
		// add a card to split
		player.addCard(new Card(Rank.ACE, Suit.HEARTS));
		
		// add a card to split
		player.addCardSplit(new Card(Rank.JACK, Suit.HEARTS));
		
		player.reset();
		
		// check for 0 cards
		assertEquals(0, player.getNumOfCards());
		
		// check for 0 cards split
		assertEquals(0, player.getNumOfCardsSplit());
	}

}
