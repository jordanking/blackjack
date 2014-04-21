/**
 * 
 */
package blackjack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Riya Modi
 *
 */
public class LossesBoard extends JPanel implements Runnable{
	
	private int losses;	//represents the player's monetary losses
	private Thread lossesStats;
	private BufferedImage starbucksImage;
	int starbucksImageWidth;
	int starbucksImageHeight;
	private int panelWidth = 500;
	private int panelHeight = 500;
	int startXCoordImage = 5;	//the x coordinate of where to start drawing coffee cup images
	int startYCoordImage = 75;	//the y coordinate of where to start drawing coffee cup images
	private final int DELAY = 25;

	/**
	 * LossesBoard()
	 * constructor initializes board and creates an image for the starbucks coffee cup
	 */
	public LossesBoard() {
		// TODO Auto-generated constructor stub
		
		setFocusable(true);
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(500,500));
		setDoubleBuffered(true);
		
		//create image of Starbucks Cup
		try {
			starbucksImage = ImageIO.read(new File("starbucksCup.jpg"));
		} catch (IOException error) {
			// TODO Auto-generated catch block
			error.printStackTrace();
		}
		
		//get the height and width of the image of the Starbucks coffee cup
		starbucksImageWidth = starbucksImage.getWidth();
		starbucksImageHeight = starbucksImage.getHeight();
		
	}
	

	/**
	 * @param layout
	 */
	public LossesBoard(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isDoubleBuffered
	 */
	public LossesBoard(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public LossesBoard(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * getLosses()
	 * returns the amount of losses the player has
	 * @return losses 
	 */
	public int getLosses(){
		return losses;
	}
	
	/*
	 * setLosses(int lossAmount)
	 * sets the amount of losses the player has
	 * @param lossAmount
	 */
	public void setLosses(int lossAmount){
		losses = lossAmount;
	}
	
	/*
	 * resetLosses()
	 * reset the losses of the player to zero
	 */
	public void resetLosses(){
		losses = 0;
	}
	
	/*
	 * addLosses(int additionalLosses)
	 * adds the passed in losses amount to the current losses amount
	 * @param additionalLosses
	 */
	public void addLosses(int additionalLosses){
		losses+=additionalLosses;
	}
	
	/*
	 * getPanelWidth()
	 * returns the width of the panel
	 * @return panelWidth
	 */
	public int getPanelWidth(){
		return panelWidth;
	}
	
	/*
	 * getPanelHeight()
	 * returns the height of the panel
	 * @return panelHeight
	 */
	public int getPanelHeight(){
		return panelHeight;
	}
	
	/*
	 * calculateNumCoffeeCups()
	 * calculates how many coffee cups the player could
	 * have bought with the amount of money he lost in playing
	 * @return numOfCoffeeCups
	 */
	public int calculateNumCoffeeCups(){
		
		//how much money player has lost
		int lossesAmount = getLosses();
		
		//price of a Starbucks coffee cup
		int priceOfCup = 2;
		
		//how many cups of coffee the player could have bought with money lost
		int numOfCoffeeCups = lossesAmount/priceOfCup;
		return numOfCoffeeCups;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#addNotify()
	 * starts the second thread (not main's thread)
	 */
	@Override
	public void addNotify(){
		super.addNotify();
		lossesStats = new Thread(this);
		lossesStats.start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * runs the second thread (not main's thread)
	 */
	@Override
	public void run(){
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		//Keep updating GUI
		while (true){
			
			//calls paintComponent() and redraws the components on the board
			repaint();
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			
			if (sleep < 0){
				sleep = 2;
			}
			
			try{
				Thread.sleep(sleep);
			}
			catch (InterruptedException error){
				System.out.println("Interrupted: " + error.getMessage());
			}
			beforeTime = System.currentTimeMillis();
		}
		
	}
	
	/**
	 * paintComponent(Graphics graphicsObject)
	 * takes in a Graphics object
	 * this function is called whenever repaint is called in the run function
	 * displays a message of how much money the player has lost so far
	 * and displays how many coffee cups the player could have bought
	 * @param graphicsObject a Graphics Object
	 */
	@Override
	public void paintComponent(Graphics graphicsObject){
		
		// Set the font and color
        graphicsObject.setFont(new Font("Times", Font.BOLD, 25));
        graphicsObject.setColor(Color.RED);
        
        //Draw message of how much money player lost
   		graphicsObject.drawString("You have lost: $" + getLosses(), 250, 50);
        
   		//Draw the Starbucks coffee cups on the screen
   		for (int i=0; i<calculateNumCoffeeCups(); i++){
   			graphicsObject.drawImage(starbucksImage,startXCoordImage,startYCoordImage,null);
   			//reset the x coordinate for the next coffee cup, the 5 represents padding for the image
   			startXCoordImage+=(starbucksImageWidth + 5);
   		}
   		
   		//release memory storing graphicsObject
   		//do not wait for garbage collection
   		graphicsObject.dispose();
	}

}
