/**
 * 
 */
package blackjack;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author Alex Post
 *
 */
@SuppressWarnings("serial")
public class SettingsPanel extends BPanel implements ActionListener{
	// list of strategies for each dealer + player hand combination for the game
	private Strategy gameStrategy; // game strategy 
	Button setStrategyButton; // button that setsGameStrategy
	Button backButton; // button to go back a screen
	Button exitButton; // button to exit
	Panel buttonsPanel; // panel for the button
	String[] hardTotal = {"17-20", "16", "15", "13-14", "12", "11", "10", "9", "5-8"};
	String[] softTotal = {"A,8-A,9", "A,7", "A,6", "A,4-A,5", "A,2-A,3"};
	String[] pairs = {"A,A", "10,10", "9,9", "8,8", "7,7", 
					  "6,6", "5,5", "4,4", "2,2-3,3"};
	
	
	/**
	 * 
	 */
	public SettingsPanel() {
		// TODO Auto-generated constructor stub
	}

	public void init(){
		//***************needs to get strategy from play panel
		//gameStrategy = (Strategy) properties.get("Game Strategy");
		gameStrategy = new Strategy();
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
				
		setLayout(new BorderLayout());
				
		//make input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}
		
		Panel panel = new Panel();
		panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTable hardStrategy = drawTable(hardTotal);
		JTable softStrategy = drawTable(softTotal);
		JTable pairsStrategy = drawTable(pairs);
		JScrollPane hardScrollPane = new JScrollPane(hardStrategy);
		JScrollPane softScrollPane = new JScrollPane(softStrategy);
		JScrollPane pairsScrollPane = new JScrollPane(pairsStrategy);
		
		add(buttonsPanel, BorderLayout.SOUTH);
		panel.add(hardScrollPane);
		panel.add(softScrollPane);
		panel.add(pairsScrollPane);
		add(panel);
		// add the button panel
		
		
	}
	
	/**
	 * drawTable()
	 * 
	 * Creates a table of buttons for the user to select a strategy
	 * for every combination of cards.
	 * 
	 * @return strategy The visual table of buttons for the user to choose
	 * 						   their strategy
	 */
	public JTable drawTable(String[] cards){
		 /*
		  * draws the table for the hard totals. 
		  */
		
		StrategyTableModel model = new StrategyTableModel(makeStrategyArray(cards));
		JTable strategy = new JTable(model);
		
		for (int i = 0; i < 10; i++){
			createOptions(strategy, strategy.getColumnModel().getColumn(i));
		}
		
		strategy.setRowSelectionAllowed(false);
		 
		return strategy;
	}
	
	
	/**
	 * Populates the strategyArray with the players options
	 * 
	 * @return strategyArray The populated strategyArray
	 */
	
	public Object[][] makeStrategyArray(String[] cards){
		Object[][] strategyArray = new Object[cards.length][11];
		Map<Integer, GameAction> dealerGameActionCombination = 
				new HashMap<Integer, GameAction>();
		GameAction desiredAction = null;
		
		for (int i = 0; i < 11; i++){
			 for(int j = 0; j < cards.length; j++){
				 
				 // the first column will be the string of ranges for the player total
				 
				 if ( i == 0){
					 strategyArray[j][i] = cards[j];
				 }
				 
				 /*	
				  * the rest of the table will be populated with options for the player to hit, 
				  * stand, or double.  They will be initialized as "hit"
				  */
				 else{
				 	dealerGameActionCombination = gameStrategy.getStrategyTable().get(cards[j]);
				 	desiredAction = dealerGameActionCombination.get(i);
				 	strategyArray[j][i] = (Object) desiredAction.toString();
				 }
			 }
		 }
		return strategyArray;
	}
	
	/**
	 * Sets preferred column sizes to the size of the largest word.
	 * @param table
	 */
	
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
	
	/**
	 * createOptions
	 * 
	 * Creates a ComboBox of gameActions for the strategy table.
	 * 
	 * @param Table the table we are adding the ComboBoxes to
	 * @param column the column in the table we are adding the ComboBoxes to
	 */
	
	public void createOptions(JTable Table, TableColumn column){
		JComboBox<GameAction> strategyOption = new JComboBox<GameAction>();
		strategyOption.addItem(GameAction.HIT);
		strategyOption.addItem(GameAction.STAND);
		strategyOption.addItem(GameAction.DOUBLE_HIT);
		strategyOption.addItem(GameAction.DOUBLE_STAND);
		strategyOption.addItem(GameAction.SURRENDER);
		strategyOption.addItem(GameAction.SPLIT);
		
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
			properties.put("Game Strategy", gameStrategy); // add gameStrategy to properties object
			// go to autoPanel
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "blackjack.AutoPanel"));
		}
		if(event.getSource() == exitButton) {
			System.out.println("exit");
			System.exit(1); // exit the application
		}
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "blackjack.StatsPanel"));
		}
	}
	
	public class StrategyTableModel extends AbstractTableModel{

			String[] dealerShowing ={"", "A","2", "3", "4", "5", "6", 
					   			      "7", "8", "9", "10"};
			
			Object[] longValues = {"Double"};
			Object[][] strategyArray;
			
			StrategyTableModel(Object[][] cardsArray){
				strategyArray = cardsArray;
			}
			
			public int getRowCount() {
				// TODO Auto-generated method stub
				return strategyArray.length;
			}

			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return dealerShowing.length;
			}
			
			@Override
			public String getColumnName(int column){
				return dealerShowing[column];
			}
			
			
			@Override
			public Object getValueAt(int row, int column){
				return strategyArray[row][column];
			}
			
			@Override
			public Class getColumnClass(int c) {
			      return getValueAt(0, c).getClass();
			}
			
			public boolean isCellEditable(int row, int col) {
		        if (col == 0){
		        	return false;
		        }
		        else{
		        	return true;
		        }
		    }
			
			public void setValueAt(Object value, int row, int col) {
				strategyArray[row][col] = (GameAction) value;
				fireTableCellUpdated(row, col);
				String playerHand = strategyArray[row][0].toString();
				GameAction desiredAction = (GameAction) value;
		        
		        gameStrategy.setGameActionForHands(playerHand, col, desiredAction);
		        System.out.println(gameStrategy.getGameActionForHands(playerHand, col));
		    }
		}
}


