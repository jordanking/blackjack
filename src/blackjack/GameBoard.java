package blackjack;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author Jordan King
 * @version 1.0
 * 
 * The panel that is used in both the PlayPanel and the AutoPanel to display and model a
 * game of blackjack. It contains the complete model of a game of blackjack, for an arbitrary
 * number of hands played between one player and a dealer. It tracks losses and finances, and draws
 * the game on a panel.
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	/**
	 * A constant for the default amount of starting cash.
	 */
	static private final int DEFAULT_CASH = 1000;
	
	/**
	 * A constant for the default amount to bet.
	 */
	static private final int DEFAULT_BET = 10;
	
	/**
	 * The dealer is a private member that models the current state of the dealer in the game.
	 */
	private Dealer dealer;
	
	/**
	 * The player is a private member that models the current state of the player in the game.
	 */
	private Player player;
	
	/**
	 * The deck is a private member that enables the game model to accurately draw cards from
	 * randomized decks.
	 */
	private Deck deck;
	
	/**
	 * The cash is a private member that represents the current amount of money that the player has
	 * to bet with.
	 */
	private int cash;
	
	/**
	 * The losses is a private member that tracks the total amount of money lost by the player over
	 * the course of this game's last initialization (or creation).
	 */
	private int losses;
	
	/**
	 * The bet is a private member that represents the amount of money that the player is betting on
	 * the current hand.
	 */
	private int bet;
	
	/**
	 * The handNumber is the number of hands of blackjack that have been played since this game's 
	 * last initialization (or creation).
	 */
	private int handNumber;
	
	/**
	 * The readyForInput bool is a marker of whether or not the game is ready to receive the player
	 * choice of hit, stand, etc... (not the bet!)
	 */
	private boolean readyForInput;
	
	/**
	 * The readyForBet bool is a marker of whether or not the game is ready to receive the player
	 * bet.
	 */
	private boolean readyForBet;

	/**
	 * 
	 */
	public GameBoard() {
		initModel();
	}

	/**
	 * @param layout
	 */
	public GameBoard(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isDoubleBuffered
	 */
	public GameBoard(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public GameBoard(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets the cash to the parameter value.
	 * 
	 * @param newCash the new value to set as the player's bet.
	 * @return none
	 * @since 1.0
	 */
	public void setCash(int newCash) {
		cash = newCash;
	}
	
	/**
	 * Returns the current amount of cash that the player can bet with.
	 * 
	 * @param none
	 * @return cash the integer value representing the amount of cash.
	 * @since 1.0
	 */
	public int getCash() {
		return cash;
	}
	
	/**
	 * Returns the current amount of cash that the player has lost in the game.
	 * 
	 * @param none
	 * @return losses the integer value representing the amount of cash lost.
	 * @since 1.0
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Sets the player bet to the parameter value.
	 * 
	 * @param newBet the new value to set as the player's bet.
	 * @return none
	 * @since 1.0
	 */
	public void setBet(int newBet) {
		bet = newBet;
	}
	
	/**
	 * Returns the current amount of cash that the player is betting in the game.
	 * 
	 * @param none
	 * @return bet the integer value representing the amount of cash being bet.
	 * @since 1.0
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Returns the current number of hands that have been played since the last initialization.
	 * 
	 * @param none
	 * @return handNumber the integer value representing the number of hands played.
	 * @since 1.0
	 */
	public int getHandNumber() {
		return handNumber;
	}

	/**
	 * Initializes the game model to a game-start configuration. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void initModel() {
		
		// get new models
		dealer = new Dealer();
		player = new Player();
		deck = new Deck();
		
		// set the numbers to a new game default
		cash = DEFAULT_CASH;
		losses = 0;
		bet = DEFAULT_BET;
		handNumber = 0;
		
		// set to a state that does not accept input
		readyForInput = false;
		readyForBet = false;
	}
	
	/**
	 * Starts the game loop for one hand of blackjack. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void playHand() {
		
	}
	
	/**
	 * Tells the model that the player would like to hit. Only registers if the 'readyForInput' bool
	 * is set to true. 
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void hit() {
		
	}
	
	/**
	 * Tells the model that the player would like to stand. Only registers if the 'readyForInput' 
	 * bool is set to true.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void stand() {
		
	}
	
	/**
	 * Returns a boolean representing whether or not the game is ready for the player's input
	 * behavior. 
	 * 
	 * @param none
	 * @return readyForInput a boolean value of whether the game is ready for input or not.
	 * @since 1.0
	 */
	public boolean isReadyForInput() {
		return readyForInput;
	}
	
	/**
	 * Returns a boolean representing whether or not the game is ready for the player's input
	 * behavior. 
	 * 
	 * @param none
	 * @return readyForBet a boolean value of whether the game is ready for a bet or not.
	 * @since 1.0
	 */
	public boolean isReadyForBet() {
		return readyForBet;
	}

}
