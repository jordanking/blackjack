/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controller.Strategy;


/**
 * @author Allie Miller
 * 
 * WelcomePanel is a Panel that welcomes the player to the app,
 * gives a brief purpose of the application, and let's the user
 * either quit, move to the PlayPanel, or to the InstructionPanel
 *
 */
public class WelcomePanel extends BPanel implements ActionListener{

	JTextArea appInstructions;
	Button getStartedButton, helpButton, exitButton, instructionsButton;
	Panel buttonsPanel;
	

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

		//make panel for the background
		Panel backgroundPanel = new Panel();

		backgroundPanel.setLayout(new BorderLayout());
		
		//create and add the background image
		JLabel background = new JLabel(new ImageIcon("images/welcomePageImage.png"));
		backgroundPanel.add(background);
		
		background.setVisible(true);
		add(backgroundPanel, BorderLayout.CENTER);
		
	
	}

	/**
	 * initializeInputButtons()
	 * 
	 * initializes buttons
	 * @return
	 * @throws HeadlessException
	 */
	private Panel initializeInputButtons() throws HeadlessException {

		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));

		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		buttonsPanel.add(instructionsButton = new Button("How to Play"));


		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		instructionsButton.addActionListener(this);

		return buttonsPanel;

	}

	/**
	 * actionPerformed()
	 * Listens for the click events for each button and adds the correct
	 * panel accordingly, or exits
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.exit(0);
		} 
		if (event.getSource() == getStartedButton)
		{
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.PlayPanel"));
		}
		if (event.getSource() == instructionsButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.InstructionsPanel"));
		}
	}

	 public Insets getInsets()
	   {
	      return new Insets(5, 5, 5, 5);
	   }
}