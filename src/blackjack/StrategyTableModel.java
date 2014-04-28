package blackjack;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StrategyTableModel extends AbstractTableModel{

	String[] dealerShowing = {"2", "3", "4", "5", "6", 
			   			      "7", "8", "9", "10", "A"};
	String[][] strategyArray = new String[9][10];
	
	String[] longValues = {"Double"};
	
	private String[][] makeData(){
		 
		for (int i = 0; i < 10; i++){
			 for(int j = 0; j < 9; j++){
				 strategyArray[j][i] = "Hit";
			 }
		 }
		 
		return strategyArray;
	 }
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return makeData().length;
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
	public String getValueAt(int row, int column){
		return makeData()[row][column];
	}
	
	@Override
	public Class getColumnClass(int c) {
	      return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
        return true;
    }
	
	public void setValueAt(String value, int row, int col) {
		makeData()[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
