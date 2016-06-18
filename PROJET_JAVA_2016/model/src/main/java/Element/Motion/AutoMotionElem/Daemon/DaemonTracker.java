package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DaemonTracker extends Daemon implements Runnable, ActionListener
{
	public DaemonTracker(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_1.png", Permeabilite.TRACKER);
		
		this.MoveTimer=new Timer(450,this);
		this.setX(x);
		this.setY(y);
		this.setIA(new TrackingBehavior(this));
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