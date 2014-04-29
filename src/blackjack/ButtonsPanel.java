package blackjack;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

/**
 * ButtonsPanel
 * 
 * Extends Panel from AWT class 
 * to enable double buffering.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class ButtonsPanel extends Panel {
	/**
	 * update()
	 * 
	 * Method to implement double buffering.
	 * update() gets called automatically by
	 * AWT components when repainting.
	 * 
	 * Code paraphrased from:
	 * http://www.ecst.csuchico.edu/~amk/classes/csciOOP/double-buffering.html
	 */
	public void update(Graphics graphicsObject) {
		// declare graphics, image
		Graphics offscreenGraphicsObject;
		Image offscreenBuffer = null;
		
		// create dimension object
		// set dimension to panel size
		@SuppressWarnings("deprecation")
		Dimension panelDimension = size();

		// create the offscreen buffer and associated Graphics
		// for double buffering
		offscreenBuffer = createImage(panelDimension.width, panelDimension.height);
		offscreenGraphicsObject = offscreenBuffer.getGraphics();
		
		// clear the offscreen graphics object
		offscreenGraphicsObject.setColor(getBackground());
		offscreenGraphicsObject.fillRect(0, 0, panelDimension.width, panelDimension.height);
		offscreenGraphicsObject.setColor(getForeground());
		
		// normal redraw method for buttonspanel
		paint(offscreenGraphicsObject);
		
		// draw this panel with components
		graphicsObject.drawImage(offscreenBuffer, 0, 0, this);
	 }
}
