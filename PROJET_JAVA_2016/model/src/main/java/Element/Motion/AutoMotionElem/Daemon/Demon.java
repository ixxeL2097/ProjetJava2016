package Element.Motion.AutoMotionElem.Daemon;


import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import model.IMapGen;

public abstract class Demon extends AutoMotionElem 
{

	public Demon(IMapGen mapgen, String picture, Permeabilite permea) 
	{
		super(mapgen, picture, permea);
	}

}
