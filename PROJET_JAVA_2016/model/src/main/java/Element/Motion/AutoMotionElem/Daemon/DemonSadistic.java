package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DemonSadistic extends Demon implements Runnable, ActionListener
{
	public DemonSadistic(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_3.png", Permeabilite.ENEMY);
		this.MoveTimer=new Timer(400,this);
		this.setX(x);
		this.setY(y);
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
