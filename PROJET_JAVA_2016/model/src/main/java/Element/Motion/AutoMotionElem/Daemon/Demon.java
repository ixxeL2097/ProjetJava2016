package Element.Motion.AutoMotionElem.Daemon;


import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;

public abstract class Demon extends AutoMotionElem 								// Superclass of all demons
{

	public Demon(String picture, Permeabilite permea, final char MapSymbol) 
	{
		super(picture, permea, MapSymbol);
	}

}
