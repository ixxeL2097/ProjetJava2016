package Element.Motion.AutoMotionElem;

import Element.Permeabilite;
import contract.ControllerOrder;

public class ShootingBehavior implements IArtificialIntelligence
{
	protected AutoMotionElem Mobil;
	
	public ShootingBehavior(AutoMotionElem Mobil)
	{
		this.setMobil(Mobil);
	}

	public void AutoMove()
	{
		this.UpdatePosition();
	}
	
	public void CheckUpcomingDirection(int UP_DW, int RT_LF, ControllerOrder order)
	{
		Permeabilite Perm = this.getMobil().getMapgen().getElemMtx(this.getMobil().getY()+UP_DW,this.getMobil().getX()+RT_LF).getPermea();
		
		if(Perm != Permeabilite.PENETRABLE && Perm != Permeabilite.HERO && Perm != Permeabilite.TRACKER && Perm != Permeabilite.ENEMY)
		{
			this.getMobil().setDirection(order);
		}
	}
	
	public void ExecuteTranslation(int UP_DW_1stMove, int RT_LF_1stMove, int UP_DW_2ndMove, int RT_LF_2ndMove, ControllerOrder order)
	{
		if(this.getMobil().CheckAllowedMapBounds(UP_DW_1stMove, RT_LF_1stMove))
		{
			this.getMobil().getMapgen().getModel().MoveDaemon(UP_DW_1stMove, RT_LF_1stMove, this.getMobil());
			if(this.getMobil().CheckAllowedMapBounds(UP_DW_1stMove, RT_LF_1stMove))
			{
				this.CheckUpcomingDirection(UP_DW_1stMove, RT_LF_1stMove, order);
			}
		}
		else
		{
			this.getMobil().getMapgen().getModel().MoveDaemon(UP_DW_2ndMove, RT_LF_2ndMove, this.getMobil());
			this.getMobil().setDirection(order);
		}
	}
	
	public void UpdatePosition()
	{
		int UP = -1;
		int DW = 1;
		int LF = -1;
		int RT = 1;
		int NULL = 0;

		switch(this.getMobil().getDirection())
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

	public AutoMotionElem getMobil() {
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) {
		Mobil = mobil;
	}
	
	
}
