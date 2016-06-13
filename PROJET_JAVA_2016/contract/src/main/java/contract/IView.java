package contract;

/**
 * The Interface IView.
 *
 * @author Jean-Aymeric Diet
 */
public interface IView {

	/**
	 * Prints the message.
	 *
	 * @param message
	 *          the message
	 */
	void printMessage(final String message);
	
	boolean getKeyUp();
	boolean getKeyDw();
	boolean getKetLf();
	boolean getKeyRt();
}
