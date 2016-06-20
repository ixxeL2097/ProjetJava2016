package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;

public class DemonTracker extends Demon implements Runnable, ActionListener
{
	public DemonTracker() 
	{
		super("C:/ProjetJava/Sprite/monster_1.png", Permeabilite.TRACKER, 'D');		
		this.MoveTimer=new Timer(400,this);
		this.setIA(new TrackingBehavior(this, 0));
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