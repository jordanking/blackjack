/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.assertEquals;
import model.Card;
import model.Rank;
import model.Suit;

import org.junit.Test;

/**
 * This tests all public methods of the card class.
 * 
 * @author Jordan King
 * @version 1.0
 */
public class CardTest {

	/**
	 * Test method for {@link model.Card#Card()}.
	 */
	@Test
	public final void testCard() {
		
		// make an ace of clubs
		Card card = new Card();
		
		// make sure it is ace
		assertEquals(Rank.ACE, card.getCardRank());
		
		// make sure it is clubs
		assertEquals(Suit.CLUBS, card.getCardSuit());
	}

	/**
	 * Test method for {@link model.Card#Card(model.Rank, model.Suit)}.
	 */
	@Test
	public final void testCardRankSuit() {
		
		// make an 8 of diamonds
		Card card = new Card(Rank.EIGHT, Suit.DIAMONDS);
		
		// make sure it is 8
		assertEquals(Rank.EIGHT, card.getCardRank());
		
		// make sure it is diamonds
		assertEquals(Suit.DIAMONDS, card.getCardSuit());
	}

	/**
	 * Test method for {@link model.Card#setCardRank(model.Rank)}.
	 */
	@Test
	public final void testSetCardRank() {
		// make an ace of clubs
		Card card = new Card();
		
		// set the rank to 7
		card.setCardRank(Rank.SEVEN);
		
		// make sure it is 7
		assertEquals(Rank.SEVEN, card.getCardRank());
	}

	/**
	 * Test method for {@link model.Card#setCardSuit(model.Suit)}.
	 */
	@Test
	public final void testSetCardSuit() {
		// make an ace of clubs
		Card card = new Card();
		
		// set the suit to diamonds
		card.setCardSuit(Suit.DIAMONDS);
		
		// make sure it is diamonds
		assertEquals(Suit.DIAMONDS, card.getCardSuit());
	}

	/**
	 * Test method for {@link model.Card#getCardRank()}.
	 */
	@Test
	public final void testGetCardRank() {
		// make an ace of clubs
		Card card = new Card();
		
		// make sure it is ace
		assertEquals(Rank.ACE, card.getCardRank());
	}

	/**
	 * Test method for {@link model.Card#getCardSuit()}.
	 */
	@Test
	public final void testGetCardSuit() {
		// make an ace of clubs
		Card card = new Card();
		
		// make sure it is clubs
		assertEquals(Suit.CLUBS, card.getCardSuit());
	}

}
