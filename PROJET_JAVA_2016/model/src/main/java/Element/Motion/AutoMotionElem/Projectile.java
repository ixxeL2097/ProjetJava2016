package Element.Motion.AutoMotionElem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import contract.ControllerOrder;
import model.Model;

public class Projectile extends AutoMotionElem implements Runnable, ActionListener
{
	protected static String MAGIC_BALL="C:/ProjetJava/Sprite/250ms.gif";
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
		switch(this.getDirection())
		{
			case UP: 			this.getModel().MoveDaemon(-1, 0, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY()-1,this.getX()).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.DOWN);
								}break;
			case DOWN: 			this.getModel().MoveDaemon(+1, 0, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.UP);
								}break;
			case LEFT: 			this.getModel().MoveDaemon(0, -1, this);
								if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.RIGHT);
								}break;
			case RIGHT: 		this.getModel().MoveDaemon(0, +1, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.LEFT);
								}break;
			case UPPERRIGHT : 	this.getModel().MoveDaemon(-1, +1, this);		
								if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()+1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.DOWNLEFT);
								}break;
			case UPPERLEFT:		this.getModel().MoveDaemon(-1, -1, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.DOWNRIGHT);
								}break;
			case DOWNRIGHT:		this.getModel().MoveDaemon(+1, +1, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.UPPERLEFT);
								}break;
			case DOWNLEFT: 		this.getModel().MoveDaemon(+1, -1, this);	
								if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea()==Permeabilite.BLOCKING)
								{
									this.setDirection(ControllerOrder.UPPERRIGHT);
								}break;
								default:
									break;		
		}
	}
	
	public void Shoot()
	{
		
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
