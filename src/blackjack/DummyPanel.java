/**
 * 
 */
package blackjack;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;


/**
 * @author Alyssa Miller, Jordan King
 *
 */
@SuppressWarnings("serial")
public class DummyPanel extends BPanel implements ActionListener, Runnable {
	
	/**
	 * Allows the drawing on a thread.
	 */
    private Thread animator;
	
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
	 * The buttons
	 */
	Button hitButton, exitButton, standButton, handButton;
	
	/**
	 * The game model.
	 */
	GameBoard gameBoard;
	
	
	
	
	
	/**
	 * Constructor, just inits.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public DummyPanel() {
		init();
	}
	
	
	/**
	 * sets up the panel.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void init() {
		
		// get a model
		gameBoard = new GameBoard();

		// set the size of this panel
		setPreferredSize(new Dimension(500, 500));
		
		// Make the buttons!
		hitButton = new Button("Hit");
		exitButton = new Button("Exit");
		standButton = new Button("Stand");
		handButton = new Button("Next Hand");

		
		hitButton.addActionListener(this);
		exitButton.addActionListener(this);
		standButton.addActionListener(this);
		handButton.addActionListener(this);

		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		buttonsPanel.add(hitButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(standButton);
		buttonsPanel.add(handButton);


		// add the button panel
		add(buttonsPanel);

	}
	
	
	/**
	 * Implementing the action listener.
	 * 
	 * @param event the button clicked event
	 * @return none
	 * @since 1.0
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == hitButton)
			
			gameBoard.hit();
		
		else if (event.getSource() == exitButton) {
			
			System.exit(0);
			
		} else if (event.getSource() == standButton) {
			
			gameBoard.stand();
			
		} else if (event.getSource() == handButton) {
			
			gameBoard.playHand();
			
		}
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
    public void paint(Graphics graphicsObject) {
    	
    	// draw all the other stuff (actually erases it!)
        super.paintComponent(graphicsObject);
        
    	// Switch to 2D
        Graphics2D graphicsObject2d = (Graphics2D) graphicsObject;
        
        // make it antialiasing for fun
    	graphicsObject2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
  	          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	graphicsObject2d.setRenderingHint(RenderingHints.KEY_RENDERING,
  	          RenderingHints.VALUE_RENDER_QUALITY);
        
        // draw background MAYBE DOES NOT WORK
        drawBackground(graphicsObject2d);
        
        // draw my stuff
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
    	ArrayList<Card> dealerHand = gameBoard.getDealerHand();
    	
		// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 10));
        graphicsObject2d.setColor(TEXT_COLOR);
    	
    	
    	int index = 0;
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: dealerHand) {
//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);
    		int x = 100 + 100*index;
    		int y = 160;
    		graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
            
            // Draw the cash
       		graphicsObject2d.drawString(card.getCardRank().toString(), x+10, y+10);
       		graphicsObject2d.drawString(card.getCardSuit().toString(), x+10, y+30);

    		index++;
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

		// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 10));
        graphicsObject2d.setColor(TEXT_COLOR);
    	
    	
    	// get the player's hand
    	ArrayList<Card> playerHand = gameBoard.getPlayerHand();
    	
    	// an index for spacing things out
    	int index = 0;
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: playerHand) {
    		
    		// the spacing
    		int x = 100 + 100*index;
    		int y = 350;
    		graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
    		

//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);

            // Draw the card rank and suit
       		graphicsObject2d.drawString(card.getCardRank().toString(), x+10, y+10);
       		graphicsObject2d.drawString(card.getCardSuit().toString(), x+10, y+30);

    		
    		index++;
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
   		graphicsObject2d.drawString(CASH_DISPLAY_STRING + gameBoard.getCash(), 5, 100);
        
   		// Draw the losses
   		graphicsObject2d.drawString(LOSSES_DISPLAY_STRING + gameBoard.getLosses(), 5, 125);
   		
   		// Draw the bet
   		graphicsObject2d.drawString(BET_DISPLAY_STRING + gameBoard.getBet(), 5, 150);
        
        // Synchronize the graphics state - now is the the to draw! (more magic)
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void cycle() {
//
    }


    /**
     * Overridden addNotify() method - this makes the the component on a new thread.
     * We want it displayed on a new thread.
     * Swing calls this method.
     * 
     * @param none
     * @return none
     * @see the parent class?
     * @since 1.0
     */
    @Override
    public void addNotify() {
    	// call the supah
        super.addNotify();

        // create and run the magical artist
        animator = new Thread(this);
        animator.start();
    } 

    
	@Override
	public void run() {
        // The timer that draws
        Timer frameTimer = new Timer(5, new ActionListener() {
        	
        	// inner method for ActionListener for this timer
            public void actionPerformed(ActionEvent timeTick) {
            	
            	cycle();
                // Update the view
                repaint();
                
            }
            
        });
        // Start the timer
        frameTimer.start();		
	}
}
