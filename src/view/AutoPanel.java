package view;

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

import controller.GameAction;
import controller.GameBoard;
import controller.Strategy;
import model.Card;
import model.GameState;
import model.Rank;
import model.Suit;

/**
 * AutoPanel 
 * 
 * Play panel class for the Blackjack simulator. 
 * Runs through specified iterations automatically.
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
	 * Default bet value for simulation.
	 */
	private Integer betValue = 50;
	
	/**
	 * Hours of pay lost.
	 */
	private int hoursLost = 0;
	
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
	static private final int TOTAL_HANDS_TO_PLAY = 10000;
    
	/**
	 * A constant for the text color.
	 */
	static private final Color TEXT_COLOR = Color.white;
	
	/**
	 * A constant for the bet display text.
	 */
	static private final String BET_DISPLAY_STRING = "Bet: ";
	
	/**
	 * A constant for the hand display text.
	 */
	static private final String HAND_DISPLAY_STRING = "Hand Number: ";
	
	/**
	 * A constant for the cash display text.
	 */
	static private final String CASH_DISPLAY_STRING = "Cash: ";
	
	/**
	 * A constant for the losses display text.
	 */
	static private final String LOSSES_DISPLAY_STRING = "Losses: ";
	
	/**
	 * A constant for the losses display text.
	 */
	static private final String PERCENT_DISPLAY_STRING = "Average Percent Lost Per Hand: %";
	
	/**
	 * variable for the hourglass Image
	 */
	private BufferedImage hourglassImage;
	
	/**
	 * Stores card images.
	 */
	private HashMap<String, BufferedImage> cardImagesMap;

	
	/**
	 * DoubleBufferedPanel for the buttons.
	 */
	DoubleBufferedPanel buttonsPanel;
	
	/**
	 * The buttons.
	 */
	JButton pauseButton, backButton, nextButton, helpButton, exitButton;
	
	/**
	 * The game model.
	 */
	GameBoard gameBoard;
	
	/**
	 * The strategy that is finalized
	 * after the player has played through
	 * the initial play panel.
	 */
	Strategy strategy;
	
	
	/**
	 * AutoPanel()
	 * 
	 * Default constructor.
	 */
	public AutoPanel() {
		
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
		strategy = (Strategy) properties.get("Game Strategy");
		betValue = (Integer) properties.get("Bet Value");
				
		// load Images
		loadImages();

		// set the size of this panel and layout
		setPreferredSize(new Dimension(1000, 800));
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
					// print error message
					System.out.println("Couldn't create dealer card image.");
					error.printStackTrace();
				}

			}
		}

		// load the deck image
		try {
			cardImage = ImageIO.read(new File("images/deck.jpg"));
			cardImagesMap.put("deck", cardImage);

		} catch (IOException error) {
			System.out.println("Couldn't create deckImage.");
			error.printStackTrace();
		}

	}
	
	
	/**
	 * initializeInputButtons()
	 * 
	 * A method to create the buttons panel
	 * 
	 * @return panel - the panel for the buttons
	 * @throws HeadlessException
	 * @since 1.0
	 */
	private DoubleBufferedPanel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons, set layout
		DoubleBufferedPanel buttonsPanel = new DoubleBufferedPanel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// Make the buttons!
		exitButton = new JButton("Exit");
		backButton = new JButton("Back");
		helpButton = new JButton("Instructions");
		nextButton = new JButton("Next");
		pauseButton = new JButton("Pause");
		
		// Add listeners to buttons.
		exitButton.addActionListener(this);
		backButton.addActionListener(this);
		helpButton.addActionListener(this);
		nextButton.addActionListener(this);
		pauseButton.addActionListener(this);
		
		// Add buttons to panel.
		buttonsPanel.add(exitButton);
		buttonsPanel.add(backButton);
		buttonsPanel.add(helpButton);
		buttonsPanel.add(nextButton);
		buttonsPanel.add(pauseButton);
		
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
		
		// pause simulation and 
		// change text to resume
		case ("Pause"):
			simulationPaused = true;
			pauseButton.setText("Resume");
			break;
		
		// resume simulation and
		// change text to pause
		case ("Resume"):
			simulationPaused = false;
			pauseButton.setText("Pause");
			break;
			
		// remove this panel
		// and proceed to previous panel
		case ("Back"):
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.AutoPanel"));
			break;
		
		// proceed to help panel
		case ("Next"):
			storeProperties();
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.HelpPanel"));
			break;
		
		// proceed to instructions panel
		case ("Instructions"):
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.InstructionsPanel"));
			break;
		
		// exit program
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
    }
    
    /**
     * calculateLostWages()
     * 
     * Calculates how many hours of pay
     * the player lost.
     * 
     * @return timeSpentOnGame
     */
    private double calculateLostWages(){
    	
    	// calculate how much the player makes per hour from actual job
    	double playerWage = 0;
		
		// retrieve playerWage from properties object
		try {
			
			playerWage = Double.parseDouble( (String) properties.get("Wage"));
			
		} catch (NumberFormatException e) {
			
			// print error message
			System.out.println("Couldn't get wage.");
			e.printStackTrace();
			
		}
    	
    	// Default wage to 10 if nonpositive
    	if (playerWage <= 0) {
    		playerWage = 10;
    	}
    	
    	//get player's losses from the game
    	int playersLosses = gameBoard.getLosses();
    	
    	// calculate how many hours of pay player lost
    	double hoursOfPayLost = playersLosses/playerWage;
    	
    	// return hours lost
    	return hoursOfPayLost;
    }
    
    
    
    /**
     * drawLosses()
     * 
     * Draws the losses.
     * 
     * @param graphicsObject2d 
     * @return none
     */
    private void drawLosses(Graphics2D graphicsObject2d){
    	
    	// calculate lost wages
    	Double lostWages = calculateLostWages();

    	int money = (int) Math.ceil(lostWages);
    	graphicsObject2d.drawString("You lost " + Integer.toString(money) + " hours of pay!", 300, 700);

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
    	
    	// index for for loop
    	int index = 0;
    	
    	// iterate through all cards of the player and draw them on the board
    	for (Card card: dealerHand) {
    		
    		//grab the card name
    		String cardName = card.getCardRank().toString() + "-" + card.getCardSuit().toString();
        	BufferedImage cardImage = cardImagesMap.get(cardName);
        	
        	// get coords
    		int x = 310 + 100*index;
    		int y = 160;
    		
    		// draw card
    		graphicsObject2d.drawImage(cardImage,x,y,80,120,this);
    		
    		// increment index
    		index++;
		}
    	
    	// Set the font and color
        graphicsObject2d.setFont(new Font("Times", Font.BOLD, 20));
        graphicsObject2d.setColor(TEXT_COLOR);
        
        //Draw the number of points
   		graphicsObject2d.drawString("Dealer's Hand Value: " + gameBoard.getDealerHandValue(), 20, 205);
        
        // Synchronize the graphics state (more magic)
        Toolkit.getDefaultToolkit().sync();
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
    		
    		// draw card
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
    	int x = 910;
    	int y = 160;
    	
    	// graphicsObject2d.drawRoundRect(x, y, 80, 120, 1, 1);
    	graphicsObject2d.drawImage(deckImage,x,y,80,120,null);

    	// Synchronize the graphics state (more magic)
    	Toolkit.getDefaultToolkit().sync();
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
   		
   		// Draw the hand number
   		graphicsObject2d.drawString(HAND_DISPLAY_STRING + gameBoard.getHandNumber(), 5, 175);
   		
   		// Draw the percent of money lost
   		Double losses = Double.valueOf(gameBoard.getLosses());
   		Double winLossCount = Double.valueOf(gameBoard.getTotalHandLosses() + 
   				gameBoard.getTotalHandWins() + gameBoard.getTotalHandTies());
   		Double totalBet = Double.valueOf(winLossCount * betValue);
   		Double percent = (losses/totalBet)*100;


   		try {
			graphicsObject2d.drawString(PERCENT_DISPLAY_STRING + percent.toString().substring(0, 3), 5, 75);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
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
    	
    	// set bar size
    	int barWidth = 600;
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
       	// store total wins and total losses
    	properties.put("Total Wins", gameBoard.getTotalHandWins());
    	properties.put("Total Losses", gameBoard.getTotalHandLosses());
    	properties.put("Hours Lost", hoursLost);
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
    		
    		// deal out hand
    		gameBoard.deal();
    		
    		// desired action based off strategy
    		GameAction desiredAction;
    		
    		// while hand is still in play
    		while (gameBoard.getMainHandState() != GameState.END
    				|| gameBoard.getSplitHandState() != GameState.END) {
    			
    			// if the split hand is not ended, grab the split action
    			if (gameBoard.getSplitHandState() != GameState.END
    					|| gameBoard.getSplitHandState() != GameState.RESOLVED
    					|| gameBoard.getSplitHandState() != GameState.DOUBLE) {
    				
    				// get desired action
    				desiredAction = getStrategyAction(true);
    			}
    			else {
    				// get desired action
    				desiredAction = getStrategyAction(false);
    			}
    			
    			// if no action (i.e. 21), assume stand
    			if (desiredAction == null) {
    				desiredAction = GameAction.STAND;
    			}
    			
    			// evaluate desired action
    			switch (desiredAction) {
    			
    				case HIT:
    					// hit split if split hand is in valid state
    					if (gameBoard.getSplitHandState() != GameState.END
    							&& gameBoard.getSplitHandState() != GameState.RESOLVED
    	    					&& gameBoard.getSplitHandState() != GameState.DOUBLE) {
    						gameBoard.hitSplit();
    					}
    					// otherwise hit on the main hand
    					else {
    						gameBoard.hit();
    					}
    					break;
 
    				case STAND:
    					// stand split if split hand is in valid state
    					if (gameBoard.getSplitHandState() != GameState.END
    					&& gameBoard.getSplitHandState() != GameState.RESOLVED
    					&& gameBoard.getSplitHandState() != GameState.DOUBLE) {
    						gameBoard.standSplit();
    					}
    					// otherwise stand on main hand
    					else {
    						gameBoard.stand();
    					}
    					break;
    					
    				case DOUBLE:
    					
    					// hit if you can't double down
    					if (gameBoard.getMainHandState() != GameState.DEAL ||
    					gameBoard.getMainHandState() != GameState.SPLIT) {
    						gameBoard.hit();
    					}
    					
    					// otherwise double down on split hand
    					if (gameBoard.getSplitHandState() != GameState.END) {
    						gameBoard.doubleDownSplit();
    					}
    					
    					// or double down on main hand
    					else {
    						gameBoard.doubleDown();
    					}
    					break;
    					
    				case SPLIT:
    					// if in deal state, you can split
    					if (gameBoard.getMainHandState() == GameState.DEAL) {
    						gameBoard.split();
    					} 
    					// otherwise,
    					else {
    						// hitsplit if less than 16
    						// or standsplit if greater than 16
    						if (gameBoard.getSplitHandState() != GameState.END
    								&& gameBoard.getSplitHandState() != GameState.RESOLVED
    								&& gameBoard.getSplitHandState() != GameState.DOUBLE) {
    							if(gameBoard.getPlayerHandValueSplit() < 16){
    								gameBoard.hitSplit();
    							}
    							else{
    								gameBoard.standSplit();
    							}
    						} 
    						// otherwise hit if less than 16 on main hand
    						// or stand if greater than 16
    						else {

    							if(gameBoard.getPlayerHandValue() < 16){
    								gameBoard.hit();
    							}
    							else{
    								gameBoard.stand();
    							}
    						}
    					}
    					break;
    					
    				case SURRENDER:
    					// surrender in deal state
    					if (gameBoard.getMainHandState() == GameState.DEAL) {
    						gameBoard.surrender();
    					}
    					else {
    						// otherwise stand on split hand
    						if (gameBoard.getSplitHandState() != GameState.END
    								&& gameBoard.getSplitHandState() != GameState.RESOLVED
    								&& gameBoard.getSplitHandState() != GameState.DOUBLE) {
    							
    								gameBoard.standSplit();
    						} 
    						// or stand on main hand
    						else {
    								gameBoard.stand();
    						}
    					}
    					break;
    				default:
    					break;
    			}
    		}
    	}
    }
    
    /**
	 * getStrategyAction()
	 * 
	 * Gets the strategy action based on what GameAction the
	 * player should make for each player hand/dealer face-up
	 * card combination experienced by the player.
	 * 
	 * 
	 * @return recommendedAction - action based off strategy
	 */
	public GameAction getStrategyAction(boolean isBottomHand) {
		
		// String value of player hand
		String playerHand = null;	
		
		// get dealer face up card
		int dealerFaceUpCard = gameBoard.getDealerHand().get(0).getCardRank().getCardPoints();
		
		// declare player variables
		Card playerCardOne;
		Card playerCardTwo;
		int playerHandSize;
		
		// return stand if player has 21
		if (gameBoard.getPlayerHandValue() == 21 || gameBoard.getPlayerHandValueSplit() == 21) {
			return GameAction.STAND;
		}
		
		// if the player is looking at his bottom hand,
		// set values accordingly
		if (gameBoard.handHasSplit() && isBottomHand){
			
			// set the size of the hand to the size of the split hand
			playerHandSize = gameBoard.getPlayerHandSplit().size();
			
			// set first two cards to the split hand that is
			// being acted upon so that we can later determine
			// if there is a soft total
			playerCardOne = gameBoard.getPlayerHandSplit().get(0);
			playerCardTwo = gameBoard.getPlayerHandSplit().get(1);
		} 
		
		else {
			
			// otherwise set values based off main hand
			playerCardOne = gameBoard.getPlayerHand().get(0);
			playerCardTwo = gameBoard.getPlayerHand().get(1);	
			playerHandSize= gameBoard.getPlayerHand().size();
			
		}
		
		
		// checks to see if the player has two cards
		// and they are a pair
		if ((playerHandSize == 2) && (gameBoard.getPlayer().hasPair(false))) {
			
			// gets the point value of one of the cards of the pair
			int playerCardValue = playerCardOne.getCardRank().getCardPoints();
			
			// checks to see if its an Ace
			if (playerCardOne.getCardRank() == Rank.ACE)
				playerHand = "A,A";
				
			//sets playerHand to the correct bin for the strategy
			switch(playerCardValue) {
				case 2: 
				case 3:
					playerHand = "2,2-3,3";
					break;
				case 4: 
					playerHand = "4,4";
					break;
				case 5:
					playerHand = "5,5";
					break;
				case 6: 
					playerHand = "6,6";
					break;
				case 7:
					playerHand = "7,7";
					break;
				case 8: 
					playerHand = "8,8";
					break;
				case 9:
					playerHand = "9,9";
					break;
				case 10: 
					playerHand = "10,10";
					break;
				default:
					break;
			}
		} 
		else
			// checks to see if one card is an Ace for a soft total if
			// there are only two cards in the hand
			if ((playerHandSize == 2) && ((playerCardOne.getCardRank() == Rank.ACE) ||
					(playerCardTwo.getCardRank() == Rank.ACE))) {
				
				// the player card that's not an Ace
				Card playerCardNotAce; 
				
				// determines which card is not an Ace and sets it equal 
				// to playerCardNotAce
				if (playerCardOne.getCardRank() == Rank.ACE) {
					playerCardNotAce = playerCardTwo;
				} else {
					playerCardNotAce = playerCardOne;
				}
				
				// checks the value of the other card and sets 
				// the correct string value for the playerHand
				// for the strategy
				switch (playerCardNotAce.getCardRank().getCardPoints()) {
					case 2:
					case 3:
						playerHand = "A,2-A,3";
						break;
					case 4:
					case 5:
						playerHand = "A,4-A,5";
						break;
					case 6:
						playerHand = "A,6";
						break;
					case 7:
						playerHand = "A,7";
						break;
					case 8:
					case 9:
						playerHand = "A,8-A,9";
						break;
					default:
						break;
				}
			} else {
				// determine the total value of the player hand
				// and set the playerHand string to that value
				// for the strategy
				switch (gameBoard.getPlayerHandValue()) {
					case 5:
					case 6:
					case 7:
					case 8:
						playerHand = "5-8";
						break;
					case 9:
						playerHand = "9";
						break;
					case 10:
						playerHand = "10";
						break;
					case 11:
						playerHand = "11";
						break;
					case 12: 
						playerHand = "12";
						break;
					case 13:
					case 14:
						playerHand = "13-14";
						break;
					case 15:
						playerHand = "15";
						break;
					case 16:
						playerHand = "16";
						break;
					case 17:
					case 18:
					case 19:
					case 20:
						playerHand = "17-20";
						break;
					default:
						break;
				}
			}	
		
		// return the strategy (GameAction) for this playerHand/dealerFaceUpCard combination
		return strategy.getGameActionForHands(playerHand, dealerFaceUpCard);
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
     * @since 1.0
     */
    @Override
    public void addNotify() {
    
        super.addNotify();

        // create thread
        animator = new Thread(this);
        animator.start();
    } 

    
    @Override
	public void run() {
        // The timer that draws
        Timer frameTimer = new Timer(5, new ActionListener() {
        	
        	// inner method for ActionListener for this timer
            public void actionPerformed(ActionEvent timeTick) {
            	
            	// cycle through
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
