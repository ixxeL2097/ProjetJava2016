package Element.Motion.AutoMotionElem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import contract.ControllerOrder;
import model.Model;

public class Projectile extends AutoMotionElem implements Runnable, ActionListener
{
	private static String MAGIC_BALL="C:/ProjetJava/Sprite/250ms.gif";
	protected ControllerOrder Direction;


	public Projectile(Model model, int Y, int X) 
	{	
		super(model, MAGIC_BALL, Permeabilite.MISSILE);
		this.MoveTimer=new Timer(250,this);
		this.setX(X);
		this.setY(Y);
		this.run();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.UpdatePosition();
	}
	
	public void CheckUpcomingDirection(int UP_DW, int RT_LF, ControllerOrder order)
	{
		Permeabilite Perm = this.getModel().getMapGen().getElemMtx(this.getY()+UP_DW,this.getX()+RT_LF).getPermea();
		
		if(Perm != Permeabilite.PENETRABLE && Perm != Permeabilite.HERO && Perm != Permeabilite.TRACKER && Perm != Permeabilite.ENEMY)
		{
			this.setDirection(order);
		}
	}
	
	public void ExecuteTranslation(int UP_DW_1stMove, int RT_LF_1stMove, int UP_DW_2ndMove, int RT_LF_2ndMove, ControllerOrder order)
	{
		if(this.CheckAllowedMapBounds(UP_DW_1stMove, RT_LF_1stMove))
		{
			this.getModel().MoveDaemon(UP_DW_1stMove, RT_LF_1stMove, this);
			if(this.CheckAllowedMapBounds(UP_DW_1stMove, RT_LF_1stMove))
			{
				this.CheckUpcomingDirection(UP_DW_1stMove, RT_LF_1stMove, order);
			}
		}
		else
		{
			this.getModel().MoveDaemon(UP_DW_2ndMove, RT_LF_2ndMove, this);
			this.setDirection(order);
		}
	}
	
	public void UpdatePosition()
	{
		int UP = -1;
		int DW = 1;
		int LF = -1;
		int RT = 1;
		int NULL = 0;

		switch(this.getDirection())
		{
			case UP: 			this.ExecuteTranslation(UP, NULL, DW, NULL, ControllerOrder.DOWN);
								break;					
			case DOWN: 			this.ExecuteTranslation(DW, NULL, UP, NULL, ControllerOrder.UP);
								break;					
			case LEFT: 			this.ExecuteTranslation(NULL, LF, NULL, RT, ControllerOrder.RIGHT);
								break;					
			case RIGHT: 		this.ExecuteTranslation(NULL, RT, NULL, LF, ControllerOrder.LEFT);
								break;				
			case UPPERRIGHT : 	this.ExecuteTranslation(UP, RT, DW, LF, ControllerOrder.DOWNLEFT);
								break;				
			case UPPERLEFT:		this.ExecuteTranslation(UP, LF, DW, RT, ControllerOrder.DOWNRIGHT);
								break;				
			case DOWNRIGHT:		this.ExecuteTranslation(DW, RT, UP, LF, ControllerOrder.UPPERLEFT);
								break;				
			case DOWNLEFT: 		this.ExecuteTranslation(DW, LF, UP, RT, ControllerOrder.UPPERRIGHT);
								break;
			default:break;		
		}
	}

	public ControllerOrder getDirection() 
	{
		return Direction;
	}

	public void setDirection(ControllerOrder direction) 
	{
		Direction = direction;
	}


	
	
	

}
