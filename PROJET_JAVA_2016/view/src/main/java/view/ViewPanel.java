package view;

import java.awt.Color;
import java.awt.Font;
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
	private JLabel score;
	private JLabel lives;
	private JLabel GameOver;
	private String gameOver="C:/ProjetJava/Sprite/GameOver4.gif";
	private GridBagConstraints gbc; 
	private Font ScoreFont;
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
		super();
		this.setViewFrame(viewFrame);
		this.setSize(this.getViewFrame().getModel().getD());
		this.setPreferredSize(this.getViewFrame().getModel().getD());
		viewFrame.getModel().getObservable().addObserver(this);
		JLabelMap = new JLabel [this.getViewFrame().getModel().getDimensionMapY()][this.getViewFrame().getModel().getDimensionMapX()];
		this.CreateHUD();
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());	
		
		this.initViewPanel();		
	}
	
	public void initViewPanel()		// initalize view. create a JLabel's matrix based on model's matrix
	{
		int x=0, y=0;
		for(y=0; y<this.getViewFrame().getModel().getDimensionMapY(); y++)
		{
			for(x=0; x<this.getViewFrame().getModel().getDimensionMapX(); x++)
			{
				JLabel sprite = new JLabel();
				sprite.setIcon(this.getViewFrame().getModel().getImageElement(y, x));
				this.JLabelMap[y][x]=sprite;
				this.getGbc().gridx = x;
				this.getGbc().gridy = y;
				this.getGbc().gridheight = 1;
				this.getGbc().gridwidth = 1;
				this.add(sprite, gbc);	
			}
		}		
		this.setHUD();
		this.getViewFrame().pack();
		this.setVisible(true);
		this.repaint();
	}
	
	public void CreateHUD()			// create the HUD with score and lives
	{
		this.score = new JLabel("SCORE : "+Integer.toString(this.getViewFrame().getModel().getScore()));
		this.lives = new JLabel("LIVES : "+Integer.toString(this.getViewFrame().getModel().getLives()));
		this.ScoreFont = new Font("Arial", Font.BOLD, 15);
		this.getScore().setForeground(Color.ORANGE);
		this.getScore().setFont(this.getScoreFont());
		this.getLives().setForeground(Color.ORANGE);
		this.getLives().setFont(this.getScoreFont());
	}
	
	public void setHUD()		// refresh the HUD
	{
		this.getGbc().gridx = 0;
		this.getGbc().gridy++;
		this.getGbc().gridwidth = 5;
		this.getScore().setText("SCORE : "+Integer.toString(this.getViewFrame().getModel().getScore()));
		this.add(this.score, gbc);	
		this.getGbc().gridx = 5;
		this.getLives().setText("LIVES : "+Integer.toString(this.getViewFrame().getModel().getLives()));
		this.add(this.lives, gbc);	
	}
	
	
	public void GameOver()		// set GameOver if necessary
	{
		this.removeAll();
		this.setLayout(null);
		this.GameOver = new JLabel(new ImageIcon(this.gameOver));
		this.GameOver.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(this.GameOver);	
		this.setVisible(true);
		this.repaint();
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
	public void update(final Observable arg0, final Object arg1) 		// update view or set gameover
	{
		if(this.getViewFrame().getModel().getLorannStatus()==false)
		{
			this.GameOver();
		}
		else
		{
			this.removeAll();
			this.initViewPanel();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) 
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		//graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		//graphics.drawString(this.getViewFrame().getModel().getMessage(), 10, 20);
	}

	public JLabel[][] getJLabelMap() {
		return JLabelMap;
	}

	public void setJLabelMap(JLabel[][] jLabelMap) {
		JLabelMap = jLabelMap;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}

	public Font getScoreFont() {
		return ScoreFont;
	}

	public void setScoreFont(Font scoreFont) {
		ScoreFont = scoreFont;
	}

	public JLabel getLives() {
		return lives;
	}

	public void setLives(JLabel lives) {
		this.lives = lives;
	}
	
	
	
}
