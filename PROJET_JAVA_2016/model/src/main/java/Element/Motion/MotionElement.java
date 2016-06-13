package Element.Motion;
import Element.*;

public abstract class MotionElement extends Element 
{
	protected int currentX;
	protected int currentY;
	protected int lastX;
	protected int lastY;
	protected int NextX;
	protected int NextY;
	protected char lastField;
	protected char currentField;
	protected char nextField;
	
	
	
	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public int getNextX() {
		return NextX;
	}

	public void setNextX(int nextX) {
		NextX = nextX;
	}

	public int getNextY() {
		return NextY;
	}

	public void setNextY(int nextY) {
		NextY = nextY;
	}

	public int getCurrentX() {
		return currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public char getLastField() {
		return lastField;
	}

	public char getCurrentField() {
		return currentField;
	}

	public char getNextField() {
		return nextField;
	}

	public MotionElement() 
	{

	}

	public MotionElement(String picture, Permeabilite permea) 
	{
		super(picture, permea);

	}
	

}
