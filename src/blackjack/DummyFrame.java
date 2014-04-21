package blackjack;




import java.awt.EventQueue;

import javax.swing.JFrame;



@SuppressWarnings("serial")
public class DummyFrame extends JFrame {

	/**
	 * Constructor.
	 * Calls the init UI to do the real work
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
    public DummyFrame() {
    	
    	// Set up the UI
    	initUI();
    	
    }
    
    
    /**
     * Initialize the GUI with simple settings, as the BreakoutBoard does most of the real work
     * 
     * @param none
     * @return none
     * @since 1.0
     */
    public void initUI() {
    	
    	// This is where the real magic happens
        add(new DummyPanel());

        // Set the title of the frame to the name of the game
        setTitle("BlackJack");
        
        // Maybe just because there is a single panel
        pack();
        
        // Do not allow it to be resized because I am a tyrant
        setResizable(true);
        
        // This centers the game window, I will allow the peasants to move the game
        setLocationRelativeTo(null);
        
        // Exit cleanly so as to not be a jerk
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /**
     * I don't like this main, we will fix it later
     * 
     * @param args
     */
    public static void main(String[] args) {
		
    	// INVOKE THE GAME																	  later
    	invokeGame();
    	
    }
    

	/**
	 * Invokes the game and its board on its own thread
	 * 
	 * @param none
	 * @return none
	 * @since 1.0
	 */
	private static void invokeGame() {
		
		// Create and tart up this magical runnable
        EventQueue.invokeLater(new Runnable() {
            
        	/**
        	 * Runs the game runnable (wow such method)
        	 * 
        	 * @param none
        	 * @return none
        	 * @see Runnable
        	 * @since 1.0
        	 */
            @Override
            public void run() {  
            	
            	// Create the game (wow so ez 2 do...)
                JFrame blackjackGameFrame = new DummyFrame();
                
                // Show the game to my friends (YOU)(OH YOU, ME)
                blackjackGameFrame.setVisible(true);                
            }
        });
	}
}
