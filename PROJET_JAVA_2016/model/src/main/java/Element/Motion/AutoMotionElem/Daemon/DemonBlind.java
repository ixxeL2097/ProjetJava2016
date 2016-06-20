package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;

public class DemonBlind extends Demon
{	
	public DemonBlind() 
	{
		super("C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENEMY, 'B');
		this.MoveTimer = new Timer(450,this);
		this.RandomMove = new Random();
		this.setIA(new BlindBehavior(this));
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
