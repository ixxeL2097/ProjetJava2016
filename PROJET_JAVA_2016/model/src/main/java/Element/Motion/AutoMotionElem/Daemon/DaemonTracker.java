package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DaemonTracker extends Daemon implements Runnable, ActionListener
{
	private int RandDigit;
	private Vector vector;
	
	public DaemonTracker(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(500,this);
		this.setX(x);
		this.setY(y);
		this.RandomMove = new Random();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.ExecuteTranslation();		
	}
	
	public void SelectRandomMove()
	{
		this.RandomSelectMove = this.RandomSelectMove+RandomMove.nextInt(RandDigit);	
		switch(RandomSelectMove)
		{
			case 0: this.MoveUP();		break;
			case 1: this.MoveUpRt();	break;
			case 2: this.MoveRT();		break;
			case 3: this.MoveDwRt();	break;
			case 4: this.MoveDW();		break;
			case 5: this.MoveDwLf();	break;
			case 6: this.MoveLF();		break;
			case 7: this.MoveUpLf();	break;
			case 8: this.MoveUP();		break;
			case 9: this.MoveUpRt();	break;
		}
	}
	
	public void ExecuteTranslation()
	{
		int diffX = this.getX()-this.getModel().getMapGen().getLorann().getX(); 
		int diffY = this.getY()-this.getModel().getMapGen().getLorann().getY();
		int Abs = Math.abs(diffX)-Math.abs(diffY);
		this.RandDigit=8;
		this.RandomSelectMove=0;
		
		if(diffX<0)															// plus a droite
		{
			if(diffY<0)														// plus a droite et plus en bas
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRTRT);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWRT);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRT);}			
				this.CheckArea();
			}
			else if(diffY>0)												// plus a droite et plus en haut
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRTRT);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPRT);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRT);}
				this.CheckArea();
			}	
			else															// plus à droite et meme hauteur
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.RT);
				this.CheckArea();
			}
		}
		else if(diffX>0)													// plus a gauche
		{
			if(diffY<0)														// plus a gauche et plus en bas
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLFLF);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWLF);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLF);}
				this.CheckArea();
			}
			else if(diffY>0)												// plus a gauche et plus en haut
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLFLF);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPLF);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLF);}
				this.CheckArea();
			}
			else															// plus a gauche et meme Y
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.LF);
				this.CheckArea();
			}
		}
		else
		{
			if(diffY<0)														// meme X et plus en bas
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DW);
				this.CheckArea();
			}
			else if(diffY>0)												// meme X et plus en haut
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UP);
				this.CheckArea();
			}
		}
	}
	
	public void CheckArea()
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
	
	public void executeSimpleMove(int UP_DW, int RT_LF)
	{
		Permeabilite perm = this.getModel().getMapGen().getElemMtx(this.getY()+UP_DW, this.getX()+RT_LF).getPermea();
		if(perm == Permeabilite.PENETRABLE || perm == Permeabilite.HERO)
		{
			this.MoveDaemon(UP_DW, RT_LF);
		}
		else{this.DefaultDaemonMove();}	
	}
	
	public void executeDoubleMove(int UP_DW_1, int RT_LF_1, int UP_DW_2, int RT_LF_2, int Random)
	{
		Permeabilite perm1 = this.getModel().getMapGen().getElemMtx(this.getY()+UP_DW_1, this.getX()+RT_LF_1).getPermea();
		Permeabilite perm2 = this.getModel().getMapGen().getElemMtx(this.getY()+UP_DW_2, this.getX()+RT_LF_2).getPermea();
		this.RandDigit=2;
		
		if(perm1 == Permeabilite.PENETRABLE || perm1 == Permeabilite.HERO || perm2 == Permeabilite.HERO || perm2 == Permeabilite.HERO)
		{
			this.RandomSelectMove=Random;
			this.SelectRandomMove();
		}
		else{this.DefaultDaemonMove();}	
	}
	
	public void CheckSimpleVectorProperties(int UP_DW, int RT_LF)
	{
		if(this.CheckAllowedMapBounds(UP_DW, RT_LF))
		{
			this.executeSimpleMove(UP_DW, RT_LF);
		}
		else{this.DefaultDaemonMove();}
	}
	
	public void CheckDoubleVectorProperties(int UP_DW_1, int RT_LF_1, int UP_DW_2, int RT_LF_2, int Random)
	{
		if(this.CheckAllowedMapBounds(UP_DW_1, RT_LF_1) && this.CheckAllowedMapBounds(UP_DW_2, RT_LF_2))
		{
			this.executeDoubleMove(UP_DW_1, RT_LF_1, UP_DW_2, RT_LF_2, Random);
		}
		else{this.DefaultDaemonMove();}
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}	

}
