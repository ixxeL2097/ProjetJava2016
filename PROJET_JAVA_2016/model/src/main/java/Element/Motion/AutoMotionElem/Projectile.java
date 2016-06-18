package Element.Motion.AutoMotionElem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class Projectile extends AutoMotionElem implements Runnable, ActionListener
{
	private static String MAGIC_BALL="C:/ProjetJava/Sprite/250ms.gif";

	public Projectile(Model model, int Y, int X) 
	{	
		super(model, MAGIC_BALL, Permeabilite.MISSILE);
		this.MoveTimer=new Timer(250,this);
		this.setX(X);
		this.setY(Y);
		this.setIA(new ShootingBehavior(this));
		this.run();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.getIA().AutoMove();
	}

}
