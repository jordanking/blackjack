package blackjack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComponent;
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
	 * A constant for the text color.
	 */
	static private final Color TEXT_COLOR = Color.red;
	
	/**
	 * A constant for the bet display text.
	 */
	static private final String BET_DISPLAY_STRING = "Bet: ";
	
	/**
	 * A constant for the cash display text.
	 */
	static private final String CASH_DISPLAY_STRING = "Cash: ";
	
	/**
	 * A constant for the losses display text.
	 */
	static private final String LOSSES_DISPLAY_STRING = "Losses: ";
	
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
	 * The default constructor just initializes the data model. I am not sure how this will interact
	 * with the rest of the panels, as the layout has not been addressed.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public GameBoard() {
		
		// initialize the game model
		initModel();
	}

	/**
	 * Constructs and initializes the data model, the layout, the buffering, and everything.
	 * 
	 * @param layout
	 * @param isDoubleBuffered
	 * @return none
	 * @since 1.0
	 */
	public GameBoard(LayoutManager layout) {
		
		// Call up to the super
		super(layout);
		
		// Set up the game model
		initModel();
		
		// Use the layout that was passed in
		setLayout(layout);
		
		// Set the double buffering to true so it looks prettier
		setDoubleBuffered(true);
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
		
		// See if the player has money to play
		if (cash < bet) {
			return;
		}
		
		// We are now on the next hand.
		handNumber++;
		
		// Shuffle deck up front
		deck.shuffle();
		
		// deal the first card to the player
		player.addCard(deck.drawCard());
		
		// deal the second card to the dealer
		dealer.addCard(deck.drawCard());
		
		// deal the third card to the player
		player.addCard(deck.drawCard());
		
		// ready for bet and input
		readyForInput = true;
		readyForBet = true;
		
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
		
		// Make sure that the model is ready for input
		if (!readyForInput) {
			return;
		}
		
		// Stop the player from changing their bet from here on out!
		readyForBet = false;
		
		// don't allow input while processing input
		readyForInput = false;
		
		// Add a card to the player
		player.addCard(deck.drawCard());
		
		// check for bust
		if (player.getPoints() > 21) {
			
			// the player loses!
			cash -= bet;
			losses += bet;
		}
		
		// now we are ready for more input
		readyForInput = true;
		
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
		
		// Make sure that the model is ready for input
		if (!readyForInput) {
			return;
		}
		
		// Stop the player from changing their bet from here on out!
		readyForBet = false;
		
		// don't allow input while processing input
		readyForInput = false;
		
		// dealer must take hits until they have < 17 points
		while (dealer.getPoints() < 17) {
			
			// take a hit
			dealer.addCard(deck.drawCard());
			
			// check for bust
			if (dealer.getPoints() > 21) {
				
				// the player wins!
				cash += bet;
				losses -= bet;
			}
		}
		
		// check the scores for the winner
		if (dealer.getPoints() > player.getPoints()) {
			
			// the player loses!
			cash -= bet;
			losses += bet;
		} else if (dealer.getPoints() < player.getPoints()) {
			
			// the player wins!
			cash += bet;
			losses -= bet;
		} else {
			// push: do nothing on a tie
		}
		
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
	
	/**
     * Calls the drawing methods of the blackjack game
     * 
     * @param graphicsObject the graphicsObject to draw with
     * @return none
     * @see JComponent
     * @since 1.0 
     */
    @Override
    public void paintComponent(Graphics graphicsObject) {
    	
    	// draw all the other stuff (actually erases it!)
        super.paintComponent(graphicsObject);
        
    	// Switch to 2D
        Graphics2D graphicsObject2d = (Graphics2D) graphicsObject;
        
        // make it antialiasing for fun
    	graphicsObject2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
  	          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	graphicsObject2d.setRenderingHint(RenderingHints.KEY_RENDERING,
  	          RenderingHints.VALUE_RENDER_QUALITY);
        
        // draw background
        drawBackground(graphicsObject2d);
        
        // draw my stuff (what else is there really?)
        drawDealer(graphicsObject2d);
        drawPlayer(graphicsObject2d);
        drawDeck(graphicsObject2d);
        
        // Draw the meta (like cash, bet, etc...)
        drawMeta(graphicsObject2d);

        // Explicitly release the memory storing the graphics. Do not wait for garbage collection
        graphicsObject2d.dispose();
    }

    /**
     * Draws the background on the game board
     * 
     * @param graphicsObject2d the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawBackground(Graphics2D graphicsObject2d) {
    	
    	// draw the background
//    	graphicsObject2d.drawImage(backImageAsset, 0, 0, this);
    	graphicsObject2d.setBackground(Color.green);
        
        // Synchronize the graphics state - now is the the to draw! (magic)
        Toolkit.getDefaultToolkit().sync();
        
        // DONT DISPOSE OF THE GRAPHICS OBJECT HERE ITLL SUCK EVERYTHING UP
    }
    
    /**
     * Draws the dealer's hand on the screen
     * 
     * @param graphicsObject2d the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawDealer(Graphics2D graphicsObject2d) {
    	
    	// get the dealer's hand
    	ArrayList<Card> dealerHand = dealer.getHand();
    	
    	// iterate through all cards of the dealer and draw them on the board
    	for (Card card: dealerHand) {
//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);
//    		graphicsObject2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		}
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();
        
        // DONT DISPOSE OF THE GRAPHICS OBJECT HERE ITLL SUCK EVERYTHING UP
    }

    /**
     * Draws the player's hand on the screen
     * 
     * @param graphicsObject2d the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawPlayer(Graphics2D graphicsObject2d) {


    	// get the player's hand
    	ArrayList<Card> playerHand = player.getHand();
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: playerHand) {
//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);
//    		graphicsObject2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		}
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();

        // DONT DELETE THE OBJECT
    }
    
    /**
     * Draws the deck on the screen
     * 
     * @param graphicsObject2d the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawDeck(Graphics2D graphicsObject2d) {
    	
    	// graphicsObject2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();

        // DONT DELETE THE OBJECT PLZ
    }
    
    /**
     * Draws the meta information on the screen
     * 
     * @param graphicsObject
     * @return none
     * @since 1.0
     */
    private void drawMeta(Graphics2D graphicsObject2d) {
    	
    	// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 25));
        graphicsObject2d.setColor(TEXT_COLOR);
        
        // Draw the cash
   		graphicsObject2d.drawString(CASH_DISPLAY_STRING + cash, 5, 25);
        
   		// Draw the losses
   		graphicsObject2d.drawString(LOSSES_DISPLAY_STRING + losses, 5, 50);
   		
   		// Draw the bet
   		graphicsObject2d.drawString(BET_DISPLAY_STRING + bet, 5, 75);
        
        // Synchronize the graphics state - now is the the to draw! (more magic)
        Toolkit.getDefaultToolkit().sync();
    }
}
