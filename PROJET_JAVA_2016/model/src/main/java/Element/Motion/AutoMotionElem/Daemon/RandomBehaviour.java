package Element.Motion.AutoMotionElem.Daemon;

import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.IArtificialIntelligence;

public class RandomBehaviour implements IArtificialIntelligence
{
	private AutoMotionElem Mobil;
	
	public RandomBehaviour(AutoMotionElem Mobil)
	{
		this.setMobil(Mobil);
	}

	public void AutoMove() 
	{
		this.UpdateMove();
	}
	
	public void UpdateMove()
	{
		this.getMobil().DefaultDaemonMove();
	}
	
	public AutoMotionElem getMobil() {
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) {
		Mobil = mobil;
	}

}
