/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * @author Allie Miller
 *
 *Shows how the user can get help for a gambling addiction.
 *Allows the user to go back to the AutoPanel or go back 
 *to the PlayPanel
 */
public class HelpPanel extends BPanel implements ActionListener{

	JTextArea helpInstructions;
	JButton backButton, playAgainButton, exitButton;
	int totalWins, totalLosses, hoursLost;
	
	public void init() {
		setBackground(Color.white);
		
//		totalWins = (int) properties.get("Total Wins");
//		totalLosses = (int) properties.get("Total Losses");
//		hoursLost = (int) properties.get("Hours Lost");
		
		
		DoubleBufferedPanel buttonsPanel = new DoubleBufferedPanel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		buttonsPanel.add(playAgainButton = new JButton("Play Again"));
		buttonsPanel.add(backButton = new JButton("Back"));
		buttonsPanel.add(exitButton = new JButton("Exit"));
		
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		playAgainButton.addActionListener(this);
		
		Panel instructionsPanel = new Panel();
		//GridLayout gridLayout = new GridLayout(2,1);
		BorderLayout borderLayout = new BorderLayout();
		instructionsPanel.setLayout(borderLayout);
		instructionsPanel.setLocation(300, 300);
		
		//create and add the background image
		JLabel background = new JLabel(new ImageIcon("images/helpPanelImage.png"));
		instructionsPanel.add(background);
				
		background.setVisible(true);
		add(instructionsPanel, BorderLayout.CENTER);
		
//		JLabel winsLabel = new JLabel("Wins: " + totalWins);
//		JLabel lossesLabel = new JLabel("Losses: " + totalLosses);
//		JLabel hoursLabel = new JLabel("Hours Lost: " + hoursLost);
//		
//		instructionsPanel.add(winsLabel);
//		instructionsPanel.add(lossesLabel);
//		instructionsPanel.add(hoursLabel);
		
		
		/*
		instructionsPanel.add(helpInstructions = new JTextArea(), BorderLayout.CENTER);
		
		
		helpInstructions.setEditable(false);
		helpInstructions.setText("Congratulations! \n \nYou have successfully " +
				"made your first step to safe gambling. \n \n" +
				"UNDERSTANDING THE PROBLEM \n \n" +
				"The first step to gambling addiction recovery is to understand \n" +
				"the problem. Not only can gambling addiction lead to excessive \n" +
				"losses in time and money, but it can also lead to significant \n" +
				"strains on relationships and emotion. \n \n" +
				"YOU MAY HAVE A GAMBLING ADDICTION IF YOU: \n \n" +
				"- Lie to others about gambling \n" +
				"- Cannot control your gambling habits \n" +
				"- Gamble regardless of whether or not you have the money \n" +
				"- Have worried loved ones \n \n" +
				"GETTING HELP: \n \n" +
				"- Gamblers Anonymous: A 12-step recovery program with the help of a sponsor. \n" +
				"- Cognitive-behavioral Therapy: Teaching yourself to control the need to \n" +
				"gamble with the help of a therapist. \n \n" +
				"More Resources: \n \n" +
				"http://www.helpguide.org/mental/gambling_addiction.php \n" +
				"http://www.gamblersanonymous.org/ga/ \n" +
				"http://www.ncpgambling.org/i4a/pages/index.cfm?pageid=4332 \n");
		
		helpInstructions.setAlignmentX(CENTER_ALIGNMENT);
		helpInstructions.setAlignmentY(CENTER_ALIGNMENT);
		*/
		
		//instructionsPanel.add(buttonsPanel, BorderLayout.AFTER_LAST_LINE);
		instructionsPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
		gridBagConstraints.gridy = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		
		gridBagLayout.setConstraints(instructionsPanel, gridBagConstraints);
		add(instructionsPanel);
		
	}

	/**
	 * actionPerformed()
	 * Listens for the click events for each button and adds the correct
	 * panel accordingly, or exits
	 */
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == exitButton) {
			System.exit(0);
		} else if (event.getSource() == playAgainButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.PlayPanel"));
		} else if (event.getSource() == backButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.HelpPanel"));
		}
	}
	
 public Insets getInsets()
   {
      return new Insets(0, 0, 0, 0);
   }
	

}
