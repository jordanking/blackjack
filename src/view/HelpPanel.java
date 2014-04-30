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


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;



/**
 * @author Allie Miller
 *
 *Shows how the user can get help for a gambling addiction.
 *Allows the user to go back to the AutoPanel or go back 
 *to the PlayPanel
 */
public class HelpPanel extends BPanel implements ActionListener{

	JButton backButton, playAgainButton, exitButton;
	int totalWins, totalLosses, hoursLost;
	
	public void init() {
		setBackground(Color.white);
				
		DoubleBufferedPanel buttonsPanel = new DoubleBufferedPanel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		//add the buttons to the screen
		buttonsPanel.add(playAgainButton = new JButton("Play Again"));
		buttonsPanel.add(backButton = new JButton("Back"));
		buttonsPanel.add(exitButton = new JButton("Exit"));
		
		//add action listeners to the buttons
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		playAgainButton.addActionListener(this);
		
		Panel instructionsPanel = new Panel();
		BorderLayout borderLayout = new BorderLayout();
		instructionsPanel.setLayout(borderLayout);
		instructionsPanel.setLocation(300, 300);
		
		//create and add the background image
		JLabel background = new JLabel(new ImageIcon("images/helpPanelImage.jpg"));
		instructionsPanel.add(background);		
		background.setVisible(true);
		add(instructionsPanel, BorderLayout.CENTER);
		
		//specify where on the panel the buttons will be
		instructionsPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		//specify grid properties
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
	/**
	 * getInsets()
	 * Returns the Insets for the panel
	 */
 public Insets getInsets()
   {
      return new Insets(0, 0, 0, 0);
   }
	

}
