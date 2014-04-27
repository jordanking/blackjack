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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * @author Alex Post
 *
 */
public class SettingsPanel extends BPanel implements ActionListener{
	// list of strategies for each dealer + player hand combination for the game
	private Strategy strategy; // game strategy 
	Button setStrategyButton; // button that setsGameStrategy
	Button backButton; // button to go back a screen
	Button exitButton; // button to exit
	Panel buttonsPanel; // panel for the button
	String strategyState[] = {"Hit", "Stand"};
	
	String[] dealerShowing = {"2", "3", "4", "5", "6", 
			   "7", "8", "9", "10", "A"}; // initialize the column names for the table
	
	JButton[][] strategyArray = null; // initialize the 2d array for the strategy table.
	
	/**
	 * 
	 */
	public SettingsPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init(){
		strategy = new Strategy();
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
				
		setLayout(new BorderLayout());
				
		//make input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}
		
		add(drawTable());

		// add the button panel
		add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * drawTable()
	 * 
	 * Creates a table of buttons for the user to select a strategy
	 * for every combination of cards.
	 * 
	 * @return strategyOptions The visual table of buttons for the user to choose
	 * 						   their strategy
	 */
	public JTable drawTable(){
		 /*
		  * create all the buttons in the table.  They will all start
		  * out with the text "hit".
		  */
		 
		 for (int i = 2; i < 12; i++){
			 for(int j = 21; j > 1; j--){
				 strategyArray[j][i] = new JButton();
				 //Strategy strategy = new Strategy(j, i, strategyState);
				 strategyArray[j][i].setText("Hit");
				 strategyArray[j][i].addActionListener(this);
			 }
		 }
		 
		 JTable strategyOptions = new JTable(strategyArray, dealerShowing);
		 
		 return strategyOptions;
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
			properties.put("Game Strategy", strategy); // add gameStrategy to properties object
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
		for(int i = 0; i < 10; i++){
			for (int j = 21; j > 1; j++){
				if (event.getSource() == strategyArray[j][i]){
					if (strategyArray[j][i].getText() == "Hit"){
						strategyArray[j][i].setText("Stand");
					}else{
						strategyArray[j][i].setText("Hit");
					}
				}
			}
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
	 
//	 public Strategy getStrategy(Integer playerTotal, Integer dealerShowing){
//		 
//	 }
	 
	
	
}
