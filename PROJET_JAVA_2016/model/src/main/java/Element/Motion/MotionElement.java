package Element.Motion;
import Element.*;
import model.IMapGen;

public abstract class MotionElement extends Element 
{
	protected int X;
	protected int Y;
	protected IMapGen mapgen;
	
	public MotionElement(String picture, Permeabilite permea, final char MapSymbol )
	{
		super(picture, permea, MapSymbol);
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
	
	public IMapGen getMapgen() {
		return mapgen;
	}

	public void setMapgen(IMapGen mapgen) {
		this.mapgen = mapgen;
	}
	
	public void setState(IMapGen mapgen, int y, int x)		// sets MotionElement position and gives MapGen interface
	{
		this.setMapgen(mapgen);
		this.setY(y);
		this.setX(x);
	}
}
