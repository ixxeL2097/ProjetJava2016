package Element.Motion;


import Element.Permeabilite;
import model.Model;

public abstract class Daemon extends AutoMotionElem 
{

	public Daemon(Model model, String picture, Permeabilite permea) 
	{
		super(model, picture, permea);
	}

}
