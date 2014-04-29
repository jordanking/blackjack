/**
 * 
 */
package blackjack;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class that represents a deck of cards, with some multiple of 52 cards. Constructs with a 
 * sorted deck, then can shuffle at any time afterwards. Does not have jokers.
 * 
 * @author Riya Modi
 * @version 1.0
 */
public class Deck {
	
	/**
	 * The ArrayList of card objects that is a deck.
	 */
	ArrayList<Card> deck;


	/**
	 * Constructor creates a deck of 52 playing cards.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public Deck() {
		
		// init the array
		deck = new ArrayList<Card>();
		
		// go through all suits
		for (int suit=0; suit<4; suit++){
			
			// go through all ranks
			for (int rank=0; rank<13; rank++){
				//use the enums
				Suit newSuit = Suit.values()[suit];
				Rank newRank = Rank.values()[rank];
				
				// create card
				Card newCard = new Card(newRank,newSuit);
				
				// add to deck
				deck.add(newCard);
			}
		}
	}
	
	/**
	 * Constructor creates a deck of playing cards with the specified number of 52 card decks
	 * 
	 * @param numberOfDecks the number of decks to build the deck with
	 * @return none
	 * @since 1.0
	 */
	public Deck(int numberOfDecks) {
		
		// init the array
		deck = new ArrayList<Card>();
		
		// number of decks
		for (int i = 0; i < numberOfDecks; i++) {
			
			// each suit
			for (int suit = 0; suit < 4; suit++) {
				
				// each rank
				for (int rank = 0; rank < 13; rank++) {

					//use enums
					Suit newSuit = Suit.values()[suit];
					Rank newRank = Rank.values()[rank];

					// create card
					Card newCard = new Card(newRank, newSuit);

					// add to deck
					deck.add(newCard);
				}
			}
		}
	}
	
	/**
	 * Returns the number of cards in the deck.
	 * 
	 * @param none
	 * @return numCardsInDeck the number of cards in the deck
	 * @since 1.0
	 */
	public int getNumCardsInDeck(){
		
		// return the size
		return deck.size();
	}

	/**
	 * Shuffles the deck by going through all the current cards and swapping them with randomly
	 * generated cards.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void shuffle() {
		
		// get some handles on the deck for a shuffle
		Random randomCard = new Random();
		int randomIndex;
		Card placeholder;
		
		// iterate through the deck, randomly swapping cards.
		for (int deckIterator=0; deckIterator<deck.size(); deckIterator++){
			
			//pick a random number from the number of cards currently in the deck
			//this random number will represent an index in the ArrayList "deck"
			randomIndex = randomCard.nextInt(deck.size());
			
			//create a Card object placeholder for the current card deckIterator is pointing to
			placeholder = deck.get(deckIterator);
			
			//swap the current card deckIterator is pointing to with the randomly generated card index
			deck.set(deckIterator,deck.get(randomIndex));
			deck.set(randomIndex,placeholder);
		}
		
	}

	/**
	 * Returns and removes the card at the top of the deck
	 * 
	 * @param none
	 * @return Card the card at the top of the deck
	 * @version 1.0
	 */
	public Card drawCard() {
		
		//if the deck is not empty, return the card on top
		if (isEmpty() == false){
			
			// remove a card
			return deck.remove(0);
		}
		
		//if the deck is empty, return a null object
		return null;
	}
	
	/**
	 * returns true if the deck is empty
	 * returns false if the deck is not empty
	 * 
	 * @param none
	 * @return boolean whether or not the deck is empty
	 * @version 1.0
	 */
	public boolean isEmpty(){
		
		// log a failure if the deck is empty...
		if (deck.size()==0) {
			System.out.println("DECK IS EMPTY");
			return true;
		} else {
			return false;
		}
	}
}
