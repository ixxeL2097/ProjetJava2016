package Element.Motion.AutoMotionElem.Daemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import model.Model;

public class DaemonTracker extends Daemon implements Runnable, ActionListener
{
	private int RandDigit;
	private Vector vector;
	
	public DaemonTracker(Model model, int y , int x) 
	{
		super(model, "C:/ProjetJava/Sprite/monster_4.png", Permeabilite.ENEMY);
		this.MoveTimer = new Timer(500,this);
		this.setX(x);
		this.setY(y);
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
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRTRT);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWRT);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWRT);}			
				this.CheckArea();
			}
			else if(diffY>0)												// plus a droite et plus en haut
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRTRT);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPRT);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPRT);}
				this.CheckArea();
			}	
			else															// plus à droite et meme hauteur
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.RT);
				this.CheckArea();
			}
		}
		else if(diffX>0)													// plus a gauche
		{
			if(diffY<0)														// plus a gauche et plus en bas
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLFLF);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWDWLF);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DWLF);}
				this.CheckArea();
			}
			else if(diffY>0)												// plus a gauche et plus en haut
			{
				if(Abs>0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLFLF);}
				if(Abs<0){this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPUPLF);}
				else{this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UPLF);}
				this.CheckArea();
			}
			else															// plus a gauche et meme Y
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.LF);
				this.CheckArea();
			}
		}
		else
		{
			if(diffY<0)														// meme X et plus en bas
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.DW);
				this.CheckArea();
			}
			else if(diffY>0)												// meme X et plus en haut
			{
				this.setVector(Element.Motion.AutoMotionElem.Daemon.Vector.UP);
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
		Permeabilite permUP = this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()).getPermea();
		Permeabilite permDW = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()).getPermea();
		Permeabilite permLF = this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()-1).getPermea();
		Permeabilite permRT = this.getModel().getMapGen().getElemMtx(this.getY(), this.getX()+1).getPermea();
		Permeabilite permUPRT = this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()+1).getPermea();
		Permeabilite permUPLF = this.getModel().getMapGen().getElemMtx(this.getY()-1, this.getX()-1).getPermea();
		Permeabilite permDWRT = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()+1).getPermea();
		Permeabilite permDWLF = this.getModel().getMapGen().getElemMtx(this.getY()+1, this.getX()-1).getPermea(); 
		
		switch(this.vector)
		{
			case UP: 	if(permUP == Permeabilite.PENETRABLE || permUP == Permeabilite.HERO)
						{
							this.MoveUP();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DW: 	if(permDW == Permeabilite.PENETRABLE || permDW == Permeabilite.HERO)
						{
							this.MoveDW();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case LF:	if(permLF == Permeabilite.PENETRABLE || permLF == Permeabilite.HERO)
						{
							this.MoveLF();	
						}
						else{this.DefaultDaemonMove();}
				break;
			case RT: 	if(permRT == Permeabilite.PENETRABLE || permRT == Permeabilite.HERO)
						{
							this.MoveRT();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case UPRT: 	if(permUPRT == Permeabilite.PENETRABLE || permUPRT == Permeabilite.HERO)
						{
							this.MoveUpRt();
						}	
						else{this.DefaultDaemonMove();}
				break;
			case UPLF: 	if(permUPLF == Permeabilite.PENETRABLE || permUPLF == Permeabilite.HERO)
						{
							this.MoveUpLf();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DWRT: 	if(permDWRT == Permeabilite.PENETRABLE || permDWRT == Permeabilite.HERO)
						{
							this.MoveDwRt();
						}
						else{this.DefaultDaemonMove();}
				break;
			case DWLF: 	if(permDWLF == Permeabilite.PENETRABLE || permDWLF == Permeabilite.HERO)
						{
							this.MoveDwLf();
						}
						else{this.DefaultDaemonMove();}
				break;
			case UPUPRT:	if(permUP == Permeabilite.PENETRABLE || permUPRT == Permeabilite.PENETRABLE || permUP == Permeabilite.HERO || permUPRT == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=0;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPRTRT:	if(permUPRT == Permeabilite.PENETRABLE || permRT == Permeabilite.PENETRABLE || permUPRT == Permeabilite.HERO || permRT == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=1;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWRTRT:	if(permRT == Permeabilite.PENETRABLE || permDWRT == Permeabilite.PENETRABLE || permRT == Permeabilite.HERO || permDWRT == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=2;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}	
				break;
			case DWDWRT:	if(permDWRT == Permeabilite.PENETRABLE || permDW == Permeabilite.PENETRABLE || permDWRT == Permeabilite.HERO || permDW == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=3;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWDWLF:	if(permDW == Permeabilite.PENETRABLE || permDWLF == Permeabilite.PENETRABLE || permDW == Permeabilite.HERO || permDWLF == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=4;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case DWLFLF:	if(permDWLF == Permeabilite.PENETRABLE || permLF == Permeabilite.PENETRABLE || permDWLF == Permeabilite.HERO || permLF == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=5;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPLFLF:	if(permLF == Permeabilite.PENETRABLE || permUPLF == Permeabilite.PENETRABLE || permLF == Permeabilite.HERO || permUPLF == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=6;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;
			case UPUPLF:	if(permUPLF == Permeabilite.PENETRABLE || permUP == Permeabilite.PENETRABLE || permUPLF == Permeabilite.HERO || permUP == Permeabilite.HERO)
							{
								this.RandDigit=2;
								this.RandomSelectMove=7;
								this.SelectRandomMove();
							}
							else{this.DefaultDaemonMove();}
				break;	
		}
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}	

}
