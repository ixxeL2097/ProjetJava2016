package Element.Motion;

import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public abstract class AutoMotionElem extends MotionElement 
{
	protected Model model;
	protected Timer MoveTimer;
	protected Random RandomMove;
	protected int RandomSelectMove;

	public AutoMotionElem(Model model, String picture, Permeabilite permea) 
	{
		super(picture, permea);
		this.setModel(model);
		this.RandomMove = new Random();
	}
	
	public void DefaultDaemonMove()
	{
		System.out.println("Default Move");
		
		this.RandomSelectMove=0;
		RandomSelectMove = RandomMove.nextInt(8);
		switch(RandomSelectMove)
		{
			case 0: this.MoveUP();		break;
			case 1: this.MoveUpRt();	break;
			case 2: this.MoveRT();		break;
			case 3: this.MoveDwRt();	break;
			case 4: this.MoveDW();		break;
			case 5: this.MoveDwLf();	break;
			case 6: this.MoveLF();		break;
			case 7: this.MoveUpLf();	break;
		}
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
	
	public synchronized Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}

	public Timer getMoveTimer() {
		return MoveTimer;
	}

	public void setMoveTimer(Timer moveTimer) {
		MoveTimer = moveTimer;
	}

}
