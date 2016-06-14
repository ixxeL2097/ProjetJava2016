package Element.Motion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class Daemon extends MotionElement implements Runnable, ActionListener
{
	private Random RandomMove;
	private int RandomSelectMove;
	private Timer MoveTimer;
	private Model model;
	
	public Daemon(Model model, int y , int x) 
	{
		super("C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENNEMY);
		this.MoveTimer = new Timer(100,this);
		this.setModel(model); 
		this.X=x;
		this.Y=y;
		this.run();
	}

	public void run() 
	{
		this.MoveTimer.start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.RandomMove = new Random();
		RandomSelectMove = RandomMove.nextInt(8);
		
		switch(RandomSelectMove)
		{
			case 0: this.MoveUP();break;
			case 1: this.MoveDW();break;
			case 2: this.MoveLF();break;
			case 3: this.MoveRT();break;
			case 4: this.MoveUpLf();break;
			case 5: this.MoveUpRt();break;
			case 6: this.MoveDwLf();break;
			case 7: this.MoveDwRt();break;
		}
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void MoveDaemon(int NextMove_UP_DW, int NextMove_RT_LF)
	{
		this.getModel().MoveDaemon(NextMove_UP_DW, NextMove_RT_LF, this);
	}	
	
	public void MoveUP() 
	{
		this.MoveDaemon(-1,0);		
	}

	public void MoveDW() 
	{
		this.MoveDaemon(1,0);	
	}

	public void MoveLF() 
	{
		this.MoveDaemon(0,-1);	
	}

	public void MoveRT() 
	{
		this.MoveDaemon(0,1);	
	}

	public void MoveUpLf() 
	{
		this.MoveDaemon(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.MoveDaemon(-1,1);
	}

	public void MoveDwLf() 
	{
		this.MoveDaemon(1,-1);
	}

	public void MoveDwRt() 
	{
		this.MoveDaemon(1,1);
	}

}
