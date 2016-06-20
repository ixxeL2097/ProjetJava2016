package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DemonRandom extends Demon implements Runnable, ActionListener
{

	public DemonRandom(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_2.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(300,this);
		this.setX(x);
		this.setY(y);
		this.RandomMove = new Random();
		this.setIA(new RandomBehavior(this));
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.getIA().AutoMove();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}
		

}
