/**
 * 
 */
package jUnitTests;

import static org.junit.Assert.assertEquals;
import model.Card;
import model.Deck;
import model.Rank;
import model.Suit;

import org.junit.Test;

/**
 * Tests all of the public methods of the deck class.
 * 
 * @author Jordan King
 * @version 1.0
 */
public class DeckTest {

	/**
	 * Test method for {@link model.Deck#Deck()}.
	 */
	@Test
	public final void testDeck() {
		
		// make a single sorted deck.
		Deck deck = new Deck();
		
		// make sure there are 52 cards
		assertEquals(52, deck.getNumCardsInDeck());
		
		// get the top card
		Card card = deck.drawCard();
		
		// make sure the first card is Ace of hearts
		assertEquals(Suit.HEARTS, card.getCardSuit());
		assertEquals(Rank.ACE, card.getCardRank());
	}

	/**
	 * Test method for {@link model.Deck#Deck(int)}.
	 */
	@Test
	public final void testDeckInt() {
		// make a single sorted deck.
		Deck deck = new Deck(8);
		
		// make sure there are 52 cards
		assertEquals(52*8, deck.getNumCardsInDeck());
		
		// get the top card
		Card card = deck.drawCard();
		
		// make sure the first card is Ace of 
		assertEquals(Suit.HEARTS, card.getCardSuit());
		assertEquals(Rank.ACE, card.getCardRank());
	}

	/**
	 * Test method for {@link model.Deck#getNumCardsInDeck()}.
	 */
	@Test
	public final void testGetNumCardsInDeck() {
		// make a single sorted deck.
		Deck deck = new Deck();
		
		// make sure there are 52 cards
		assertEquals(52, deck.getNumCardsInDeck());
		
		// remove the top card
		deck.drawCard();
		
		// make sure there are 51 cards
		assertEquals(51, deck.getNumCardsInDeck());
		
	}

	/**
	 * Test method for {@link model.Deck#shuffle()}.
	 */
	@Test
	public final void testShuffle() {
		// make a single sorted deck.
		Deck deck = new Deck();
		
		// shuffle the deck
		deck.shuffle();

		// get the top card
		Card card = deck.drawCard();
		
		// make sure the first card is not Ace of hearts
		assertEquals(false, Suit.HEARTS == card.getCardSuit() && Rank.ACE == card.getCardRank());
	}

	/**
	 * Test method for {@link model.Deck#drawCard()}.
	 */
	@Test
	public final void testDrawCard() {
		// make a single sorted deck.
		Deck deck = new Deck();
		
		// make sure there are 52 cards
		assertEquals(52, deck.getNumCardsInDeck());
		
		// remove the top card
		Card card = deck.drawCard();
		
		// make sure there are 51 cards
		assertEquals(51, deck.getNumCardsInDeck());
		
		// make sure the first card is Ace of hearts
		assertEquals(Suit.HEARTS, card.getCardSuit());
		assertEquals(Rank.ACE, card.getCardRank());
	}

	/**
	 * Test method for {@link model.Deck#isEmpty()}.
	 */
	@Test
	public final void testIsEmpty() {
		// make a single sorted deck.
		Deck deck = new Deck();
		
		// make sure the deck is not empty
		assertEquals(false, deck.isEmpty());
		
		// remove all cards
		for (int i = 0; i < 52; i++) {
			deck.drawCard();
		}
		
		// make sure the deck is not empty
		assertEquals(true, deck.isEmpty());

	}

}
