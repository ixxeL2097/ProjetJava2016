package Element.MotionLess;

import Element.*;


public abstract class MotionLessElem extends Element 
{
	private final char MapSymbol;
	
	public MotionLessElem(String picture, final Permeabilite permea, final char MapSymbol) 
	{
		super(picture, permea);
		this.MapSymbol = MapSymbol;
	}

	public char getMapSymbol() 
	{
		return this.MapSymbol;
	}

	
}
