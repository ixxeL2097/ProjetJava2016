package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DaemonRandom extends Daemon implements Runnable, ActionListener
{

	public DaemonRandom(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_2.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(350,this);
		this.setX(x);
		this.setY(y);
		this.RandomMove = new Random();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.DefaultDaemonMove();
	}

	public void run() 
	{
		this.getMoveTimer().start();
	}
		

}
