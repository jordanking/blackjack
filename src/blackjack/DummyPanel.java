/**
 * 
 */
package blackjack;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author alyssamiller
 *
 */
public class DummyPanel extends JPanel implements ActionListener {

	Button backButton, exitButton, helpButton;
	/**
	 * 
	 */
	public void init() {
		
		
		backButton = new Button("Back");
		exitButton = new Button("Exit");
		helpButton = new Button("To help");
		
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		helpButton.addActionListener(this);
		
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		buttonsPanel.add(backButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(helpButton);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		
		
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		
		gridBagLayout.setConstraints(buttonsPanel, gridBagConstraints);
		add(buttonsPanel);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton)
			
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.DummyPanel"));
		else
			if (event.getSource() == exitButton)
			{
				System.exit(0);
				//panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.HelpPanel"));
			}
			else
				if (event.getSource() == helpButton) {
					panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.HelpPanel"));
				}
	}

}
