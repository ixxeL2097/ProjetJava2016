package Element.Motion.AutoMotionElem;

import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import Element.Motion.MotionElement;
import contract.ControllerOrder;

public abstract class AutoMotionElem extends MotionElement implements Runnable, ActionListener
{
	protected Timer MoveTimer;
	protected Random RandomMove;
	protected int RandomSelectMove;
	protected IArtificialIntelligence IA;
	protected ControllerOrder Direction;	

	public AutoMotionElem(String picture, Permeabilite permea, final char MapSymbol) 
	{
		super(picture, permea, MapSymbol);
		this.RandomMove = new Random();
	}
	
	public void DefaultDaemonMove()				// default move of an automotionelement
	{
		System.out.println("Default Move");
		
		this.RandomSelectMove=0;
		RandomSelectMove = RandomMove.nextInt(8);
		switch(RandomSelectMove)
		{
			case 0: if(this.CheckAllowedMapBounds(-1,0)){this.MoveUP();}
					else{this.DefaultDaemonMove();}
					break;					
			case 1: if(this.CheckAllowedMapBounds(-1,1)){this.MoveUpRt();}
					else{this.DefaultDaemonMove();}
					break;
			case 2: if(this.CheckAllowedMapBounds(0,1)){this.MoveRT();}
					else{this.DefaultDaemonMove();}
					break;
			case 3: if(this.CheckAllowedMapBounds(1,1)){this.MoveDwRt();}
					else{this.DefaultDaemonMove();}
					break;
			case 4: if(this.CheckAllowedMapBounds(1,0)){this.MoveDW();}
					else{this.DefaultDaemonMove();}
					break;
			case 5: if(this.CheckAllowedMapBounds(1,-1)){this.MoveDwLf();}
					else{this.DefaultDaemonMove();}
					break;
			case 6: if(this.CheckAllowedMapBounds(0,-1)){this.MoveLF();}	
					else{this.DefaultDaemonMove();}
					break;
			case 7: if(this.CheckAllowedMapBounds(-1,-1)){this.MoveUpLf();}
					else{this.DefaultDaemonMove();}
					break;
		}
	}
	
	public void SadisticDaemonMove()
	{
		System.out.println("Sadistic Move");
		
		int RandomUP_DW = -2;
		int RandomRT_LF = -2;
		RandomUP_DW = RandomUP_DW+RandomMove.nextInt(5);
		RandomRT_LF = RandomRT_LF+RandomMove.nextInt(5);
		
		if(this.CheckAllowedMapBounds(RandomUP_DW, RandomRT_LF)){this.MoveDaemon(RandomUP_DW,RandomRT_LF);}
		else{this.SadisticDaemonMove();}

	}
	
	public boolean CheckAllowedMapBounds(int UP_DW, int RT_LF)			// check if the next move is in bounds
	{
		if((this.getY()+UP_DW < this.getMapgen().getModel().getDimensionMapY() && this.getX()+RT_LF < this.getMapgen().getModel().getDimensionMapX()) && (this.getY()+UP_DW >= 0 && this.getX()+RT_LF >= 0)) 
		{
			return true;
		}
		else {return false;}
	}
	
	public void MoveDaemon(int NextMove_UP_DW, int NextMove_RT_LF)			// standard move of an automotionelement
	{
		this.getMapgen().getModel().MoveDaemon(NextMove_UP_DW, NextMove_RT_LF, this);
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

	public Timer getMoveTimer() {
		return MoveTimer;
	}

	public void setMoveTimer(Timer moveTimer) {
		MoveTimer = moveTimer;
	}

	public IArtificialIntelligence getIA() {
		return IA;
	}

	public void setIA(IArtificialIntelligence iA) {
		IA = iA;
	}
	
	public ControllerOrder getDirection() 
	{
		return Direction;
	}

	public void setDirection(ControllerOrder direction) 
	{
		Direction = direction;
	}

	public Random getRandomMove() {
		return RandomMove;
	}

	public void setRandomMove(Random randomMove) {
		RandomMove = randomMove;
	}

	public int getRandomSelectMove() {
		return RandomSelectMove;
	}

	public void setRandomSelectMove(int randomSelectMove) {
		RandomSelectMove = randomSelectMove;
	}
	
	
	

}
