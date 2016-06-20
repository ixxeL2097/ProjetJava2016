package Element.Motion;
import Element.*;
import model.IMapGen;

public abstract class MotionElement extends Element 
{
	protected int X;
	protected int Y;
	protected int lastX;
	protected int lastY;
	protected IMapGen mapgen;
	private final char MapSymbol;
	
	public MotionElement(String picture, Permeabilite permea, final char MapSymbol )
	{
		super(picture, permea);
		this.MapSymbol=MapSymbol;
	}	
	
/*	public MotionElement(IMapGen mapgen,String picture, Permeabilite permea )
	{
		super(picture, permea);
		this.setMapgen(mapgen);
	}	*/
	
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
	
	public IMapGen getMapgen() {
		return mapgen;
	}

	public void setMapgen(IMapGen mapgen) {
		this.mapgen = mapgen;
	}
	
	public void setState(IMapGen mapgen, int y, int x)
	{
		this.setMapgen(mapgen);
		this.setY(y);
		this.setX(x);
	}
	
	public char getMapSymbol() 
	{
		return this.MapSymbol;
	}
	
	
	

}
