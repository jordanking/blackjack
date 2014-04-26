/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Alex Post
 *
 */
public class SettingsPanel extends BPanel implements ActionListener{
	// list of strategies for each dealer + player hand combination for the game
	private ArrayList<Strategy> gameStrategy; 
	Button setStrategyButton; // button that setsGameStrategy
	Button backButton; // button to go back a screen
	Button exitButton; // button to exit
	Panel buttonsPanel; // panel for the button
	/**
	 * 
	 */
	public SettingsPanel() {
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param layout
//	 */
//	public SettingsPanel(LayoutManager layout) {
//		super(layout);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param isDoubleBuffered
//	 */
//	public SettingsPanel(boolean isDoubleBuffered) {
//		super(isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param layout
//	 * @param isDoubleBuffered
//	 */
//	public SettingsPanel(LayoutManager layout, boolean isDoubleBuffered) {
//		super(layout, isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
	public void init(){
		gameStrategy = new ArrayList<Strategy>();
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
				
		setLayout(new BorderLayout());
				
		//make input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}

		// add the button panel
		add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	
	

	/**
	 * @return the gameStrategy
	 */
	public ArrayList<Strategy> getGameStrategy() {
		return gameStrategy;
	}

	/**
	 * @param gameStrategy the gameStrategy to set
	 */
	public void setGameStrategy(ArrayList<Strategy> gameStrategy) {
		this.gameStrategy = gameStrategy;
	}

	public void tabbedPane() {
        //super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Manual", null, panel1,
                "Manual Play");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Automatic", null, panel2,
                "Simulation Settings");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
		
		// make buttons
		setStrategyButton = new Button("Set Strategy");
		backButton = new Button("Back");
		exitButton = new Button("Exit");
		
		// add Action Listeners for the buttons
		setStrategyButton.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// add buttons to the panel
		buttonsPanel.add(exitButton);
		buttonsPanel.add(backButton);
		buttonsPanel.add(setStrategyButton);
		
		return buttonsPanel;
		
	}
	
	public void ActionPerformed(ActionEvent event){
		if(event.getSource() == setStrategyButton){
			properties.put("Game Strategy", gameStrategy); // add gameStrategy to properties object
			// go to autoPanel
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.AutoPanel"));
		}
		if(event.getSource() == exitButton) {
			System.exit(0); // exit the application
		}
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.StatsPanel"));
		}
	}
	
	 protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	 
	 /**
	  * setStrategy() 
	  * 
	  * creates a new strategy for a player hand and dealerFaceUpCard combination 
	  * and adds the strategy to the gameStrategy ArrayList
	  * 
	  * @param firstPlayerCard
	  * @param secondPlayerCard
	  * @param dealerFaceUpCard
	  * @param desiredAction
	  */
	 public void setStrategy(Card firstPlayerCard, Card secondPlayerCard, Card dealerFaceUpCard, GameAction desiredAction) {
		 // create new strategy
		 Strategy strategy = new Strategy(firstPlayerCard, secondPlayerCard, dealerFaceUpCard, desiredAction);
		// add to gameStrategy ArrayList
		 gameStrategy.add(strategy);
	 }
	
}
