package Element.Motion;
import Element.*;

public class Hero extends MotionElement
{
	private static String HERO_MOVE="C:/ProjetJava/Sprite/lorann_l.png";
	private boolean alive = true ;
	private int lives;
	
	
	//---------------------------------------CONSTRUCTEURS----------------------------------------------------------------------------

	public Hero(int Y, int X) 
	{
		super(HERO_MOVE, Permeabilite.PENETRABLE);
		this.currentX=X;
		this.currentY=Y;	
		this.lastX=X;
		this.lastY=Y;
	}
	
	
}
