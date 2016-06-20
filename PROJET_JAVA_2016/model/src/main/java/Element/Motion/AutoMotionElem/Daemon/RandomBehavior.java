package Element.Motion.AutoMotionElem.Daemon;

import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.Behavior;

public class RandomBehavior extends Behavior
{
	
	public RandomBehavior(AutoMotionElem Mobil)
	{
		super(Mobil);
	}

	public void AutoMove() 
	{
		this.UpdateMove();
	}
	
	public void UpdateMove()
	{
		this.getMobil().DefaultDaemonMove();
	}
	
}
