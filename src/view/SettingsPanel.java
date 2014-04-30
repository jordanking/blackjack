/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.GameAction;
import controller.Strategy;

/**
 * @author Alex Post
 *
 *Resources: 
 *
 *http://www.javalobby.org/articles/jtable/?source=archives
 *http://stackoverflow.com/questions/11858286/how-to-use-custom-jtable-cell-editor-and-cell-renderer
 *http://stackoverflow.com/questions/11684903/how-to-add-a-drop-down-menu-to-a-jtable-cell
 */
@SuppressWarnings("serial")
public class SettingsPanel extends BPanel implements ActionListener, KeyListener{
	
	private Strategy gameStrategy; 
	Integer betValue;
	static private final Color GREEN_BACKGROUND = new Color(0,102,0); //constant for background color
	JButton setStrategyButton, submitButton, backButton, exitButton, helpButton;

	Panel buttonsPanel; 
	JTextArea title, salaryTitle, betTitle;
	JTextField salaryField, betField;
	JComboBox<String> betComboBox;
	String myWage, betNumber;
	String[] hardTotal = {"17-20", "16", "15", "13-14", "12", "11", "10", "9", "5-8"};
	String[] softTotal = {"A,8-A,9", "A,7", "A,6", "A,4-A,5", "A,2-A,3"};
	String[] pairs = {"A,A", "10,10", "9,9", "8,8", "7,7", 
					  "6,6", "5,5", "4,4", "2,2-3,3"};
	String[] betValueString = {"-", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
	
	
	/**
	 * 
	 */
	public SettingsPanel() {}

	public void init(){
		

		gameStrategy = (Strategy) properties.get("Game Strategy");
		
		setLayout(new BorderLayout());
		
		JPanel tables = new JPanel();
		Panel northPanel = new Panel();
		Panel southPanel = new Panel();
		Panel westPanel = new Panel();
		
		tables = drawTablePanel();
		northPanel = drawNorthPanel();
		southPanel = drawSouthPanel();
		westPanel = drawWestPanel();
		
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(tables, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		showInitMessage();
	}
	
	/**
	 * showInitMessage()
	 * 
	 * This method shows a message before the settings panel actually shows up, telling the user how
	 * to use the page.
	 */
	
	public void showInitMessage(){
		JOptionPane.showMessageDialog(null,"You are about to our interpretation of your"
				+ " strategy.  If you think you are even smarter, you can change the value \n"
				+ " of any cell by clicking on it.  Then you can see a simulation of your"
				+ " choices.", "Strategy", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * showHelpMessage()
	 * 
	 * Shows the popup to help the user use the settings panel
	 */
	
	public void showHelpMessage(){
		JOptionPane.showMessageDialog(null,"The simulation will run this strategy. You can change"
				+ " any of the values by clicking on them.", "Strategy", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * drawWestPanel()
	 * 
	 * Draws the panel holding the player hand icon
	 * 
	 * @return westPanel The panel to be added to the BPanel
	 */
	
	public Panel drawWestPanel(){
		Panel westPanel = new Panel();
		JLabel playerHandText = new JLabel(new ImageIcon("images/PlayerHandText.jpg"));
		
		westPanel.add(playerHandText);
		add(westPanel,BorderLayout.WEST);
		westPanel.setVisible(true);
		
		return westPanel;
	}
	
	/**
	 * drawNorthPanel()
	 * 
	 * This will draw the panel to hold the dealer hand label.
	 * 
	 * @return northPanel This is the panel that will be added to the BPanel
	 */
	
	public Panel drawNorthPanel(){
		Panel northPanel = new Panel();

		// get strategy from properties object
		gameStrategy = (Strategy) properties.get("Game Strategy");
		
		// images for the x and y axis labels of the table
		JLabel dealerHandText = new JLabel(new ImageIcon("images/DealerHandText.jpg"));
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
		
		//set background color to match color of the game table
		setBackground(GREEN_BACKGROUND);
		
		northPanel.add(dealerHandText);
		add(northPanel, BorderLayout.NORTH);
		northPanel.setVisible(true);
		
		return northPanel;
	}
	
	/**
	 * drawTablePanel()
	 * 
	 * This method draws each of the tables to be shown on the page.
	 * 
	 * @return tables This is the panel that holds the three different tables.  This panel will be added
	 * 		   to the BPanel
	 */
	
	public JPanel drawTablePanel(){
		JPanel tables = new JPanel();
		tables.setLayout((LayoutManager) new BoxLayout(tables, BoxLayout.Y_AXIS));
		
		JTable hardStrategy = drawTable(hardTotal);
		JTable softStrategy = drawTable(softTotal);
		JTable pairsStrategy = drawTable(pairs);
			
		hardStrategy.setOpaque(false);
		softStrategy.setOpaque(false);
		pairsStrategy.setOpaque(false);
		
		hardStrategy.setFillsViewportHeight(true);
		softStrategy.setFillsViewportHeight(true);
		pairsStrategy.setFillsViewportHeight(true);
		
		JScrollPane hardScrollPane = new JScrollPane(hardStrategy);
		JScrollPane softScrollPane = new JScrollPane(softStrategy);
		JScrollPane pairsScrollPane = new JScrollPane(pairsStrategy);
		
		hardScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		softScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		pairsScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		
		tables.add(hardScrollPane);
		tables.add(softScrollPane);
		tables.add(pairsScrollPane);
		tables.setBackground(GREEN_BACKGROUND);
		tables.setBorder(BorderFactory.createLineBorder(GREEN_BACKGROUND));
		
		return tables;
	}
	
	/**
	 * This method draws the panel that will be used to hold the buttons at the bottom of the screen.
	 * 
	 * @return southPanel The panel to hold the buttons
	 */
	
	public Panel drawSouthPanel(){
		//Initialize the main panel that will be returned
		Panel southPanel = new Panel();
		//Initialize the panel to hold the strategy and bet buttons/text fields
		Panel salaryPanel = new Panel();
		
		Font font = new Font("Verdana", Font.PLAIN, 14);
		
		//Initialize the buttons panel
		buttonsPanel = new Panel();
		buttonsPanel = initializeInputButtons();
		
		southPanel.setLayout((LayoutManager) new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		
		salaryField = new JTextField(10);
		salaryTitle = new JTextArea("Please Enter Your Hourly Wage: ");
		betTitle = new JTextArea("How much do you want to bet?");
		submitButton = new JButton("Proceed to Simulation");
		
		//Make everything green
		salaryTitle.setBackground(GREEN_BACKGROUND);
		betTitle.setBackground(GREEN_BACKGROUND);
		
		//Make it white so it is visible
		salaryTitle.setFont(font);
		salaryTitle.setForeground(Color.WHITE);
		
		betTitle.setFont(font);
		betTitle.setForeground(Color.WHITE);
		
		salaryPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		submitButton.addActionListener(this);
		salaryField.addActionListener(this);
		submitButton.addKeyListener(this);
		salaryPanel.addKeyListener(this);
		salaryPanel.addKeyListener(this);
		salaryPanel.setFocusable(true);
		submitButton.setFocusable(true);
		
		betComboBox = createBetOptions();
		
		betTitle.setEditable(false);
		salaryTitle.setEditable(false);
		
		//make everything focusable so the keyboard can access it
		submitButton.setFocusable(true);
		salaryField.setFocusable(true);
		betComboBox.setFocusable(true);
		salaryPanel.setFocusable(true);
		setFocusable(true);
		
		salaryPanel.add(salaryTitle);
		salaryPanel.add(salaryField);
		salaryPanel.add(betTitle);
		salaryPanel.add(betComboBox);
		salaryPanel.add(submitButton);
		
		southPanel.add(salaryPanel);
		southPanel.add(buttonsPanel);
		
		return southPanel;
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
		
		Boolean isPair = false;
		if (cards == pairs){
			isPair = true;
		}
		
		StrategyTableModel model = new StrategyTableModel(makeStrategyArray(cards));
		JTable strategy = new JTable(model);
		
		//create gridlines for table
		strategy.setShowGrid(true);
		strategy.setGridColor(GREEN_BACKGROUND);
		
		if (isPair == false){
			for (int i = 0; i < 11; i++){
				createOptions(strategy, strategy.getColumnModel().getColumn(i));
				strategy.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
			}
		}
		else{
			for (int i = 0; i < 11; i++){
				createPairOptions(strategy, strategy.getColumnModel().getColumn(i));
				strategy.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
			}
		}
		
		strategy.setRowSelectionAllowed(false);
		 
		return strategy;
	}
	
	
	/**
	 * Populates the specific strategyArray with the correct values.  Allows for multiple tables to be
	 * made because it is possible to pass a specific number of rows.
	 * 
	 * @param  cards Takes a string array of cards to use as the number and label for each of the rows
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
				  * stand, or double.  They will be initialized with the game strategy passed in from playpanel
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
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 10; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, "SURRENDER",
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
        }
        column.setPreferredWidth(Math.max(headerWidth, cellWidth));
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
		strategyOption.addItem(GameAction.DOUBLE);
		strategyOption.addItem(GameAction.SURRENDER);
		
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		CustomRenderer renderer = new CustomRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
		Table.setDefaultRenderer(Object.class, renderer);
	}
	
	/**
	 * This method creates the combo box for the pairs table. The only difference between this method and
	 * the createOptions() method is that this adds an option for splits.
	 * 
	 * @param Table Takes a JTable object for which to add the combo box.
	 * @param column Takes a column object for which column to focus on at a time.
	 */
	
	public void createPairOptions(JTable Table, TableColumn column){
		JComboBox<GameAction> strategyOption = new JComboBox<GameAction>();
		strategyOption.addItem(GameAction.HIT);
		strategyOption.addItem(GameAction.STAND);
		strategyOption.addItem(GameAction.DOUBLE);
		strategyOption.addItem(GameAction.SURRENDER);
		strategyOption.addItem(GameAction.SPLIT);
		
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		CustomRenderer renderer = new CustomRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
		Table.setDefaultRenderer(Object.class, renderer);
	}
	
	public JComboBox<String> createBetOptions(){
		JComboBox<String> comboBox = new JComboBox<String>(betValueString);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
		
		return comboBox;
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
		helpButton = new JButton("Help");
		backButton = new JButton("Back");
		exitButton = new JButton("Exit");
		
		// add Action Listeners for the buttons
		helpButton.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// add buttons to the panel
		buttonsPanel.add(exitButton);
		buttonsPanel.add(backButton);
		buttonsPanel.add(helpButton);
		
		return buttonsPanel;
		
	}
	
	/**
	 * actionPerformed()
	 * 
	 * Gives all of the buttons functionality.
	 */
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == exitButton) {
			System.out.println("exit");
			System.exit(1); 
		}
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.StatsPanel"));
		}
		
		if(event.getSource() == salaryField){
			myWage = salaryField.getText();
			properties.put("Salary", myWage);
			salaryField.removeAll();
		}
		
		/*
		 * checks to see if the bet field or the salary field is empty.  If they are not empty, it submits the
		 * values into the properties and proceeds to the auto panel.
		 */
		
		if(event.getSource() == submitButton){
			if(salaryField.getText().trim().isEmpty() || betNumber == "-"){
				JOptionPane.showMessageDialog(null,"Please enter both values.","Blackjack",JOptionPane.OK_OPTION);
			}
			else{

				properties.put("Game Strategy", gameStrategy);
				myWage = salaryField.getText();

				betValue = Integer.parseInt(betNumber);
				properties.put("Wage", myWage);
				properties.put("Bet Value", betValue);
				System.out.println(properties.get("Salary") + " " + properties.get("Bet Value"));
				panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.AutoPanel"));
			}
		}
	
		if(event.getSource() == betComboBox){
			JComboBox<String> cb = (JComboBox<String>) event.getSource();
			betNumber = (String) cb.getSelectedItem();
		}
		
		if(event.getSource() == helpButton){
			showHelpMessage();
		}
	}
	
	/**
	 * keyPressed()
	 * 
	 * Allows the buttons on the main panel to be accessed with the keyboard.
	 */
	
	public void keyPressed(KeyEvent event){
		if(event.getKeyCode() == KeyEvent.VK_ENTER){

			if(event.getSource() == submitButton){
				submitButton.doClick();
			}else if( event.getSource() == backButton){
				backButton.doClick();
			}else if(event.getSource() == exitButton){
				exitButton.doClick();
			}else if(event.getSource() == setStrategyButton){
				setStrategyButton.doClick();
			}
		}
	}
	
	public void keyTyped(KeyEvent event){}
	
	public void keyReleased(KeyEvent event){} 
	
	/**
	 * StrategyTableModel
	 * 
	 * This is a model for creating the table.  Adds functionality to the table and
	 * accessible methods.  Also makes the table editable.
	 * 
	 * @author alexpost
	 *
	 */
	
	public class StrategyTableModel extends AbstractTableModel{

			/**
			 * String array to hold the different values of the dealer face up card
			 */
			String[] dealerShowing ={"", "A","2", "3", "4", "5", "6", 
					   			      "7", "8", "9", "10"};
			
			/**
			 * 2D array holding the contents of each cell in the table.  Set equal to the
			 * array passed in in the constructor
			 */
			Object[][] strategyArray;
			
			/**
			 * Constructor for StrategyTableModel.
			 * 
			 * @param cardsArray Takes a 2D array of a strategy table to give that
			 * 					 table functionality.
			 */
			
			StrategyTableModel(Object[][] cardsArray){
				strategyArray = cardsArray;
			}
			
			public int getRowCount() {
				return strategyArray.length;
			}

			@Override
			public int getColumnCount() {
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
			
			/**
			 * isCellEditable
			 * 
			 * Makes it possible to edit the contents of the table.  Does not allow the first
			 * column to be edited because it contains labels.
			 */
			
			public boolean isCellEditable(int row, int col) {
		        if (col == 0){
		        	return false;
		        }
		        else{
		        	return true;
		        }
		    }
			
			/**
			 * setValueAt
			 * 
			 * This allows us to edit the values at each row/column pair in the 2D array.
			 * Every time the coordinate is updated, that value is sent to the strategy object
			 * to keep the strategy current.
			 * 
			 * @param value This holds the value of the object at strategyArray[row][column]
			 * @param row The specific row the value is in
			 * @param col The specific column the value is in
			 */
			
			public void setValueAt(Object value, int row, int col) {
				strategyArray[row][col] = (GameAction) value;
				fireTableCellUpdated(row, col);
				String playerHand = strategyArray[row][0].toString();
				GameAction desiredAction = (GameAction) value;
		        
		        gameStrategy.setGameActionForHands(playerHand, col, desiredAction);
		    }
		}
	
	/**
	 * CustomRenderer()
	 * 
	 * This is a class to enable each cell of the strategy table to change colors as the text changes.
	 * 
	 * @author alexpost
	 *
	 */
	
	class CustomRenderer extends DefaultTableCellRenderer {

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	       
	        if(table.getValueAt(row, column).toString() == "HIT"){
	        	cellComponent.setBackground(Color.GREEN);
	        } else if(table.getValueAt(row, column).toString() == "STAND"){
	        	cellComponent.setBackground(Color.RED);
	        } else if(table.getValueAt(row, column).toString() == "DOUBLE"){
	        	cellComponent.setBackground(Color.CYAN);
	      	} else if(table.getValueAt(row, column).toString() == "SPLIT"){
	       		cellComponent.setBackground(Color.YELLOW);
	       	} else if(table.getValueAt(row, column).toString() == "SURRENDER"){
	       		cellComponent.setBackground(Color.WHITE);
	       	}else{
	       		cellComponent.setBackground(Color.LIGHT_GRAY);
	       	}
	        
	        return cellComponent;
	    }
	}
}