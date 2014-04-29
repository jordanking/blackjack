/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Button;
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

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 */
@SuppressWarnings("serial")
public class SettingsPanel extends BPanel implements ActionListener, KeyListener{
	
	private Strategy gameStrategy; 
	Integer betValue;

	JButton setStrategyButton, submitButton;
	Button backButton; 
	Button exitButton; 
	Panel buttonsPanel; 
	JTextArea title, salaryTitle, betTitle;
	JTextField salaryField, betField;
	JComboBox<String> betComboBox;
	String mySalary, betNumber;
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
		
		// get strategy from properties object
		gameStrategy = (Strategy) properties.get("Game Strategy");
		
		// images for the x and y axis labels of the table
		JLabel dealerHandText = new JLabel(new ImageIcon("images/DealerHandText.jpg"));
		JLabel playerHandText = new JLabel(new ImageIcon("images/PlayerHandText.jpg"));
		
		// set jtext size
		playerHandText.setSize(50, 300);
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
		
		// set title and title font
		title = new JTextArea("Settings");
		title.setFont(new Font("Times", Font.BOLD, 20));
		title.setEditable(false);
		
		// create salary field and bet options
		salaryField = new JTextField(10);
		betComboBox = createBetOptions();
		
		// create jtextareas for salary and bet
		salaryTitle = new JTextArea("Please Enter Your Hourly Wage: ");
		betTitle = new JTextArea("How much do you want to bet?");
		
		// set editable false
		betTitle.setEditable(false);
		salaryTitle.setEditable(false);


		submitButton = new JButton("Submit Values");
		submitButton.addActionListener(this);
		salaryField.addActionListener(this);
		submitButton.addKeyListener(this);
		salaryField.setFocusable(true);
		betComboBox.setFocusable(true);
		setFocusable(true);
		
		
		
		// add action listeners to submit button
		// and salary field
		submitButton.addActionListener(this);
		salaryField.addActionListener(this);
		
		// add key listener
		submitButton.addKeyListener(this);
		
		// set border layout
		setLayout(new BorderLayout());
		
		// panels for the axes labels
		DoubleBufferedPanel xAxisPanel = new DoubleBufferedPanel();
		DoubleBufferedPanel yAxisPanel = new DoubleBufferedPanel();
		
		// add the x and y axes labels and set them visible
		yAxisPanel.add(playerHandText);
		add(yAxisPanel,BorderLayout.WEST);
		yAxisPanel.setVisible(true);
		
		xAxisPanel.add(dealerHandText);
		add(xAxisPanel, BorderLayout.NORTH);
		xAxisPanel.setVisible(true);
				
		//make input buttons
		try {
			buttonsPanel = initializeInputButtons();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}
		
		Panel tables = new Panel();
		Panel salaryPanel = new Panel();
		salaryPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		tables.setLayout((LayoutManager) new BoxLayout(tables, BoxLayout.Y_AXIS));
		
		JTable hardStrategy = drawTable(hardTotal);
		JTable softStrategy = drawTable(softTotal);
		JTable pairsStrategy = drawTable(pairs);
		JScrollPane hardScrollPane = new JScrollPane(hardStrategy);
		JScrollPane softScrollPane = new JScrollPane(softStrategy);
		JScrollPane pairsScrollPane = new JScrollPane(pairsStrategy);
		
		
		salaryPanel.add(salaryTitle);
		salaryPanel.add(salaryField);
		salaryPanel.add(betTitle);
		salaryPanel.add(betComboBox);
		salaryPanel.add(submitButton);
		submitButton.setFocusable(true);

		tables.add(hardScrollPane);
		tables.add(softScrollPane);
		tables.add(pairsScrollPane);
		//tables.add(salaryPanel);
		add(tables, BorderLayout.CENTER);
		// add the button panel
		
		//panel for the south border that holds the salary and bet info (salaryPanel)
		//as well as the buttonsPanel
		Panel southBorderPanel = new Panel();
		southBorderPanel.setLayout((LayoutManager) new BoxLayout(southBorderPanel, BoxLayout.Y_AXIS));
		
		southBorderPanel.add(salaryPanel);
		southBorderPanel.add(buttonsPanel);
		add(southBorderPanel, BorderLayout.SOUTH);

		
		
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
		
		if (isPair == false){
			for (int i = 0; i < 10; i++){
				createOptions(strategy, strategy.getColumnModel().getColumn(i));
			}
		}
		else{
			for (int i = 0; i < 10; i++){
				createPairOptions(strategy, strategy.getColumnModel().getColumn(i));
			}
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
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
	}
	
	public void createPairOptions(JTable Table, TableColumn column){
		JComboBox<GameAction> strategyOption = new JComboBox<GameAction>();
		strategyOption.addItem(GameAction.HIT);
		strategyOption.addItem(GameAction.STAND);
		strategyOption.addItem(GameAction.DOUBLE);
		strategyOption.addItem(GameAction.SURRENDER);
		strategyOption.addItem(GameAction.SPLIT);
		
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
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
		setStrategyButton = new JButton("Set Strategy");
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
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == setStrategyButton){
			if(properties.get("Salary") == null){
				JOptionPane.showMessageDialog(null,"Please enter your salary.","Error",JOptionPane.OK_OPTION);
			}else{
				properties.put("Game Strategy", gameStrategy); // add gameStrategy to properties object
				System.out.println(gameStrategy);
				properties.put("Salary", mySalary);
				// go to autoPanel
				panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.AutoPanel"));
			}
		}
		if(event.getSource() == exitButton) {
			System.out.println("exit");
			System.exit(1); 
		}
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.StatsPanel"));
		}
		
		if(event.getSource() == salaryField){
			mySalary = salaryField.getText();
			properties.put("Salary", mySalary);
			salaryField.removeAll();
		}
		
		if(event.getSource() == submitButton){
			if(salaryField.getText().trim().isEmpty() || betNumber == "-"){
				JOptionPane.showMessageDialog(null,"Have you entered both values?","Blackjack",JOptionPane.OK_OPTION);
			}
			else{
				mySalary = salaryField.getText();
				betValue = Integer.parseInt(betNumber);
				properties.put("Salary", mySalary);
				properties.put("Bet Value", betValue);
				System.out.println(properties.get("Salary") + " " + properties.get("Bet Value"));
				JOptionPane.showMessageDialog(null, "Values Entered.", "Blackjack", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	
		
		if(event.getSource() == betComboBox){
			JComboBox<String> cb = (JComboBox<String>) event.getSource();
			betNumber = (String) cb.getSelectedItem();
		}
	}
	
	public void keyPressed(KeyEvent event){
		if(event.getKeyCode() == KeyEvent.VK_ENTER){

			submitButton.doClick();

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
	}


