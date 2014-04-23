/**
 * 
 */
package blackjack;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Alex Post
 *
 */
public class SettingsPanel extends JPanel {

	/**
	 * 
	 */
	public SettingsPanel() {
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param layout
//	 */
//	public SettingsPanel(LayoutManager layout) {
//		super(layout);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param isDoubleBuffered
//	 */
//	public SettingsPanel(boolean isDoubleBuffered) {
//		super(isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param layout
//	 * @param isDoubleBuffered
//	 */
//	public SettingsPanel(LayoutManager layout, boolean isDoubleBuffered) {
//		super(layout, isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
	public void init(){
		
	}
	
	public void tabbedPane() {
        //super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Manual", null, panel1,
                "Manual Play");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Automatic", null, panel2,
                "Simulation Settings");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
	
	 protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	
}
