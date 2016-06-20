package Element.Motion.AutoMotionElem;

import Element.Motion.*;
import Element.Motion.AutoMotionElem.Daemon.DemonBlind;
import Element.Motion.AutoMotionElem.Daemon.DemonRandom;
import Element.Motion.AutoMotionElem.Daemon.DemonSadistic;
import Element.Motion.AutoMotionElem.Daemon.DemonTracker;

public class MotionElemFACTORY 
{	
	public static final MotionElement PROJECTILE 							= new Projectile();
	public static final MotionElement HERO 									= new Hero();
	public static final MotionElement DEMONBLIND 							= new DemonBlind();
	public static final MotionElement DEMONRANDOM 							= new DemonRandom();
	public static final MotionElement DEMONSADISTIC 						= new DemonSadistic();
	public static final MotionElement DEMONTRACKER							= new DemonTracker();

	
	private static MotionElement	MotionElemFACTORY []	= 
		
		{PROJECTILE, HERO, DEMONBLIND, DEMONRANDOM, DEMONSADISTIC, DEMONTRACKER};
	
	public static MotionElement getElemenFromCHAR(final char MapSymbol) 
	{
		for (final MotionElement motionElem : MotionElemFACTORY) 
		{
			if (motionElem.getMapSymbol() == MapSymbol) 
			{
				return motionElem;
			}
		}
		return null;
	}

	public static void setMotionElemFACTORY(MotionElement motionElemFACTORY[]) 
	{
		MotionElemFACTORY = motionElemFACTORY;
	}
}


