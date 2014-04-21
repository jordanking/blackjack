// 
// Project 4 
// Name: Allie Miller, Alex Post, Jordan King, Kevin Tian, Riya Modi
// E-mail: rm662@georgetown.edu 
// Instructor: Singh 
// COSC 150 
// 
// In accordance with the class policies and Georgetown's Honor Code, 
// I certify that, with the exceptions of the lecture notes and those 
// items noted below, I have neither given nor received any assistance 
// on this project. 
// 
// Description: Our program simulates a Blackjack game and
// allows the player to visualize the true weight of his monetary losses
// 



/**
 * 
 */
package blackjack;

import java.awt.AWTEvent;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JApplet;

/**
<<<<<<< HEAD
 * @author alyssamiller
=======
 * @author Allie Miller
>>>>>>> FETCH_HEAD
 *
 */
public class BlackjackApplet extends JApplet implements ActionListener{
	
	CardLayout  jCardLayout; // cardlayout for the applet
	Properties  properties; // properties for the applet that all panels will have access to
	Vector  panelsVector = new Vector(6); // Vector containing panels
	
	// Event IDs for ADD and REMOVE functions
	final static int ADD = AWTEvent.RESERVED_ID_MAX + 1,
            				REMOVE = AWTEvent.RESERVED_ID_MAX + 2;
	 
	public void init() {
		try {
			
			 properties  =  new Properties();
	         properties.put("Applet", this);
	         properties.put("PanelManager", (ActionListener)this);  // makes it possible for panels to send events
	         
		} catch (Exception e) { 
	    	 e.printStackTrace(); 
	    	 properties = null; 
		}
			//sets the size and layout of the applet
			setSize(800,800);
	       	setLayout(jCardLayout  = new CardLayout());
	       	// adds the first panel, the Welcome screen
	       	panelAdd("blackjack.WelcomePanel");

	}
	
	 /**
	  * Handle ADD and REMOVE events from/for panels
	  */
  public void actionPerformed(ActionEvent evt)
  {   
      switch(evt.getID())
      {
          case ADD:
               panelAdd(evt.getActionCommand());
               break;

          case REMOVE:
               Object object = evt.getSource();
               panelRemove(object);
               object = null;  // deallocate via garbage collector
               break;
      }
  }
	
  /**
   * Instantiate a class file, call it's init() and start() methods, call stop() on 
   * previous panel, show newly added panel in the cardlayout, add new Panel
   * to the panel vector, and finally add the reference to Properties so that
   * it can be accessed by all panels
   */
  public void panelAdd(Object arg)
  {
     showStatus("Please wait...");
     JPanel jPanel = null;
     try 
     {
        Class c = Class.forName((String)arg);
        jPanel = (JPanel)c.newInstance();
     }
     catch (Exception except)
     {
        showStatus(except.toString());
        except.printStackTrace();
        return;
     }

     jPanel.setProperties(properties);


     // If other components exists, call "stop" method on last component
     if (panelsVector.size() > 0)
         ((JPanel)panelsVector.lastElement()).stop();

     // Add new component and show it
     panelsVector.addElement(jPanel);
     add(jPanel, jPanel.getClass().getName());

     jPanel.init();
     jPanel.start();

     jCardLayout.next(this.getContentPane());

     this.properties.put(jPanel.getClass().getName(), jPanel);
     showStatus("");
  }


  /**
   * Remove requested panel from panel vector, show previous panel, call stop() on
   * panel being removed, call start() on previous panel and show it in
   * the cardlayout, call destroy() on panel being removed, and finally
   * remove panel from CardLayout and Properties object
   */
  public void panelRemove(Object arg)
  {
     JPanel jPanel = (JPanel)arg;

     jCardLayout.previous(this.getContentPane());
     panelsVector.removeElement(jPanel);
     jPanel.stop();

     // If other components exists, call "start" method on second to last
     if (panelsVector.size() > 0)
         ((JPanel)panelsVector.lastElement()).start();

     jPanel.destroy();
     remove(jPanel);

     this.properties.remove(jPanel);
  }
}
