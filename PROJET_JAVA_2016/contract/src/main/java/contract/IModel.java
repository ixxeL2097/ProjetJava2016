package contract;

import java.util.Observable;

import javax.swing.ImageIcon;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel {

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	String getMessage();

	/**
	 * Load the message.
	 *
	 * @param key
	 *          the key
	 */
	void loadMessage(String key);

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
	
	ImageIcon getImageElement(int y, int x);
	
	void MoveLorann(int nextMoveUP_DWN, int nextMoveRGT_LFT);
	void MoveUP();
	void MoveDW();
	void MoveLF();
	void MoveRT();
	
}
