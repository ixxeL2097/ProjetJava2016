package Element.Motion;
import Element.*;
import model.Model;

public abstract class MotionElement extends Element 
{
	protected int X;
	protected int Y;
	protected int lastX;
	protected int lastY;
	protected Model model;
	
	public MotionElement(Model model,String picture, Permeabilite permea )
	{
		super(picture, permea);
		this.setModel(model);
	}	
	
	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public synchronized void setX(int currentX) {
		this.X = currentX;
	}

	public synchronized void setY(int currentY) {
		this.Y = currentY;
	}

	public synchronized int getX() {
		return X;
	}

	public synchronized int getY() {
		return Y;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public MotionElement(String picture, Permeabilite permea) 
	{
		super(picture, permea);

	}
	
	public synchronized Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	

}
