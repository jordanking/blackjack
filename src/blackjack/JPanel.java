/**
 * 
 */
package blackjack;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * @author alyssamiller
 *
 */
public class JPanel extends Panel
{
	
   protected ActionListener panelManager = null;
   protected Applet         applet = null;
   protected Properties     properties = null;


   public void init()    {
	  
   }
   public void start()   {}
   public void stop()    {}
   public void destroy() {}

   /**
    * The <I>Properties</I> object passed here is a global way of exchanging
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
    * This will probably be overriden by a sub-class 
    */
   public void actionPerformed(ActionEvent ae)
   {
   }

   public Insets getInsets()
   {
      return new Insets(2, 2, 2, 2);
   }

   public void paint(Graphics g)
   {
      g.draw3DRect(0, 0, getSize().width - 1, getSize().height - 1, false);
   }
}