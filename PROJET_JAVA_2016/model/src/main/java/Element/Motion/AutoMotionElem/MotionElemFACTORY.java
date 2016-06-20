package Element.Motion.AutoMotionElem;

import Element.Motion.*;
import Element.Motion.AutoMotionElem.Daemon.DemonBlind;
import Element.Motion.AutoMotionElem.Daemon.DemonRandom;
import Element.Motion.AutoMotionElem.Daemon.DemonSadistic;
import Element.Motion.AutoMotionElem.Daemon.DemonTracker;

public class MotionElemFACTORY 
{
	public static final Projectile PROJECTILE 								= new Projectile();
	public static final Hero HERO 											= new Hero();
	public static final DemonBlind DEMONBLIND 								= new DemonBlind();
	public static final DemonRandom DEMONRANDOM 							= new DemonRandom();
	public static final DemonSadistic DEMONSADISTIC 						= new DemonSadistic();
	public static final DemonTracker DEMONTRACKER							= new DemonTracker();

	
	private static MotionElement	MotionElemFACTORY []	= 
		
		{PROJECTILE, HERO, DEMONBLIND, DEMONRANDOM, DEMONSADISTIC, DEMONTRACKER};
	
	public static MotionElement getElemenFromCHAR(final char MapSymbol) 
	{
		for (final MotionElement motionlessElem : MotionElemFACTORY) 
		{
			if (motionlessElem.getMapSymbol() == MapSymbol) 
			{
				return motionlessElem;
			}
		}
		return HERO;
	}


	public static void setMotionLessElemFACTORY(MotionElement motionLessElemFACTORY[]) 
	{
		MotionElemFACTORY = motionLessElemFACTORY;
	}
}


