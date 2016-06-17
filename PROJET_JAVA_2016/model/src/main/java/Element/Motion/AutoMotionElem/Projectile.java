package Element.Motion.AutoMotionElem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import contract.ControllerOrder;
import model.Model;

public class Projectile extends AutoMotionElem implements Runnable, ActionListener
{
	private static String MAGIC_BALL="C:/ProjetJava/Sprite/nazi.gif";
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
	
	public void UpdatePosition()
	{
		switch(this.getDirection())
		{
			case UP: 			this.getModel().MoveDaemon(-1, 0, this);
								Permeabilite PermUP = this.getModel().getMapGen().getElemMtx(this.getY()-1,this.getX()).getPermea();
								if(PermUP != Permeabilite.PENETRABLE && PermUP != Permeabilite.HERO && PermUP != Permeabilite.TRACKER && PermUP != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.DOWN);
								}break;
								
			case DOWN: 			this.getModel().MoveDaemon(+1, 0, this);
								Permeabilite PermDW = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea();
								if(PermDW != Permeabilite.PENETRABLE && PermDW != Permeabilite.HERO && PermDW != Permeabilite.TRACKER && PermDW != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.UP);
								}break;
								
			case LEFT: 			this.getModel().MoveDaemon(0, -1, this);
								Permeabilite PermLF = this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea();
								if(PermLF != Permeabilite.PENETRABLE && PermLF != Permeabilite.HERO && PermLF != Permeabilite.TRACKER && PermLF != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.RIGHT);
								}break;
								
			case RIGHT: 		this.getModel().MoveDaemon(0, +1, this);	
								Permeabilite PermRT = this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea();
								if(PermRT != Permeabilite.PENETRABLE && PermRT != Permeabilite.HERO && PermRT != Permeabilite.TRACKER && PermRT != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.LEFT);
								}break;
								
			case UPPERRIGHT : 	this.getModel().MoveDaemon(-1, +1, this);	
								Permeabilite PermUPRT = this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()+1).getPermea();
								if(PermUPRT != Permeabilite.PENETRABLE && PermUPRT != Permeabilite.HERO && PermUPRT != Permeabilite.TRACKER && PermUPRT != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.DOWNLEFT);
								}break;
								
			case UPPERLEFT:		this.getModel().MoveDaemon(-1, -1, this);	
								Permeabilite PermUPLF = this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea();
								if(PermUPLF != Permeabilite.PENETRABLE && PermUPLF != Permeabilite.HERO && PermUPLF != Permeabilite.TRACKER && PermUPLF != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.DOWNRIGHT);
								}break;
								
			case DOWNRIGHT:		this.getModel().MoveDaemon(+1, +1, this);	
								Permeabilite PermDWRT = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea();
								if(PermDWRT != Permeabilite.PENETRABLE && PermDWRT != Permeabilite.HERO && PermDWRT != Permeabilite.TRACKER && PermDWRT != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.UPPERLEFT);
								}break;
								
			case DOWNLEFT: 		this.getModel().MoveDaemon(+1, -1, this);	
								Permeabilite PermDWLF = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea();
								if(PermDWLF != Permeabilite.PENETRABLE && PermDWLF != Permeabilite.HERO && PermDWLF != Permeabilite.TRACKER && PermDWLF != Permeabilite.ENEMY)
								{
									this.setDirection(ControllerOrder.UPPERRIGHT);
								}break;
			default:
									break;		
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
