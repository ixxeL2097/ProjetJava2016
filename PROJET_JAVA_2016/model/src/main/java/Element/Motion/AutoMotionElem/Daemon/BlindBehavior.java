package Element.Motion.AutoMotionElem.Daemon;

import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.Behavior;

public class BlindBehavior extends Behavior				
{
	private int RandDigit;
	private Vector vector;
	private int deltaX;
	private int deltaY;
	private int Abs;

	public BlindBehavior(AutoMotionElem Mobil) 
	{
		super(Mobil);
	}

	public void AutoMove() 
	{
		this.UpdatePosition();
	}
	
	public void SelectRandomMove()		// select a move between all possible directions
	{
		this.getMobil().setRandomSelectMove(this.getMobil().getRandomSelectMove()+this.getMobil().getRandomMove().nextInt(RandDigit));
		//this.getMobil().RandomSelectMove = this.RandomSelectMove+RandomMove.nextInt(RandDigit);	
		switch(this.getMobil().getRandomSelectMove())
		{
			case 0: this.getMobil().MoveUP();		break;
			case 1: this.getMobil().MoveUpRt();		break;
			case 2: this.getMobil().MoveRT();		break;
			case 3: this.getMobil().MoveDwRt();		break;
			case 4: this.getMobil().MoveDW();		break;
			case 5: this.getMobil().MoveDwLf();		break;
			case 6: this.getMobil().MoveLF();		break;
			case 7: this.getMobil().MoveUpLf();		break;
			case 8: this.getMobil().MoveUP();		break;
			case 9: this.getMobil().MoveUpRt();		break;
		}
	}
	
	public void UpdatePosition()			// update the object's position which has this behavior
	{
		this.deltaX = this.getMobil().getX()-this.getMobil().getMapgen().getLorann().getX(); 
		this.deltaY = this.getMobil().getY()-this.getMobil().getMapgen().getLorann().getY();
		this.Abs = Math.abs(this.deltaX)-Math.abs(this.deltaY);
		this.RandDigit=8;
		this.getMobil().setRandomSelectMove(0);
		//this.RandomSelectMove=0;
		
		if(this.deltaX<0)															// plus a droite
		{
			this.CalculateAbsRight();
		}
		else if(this.deltaX>0)													// plus a gauche
		{
			this.CalculateAbsLeft();
		}
		else
		{
			this.CalculateAbsEqual();
		}
	}
	
	public void CheckArea()					// check move possibilities
	{	
		int UP = -1;
		int DW = 1;
		int LF = -1;
		int RT = 1;
		int NULL = 0;
		
		switch(this.vector)
		{
			case UP: 		this.CheckSimpleVectorProperties(UP, NULL);
							break;
			case DW: 		this.CheckSimpleVectorProperties(DW, NULL);		
							break;
			case LF:		this.CheckSimpleVectorProperties(NULL, LF);
							break;
			case RT: 		this.CheckSimpleVectorProperties(NULL, RT);
							break;
			case UPRT: 		this.CheckSimpleVectorProperties(UP, RT);
							break;
			case UPLF: 		this.CheckSimpleVectorProperties(UP, LF);
							break;
			case DWRT: 		this.CheckSimpleVectorProperties(DW, RT);
							break;
			case DWLF: 		this.CheckSimpleVectorProperties(DW, LF);
							break;			
			case UPUPRT:	this.CheckDoubleVectorProperties(UP, NULL, UP, RT, 0);
							break;
			case UPRTRT:	this.CheckDoubleVectorProperties(UP, RT, NULL, RT, 1);
							break;
			case DWRTRT:	this.CheckDoubleVectorProperties(DW, RT, NULL, RT, 2);
							break;
			case DWDWRT:	this.CheckDoubleVectorProperties(DW, NULL, DW, RT, 3);
							break;
			case DWDWLF:	this.CheckDoubleVectorProperties(DW, NULL, DW, LF, 4);
							break;
			case DWLFLF:	this.CheckDoubleVectorProperties(DW, LF, NULL, LF, 5);
							break;
			case UPLFLF:	this.CheckDoubleVectorProperties(UP, LF, NULL, LF, 6);
							break;
			case UPUPLF:	this.CheckDoubleVectorProperties(UP, NULL, UP, LF, 6);
							break;	
		}
	}
	
	public void CalculateAbsRight()
	{
		if(this.deltaY<0)														// more right and down
		{
			if(this.Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRTRT);}
			if(this.Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWRT);}
			else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRT);}			
			this.CheckArea();
		}
		else if(this.deltaY>0)												// more right and up
		{
			if(this.Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRTRT);}
			if(this.Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPRT);}
			else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRT);}
			this.CheckArea();
		}	
		else															// more right and same height
		{
			this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.RT);
			this.CheckArea();
		}
	}
	
	public void CalculateAbsLeft()
	{
		if(this.deltaY<0)														// more left and down
		{
			if(this.Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLFLF);}
			if(this.Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWLF);}
			else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLF);}
			this.CheckArea();
		}
		else if(this.deltaY>0)												// more left and up
		{
			if(this.Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLFLF);}
			if(this.Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPLF);}
			else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLF);}
			this.CheckArea();
		}
		else															// more left and same height
		{
			this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.LF);
			this.CheckArea();
		}
	}
	
	public void CalculateAbsEqual()
	{
		if(this.deltaY<0)														// same width and more down 
		{
			this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DW);
			this.CheckArea();
		}
		else if(this.deltaY>0)												// same width and more up
		{
			this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UP);
			this.CheckArea();
		}
	}
	
	public void executeSimpleMove(int UP_DW, int RT_LF)
	{
		Permeabilite perm = this.getMobil().getMapgen().getElemMtx(this.getMobil().getY()+UP_DW, this.getMobil().getX()+RT_LF).getPermea();
		if(perm == Permeabilite.PENETRABLE || perm == Permeabilite.HERO)
		{
			this.getMobil().MoveDaemon(UP_DW, RT_LF);
		}
		else{this.getMobil().DefaultDaemonMove();}	
	}
	
	public void executeDoubleMove(int UP_DW_1, int RT_LF_1, int UP_DW_2, int RT_LF_2, int Random)
	{
		Permeabilite perm1 = this.getMobil().getMapgen().getElemMtx(this.getMobil().getY()+UP_DW_1, this.getMobil().getX()+RT_LF_1).getPermea();
		Permeabilite perm2 = this.getMobil().getMapgen().getElemMtx(this.getMobil().getY()+UP_DW_2, this.getMobil().getX()+RT_LF_2).getPermea();
		this.RandDigit=2;
		
		if(perm1 == Permeabilite.PENETRABLE || perm1 == Permeabilite.HERO || perm2 == Permeabilite.HERO || perm2 == Permeabilite.HERO)
		{
			this.getMobil().setRandomSelectMove(Random);
			//this.RandomSelectMove=Random;
			this.SelectRandomMove();
		}
		else{this.getMobil().DefaultDaemonMove();}	
	}
	
	public void CheckSimpleVectorProperties(int UP_DW, int RT_LF)
	{
		if(this.getMobil().CheckAllowedMapBounds(UP_DW, RT_LF))
		{
			this.executeSimpleMove(UP_DW, RT_LF);
		}
		else{this.getMobil().DefaultDaemonMove();}
	}
	
	public void CheckDoubleVectorProperties(int UP_DW_1, int RT_LF_1, int UP_DW_2, int RT_LF_2, int Random)
	{
		if(this.getMobil().CheckAllowedMapBounds(UP_DW_1, RT_LF_1) && this.getMobil().CheckAllowedMapBounds(UP_DW_2, RT_LF_2))
		{
			this.executeDoubleMove(UP_DW_1, RT_LF_1, UP_DW_2, RT_LF_2, Random);
		}
		else{this.getMobil().DefaultDaemonMove();}
	}
	
	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}	

}
