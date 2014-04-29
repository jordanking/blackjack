/**
 * 
 */
package blackjack;

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


/**
 * @author Allie Miller
 *
 */
public class WelcomePanel extends BPanel implements ActionListener{

	JTextArea appInstructions;
	Button getStartedButton, helpButton, exitButton, instructionsButton;
	Panel buttonsPanel;
	

	public void init() {
		
		// set the size of this panel

		//setPreferredSize(new Dimension(800, 800));
		setPreferredSize(new Dimension(1000, 800));

		Strategy gameStrategy = new Strategy();
		properties.put("Game Strategy", gameStrategy);

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
		JLabel background = new JLabel(new ImageIcon("images/welcomePageImage.png"));
		instructionsPanel.add(background);
		//background.setPreferredSize(new Dimension(100,100));
		background.setVisible(true);
		add(instructionsPanel, BorderLayout.CENTER);
		//instructionsPanel.add(appInstructions = new JTextArea(), BorderLayout.CENTER);

/*
		appInstructions.setEditable(false);
		appInstructions.setText("Welcome! \n \n" +
				" Whether you are new to gambling or are a well-seasoned gambler, \n " +
				"this application will teach you how to safely gamble. \n " +
				"Gambling can be a fun, harmless acivity when approached in the \n " +
				"right way. \n \n" +
				" This application will show you that less is more when it comes to gambling. \n " +
				"In other words, this application will teach you to quit while you are ahead \n " +
				"and steer you away from gambling addiction. \n \n" +
				" To begin, you will manually play up to eight games of BlackJack \n " +
				"with the option of changing your bet, and changing your \"holding\" \n " +
				"and \"hitting\" values for each game. You will next be given the \n " +
				"statistics of the eight games played, such as your average holding value, wins, and losses." +
				"\n You will then be instructed to either use these values as your \"strategy\" for a specified" +
				"\n amount of games automatically played. The results from these games will be displayed \n " +
				"so that you may see the monetary outcome of an excess amount of gambling.");

		//add(instructionsPanel, BorderLayout.NORTH);
		 * 
		 */
	
	}

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

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.exit(0);
		} 
		if (event.getSource() == getStartedButton)
		{
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.PlayPanel"));
		}
		if (event.getSource() == instructionsButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.InstructionsPanel"));
		}
	}

	 public Insets getInsets()
	   {
	      return new Insets(5, 5, 5, 5);
	   }
}