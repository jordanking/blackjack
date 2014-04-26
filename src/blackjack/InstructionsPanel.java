/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.JTextArea;


/**
 * @author Allie Miller
 *
 */
public class InstructionsPanel extends BPanel implements ActionListener{

	JTextArea appInstructions; //instructions on how to play blackjack
	Button getStartedButton, helpButton, exitButton, backButton; //buttons
	Panel buttonsPanel; //panel to hold the buttons
	
	public void init() {

		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
		
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
		instructionsPanel.add(appInstructions = new JTextArea(), BorderLayout.CENTER);

		
		appInstructions.setEditable(false);
		appInstructions.setText("Instructions go here.");
		appInstructions.setAlignmentX(CENTER_ALIGNMENT);
		appInstructions.setAlignmentY(CENTER_ALIGNMENT);
		
		add(instructionsPanel, BorderLayout.CENTER);
		
	}
	
	/** 
	 * @return panel the panel for the buttons
	 * @throws HeadlessException
	 * @since 1.0
	 */
	private Panel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		//buttonsPanel.add(helpButton = new Button("Help"));
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(backButton = new Button("Back"));
		buttonsPanel.add(getStartedButton = new Button("Get Started"));
		
		//helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		getStartedButton.addActionListener(this);
		backButton.addActionListener(this);
		
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
		if (event.getSource() == backButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.InstructionsPanel"));
		}
	}
	
	 public Insets getInsets()
	   {
	      return new Insets(0, 0, 0, 0);
	   }
}