/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author Alex Post
 *
 */
public class SettingsPanel extends BPanel implements ActionListener{
	// list of strategies for each dealer + player hand combination for the game
	private Strategy strategy; // game strategy 
	Button setStrategyButton; // button that setsGameStrategy
	Button backButton; // button to go back a screen
	Button exitButton; // button to exit
	Panel buttonsPanel; // panel for the button
	String strategyState[] = {"Hit", "Stand"};
	
	/**
	 * 
	 */
	public SettingsPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init(){
		strategy = new Strategy();
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
				
		setLayout(new BorderLayout());
				
		//make input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}
		

		JTable strategy = drawTable();
		JScrollPane scrollPane = new JScrollPane(strategy);
		add(scrollPane);
		// add the button panel
		add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * drawTable()
	 * 
	 * Creates a table of buttons for the user to select a strategy
	 * for every combination of cards.
	 * 
	 * @return strategyOptions The visual table of buttons for the user to choose
	 * 						   their strategy
	 */
	public JTable drawTable(){
		 /*
		  * create all the buttons in the table.  They will all start
		  * out with the text "hit".
		 */
		
		StrategyTableModel model = new StrategyTableModel(); 
		JTable strategy = new JTable(model);
		for (int i = 0; i < 10; i++){
			createOptions(strategy, strategy.getColumnModel().getColumn(i));
		}
		 
		return strategy;
	}
	
	public void initColumnSizes(JTable table){
		StrategyTableModel model = (StrategyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, "Double",
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
        }
	}
	
	public void createOptions(JTable Table, TableColumn column){
		JComboBox strategyOption = new JComboBox();
		strategyOption.addItem("Hit");
		strategyOption.addItem("Stand");
		strategyOption.addItem("Double");
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
	}
	
	/**
	 * initializeInputButtons()
	 * 
	 * A method to create the buttons panel
	 * 
	 * @return panel the panel for the buttons
	 * @throws HeadlessException
	 * @since 1.0
	 */
	private Panel initializeInputButtons() throws HeadlessException {
		
		// a panel for the buttons for fun
		Panel buttonsPanel = new Panel();
		buttonsPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// make buttons
		setStrategyButton = new Button("Set Strategy");
		backButton = new Button("Back");
		exitButton = new Button("Exit");
		
		// add Action Listeners for the buttons
		setStrategyButton.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// add buttons to the panel
		buttonsPanel.add(exitButton);
		buttonsPanel.add(backButton);
		buttonsPanel.add(setStrategyButton);
		
		return buttonsPanel;
		
	}
	
	public void ActionPerformed(ActionEvent event){
		if(event.getSource() == setStrategyButton){
			properties.put("Game Strategy", strategy); // add gameStrategy to properties object
			// go to autoPanel
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.AutoPanel"));
		}
		if(event.getSource() == exitButton) {
			System.exit(0); // exit the application
		}
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.StatsPanel"));
		}
	}
	
	 protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	 
//	 public Strategy getStrategy(Integer playerTotal, Integer dealerShowing){
//		 
//	 }
	 
	
	
}
