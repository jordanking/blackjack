/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	 * Turned off when max hands reached.
	 */
	private boolean simulationOn = true;
	
	/**
	 * Indicates if simulation is paused.
	 */
	private boolean simulationPaused = false;
	
	/**
	 * Stand value for simulation.
	 */
	private Integer standValue = 17;
	
	/**
	 * Default bet value for simulation.
	 */
	private Integer betValue = 50;
	
	/**
	 * Allows the drawing on a thread.
	 */
    private Thread animator;
    
	/**
	 * A constant for the background color
	 */
	static private final Color GREEN_BACKGROUND = new Color(0,102,0);
	
	/**
	 * A constant for the total hands to play in this panel.
	 */
	static private final int TOTAL_HANDS_TO_PLAY = 1000;
    
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
	 * variable for card image
	 */
	private HashMap<String, BufferedImage> cardImagesMap;

	
	/**
	 * Panel for the buttons DO NOT CHANGE TO JPANEL
	 */
	ButtonsPanel buttonsPanel;
	
	/**
	 * The buttons
	 */
	JButton pauseButton, backButton, nextButton, helpButton, exitButton;
	
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
				
		// load Images
		loadImages();

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
	 * loadImages()
	 * 
	 * Loads all of the card images
	 * 
	 * @return none
	 * @throws none
	 * @since 1.0
	 */
	private void loadImages() {
		cardImagesMap = new HashMap<String, BufferedImage>();

		BufferedImage cardImage;

		// load all 52 cards
		for (Rank rank: Rank.values()) {
			for (Suit suit: Suit.values()) {

				// construct the card name
				String cardName = rank.toString() + "-" + suit.toString();

				//create card image
				try {
					// load the image
					cardImage = ImageIO.read(new File("images/" + cardName + ".png"));

					// store the image in the map
					cardImagesMap.put(cardName, cardImage);

				} catch (IOException error) {
					System.out.println("couldn't create dealer card image");
					error.printStackTrace();
				}

			}
		}

		// load the deck image
		try {
			cardImage = ImageIO.read(new File("images/deck.jpg"));
			cardImagesMap.put("deck", cardImage);

		} catch (IOException error) {
			System.out.println("couldn't create deckImage");
			error.printStackTrace();
		}

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
	private ButtonsPanel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons for fun
		ButtonsPanel buttonsPanel = new ButtonsPanel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// Make the buttons!

		pauseButton = new JButton("Pause");
		backButton = new JButton("Back");
		nextButton = new JButton("Next");
		helpButton = new JButton("Help");
		exitButton = new JButton("Exit");
		
		// Add listeners to buttons.
		pauseButton.addActionListener(this);
		backButton.addActionListener(this);
		nextButton.addActionListener(this);
		helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// Add buttons to panel.
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(helpButton);
		buttonsPanel.add(exitButton);
		
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
		
		/**
		 * Code from stackoverflow.com. See BlackjackApplet class.
		 * Purpose of this is to use switch statement
		 * instead of if-else combo.
		 */
		
		// get object
		Object object = event.getSource();
		
		// declare variables
		JButton button = null;
		String buttonText = "";
		
		// if the object is an instance of JButton
		// convert to JButton
		if(object instanceof JButton)
			button = (JButton)object;
		
		// if button is not null
		// get button text
		if(button != null)
		    buttonText = button.getText();
		
		// evaluate button text
		// and call methods accordingly
		switch (buttonText) {
		
		// hit top is the same as normal hit
		// call mainhand hit
		case ("Pause"):
			simulationPaused = true;
			pauseButton.setText("Resume");
			break;
			
		case ("Resume"):
			simulationPaused = false;
			pauseButton.setText("Pause");
			break;
			
		case ("Back"):
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.AutoPanel"));
			break;
		
		case ("Next"):
			storeProperties();
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.HelpPanel"));
			break;
		
		case ("Help"):
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.InstructionsPanel"));
			break;
			
		case ("Exit"): 
			System.exit(0);
			break;
		
		default:
			break;
		}
		

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
        drawPlayerSplit(graphicsObject2d);
        drawDeck(graphicsObject2d);
        
        // Draw the meta (like cash, bet, etc...)
        drawMeta(graphicsObject2d);
        
        // Draw the progress bar
        drawProgressBar(graphicsObject2d);
        
        //Draw losses
        drawLosses(graphicsObject2d);

        // Explicitly release the memory storing the graphics. Do not wait for garbage collection
        graphicsObject2d.dispose();
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
     * 
     * Calculates how much time the player spent on the game
     * in terms of work hours
     * 
     * @return timeSpentOnGame
     */
    public int calculateLostHours(){
    	
    	//calculate how much the player makes per hour from actual job
    	int salary = (Integer) properties.get("Salary");
    	int hoursInWorkYear = 2080;
    	int dollarsPerHour = salary/hoursInWorkYear;
    	
    	//get player's losses from the game
    	int playersLosses = gameBoard.getLosses();
    	
    	//calculate how much work time player spent on game
    	int timeSpentOnGame = playersLosses/dollarsPerHour;
 
    	return timeSpentOnGame;
    }
    
    /**
     * 
     * Draws the losses
     * 
     * @param graphicsObject2d
     * @return none
     */
    private void drawLosses(Graphics2D graphicsObject2d){
    	int lostHours = calculateLostHours();
    	graphicsObject2d.drawString("You have lost: " + lostHours + "hours you could have been at work", 400, 600);
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

    	int index = 0;
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: dealerHand) {
    		
    		//grab the card name
    		String cardName = card.getCardRank().toString() + "-" + card.getCardSuit().toString();
        	BufferedImage cardImage = cardImagesMap.get(cardName);
        	
        	// get coords
    		int x = 310 + 100*index;
    		int y = 160;
    		
    		
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,this);

    		index++;
		}
    	
    	//Draw the value of the dealer's hand
    	
    	// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 20));
        graphicsObject2d.setColor(TEXT_COLOR);
        
        //Draw the number of points
   		graphicsObject2d.drawString("Dealer's Hand Value: " + gameBoard.getDealerHandValue(), 20, 205);
        
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
    	
    	// get the player's hand
    	ArrayList<Card> playerHand = gameBoard.getPlayerHand();
    	
    	// an index for spacing things out
    	int index = 0;
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: playerHand) {
    		
    		//grab the card name
    		String cardName = card.getCardRank().toString() + "-" + card.getCardSuit().toString();
        	BufferedImage cardImage = cardImagesMap.get(cardName);
    		    		
    		// the spacing
    		int x = 100 + 100*index;
    		int y = 350;
    		
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,this);

    		
    		index++;
		}
    	
    	//Draw the value of the player's hand
    	
    	// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 20));
        graphicsObject2d.setColor(TEXT_COLOR);
        
        //Draw the number of points
   		graphicsObject2d.drawString("Your Hand Value: " + gameBoard.getPlayerHandValue(), 20, 335);
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();

        // DONT DELETE THE OBJECT
    }
    
    /**
     * drawPlayerSplit()
     * 
     * Draws the player's split hand on the screen
     * 
     * @param graphicsObject2d - the 2d graphics object we draw with
     * @return none
     * @since 1.0
     */
    private void drawPlayerSplit(Graphics2D graphicsObject2d) {
    	
    	// get the player's hand
    	ArrayList<Card> playerHandSplit = gameBoard.getPlayerHandSplit();
    	
    	// an index for spacing things out
    	int index = 0;
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: playerHandSplit) {
    		
    		//grab the card name
    		String cardName = card.getCardRank().toString() + "-" + card.getCardSuit().toString();
        	BufferedImage cardImage = cardImagesMap.get(cardName);
    		    		
    		// the spacing
    		int x = 100 + 100*index;
    		int y = 500;
    		
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,this);

    		
    		index++;
		}
    	
    	//Draw the value of the player's hand if there's a split
    	if (gameBoard.handHasSplit()==true){
    		// Set the font and color
            graphicsObject2d.setFont(new Font("Times", Font.BOLD, 20));
            graphicsObject2d.setColor(TEXT_COLOR);
            
            //Draw the number of points
       		graphicsObject2d.drawString("Your Hand Value: " + gameBoard.getPlayerHandValueSplit(), 20, 485);
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

    	// get the image
    	BufferedImage deckImage = cardImagesMap.get("deck");

    	// the spacing
    	int x = 700;
    	int y = 160;
    	//    		graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
    	graphicsObject2d.drawImage(deckImage,x,y,80,120,null);

    	//    		graphicsObject2d.drawImage(cardImageAsset, card.getxCoordinate(), card.getyCoordinate(), this);

    	// Draw the card rank and suit
    	//       		graphicsObject2d.drawString("Deck", x+10, y+10);

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
   		
   		// Draw the state
   		graphicsObject2d.drawString(gameBoard.getMainHandState().toString(), 5, 75);
        
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
     * storeProperties()
     * 
     * Store properties for next panel.
     */
    private void storeProperties() {
    	properties.put("AverageHold", 17);
    	properties.put("TotalLosses", gameBoard.getTotalLosses());
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
    	if (simulationOn && !simulationPaused) {
    		
    		// play hand
    		// method self-checks to see if hand is already in play
    		gameBoard.playHand();
    		
    		// set bet
    		// method self-checks to see if it's ready to accept bet
    		gameBoard.bet(betValue);
    		
    		gameBoard.deal();
    		
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
            	revalidate();
                repaint();
                
            
            }
            
        });
        // Start the timer
        frameTimer.start();		
	}
}
