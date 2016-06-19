package contract;

import java.awt.Dimension;
import java.util.Observable;

import javax.swing.ImageIcon;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel 
{
	/**
	 * Load the message.
	 *
	 * @param key
	 *          the key
	 */
	void loadDB();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
	
	int getDimensionMapX();
	int getDimensionMapY();
	int getWindowMapWIDTH();
	int getWindowMapHEIGHT();
	Dimension getD();
	
	ImageIcon getImageElement(int y, int x);
	
	void MoveLorann(int nextMoveUP_DWN, int nextMoveRGT_LFT);
	void setLorannGIF();
	void LorannIsShooting();
	
	int getScore();
	int getLives();
	void setScore(int score);
	boolean getLorannStatus();
	IPlayer getPlayer();
	
}
