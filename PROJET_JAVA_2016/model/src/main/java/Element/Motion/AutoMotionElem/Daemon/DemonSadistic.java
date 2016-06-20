package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;

public class DemonSadistic extends Demon implements Runnable, ActionListener
{
	public DemonSadistic() 
	{
		super("C:/ProjetJava/Sprite/monster_3.png", Permeabilite.ENEMY, 'C');
		this.MoveTimer=new Timer(400,this);
		this.setIA(new SadisticBehavior(this));
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
