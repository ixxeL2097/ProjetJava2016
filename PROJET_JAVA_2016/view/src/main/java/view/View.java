package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The Class View.
 *
 * @author Jean-Aymeric Diet
 */
public class View implements IView, Runnable 
{

	/** The frame. */
	private final ViewFrame viewFrame;
	private static boolean KeyUp=false;
	private static boolean KeyDw=false;
	private static boolean KeyLf=false;
	private static boolean KeyRt=false;

	/**
	 * Instantiates a new view.
	 *
	 * @param model
	 *          the model
	 */
	public View(final IModel model) 
	{
		this.viewFrame = new ViewFrame(model);
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Key code to controller order.
	 *
	 * @param keyCode
	 *          the key code
	 * @return the controller order
	 */
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode) 
	{
		switch (keyCode) 
		{
			case KeyEvent.VK_UP:
				KeyUp=true;
				return ControllerOrder.UP;
			case KeyEvent.VK_DOWN:
				KeyDw=true;
				return ControllerOrder.DOWN;
			case KeyEvent.VK_LEFT:
				KeyLf=true;
				return ControllerOrder.LEFT;
			case KeyEvent.VK_RIGHT:
				KeyRt=true;
				return ControllerOrder.RIGHT;
			default:
				return ControllerOrder.VOID;
		}
	}
	
	protected static ControllerOrder keyCodeToControllerOrderReleased(final int keyCode) 
	{
		switch (keyCode) 
		{
			case KeyEvent.VK_UP:
				KeyUp=false;
				return ControllerOrder.VOID;
			case KeyEvent.VK_DOWN:
				KeyDw=false;
				return ControllerOrder.VOID;
			case KeyEvent.VK_LEFT:
				KeyLf=false;
				return ControllerOrder.VOID;
			case KeyEvent.VK_RIGHT:
				KeyRt=false;
				return ControllerOrder.VOID;
			default:
				return ControllerOrder.VOID;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IView#printMessage(java.lang.String)
	 */
	public void printMessage(final String message) {
		this.viewFrame.printMessage(message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.viewFrame.setVisible(true);
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *          the new controller
	 */
	public void setController(final IController controller) {
		this.viewFrame.setController(controller);
	}

	public boolean getKeyUp() 
	{
		return KeyUp;
	}

	public boolean getKeyDw() 
	{
		return KeyDw;
	}

	public boolean getKetLf() 
	{
		return KeyLf;
	}

	public boolean getKeyRt() 
	{
		return KeyRt;
	}
}
