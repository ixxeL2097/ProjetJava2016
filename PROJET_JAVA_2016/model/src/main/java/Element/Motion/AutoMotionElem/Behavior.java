package Element.Motion.AutoMotionElem;

public abstract class Behavior implements IArtificialIntelligence 		// superclass of different behavior
{
	protected AutoMotionElem Mobil;
	
	public Behavior(AutoMotionElem mobil)								// set the current mobil
	{
		this.setMobil(mobil);
	}
	
	public AutoMotionElem getMobil() 
	{
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) 
	{
		Mobil = mobil;
	}

}
