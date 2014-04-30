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
 * SettingsPanel
 * 
 * The settings panel shows the user his or her
 * current strategy based off default/user inputs.
 * 
 * The user can then modify specific game actions
 * in the strategy, specify a wage and bet value, and
 * then proceed to simulation.
 * 
 * @author Alex Post
 */
@SuppressWarnings("serial")
public class SettingsPanel extends BPanel implements ActionListener, KeyListener{
	
	// game strategy to be received
	private Strategy gameStrategy; 
	// bet value to be passed
	Integer betValue;
	
	// constant for background color
	static private final Color GREEN_BACKGROUND = new Color(0,102,0);
	
	// declare buttons
	JButton setStrategyButton, submitButton, backButton, exitButton, helpButton;
	
	// declare buttonspanel
	Panel buttonsPanel; 
	
	// declare textareas and textfields
	JTextArea title, salaryTitle, betTitle;
	JTextField salaryField, betField;
	
	// declare combobox
	JComboBox<String> betComboBox;
	
	// wage and betnumber to be passed
	String myWage, betNumber;
	
	// possible string values for row labels in strategy
	String[] hardTotal = {"17-20", "16", "15", "13-14", "12", "11", "10", "9", "5-8"};
	String[] softTotal = {"A,8-A,9", "A,7", "A,6", "A,4-A,5", "A,2-A,3"};
	String[] pairs = {"A,A", "10,10", "9,9", "8,8", "7,7", 
					  "6,6", "5,5", "4,4", "2,2-3,3"};
	
	// possible bet values
	String[] betValueString = {"-", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
	
	
	/**
	 * SettingsPanel()
	 * 
	 * Default constructor.
	 */
	public SettingsPanel() {}
	
	/**
	 * init()
	 * 
	 * Initializes setting panel when started up.
	 */
	public void init(){
		
		// get game strategy from PlayPanel
		gameStrategy = (Strategy) properties.get("Game Strategy");
		
		// set layout
		setLayout(new BorderLayout());
		
		// create tables and panels
		JPanel tables = new JPanel();
		Panel northPanel = new Panel();
		Panel southPanel = new Panel();
		Panel westPanel = new Panel();
		
		// draw tables and panels
		tables = drawTablePanel();
		northPanel = drawNorthPanel();
		southPanel = drawSouthPanel();
		westPanel = drawWestPanel();
		
		// add tables and panels to SettingsPanel
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(tables, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		// show initial message
		showInitMessage();
	}
	
	/**
	 * showInitMessage()
	 * 
	 * Shows initial message to explain
	 * purpose of SettingsPanel.
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
	 * Shows help message to further help
	 * confused user.
	 */
	public void showHelpMessage(){
		JOptionPane.showMessageDialog(null,"The simulation will run this strategy. You can change"
				+ " any of the values by clicking on them.", "Strategy", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * drawWestPanel()
	 * 
	 * Draws playerhandtext label for row labels.
	 * 
	 * @return westPanel - panel on left of screen
	 */
	public Panel drawWestPanel(){
		
		// create new panel
		Panel westPanel = new Panel();
		
		// create jlabel using imageicon
		JLabel playerHandText = new JLabel(new ImageIcon("images/PlayerHandText.jpg"));
		
		// add text to panel
		westPanel.add(playerHandText);
		
		// add panel to SettingsPanel and set visible
		add(westPanel,BorderLayout.WEST);
		westPanel.setVisible(true);
		
		return westPanel;
	}
	
	/**
	 * drawNorthPanel()
	 * 
	 * Draws labels for columns on top.
	 * 
	 * @return northPanel - panel on top of screen
	 */
	public Panel drawNorthPanel(){
		
		// create new panel
		Panel northPanel = new Panel();

		// get strategy from properties object
		gameStrategy = (Strategy) properties.get("Game Strategy");
		
		// images for the x and y axis labels of the table
		JLabel dealerHandText = new JLabel(new ImageIcon("images/DealerHandText.jpg"));
		JLabel playerHandText = new JLabel(new ImageIcon("images/PlayerHandText.jpg"));
		
		// set jtext size
		playerHandText.setSize(50, 300);
		
		// set the size of this panel
		setPreferredSize(new Dimension(800, 800));
		
		// set background color to match color of the game table
		setBackground(GREEN_BACKGROUND);
		
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

		// create submitbutton
		submitButton = new JButton("Submit Values");
		
		// add action listeners to button and field
		submitButton.addActionListener(this);
		salaryField.addActionListener(this);
		
		// add key listener to submit button
		submitButton.addKeyListener(this);
		
		// set focusable true
		salaryField.setFocusable(true);
		betComboBox.setFocusable(true);
		setFocusable(true);
		
		// set border layout
		setLayout(new BorderLayout());
		
		// panels for the axes labels
		DoubleBufferedPanel xAxisPanel = new DoubleBufferedPanel();
		DoubleBufferedPanel yAxisPanel = new DoubleBufferedPanel();
		
		// add the x and y axes labels
		// add them to the SettingsPanel
		// and set them visible
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

		// add text to northpanel, 
		// add to settings panel
		// set visible
		northPanel.add(dealerHandText);
		add(northPanel, BorderLayout.NORTH);
		northPanel.setVisible(true);
		
		return northPanel;
	}
	
	/**
	 * drawTablePanel()
	 * 
	 * Draws the table for our strategy display.
	 * 
	 * @return tables - panel storing table of strategies
	 */
	public JPanel drawTablePanel(){
		
		// new tables panel, set layout
		JPanel tables = new JPanel();
		tables.setLayout((LayoutManager) new BoxLayout(tables, BoxLayout.Y_AXIS));
		
		// create tables to show different strategies
		JTable hardStrategy = drawTable(hardTotal);
		JTable softStrategy = drawTable(softTotal);
		JTable pairsStrategy = drawTable(pairs);
		
		// set opaque false
		hardStrategy.setOpaque(false);
		softStrategy.setOpaque(false);
		pairsStrategy.setOpaque(false);
		
		// sets fillsviewportheight true to match panel
		hardStrategy.setFillsViewportHeight(true);
		softStrategy.setFillsViewportHeight(true);
		pairsStrategy.setFillsViewportHeight(true);
		
		// provides scrollability
		JScrollPane hardScrollPane = new JScrollPane(hardStrategy);
		JScrollPane softScrollPane = new JScrollPane(softStrategy);
		JScrollPane pairsScrollPane = new JScrollPane(pairsStrategy);
		
		// set background to green
		hardScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		softScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		pairsScrollPane.getViewport().setBackground(GREEN_BACKGROUND);
		
		// add scrolling
		tables.add(hardScrollPane);
		tables.add(softScrollPane);
		tables.add(pairsScrollPane);
		
		// set background to green and border to green
		tables.setBackground(GREEN_BACKGROUND);
		tables.setBorder(BorderFactory.createLineBorder(GREEN_BACKGROUND));
		
		return tables;
	}
	
	/**
	 * drawSouthPanel()
	 * 
	 * Draws panel containing components at the bottom.
	 * 
	 * @return
	 */
	public Panel drawSouthPanel(){
		
		// create panels to be added
		Panel southPanel = new Panel();
		Panel salaryPanel = new Panel();
		
		// create font
		Font font = new Font("Verdana", Font.PLAIN, 14);
		
		// construct panel and add input buttons
		buttonsPanel = new Panel();
		buttonsPanel = initializeInputButtons();
		
		// set layout to boxlayout
		southPanel.setLayout((LayoutManager) new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		
		// add salary field and title
		salaryField = new JTextField(10);
		salaryTitle = new JTextArea("Please Enter Your Hourly Wage: ");
		
		// add bet title
		betTitle = new JTextArea("How much do you want to bet?");
		
		// add submitButton
		submitButton = new JButton("Proceed to Simulation");
		
		// set backgrounds to green
		salaryTitle.setBackground(GREEN_BACKGROUND);
		betTitle.setBackground(GREEN_BACKGROUND);
		
		// adjust appearance for salary and bet titles
		salaryTitle.setFont(font);
		salaryTitle.setForeground(Color.WHITE);
		
		betTitle.setFont(font);
		betTitle.setForeground(Color.WHITE);
		
		// set layout to flowlayout
		salaryPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		
		// add action listeners to buttoon and field
		submitButton.addActionListener(this);
		salaryField.addActionListener(this);
		
		// add key listeners to button and panel
		submitButton.addKeyListener(this);
		salaryPanel.addKeyListener(this);
		
		// create bet options
		betComboBox = createBetOptions();
		
		// titles are not editable
		betTitle.setEditable(false);
		salaryTitle.setEditable(false);
		
		// set focusable to true for
		// button, panel, text field, and combobox
		submitButton.setFocusable(true);
		salaryField.setFocusable(true);
		betComboBox.setFocusable(true);
		salaryPanel.setFocusable(true);
		setFocusable(true);
		
		// add components to panel
		salaryPanel.add(salaryTitle);
		salaryPanel.add(salaryField);
		salaryPanel.add(betTitle);
		salaryPanel.add(betComboBox);
		salaryPanel.add(submitButton);
		
		// add salarypanel and buttonspanel to southPanel
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
	 * @return strategy - The visual table of buttons for the user to choose
	 * 						   their strategy
	 */
	public JTable drawTable(String[] cards){
		 
		// tracks if hand contains a pair
		Boolean isPair = false;
		
		// if the cards are pairs,
		// set boolean to true
		if (cards == pairs){
			isPair = true;
		}
		
		// create new strategy table model and jtable
		StrategyTableModel model = new StrategyTableModel(makeStrategyArray(cards));
		JTable strategy = new JTable(model);
		
		// create gridlines for table
		// set to green
		strategy.setShowGrid(true);
		strategy.setGridColor(GREEN_BACKGROUND);
		
		// if not a pair (hard and soft totals)
		// create respective options
		if (isPair == false){
			for (int i = 0; i < 11; i++){
				createOptions(strategy, strategy.getColumnModel().getColumn(i));
				strategy.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
			}
		}
		// otherwise createpairoptions
		else{
			for (int i = 0; i < 11; i++){
				createPairOptions(strategy, strategy.getColumnModel().getColumn(i));
				strategy.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
			}
		}
		
		// rows are unable to be selected
		strategy.setRowSelectionAllowed(false);
		 
		return strategy;
	}
	
	
	/**
	 * makeStrategyArray()
	 * 
	 * Populates the strategyArray with the players options.
	 * 
	 * @return strategyArray - The populated strategyArray
	 */
	
	public Object[][] makeStrategyArray(String[] cards){
		
		// create a strategyarray
		Object[][] strategyArray = new Object[cards.length][11];
		
		// create map for all the possible actions
		Map<Integer, GameAction> dealerGameActionCombination = 
				new HashMap<Integer, GameAction>();
		
		// desired game action
		GameAction desiredAction = null;
		
		// populate table
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
	 * initColumnSizes()
	 * 
	 * Sets preferred column sizes to the size of the largest word.
	 * @param table
	 */
	public void initColumnSizes(JTable table){
		
		// create strategy table model
		StrategyTableModel model = (StrategyTableModel)table.getModel();
		
		// create table column and component
        TableColumn column = null;
        Component comp = null;
        
        // initialize headerwidth and cellwidth
        int headerWidth = 0;
        int cellWidth = 0;
        
        // create table cell renderer
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();
        
        // render cells in tables
        // setting preferred width to size of largest word
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
	 * createOptions()
	 * 
	 * Creates a ComboBox of gameActions for the strategy table.
	 * 
	 * @param Table - the table we are adding the ComboBoxes to
	 * @param column - the column in the table we are adding the ComboBoxes to
	 */
	
	public void createOptions(JTable Table, TableColumn column){
		
		// create combobox
		JComboBox<GameAction> strategyOption = new JComboBox<GameAction>();
		
		// add possible items (gameactions)
		strategyOption.addItem(GameAction.HIT);
		strategyOption.addItem(GameAction.STAND);
		strategyOption.addItem(GameAction.DOUBLE);
		strategyOption.addItem(GameAction.SURRENDER);
		
		// set cell editor
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		// render cell and create tooltip
		CustomRenderer renderer = new CustomRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
		Table.setDefaultRenderer(Object.class, renderer);
	}
	
	/**
	 * createPairOptions()
	 * 
	 * Creates a ComboBox of gameActions for the strategy table
	 * that has pairs. Includes SPLIT action.
	 * 
	 * @param Table - the table we are adding the ComboBoxes to
	 * @param column - the column in the table we are adding the ComboBoxes to
	 */
	public void createPairOptions(JTable Table, TableColumn column){
		
		// create comboxbox
		JComboBox<GameAction> strategyOption = new JComboBox<GameAction>();
		
		// add gameaction items
		strategyOption.addItem(GameAction.HIT);
		strategyOption.addItem(GameAction.STAND);
		strategyOption.addItem(GameAction.DOUBLE);
		strategyOption.addItem(GameAction.SURRENDER);
		strategyOption.addItem(GameAction.SPLIT);
		
		// set cell editor
		column.setCellEditor(new DefaultCellEditor(strategyOption));
		
		// render cells and add tooltip
		CustomRenderer renderer = new CustomRenderer();
		renderer.setToolTipText("Click to choose action");
		column.setCellRenderer(renderer);
		Table.setDefaultRenderer(Object.class, renderer);
	}
	
	/**
	 * createBetOptions()
	 * 
	 * Create comboxbox with possible bet values 
	 * for the simulator.
	 * 
	 * @return combobox - contains bet values
	 */
	public JComboBox<String> createBetOptions(){
		
		// create combobox based off betvaluestring array
		JComboBox<String> comboBox = new JComboBox<String>(betValueString);
		
		// initialize index
		comboBox.setSelectedIndex(0);
		
		// add action listener
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
	 * Handle action events.
	 */
	public void actionPerformed(ActionEvent event){
		
		// exit if exitbutton
		if(event.getSource() == exitButton) {
			System.out.println("exit");
			System.exit(1); 
		}
		
		// if backbutton, remove this panel
		// and go to previous panel
		if(event.getSource() == backButton) {
			//go back a screen
			panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.REMOVE, "view.StatsPanel"));
		}
		
		// if salaryField, store value in properties
		if(event.getSource() == salaryField){
			myWage = salaryField.getText();
			properties.put("Salary", myWage);
			salaryField.removeAll();
		}
		
		// if submitbutton
		// submit
		if(event.getSource() == submitButton){
			
			// make sure wage and bet value were specified
			if(salaryField.getText().trim().isEmpty() || betNumber == "-"){
				JOptionPane.showMessageDialog(null,"Please enter both values.","Blackjack",JOptionPane.OK_OPTION);
			}
			// otherwise
			else{
				
				// store strategy 
				properties.put("Game Strategy", gameStrategy);
				
				// store wage and bet value
				myWage = salaryField.getText();
				betValue = Integer.parseInt(betNumber);
				properties.put("Wage", myWage);
				properties.put("Bet Value", betValue);
				
				// proceed to simulator
				panelManager.actionPerformed(new ActionEvent(this, BlackjackApplet.ADD, "view.AutoPanel"));
			}
		}
		
		// if betcombobox, specify bet number
		if(event.getSource() == betComboBox){
			JComboBox<String> cb = (JComboBox<String>) event.getSource();
			betNumber = (String) cb.getSelectedItem();
		}
		
		// if help button, show help message
		if(event.getSource() == helpButton){
			showHelpMessage();
		}
	}
	
	/**
	 * keyPressed()
	 * 
	 * Handles keypressed events.
	 */
	public void keyPressed(KeyEvent event){
		
		// if enter key is pressed
		if(event.getKeyCode() == KeyEvent.VK_ENTER){
			
			// if submit button, act if submit button is clicked
			if(event.getSource() == submitButton){
				submitButton.doClick();
			}
			// if back button, act as if backbutton is clicked
			else if( event.getSource() == backButton){
				backButton.doClick();
			}
			// if exit button, act as if exitbutton is clicked
			else if(event.getSource() == exitButton){
				exitButton.doClick();
			}
			// if set strategy button, act as if strategybutton is clicked
			else if(event.getSource() == setStrategyButton){
				setStrategyButton.doClick();
			}
		}
	}
	
	/**
	 * keyTyped()
	 * 
	 * Handles key typed event. Do nothing in this case.
	 */
	public void keyTyped(KeyEvent event){}
	
	/**
	 * keyReleased()
	 * 
	 * Handles key released event. Do nothing in this case.
	 */
	public void keyReleased(KeyEvent event){} 
	
	/**
	 * StrategyTableModel()
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
			
			/**
			 * getRowCount()
			 * 
			 * @return row count
			 */
			public int getRowCount() {
				return strategyArray.length;
			}
			
			/**
			 * getColumnCount()
			 * 
			 * @return column count
			 */
			@Override
			public int getColumnCount() {
				return dealerShowing.length;
			}
			
			/**
			 * getColumnName()
			 * 
			 * @return column name
			 */
			@Override
			public String getColumnName(int column){
				return dealerShowing[column];
			}
			
			/**
			 * getValueAt()
			 * 
			 * @param row 
			 * @param column
			 * @return value at index
			 */
			@Override
			public Object getValueAt(int row, int column){
				return strategyArray[row][column];
			}
			
			/**
			 * getColumnClass()
			 * 
			 * @param column
			 * @return column class
			 */
			@Override
			public Class getColumnClass(int column) {
			      return getValueAt(0, column).getClass();
			}
			
			/**
			 * isCellEditable()
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
			 * setValueAt()
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
				
				// set index to gameaction value
				strategyArray[row][col] = (GameAction) value;
				
				// update view
				fireTableCellUpdated(row, col);
				String playerHand = strategyArray[row][0].toString();
				
				// update model
				GameAction desiredAction = (GameAction) value;
		        gameStrategy.setGameActionForHands(playerHand, col, desiredAction);
		    }
		}
	
	/**
	 * customRenderer
	 *
	 * Class renders and colors cells accordingly.
	 */
	class CustomRenderer extends DefaultTableCellRenderer {
		
		/**
		 * getTableCellRendererComponent()
		 * 
		 * Color codes cells according to game actions.
		 */
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