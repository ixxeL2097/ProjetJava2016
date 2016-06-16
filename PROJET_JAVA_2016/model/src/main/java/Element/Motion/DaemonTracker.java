package Element.Motion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;
import Element.Motion.Vector;

public class DaemonTracker extends MotionElement implements Runnable, ActionListener
{
	private Random RandomMove;
	private int RandomSelectMove;
	private Timer MoveTimer;
	private Model model;
	private int RandDigit;
	private Vector vector;
	
	public DaemonTracker(Model model, int y , int x) 
	{
		super("C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENNEMY);
		this.MoveTimer = new Timer(600,this);
		this.setModel(model); 
		this.X=x;
		this.Y=y;
		this.RandomMove = new Random();
	}

	public void run() 
	{
		this.MoveTimer.start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		int diffX = this.getX()-this.getModel().getLorann().getX(); 
		int diffY = this.getY()-this.getModel().getLorann().getY();
		int Abs = Math.abs(diffX)-Math.abs(diffY);
		this.RandDigit=8;
		this.RandomSelectMove=0;
		
		if(diffX<0)															// plus a droite
		{
			if(diffY<0)														// plus a droite et plus en bas
			{
				System.out.println("DWRT");
				if(Abs>0){this.setVector(Element.Motion.Vector.DWRTRT);}
				if(Abs<0){this.setVector(Element.Motion.Vector.DWDWRT);}
				else{this.setVector(Element.Motion.Vector.DWRT);}			
				this.CheckArea();
			}
			else if(diffY>0)												// plus a droite et plus en haut
			{
				System.out.println("UPRT");
				if(Abs>0){this.setVector(Element.Motion.Vector.UPRTRT);}
				if(Abs<0){this.setVector(Element.Motion.Vector.UPUPRT);}
				else{this.setVector(Element.Motion.Vector.UPRT);}
				this.CheckArea();
			}	
			else															// plus à droite et meme hauteur
			{
				System.out.println("RT");
				this.setVector(Element.Motion.Vector.RT);
				this.CheckArea();
			}
		}
		else if(diffX>0)													// plus a gauche
		{
			if(diffY<0)														// plus a gauche et plus en bas
			{
				System.out.println("DWLF");
				if(Abs>0){this.setVector(Element.Motion.Vector.DWLFLF);}
				if(Abs<0){this.setVector(Element.Motion.Vector.DWDWLF);}
				else{this.setVector(Element.Motion.Vector.DWLF);}
				this.CheckArea();
			}
			else if(diffY>0)												// plus a gauche et plus en haut
			{
				System.out.println("UPLF");
				if(Abs>0){this.setVector(Element.Motion.Vector.UPLFLF);}
				if(Abs<0){this.setVector(Element.Motion.Vector.UPUPLF);}
				else{this.setVector(Element.Motion.Vector.UPLF);}
				this.CheckArea();
			}
			else															// plus a gauche et meme Y
			{
				System.out.println("LF");
				this.setVector(Element.Motion.Vector.LF);
				this.CheckArea();
			}
		}
		else
		{
			if(diffY<0)														// meme X et plus en bas
			{
				System.out.println("DW");
				this.setVector(Element.Motion.Vector.DW);
				this.CheckArea();
			}
			else if(diffY>0)												// meme X et plus en haut
			{
				System.out.println("UP");
				this.setVector(Element.Motion.Vector.UP);
				this.CheckArea();
			}
		}				
	}
	
	public void SelectRandomMove()
	{
		this.RandomSelectMove = this.RandomSelectMove+RandomMove.nextInt(RandDigit);	
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
			case 8: this.MoveUP();		break;
			case 9: this.MoveUpRt();	break;
		}
	}
	
	public void CheckArea()
	{
		switch(this.vector)
		{
			case UP: 	if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveUP();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DW: 	if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveDW();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case LF:	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveLF();	
						}
						else{this.DefaultDaemonMove();}
				break;
			case RT: 	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveRT();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case UPRT: 	if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveUpRt();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case UPLF: 	if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveUpLf();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DWRT: 	if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveDwRt();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DWLF: 	if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
						{
							this.MoveDwLf();
						}
						else{this.DefaultDaemonMove();}
				break;
			case UPUPRT:	if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=0;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPRTRT:	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=1;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWRTRT:	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=2;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}	
				break;
			case DWDWRT:	if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=3;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWDWLF:	if(this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=4;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWLFLF:	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=5;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPLFLF:	if(this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=6;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPUPLF:	if(this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()).getPermea() == Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea() == Permeabilite.PENETRABLE)
							{
								this.RandDigit=2;
								this.RandomSelectMove=7;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;	
		}
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

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}
	
	public Model getModel() {
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
