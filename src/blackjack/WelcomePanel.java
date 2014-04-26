/**
 * 
 */
package blackjack;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;


/**
 * @author Allie Miller
 *
 */
public class WelcomePanel extends BPanel implements ActionListener{

	JTextArea appInstructions;
	Button getStartedButton, helpButton, exitButton, instructionsButton;
	
	public void init() {
		
		
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		//buttonsPanel.add(helpButton = new Button("Help"));
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		buttonsPanel.add(instructionsButton = new Button("How to Play"));
		
		//helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		instructionsButton.addActionListener(this);
		
		Panel instructionsPanel = new Panel();
		instructionsPanel.setLayout(new GridLayout(2, 1));
		
		instructionsPanel.add(appInstructions = new JTextArea());
		
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
		
		appInstructions.setAlignmentX(CENTER_ALIGNMENT);
		appInstructions.setAlignmentY(CENTER_ALIGNMENT);
		
		
		/**
		 * What does gridBag do guys?
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		
		
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		
		gridBagLayout.setConstraints(instructionsPanel, gridBagConstraints);
		add(instructionsPanel);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		
		gridBagLayout.setConstraints(buttonsPanel, gridBagConstraints);
		add(buttonsPanel);
		
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
	      return new Insets(0, 0, 0, 0);
	   }
}