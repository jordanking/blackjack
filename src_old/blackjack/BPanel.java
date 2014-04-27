/**
 * 
 */
package blackjack;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JPanel;

/**
 * BPanel
 * 
 * Panel class that facilitates 
 * interactions with Panel Manager.
 * 
 * @author alyssamiller
 *
 */
@SuppressWarnings("serial")
public class BPanel extends JPanel
{
	
	/**
	 * Protected objects to manage information flow in system.
	 */
   protected ActionListener panelManager = null;
   protected Applet         applet = null;
   protected Properties     properties = null;

   
   /**
    * init()
    * 
    */
   public void init()    {
	  
   }
   public void start()   {}
   public void stop()    {}
   public void destroy() {}

   /**
    * setProperties()
    * 
    * Set local properties object to parameter.
    * 
    * @param p - properties object
    * 
    * The Properties object passed here is a global way of exchanging
    * data between screens.
    */
   public void setProperties(Properties p)
   {
       properties = p;
       
       if (properties != null)
       {
          applet  = (Applet)properties.get("Applet");
          panelManager = (ActionListener)properties.get("PanelManager");
       }
   }
   
   /**
    * This will probably be overridden by a sub-class 
    */
   public void actionPerformed(ActionEvent ae)
   {
   }
   
   /**
    * getInsets()
    * 
    * Sets inset from border (padding).
    */
   public Insets getInsets()
   {
      return new Insets(2, 2, 2, 2);
   }
   
   /**
    * paint()
    * 
    * Draws panel.
    */
   public void paint(Graphics g)
   {
      g.draw3DRect(0, 0, getSize().width - 1, getSize().height - 1, false);
   }
}