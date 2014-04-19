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
	
	ArrayList<Card> deck = new ArrayList<Card>();	//ArrayList of Card objects
	private int numCardsInDeck;						//keeps track of the number of Cards in the deck

	/**
	 * Deck()
	 * Constructor creates a deck of playing cards (no joker)
	 */
	public Deck() {
		// TODO Auto-generated constructor stub
		
		for (int suit=0; suit<4; suit++){
			for (int rank=0; rank<13; rank++){
				
				//use enums
				Suit newSuit = Suit.values()[suit];
				Rank newRank = Rank.values()[rank];
				
				Card newCard = new Card(newRank,newSuit);
				
				deck.add(newCard);
			}
		}
		
		//initialize the number of cards in a new deck
		numCardsInDeck = 52;
	}
	
	/*
	 * printDeck()
	 * prints the contents of the deck
	 */
	public void printDeck(){
		for (int i=0; i<deck.size();i++){
			System.out.println(deck.get(i).getCardSuit() + " " + deck.get(i).getCardRank());
		}
	}
	
	/*
	 * getNumCardsInDeck()
	 * @return numCardsInDeck the number of cards in the deck
	 */
	public int getNumCardsInDeck(){
		return numCardsInDeck;
	}

	/*
	 * shuffle()
	 * shuffles the deck by going through all the current cards
	 * and swapping them with randomly generated cards
	 */
	public void shuffle() {
		// TODO Auto-generated method stub
		
		Random randomCard = new Random();
		int randomIndex;
		Card placeholder;
		
		for (int deckIterator=0; deckIterator<getNumCardsInDeck();deckIterator++){
			//pick a random number from the number of cards currently in the deck
			//this random number will represent an index in the ArrayList "deck"
			randomIndex = randomCard.nextInt(getNumCardsInDeck());
			
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
		// TODO Auto-generated method stub
		
		//if the deck is not empty, return the card on top
		if (isEmpty()==false){
			
			//reduce the number of cards in the deck by one
			numCardsInDeck--;
			
			return deck.get(0);
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
		if (numCardsInDeck==0)
			return true;
		else{
			System.out.println("DECK IS EMPTY");
			return false;
		}
			
	}
	
	public void main(String[] args){
		printDeck();
	}

}
