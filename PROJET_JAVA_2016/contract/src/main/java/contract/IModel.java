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
	
	int getDimensionMapX();					// methods to get Static dimensionMap of the game and frame
	int getDimensionMapY();
	int getWindowMapWIDTH();
	int getWindowMapHEIGHT();
	Dimension getD();
	
	ImageIcon getImageElement(int y, int x);		
	
	void MoveLorann(int nextMoveUP_DWN, int nextMoveRGT_LFT);		// methode to make lorann move
	void setLorannGIF();											// change lorann's animation
	void askLorannToShoot();										// make lorann shoot
	
	int getScore();													// get lorann's score
	int getLives();													// get lorann's lives
	void setScore(int score);										// set lorann's score
	boolean getLorannStatus();										// return if lorann is alive or not
	IPlayer getPlayer();												
	
}
