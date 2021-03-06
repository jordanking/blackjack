/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * @author Allie Miller
 *
 *InstructionsPanel gives the instructions for how to play
 *blackjack. Allows the user to go on to play (PlayPanel),
 *exit, or go back to the WelcomePanel
 *
 */
public class InstructionsPanel extends BPanel implements ActionListener{

	JTextArea appInstructions; //instructions on how to play blackjack
	Button getStartedButton, exitButton, backButton; //buttons
	Panel buttonsPanel; //panel to hold the buttons
	
	/**
	 * init()
	 * initializes the visuals for the panel
	 */
	public void init() {

		
		// set the size of this panel
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
		
		Panel instructionsPanel = new Panel();
		
		instructionsPanel.setLayout(new BorderLayout());
		
		//create and add the background image
		JLabel background = new JLabel(new ImageIcon("images/instructions2.jpg"));
		instructionsPanel.add(background);
		background.setVisible(true);
		add(instructionsPanel, BorderLayout.CENTER);
		
	}
	
	/** 
	 * initializeInputButtons()
	 * 
	 * initializes buttons
	 * @return panel the panel for the buttons
	 * @throws HeadlessException
	 * @since 1.0
	 */
	private Panel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		//add the buttons to the panel
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(backButton = new Button("Back"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		
		//add action listeners
		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		backButton.addActionListener(this);
		
		return buttonsPanel;
		
	}
	
	/**
	 * actionPerformed()
	 * Listens for the click events for each button and adds/removes the correct
	 * panel accordingly, or exits
	 * @param event ActionEvent object
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.exit(0);
		} 
		if (event.getSource() == getStartedButton)
		{
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.PlayPanel"));
		}
		if (event.getSource() == backButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.InstructionsPanel"));
		}
	}
	
	/**
	 * getInsets()
	 * returns the margins for the panel
	 * @return Insets
	 */
	 public Insets getInsets()
	   {
	      return new Insets(0, 0, 0, 0);
	   }
}