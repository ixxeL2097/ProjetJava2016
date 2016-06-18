package Element.Motion.AutoMotionElem.Daemon;

import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.IArtificialIntelligence;

public class BlindBehaviour implements IArtificialIntelligence
{
	protected AutoMotionElem Mobil;

	public BlindBehaviour(AutoMotionElem Mobil) 
	{
		this.setMobil(Mobil);
	}

	public void AutoMove() 
	{
		
	}
	
	public AutoMotionElem getMobil() {
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) {
		Mobil = mobil;
	}

}
