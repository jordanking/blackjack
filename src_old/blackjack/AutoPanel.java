/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * AutoPanel 
 * 
 * Play panel class for the Blackjack simulator.
 * 
 * @author Kevin Tian
 * Modified version of DummyPanel written up by Jordan King and Allie Miller.
 */

@SuppressWarnings("serial")
public class AutoPanel extends BPanel implements ActionListener, Runnable {
	
	/**
	 * Indicates if simulation is still running.
	 */
	private boolean simulationOn = true;
	
	/**
	 * Stand value for simulation.
	 */
	private Integer standValue = 17;
	
	/**
	 * Bet value for simulation.
	 */
	private Integer betValue = 50;
	
	/**
	 * Allows the drawing on a thread.
	 */
    private Thread animator;
	
	/**
	 * A constant for the total hands to play in this panel.
	 */
	static private final int TOTAL_HANDS_TO_PLAY = 1000;
	
	/**
	 * A constant for the background color
	 */
	static private final Color GREEN_BACKGROUND = new Color(0,102,0);
    
	/**
	 * A constant for the text color.
	 */
	static private final Color TEXT_COLOR = Color.white;
	
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
	 * variable for the Starbucks Image
	 */
	private BufferedImage starbucksImage;
	
	/**
	 * variable for the card image
	 */
	private BufferedImage cardImage;
	
	/**
	 * variable for the deck image
	 */
	private BufferedImage deckImage;
	
	/**
	 * Panel for the buttons
	 */
	Panel buttonsPanel;
	
	/**
	 * The buttons
	 */
	Button hitButton, standButton, handButton, exitButton, skipButton, betButton1, betButton2,
		betButton3;
	
	/**
	 * The game model.
	 */
	GameBoard gameBoard;
	
	
	/**
	 * AutoPanel()
	 * 
	 * Default constructor.
	 */
	public AutoPanel() {
		// initialize standValue and betValue
		
		standValue = 17;

		betValue = 100;

		betValue = 50;

		
		/*
		standValue = (Integer) properties.get("standValue");
		betValue = (Integer) properties.get("betValue");*/
		
		
	}
	
	/**
	 * init()
	 * 
	 * Initializes AutoPanel GUI.
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	public void init() {
		
		// gets model
		gameBoard = new GameBoard();

		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
		
		setLayout(new BorderLayout());
		
		// make the input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}

		// add the button panel
		add(buttonsPanel, BorderLayout.SOUTH);
		
		// play hand in game
		gameBoard.playHand();

	}
	
	/**
	 * initializeInputButtons()
	 * 
	 * A method to create the buttons panel
	 * 
	 * @return panel the panel for the buttons
	 * @throws HeadlessException
	 * @since 1.0
	 */
	private Panel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// Make the buttons!
		hitButton = new Button("Hit");
		standButton = new Button("Stand");
		handButton = new Button("Next Hand");
		exitButton = new Button("Exit");
		skipButton = new Button("Skip");
		betButton1 = new Button("Bet 10");
		betButton2 = new Button("Bet 50");
		betButton3 = new Button("Bet 100");

		// Add listeners to buttons.
		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		handButton.addActionListener(this);
		exitButton.addActionListener(this);
		skipButton.addActionListener(this);
		betButton1.addActionListener(this);
		betButton2.addActionListener(this);
		betButton3.addActionListener(this);

		// Add buttons to panel.
		buttonsPanel.add(hitButton);
		buttonsPanel.add(standButton);
		buttonsPanel.add(handButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(skipButton);
		buttonsPanel.add(betButton1);
		buttonsPanel.add(betButton2);
		buttonsPanel.add(betButton3);

		return buttonsPanel;
	}
	
	/**
	 * actionPerformed()
	 * 
	 * Implementing the action listener. 
	 * 
	 * @param event - the button clicked event
	 * @return none
	 * @since 1.0
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		/*
		if (event.getSource() == hitButton)
			
			gameBoard.hit();*/
		
		if (event.getSource() == exitButton) {
			
			System.exit(0);
			
		} /*else if (event.getSource() == standButton) {
			
			gameBoard.stand();
			
		} else if (event.getSource() == handButton) {
			
			gameBoard.playHand();*/
			
		else if (event.getSource() == skipButton) {
			
			skip();
			
		} /*else if (event.getSource() == betButton1) {
			
			gameBoard.setBet(10);
			
		} else if (event.getSource() == betButton2) {
			
			gameBoard.setBet(50);
			
		} else if (event.getSource() == betButton3) {
			
			gameBoard.setBet(100);
			
		}*/
	}
	

	/**
     * paint()
     * 
     * Calls the drawing methods of the blackjack game
     * 
     * @param graphicsObject - the graphicsObject to draw with
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
        
        // make it antialiasing (fuzzy) for fun
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
        
        // Draw the progress bar
        drawProgressBar(graphicsObject2d);
        
        //Draw the losses board
        drawLosses(graphicsObject2d);

        // Explicitly release the memory storing the graphics. Do not wait for garbage collection
        graphicsObject2d.dispose();
    }
    
    /**
     * 
     * @param graphicsObject2d
     */
    private void drawLosses(Graphics2D graphicsObject2d){
    
    	//Set the font and color
    	graphicsObject2d.setColor(TEXT_COLOR);
    	graphicsObject2d.setFont(new Font("Times", Font.BOLD, 30));
    	
    	//Draw message of how much money player lost
   		graphicsObject2d.drawString("You have lost: $" + gameBoard.getLosses(), 400, 500);
   		
   		//create image of Starbucks Cup
    	try {
    		starbucksImage = ImageIO.read(new File("images/starbucksCup.jpg"));
    	} catch (IOException error) {
    		// TODO Auto-generated catch block
    		System.out.println("couldn't create image");
    		error.printStackTrace();
    	}
    	
		//startingXCoord and startingYCoord
		int x = 5;
		int y=500;
				
    	//Draw the Starbucks coffee cups on the screen
   		for (int i=0; i<calculateNumCoffeeCups(); i++){
   			//graphicsObject2d.drawImage(starbucksImage,x,y,null);
   			graphicsObject2d.drawImage(starbucksImage,x,y,100,100,null);
   			//reset the x coordinate for the next coffee cup, the 5 represents padding for the image
   			x+=105;
   		}
    	
   		//release memory storing graphicsObject
   		//do not wait for garbage collection
   		//graphicsObject2d.dispose();
    }
    
    /**
	 * calculateNumCoffeeCups()
	 * calculates how many coffee cups the player could
	 * have bought with the amount of money he lost in playing
	 * @return numOfCoffeeCups
	 */
	public int calculateNumCoffeeCups(){
		
		//how much money player has lost
		int lossesAmount = gameBoard.getLosses();
		
		//price of a Starbucks coffee cup
		int priceOfCup = 2;
		
		//how many cups of coffee the player could have bought with money lost
		int numOfCoffeeCups = lossesAmount/priceOfCup;
		return numOfCoffeeCups;
	}
    
    /**
     * drawBackground()
     * 
     * Draws the background on the game board.
     * 
     * @param graphicsObject2d - the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawBackground(Graphics2D graphicsObject2d) {
    	
    	// draw the background
    	//graphicsObject2d.drawImage(backImageAsset, 0, 0, this);
    	
    	//set the background color
    	setBackground(GREEN_BACKGROUND);
        
        // Synchronize the graphics state - now is the time to draw! (magic)
        Toolkit.getDefaultToolkit().sync();
        
        // DONT DISPOSE OF THE GRAPHICS OBJECT HERE IT'LL SUCK EVERYTHING UP
    }
   
    
    /**
     * drawDealer()
     * 
     * Draws the dealer's hand on the screen.
     * 
     * @param graphicsObject2d - the 2d graphics object we draw with
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
    		int x = 310 + 100*index;
    		int y = 160;
    		
    		//create dealer's card image
    		try {
        		cardImage = ImageIO.read(new File("images/" + card.getCardRank().toString() +
        										"-" + card.getCardSuit().toString() + ".png"));
        	} catch (IOException error) {
        		// TODO Auto-generated catch block
        		System.out.println("couldn't create image");
        		error.printStackTrace();
        	}
    		
    		//graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,null);
            
    		System.out.println(card.getCardRank().toString()+"-"+card.getCardSuit().toString());
    		
    		/*
            // Draw the card's rank and suit
       		graphicsObject2d.drawString(card.getCardRank().toString(), x+10, y+10);
       		graphicsObject2d.drawString(card.getCardSuit().toString(), x+10, y+30);
       		*/
    		
    		index++;
		}
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();
        
        // DONT DISPOSE OF THE GRAPHICS OBJECT HERE ITLL SUCK EVERYTHING UP
    }

    /**
     * drawPlayer()
     * 
     * Draws the player's hand on the screen
     * 
     * @param graphicsObject2d - the 2d graphics object we draw with
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
    		
    		//create player's card image
    		try {
        		cardImage = ImageIO.read(new File("images/" + card.getCardRank().toString() +
        										"-" + card.getCardSuit().toString() + ".png"));
        	} catch (IOException error) {
        		// TODO Auto-generated catch block
        		System.out.println("couldn't create image");
        		error.printStackTrace();
        	}
    		
    		// the spacing
    		int x = 100 + 100*index;
    		int y = 350;
    		//graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,null);
    		

//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);

    		/*
            // Draw the card rank and suit
       		graphicsObject2d.drawString(card.getCardRank().toString(), x+10, y+10);
       		graphicsObject2d.drawString(card.getCardSuit().toString(), x+10, y+30);
    		 */
    		
    		index++;
		}
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();

        // DONT DELETE THE OBJECT
    }
    
    /**
     * drawDeck()
     * 
     * Draws the deck on the screen.
     * 
     * @param graphicsObject2d - the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawDeck(Graphics2D graphicsObject2d) {
    	
    	// graphicsObject2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    	// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 10));
        graphicsObject2d.setColor(TEXT_COLOR);
    	
        //create deck image
        try {
    		deckImage = ImageIO.read(new File("images/deck.jpg"));
    	} catch (IOException error) {
    		// TODO Auto-generated catch block
    		System.out.println("couldn't create deckImage");
    		error.printStackTrace();
    	}
    	
    		// the spacing
    		int x = 700;
    		int y = 160;
    		graphicsObject2d.drawImage(deckImage,x,y,80,120,null);
    		//graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);

//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);

            // Draw the card rank and suit
       		graphicsObject2d.drawString("Deck", x+10, y+10);

        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();

        // DONT DELETE THE OBJECT PLZ
    }
    
    /**
     * drawMeta()
     * 
     * Draws the meta information on the screen.
     * 
     * @param graphicsObject2d
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
    
    /**
     * drawProgressBar()
     * 
     * Draws the progress bar.
     * 
     * @param graphicsObject2d
     * @return none
     * @since 1.0
     */
    private void drawProgressBar(Graphics2D graphicsObject2d) {
		
    	int totalHandsToPlay = TOTAL_HANDS_TO_PLAY;
    	
    	int barWidth = 400;
    	int barHeight = 25;
    	int barX = 200;
    	int barY = 10;
    	
    	// sliceWidth will be fraction
    	double sliceWidth = (double)barWidth/totalHandsToPlay;
    	
    	// draw a border
    	graphicsObject2d.drawRoundRect(barX, barY, barWidth, barHeight, 1, 1);
    	
    	// fill in based on hands played!
    	for (int i = 0; i < gameBoard.getHandNumber(); i++) {
    		// can't fill fractions so
    		// fill 1 from floor of i * sliceWidth
        	graphicsObject2d.fillRect(barX + (int)Math.floor(i*sliceWidth), barY, 1, barHeight);
		}
    	
    	
	}
    
    /**
     * cycle()
     * 
     * Checks to see if hand is above total hands to play.
     * Otherwise, runs through simulations.
     */
    public void cycle() {
    	
    	// if hand reaches total hands to play
    	// turn off simulation
    	if (gameBoard.getHandNumber() > TOTAL_HANDS_TO_PLAY) {
    		simulationOn = false;
		}
    	
    	// if the simulation is still running
    	if (simulationOn) {
    		
    		// play hand
    		// method self-checks to see if hand is already in play
    		gameBoard.playHand();
    		
    		// set bet
    		// method self-checks to see if it's ready to accept bet
    		gameBoard.bet(betValue);
    		
    		// if points is less than stand value, hit
    		// otherwise, stand
    		if (gameBoard.getPlayer().getPoints() < standValue) {
    			gameBoard.hit();
    		}
    		else {
    			gameBoard.stand();
    		}
    	}
    }
    
    /**
     * skip()
     * 
     * Goes to the stats panel.
     * 
     * @param none
     * @return none
     * @see BlackjackApplet
     */
	public void skip() {
		panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.StatsPanel"));
	}


    /**
     * addNotify()
     * 
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


