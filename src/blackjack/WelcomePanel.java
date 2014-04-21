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
 * @author jordan
 *
 */
public class WelcomePanel extends JPanel implements ActionListener{

	JTextArea appInstructions;
	Button getStartedButton, helpButton, exitButton;
	
	public void init() {
		setBackground(Color.white);
		
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		//buttonsPanel.add(helpButton = new Button("Help"));
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		
		//helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		
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
		if (event.getSource() == exitButton)
			System.exit(0);
			//panelManager.actionPerformed(new ActionEvent(this, JPanelManager.REMOVE, "blackjack.WelcomePanel"));
		else
			if (event.getSource() == getStartedButton)
			{
				panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.HelpPanel"));
			}
	}
	
	 public Insets getInsets()
	   {
	      return new Insets(0, 0, 0, 0);
	   }
	/**
	***
	* * @param layout
	* *
	*public WelcomePanel(LayoutManager layout) {
	*	super(layout);
	*	// TODO Auto-generated constructor stub
	*}
*
*	
*	 * @param isDoubleBuffered
*	
*	public WelcomePanel(boolean isDoubleBuffered) {
*		super(isDoubleBuffered);
*		// TODO Auto-generated constructor stub
*	}
*
*	
*	 * @param layout
*	 * @param isDoubleBuffered
*	 
*	public WelcomePanel(LayoutManager layout, boolean isDoubleBuffered) {
*		super(layout, isDoubleBuffered);
*		// TODO Auto-generated constructor stub
*	}
	*/

}
