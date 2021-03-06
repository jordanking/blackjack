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
 * WelcomePanel is a Panel that welcomes the player to the app,
 * gives a brief purpose of the application, and let's the user
 * either quit, move to the PlayPanel, or to the InstructionPanel
 *
 */
public class WelcomePanel extends BPanel implements ActionListener{

	// Declare GUI components
	JTextArea appInstructions;
	Button getStartedButton, helpButton, exitButton, instructionsButton;
	Panel buttonsPanel;
	

	/**
	 * init()
	 * 
	 * Initializes WelcomePanel when panel is started.
	 */
	public void init() {
		
		// set the size of this panel
		setPreferredSize(new Dimension(1000, 800));
		
		// set Layout
		setLayout(new BorderLayout());
		    	
		// make the input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {

			e.printStackTrace();
		}

		// add the button panel
		add(buttonsPanel, BorderLayout.SOUTH);

		// make panel for the background
		Panel backgroundPanel = new Panel();
		
		backgroundPanel.setLayout(new BorderLayout());
		
		//create and add the background image
		JLabel background = new JLabel(new ImageIcon("images/welcomePageImage2.jpg"));
		backgroundPanel.add(background);
		
		// set background to visible and center
		background.setVisible(true);
		add(backgroundPanel, BorderLayout.CENTER);
		
	
	}

	/**
	 * initializeInputButtons()
	 * 
	 * Initializes buttons
	 * @return buttonsPanel
	 * @throws HeadlessException
	 */
	private Panel initializeInputButtons() throws HeadlessException {

		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// add buttons to panel
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		buttonsPanel.add(instructionsButton = new Button("How to Play"));

		// add action listeners
		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		instructionsButton.addActionListener(this);
		
		// return panel
		return buttonsPanel;

	}

	/**
	 * actionPerformed()
	 * 
	 * Listens for the click events for each button and adds the correct
	 * panel accordingly, or exits.
	 */
	public void actionPerformed(ActionEvent event) {
		
		// if exitbutton, exit
		if (event.getSource() == exitButton) {
			System.exit(0);
		} 
		
		// if getstarted button, play panel
		if (event.getSource() == getStartedButton)
		{
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.PlayPanel"));
		}
		
		// if instructionsbutton, add instructions panel
		if (event.getSource() == instructionsButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.InstructionsPanel"));
		}
	}
	
	/**
	 * getInsets()
	 * 
	 * Declares insets for the panel.
	 */
	 public Insets getInsets()
	   {
	      return new Insets(5, 5, 5, 5);
	   }
}