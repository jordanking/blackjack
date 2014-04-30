// 
// Project 4 
// Name: Allie Miller, Alex Post, Jordan King, Kevin Tian, Riya Modi
// E-mail: rm662@georgetown.edu, kt493@georgetown.edu, jwk67@georgetown.edu,
// amm359@georgetown.edu, arp68@georgetown.edu
// Instructor: Singh 
// COSC 150 
// 
// In accordance with the class policies and Georgetown's Honor Code, 
// I certify that, with the exceptions of the lecture notes and those 
// items noted below, I have neither given nor received any assistance 
// on this project. 
// 
// Description: Our program allows the user to manually play Blackjack games. It learns 
// the user's strategies and then uses these strategies (or new ones entered by the user)
// to implement an automated simulation of Blackjack. Our applet shows the user just how much
// one would lose (money and time), no matter the strategies, over a long period of time as an addict
// 
// Resources:
// http://stackoverflow.com/questions/9029162/how-do-i-get-the-text-of-a-button-in-java
// http://www.ecst.csuchico.edu/~amk/classes/csciOOP/double-buffering.html
// http://www.javaworld.com/article/2076917/client-side-java/mpad--a-new-design-and-development-methodology-for-multi-panel-applets.html
// 

package view;

import java.awt.AWTEvent;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JApplet;

/**
 * @author Allie Miller
 * 
 * BlackJack Applet; manages the Panels for the Blackjack applet
 */
@SuppressWarnings("serial")
public class BlackjackApplet extends JApplet implements ActionListener{

	CardLayout  jCardLayout; // cardlayout for the applet
	Properties  properties; // properties for the applet that all panels will have access to
	Vector<BPanel>  panelsVector = new Vector<BPanel>(6); // Vector containing panels

	// Event IDs for ADD and REMOVE functions
	final static int ADD = AWTEvent.RESERVED_ID_MAX + 1,
			REMOVE = AWTEvent.RESERVED_ID_MAX + 2;
	
	/**
	 * init()
	 * Initializes properties for the applet
	 */
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
		setPreferredSize(new Dimension(1000, 800));
	
		setLayout(jCardLayout  = new CardLayout());

		// adds the first panel, the Welcome screen
		panelAdd("view.WelcomePanel");

	}

	/**
	 * actionPerformed(ActionEvent event)
	 * Handle ADD and REMOVE events from/for panels
	 * @param event ActionEvent object
	 */
	public void actionPerformed(ActionEvent event)
	{   
		switch(event.getID())
		{
		
		// call add panel method
		case ADD:
			panelAdd(event.getActionCommand());
			break;
			
		// call remove panel method
		case REMOVE:
			Object object = event.getSource();
			panelRemove(object);
			object = null;  // deallocate via garbage collector
			break;
		}
	}

	/**
	 * panelAdd(Object arg)
	 * 
	 * Instantiate a class file, call its init() and start() methods, call stop() on 
	 * previous panel, show newly added panel in the cardlayout, add new Panel
	 * to the panel vector, and finally add the reference to Properties so that
	 * it can be accessed by all panels
	 * 
	 *@param arg
	 */
	public void panelAdd(Object arg)
	{
		showStatus("Please wait...");
		BPanel jPanel = null;
		
		try {
			
			Class<?> tempClass = Class.forName( (String) arg);
			jPanel = (BPanel) tempClass.newInstance();
			
		} catch (Exception except) {
			
			showStatus(except.toString());
			except.printStackTrace();
			return;
			
		}

		jPanel.setProperties(properties);


		// If other components exists, call "stop" method on last component
		if (panelsVector.size() > 0) {
			((BPanel)panelsVector.lastElement()).stop();
		}
		
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
	 * panelRemove(Object arg)
	 * 
	 * Remove requested panel from panel vector, show previous panel, call stop() on
	 * panel being removed, call start() on previous panel and show it in
	 * the cardlayout, call destroy() on panel being removed, and finally
	 * remove panel from CardLayout and Properties object
	 * 
	 * @param arg
	 */
	public void panelRemove(Object arg)
	{
		BPanel jPanel = (BPanel)arg;

		jCardLayout.previous(this.getContentPane());
		panelsVector.removeElement(jPanel);
		jPanel.stop();

		// If other components exists, call "start" method on last panel in vectoe
		if (panelsVector.size() > 0)
			((BPanel)panelsVector.lastElement()).start();
		
		// destroy and remove requested panel
		jPanel.destroy();
		remove(jPanel);
		
		// remove requested panel from properties
		this.properties.remove(jPanel);
	}
}
