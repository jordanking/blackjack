/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

/**
 * @author Alex Post
 *
 */
public class StatsPanel extends BPanel implements ActionListener {

	Button backButton;
	JTextArea statistics, title;
	Integer totalWins, cashWon, totalLosses, cashLost, avgHold, totalHold;
	
	/**
	 * 
	 */
	public StatsPanel() {
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param layout
//	 */
//	public StatsPanel(LayoutManager layout) {
//		super(layout);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param isDoubleBuffered
//	 */
//	public StatsPanel(boolean isDoubleBuffered) {
//		super(isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param layout
//	 * @param isDoubleBuffered
//	 */
//	public StatsPanel(LayoutManager layout, boolean isDoubleBuffered) {
//		super(layout, isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}

	public void init(){
		title = new JTextArea();
		statistics = new JTextArea();
		backButton = new Button("Back");
		Panel statsPanel = new Panel();
		
		title.setFont(new Font("Comic Sans", Font.BOLD, 24));
		
		backButton.addActionListener(this);
		
		statsPanel.setLayout(new BorderLayout(100, 50));
		title.setEditable(false);
		statistics.setEditable(false);
		
		calculateValues();
		
		title.setText("Statistics:");
		statistics.setText("Total Wins: " + totalWins + "\nCash Won: " + cashWon + "\nTotal Losses: " + totalLosses +
				"\nCash Lost: " + cashLost + "\nAverage Hold Value: " + avgHold);
		
		statsPanel.add(statistics, BorderLayout.LINE_START);
		statsPanel.add(backButton, BorderLayout.PAGE_END);
		statsPanel.add(title, BorderLayout.PAGE_START);
		
		add(statsPanel);
	}
	
	/**
	 * retrieveValues
	 */
	public void retrieveValues() {
		//totalWins = properties.
		//totalLosses =
	}
	
	/*
	 * This class will eventually get values from the gameboard class.  The methods I have here are made up, but the general
	 * idea is there.
	 */
	
	public void calculateValues(){
//		for (int i = 0; i < holdNumber.size(); i++){
//			totalHold += holdNumber.get(i);
//		}
//		avgHold = totalHold/holdNumber.size();
//		totalWins = board.getWins();
//		totalLosses = board.getLosses();
//		cashWon = board.getCashWon();
//		cashLost = board.getCashLost();
		
	}
	
	public void ActionPerformed(ActionEvent event){
		if(event.getSource() == backButton){
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.StatsPanel"));
		}
	}

}
