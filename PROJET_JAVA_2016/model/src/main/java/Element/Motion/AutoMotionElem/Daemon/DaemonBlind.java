package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DaemonBlind extends Daemon implements Runnable, ActionListener
{	
	public DaemonBlind(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(500,this);
		this.setX(x);
		this.setY(y);
		this.RandomMove = new Random();
		this.setIA(new BlindBehaviour(this));
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
