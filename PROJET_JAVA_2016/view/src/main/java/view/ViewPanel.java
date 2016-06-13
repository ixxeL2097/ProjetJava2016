package view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer 
{

	/** The view frame. */
	private ViewFrame					viewFrame;
	private JLabel [][] JLabelMap;
	private GridBagConstraints gbc;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -998294702363713521L;

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) 
	{
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		
		JLabelMap = new JLabel [viewFrame.getModel().getDimensionMapY()][viewFrame.getModel().getDimensionMapX()];
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.initViewPanel();
		
	}
	
	public void initViewPanel()
	{
		int x=0, y=0;
		for(y=0; y<this.viewFrame.getModel().getDimensionMapY(); y++)
		{
			for(x=0; x<this.viewFrame.getModel().getDimensionMapX(); x++)
			{
				JLabel sprite = new JLabel();
				sprite.setIcon(this.viewFrame.getModel().getImageElement(y, x));
				this.JLabelMap[y][x]=sprite;
				this.gbc.gridx = x;
				this.gbc.gridy = y;
				this.gbc.gridheight = 1;
				this.gbc.gridwidth = 1;
				this.add(sprite, gbc);	
			}
		}
		this.viewFrame.pack();
		this.setVisible(true);
		
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() 
	{
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) 
	{
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) 
	{
		this.removeAll();
		this.initViewPanel();
		//this.repaint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	/*@Override
	protected void paintComponent(final Graphics graphics) 
	{
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		graphics.drawString(this.getViewFrame().getModel().getMessage(), 10, 20);
	}*/
}
