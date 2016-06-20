package Element.Motion.AutoMotionElem.Daemon;


import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import model.Model;

public abstract class Demon extends AutoMotionElem 
{

	public Demon(Model model, String picture, Permeabilite permea) 
	{
		super(model, picture, permea);
	}

}
