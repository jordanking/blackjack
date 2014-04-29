/**
 * 
 */
package view;

import java.awt.BorderLayout;
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
public class HelpPanel extends BPanel implements ActionListener{

	JTextArea helpInstructions;
	Button playAgainButton, exitButton;
	
	public void init() {
		setBackground(Color.white);
		
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		buttonsPanel.add(exitButton = new Button("Exit"));
		buttonsPanel.add(playAgainButton = new Button("Play Again"));
		
		exitButton.addActionListener(this);
		playAgainButton.addActionListener(this);
		
		Panel instructionsPanel = new Panel();
		//GridLayout gridLayout = new GridLayout(2,1);
		BorderLayout borderLayout = new BorderLayout();
		instructionsPanel.setLayout(borderLayout);
		instructionsPanel.setLocation(300, 300);
		
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
		
		instructionsPanel.add(buttonsPanel, BorderLayout.AFTER_LAST_LINE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
		gridBagConstraints.gridy = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		
		gridBagLayout.setConstraints(instructionsPanel, gridBagConstraints);
		add(instructionsPanel);
		/**
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		
		gridBagLayout.setConstraints(buttonsPanel, gridBagConstraints);
		add(buttonsPanel);
		*/
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == exitButton) {
			System.exit(0);
//			panelManager.actionPerformed(new ActionEvent(this, JPanelManager.REMOVE, "view.HelpPanel"));
		} else if (event.getSource() == playAgainButton) {
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.DummyPanel"));
		}
	}
	
 public Insets getInsets()
   {
      return new Insets(0, 0, 0, 0);
   }
	/**
	 * @param layout
	 */
	/**
	public HelpPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isDoubleBuffered
	 */
	/**
	public HelpPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	/**
	public HelpPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	*/

}
