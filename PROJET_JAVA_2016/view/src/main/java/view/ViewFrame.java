package view;
import java.util.TreeSet;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import contract.IController;
import contract.IModel;

/**
 * The Class ViewFrame.
 *
 * @author Jean-Aymeric Diet
 */
class ViewFrame extends JFrame implements KeyListener , ActionListener
{

	/** The model. */
	private IModel	model;
	private TreeSet<Integer> TreeSet= new TreeSet<Integer>();
	private Timer timer = new Timer(1000,this);
	//private Timer keyTimer = new Timer(50,this);
	//public static int firstKey=0;
	//public static int secondKey=0;
	/** The controller. */
	private IController	controller;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -697358409737458175L;

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model
	 *          the model
	 * @throws HeadlessException
	 *           the headless exception
	 */
	public ViewFrame(final IModel model) throws HeadlessException {
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model
	 *          the model
	 * @param gc
	 *          the gc
	 */
	public ViewFrame(final IModel model, final GraphicsConfiguration gc) {
		super(gc);
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model
	 *          the model
	 * @param title
	 *          the title
	 * @throws HeadlessException
	 *           the headless exception
	 */
	public ViewFrame(final IModel model, final String title) throws HeadlessException {
		super(title);
		this.buildViewFrame(model);
	}

	/**
	 * Instantiates a new view frame.
	 *
	 * @param model
	 *          the model
	 * @param title
	 *          the title
	 * @param gc
	 *          the gc
	 */
	public ViewFrame(final IModel model, final String title, final GraphicsConfiguration gc) {
		super(title, gc);
		this.buildViewFrame(model);
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	private IController getController() {
		return this.controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *          the new controller
	 */
	protected void setController(final IController controller) {
		this.controller = controller;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	protected IModel getModel() {
		return this.model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Builds the view frame.
	 *
	 * @param model
	 *          the model
	 */
	private void buildViewFrame(final IModel model) {
		this.setModel(model);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.addKeyListener(this);
		this.setContentPane(new ViewPanel(this));
		this.setSize(this.getModel().getD());
		this.setPreferredSize(this.getModel().getD());			// set a preferredSize to force the frame when using pack method
		this.setLocationRelativeTo(null);
		
		/*this.keyTimer.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent ev)
			{
				ViewFrame.controller.orderPerform(View.keyCodeToControllerOrder(ViewFrame.firstKey,ViewFrame.secondKey));
			}
			
		});*/
	}

	/**
	 * Prints the message.
	 *
	 * @param message
	 *          the message
	 */
	public void printMessage(final String message) 
	{
		JOptionPane.showMessageDialog(null, message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(final KeyEvent e) 
	{

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(final KeyEvent e) 
	{
		this.timer.stop();												// stop the Lorann's changing sprite timer when typing a key
		//this.keyTimer.start();
		int x=0 ,y=0;
		this.getTreeSet().add(e.getExtendedKeyCode());					// using a treeSet to store different KeyEvent and act in consequence
		x=this.getTreeSet().first();
		if(getTreeSet().first() != getTreeSet().last())
		{
			y=getTreeSet().last();
		}		
		this.getController().orderPerform(View.keyCodeToControllerOrder(x,y));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(final KeyEvent e) 
	{
		this.timer.start();												// start the timer to change Lorann's animation
		//this.keyTimer.stop();

		TreeSet.remove(e.getExtendedKeyCode());
	}

	public void actionPerformed(ActionEvent e) {
		this.getModel().setLorannGIF();
	}

	public TreeSet<Integer> getTreeSet() {
		return TreeSet;
	}

	public void setTreeSet(TreeSet<Integer> treeSet) {
		TreeSet = treeSet;
	}
	
	
}
