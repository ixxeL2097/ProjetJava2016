package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.IMapGen;

public class DemonBlind extends Demon implements Runnable, ActionListener
{	
	public DemonBlind(IMapGen mapgen, int y , int x) 
	{
		super(mapgen, "C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(450,this);
		this.setX(x);
		this.setY(y);
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
