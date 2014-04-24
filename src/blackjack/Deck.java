/**
 * 
 */
package blackjack;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Riya Modi
 *
 */
public class Deck {
	
	ArrayList<Card> deck;	//ArrayList of Card objects


	/**
	 * Deck()
	 * Constructor creates a deck of 52 playing cards (no jokers)
	 */
	public Deck() {
		deck = new ArrayList<Card>();
		
		for (int suit=0; suit<4; suit++){
			for (int rank=0; rank<13; rank++){
				
				//use enums
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
	 * Deck(int)
	 * Constructor creates a deck of playing cards (no jokers) with the specified number of 52 card
	 * decks
	 * 
	 * @param numberOfDecks the number of decks to build the deck with
	 * @return none
	 * @since 1.0
	 */
	public Deck(int numberOfDecks) {
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
	 * printDeck()
	 * 
	 * Prints the contents of the deck
	 */
	public void printDeck() {
		for (int i=0; i<deck.size();i++) {
			System.out.println(deck.get(i).getCardSuit() + " " + deck.get(i).getCardRank());
		}
	}
	
	/*
	 * getNumCardsInDeck()
	 * @return numCardsInDeck the number of cards in the deck
	 */
	public int getNumCardsInDeck(){
		return deck.size();
	}

	/**
	 * shuffle()
	 * 
	 * shuffles the deck by going through all the current cards
	 * and swapping them with randomly generated cards
	 */
	public void shuffle() {
		
		Random randomCard = new Random();
		int randomIndex;
		Card placeholder;
		
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

	/*
	 * drawCard()
	 * returns the card at the top of the deck
	 * @return Card object 
	 */
	public Card drawCard() {
		
		//if the deck is not empty, return the card on top
		if (isEmpty()==false){
			
			// remove a card
			return deck.remove(0);
		}
		
		//if the deck is empty, return a null object
		return null;
	}
	
	/*
	 * isEmpty()
	 * returns true if the deck is empty
	 * returns false if the deck is not empty
	 * @return boolean whether or not the deck is empty
	 */
	public boolean isEmpty(){
		if (deck.size()==0) {
			System.out.println("DECK IS EMPTY");
			return true;
		} else {
			return false;
		}
	}
}
