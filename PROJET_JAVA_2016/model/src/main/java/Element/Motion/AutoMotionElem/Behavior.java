package Element.Motion.AutoMotionElem;

public abstract class Behavior implements IArtificialIntelligence 
{
	protected AutoMotionElem Mobil;
	
	public Behavior(AutoMotionElem mobil)
	{
		this.setMobil(mobil);
	}
	
	public AutoMotionElem getMobil() {
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) {
		Mobil = mobil;
	}

}
