package model;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Observable;

import Element.Permeabilite;
import Element.Motion.*;
import Element.MotionLess.MotionLessElemFACTORY;

import javax.swing.ImageIcon;

import contract.ControllerOrder;
import contract.IModel;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model extends Observable implements IModel 
{
	/** The message. */
	private MapGen MapGen;
	private Hero Lorann;
	private Projectile Missile;
	private MapFinder MapFinder;
	private Permeabilite permea;
	private int score=0;
	private DAOHelloWorld daohelloworld;
	private int LevelMapOrder=0;

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		//this.loadMessage();		
		this.MapFinder = new MapFinder();
		this.MapGen = new MapGen(this.getLevelMapOrder(),this);
		this.Lorann = new Hero(5,10);
		this.MapGen.PlaceLorann(this.getLorann());
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getMessage(java.lang.String)
	 */
	public void loadMessage() 
	{
		try 
		{
			System.out.println("\n");
			System.out.println("Connection Database ...");		
			this.daohelloworld = new DAOHelloWorld(DBConnection.getInstance().getConnection());			
		} 
		catch (final SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getObservable()
	 */
	public Observable getObservable() 
	{
		return this;
	}

	public void setLorannGIF() 
	{
		this.getLorann().setElemIcon(this.getLorann().getLorannGIF());
		this.setChanged();
		this.notifyObservers();
	}
	
	public synchronized void MoveDaemon(int UP_DWN, int RGT_LFT, AutoMotionElem daemon)
	{
		int y, x, y1, x1;
		y=daemon.getY()+UP_DWN;
		x=daemon.getX()+RGT_LFT;
		y1=daemon.getY();
		x1=daemon.getX();
		
		this.setPermea(this.getMapGen().getElemMtx(y,x).getPermea());
		
		if(this.permea == Permeabilite.PENETRABLE)
		{
			this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y1,x1 ), y, x);
			this.getMapGen().setElemMtx(MotionLessElemFACTORY.EMPTY, y1, x1);
			daemon.setY(daemon.getY()+UP_DWN);
			daemon.setX(daemon.getX()+RGT_LFT);		
		}
		else if(this.permea == Permeabilite.BLOCKING)
		{
			System.out.println("BLOCKED");
			daemon.DefaultDaemonMove();
		}		
		else if(this.permea == Permeabilite.HERO)
		{
			if(daemon instanceof Projectile)
			{
				daemon.getMoveTimer().stop();
				this.getMapGen().ElemMtx[daemon.getY()][daemon.getX()]=MotionLessElemFACTORY.EMPTY;
				this.Missile=null;
			}
			else
			{
				System.out.println("T'es MORT!!!");
				this.StopAllDaemons();
				this.getLorann().setAlive(false);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}

	public synchronized void MoveLorann(int UP_DWN, int RGT_LFT) 
	{
		int y, x, y1, x1;
		y=this.getLorann().getY()+UP_DWN;
		x=this.getLorann().getX()+RGT_LFT;
		y1=this.getLorann().getY();
		x1=this.getLorann().getX();
		
		this.setPermea(this.getMapGen().getElemMtx(y,x).getPermea());
		
		if(this.getLorann().isHasMoved()==false && this.getMapGen().getMapLevel() != 0)
		{
			this.getLorann().setHasMoved(true);
			this.AnimateDaemons();
		}
		
		if(this.getPermea() ==Permeabilite.ENERGY || this.getPermea() ==Permeabilite.MONEY || this.getPermea() ==Permeabilite.PENETRABLE  )
		{
			switch(this.getPermea())
			{
				case ENERGY:
						this.getMapGen().UnlockGate();
						this.setLevelMapOrder(0);
						break;
				case MONEY:
						this.setScore(this.getScore()+650);
				default:	break;
			}
			this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y1,x1), y, x);
			this.getMapGen().setElemMtx(MotionLessElemFACTORY.EMPTY, y1, x1);
			this.getLorann().setY(y);
			this.getLorann().setX(x);
		}
		else if(this.getPermea() == Permeabilite.TRANSLATABLE)
		{
			if(this.getMapGen().getElemMtx(y+UP_DWN,x+RGT_LFT).getPermea()==Permeabilite.PENETRABLE)
			{
				this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y,x), y+UP_DWN, x+RGT_LFT);
				this.getMapGen().setElemMtx(MotionLessElemFACTORY.EMPTY, y, x);
			}
		}
		else if(this.getPermea() == Permeabilite.LVLCHANGE)
		{
			this.setLevelMapOrder(this.getLevelMapOrder()-UP_DWN);
			this.getMapGen().ChangeLevelMap();
		}
		else if(this.getPermea() == Permeabilite.GATE)
		{
			this.StopAllDaemons();
			this.getMapGen().DestroyDaemons();
			this.getMapGen().setMapLevel(this.getLevelMapOrder());
			this.getMapGen().setMapName(this.getMapFinder().getMap(this.getMapGen().getMapLevel()));
			this.getMapGen().ResetWelcomeMenu(this.getLorann());				
			this.getLorann().setHasMoved(false);		
		}	
		else if(this.getPermea() == Permeabilite.TRACKER || this.getPermea() == Permeabilite.ENEMY || this.getPermea() == Permeabilite.CLOSEDGATE)
		{
			System.out.println("T'es MORT!!!");
			this.StopAllDaemons();
			this.getMapGen().DestroyDaemons();
			this.getLorann().setAlive(false);
		}
		this.setChanged();
		this.notifyObservers();	
	}
	
	public synchronized void LorannIsShooting()
	{
		System.out.println("On shoot");
		if(this.getMissile() == null)
		{
			System.out.println("Projectile null");
			switch(this.getLorann().getLastLorannMove())
			{
				case UP:			this.setMissile(new Projectile(this, this.getLorann().getY()+1, this.getLorann().getX()));
									this.getMissile().setDirection(ControllerOrder.DOWN);
				break;
				case DOWN:			this.setMissile(new Projectile(this, this.getLorann().getY()-1, this.getLorann().getX()));
									this.getMissile().setDirection(ControllerOrder.UP);
				break;
				case LEFT:			this.setMissile(new Projectile(this, this.getLorann().getY(), this.getLorann().getX()+1));
									this.getMissile().setDirection(ControllerOrder.RIGHT);
				break;
				case RIGHT:			this.setMissile(new Projectile(this, this.getLorann().getY(), this.getLorann().getX()-1));
									this.getMissile().setDirection(ControllerOrder.LEFT);
				break;
				case UPPERRIGHT:	this.setMissile(new Projectile(this, this.getLorann().getY()+1, this.getLorann().getX()-1));
									this.getMissile().setDirection(ControllerOrder.DOWNLEFT);
				break;
				case UPPERLEFT:		this.setMissile(new Projectile(this, this.getLorann().getY()+1, this.getLorann().getX()+1));
									this.getMissile().setDirection(ControllerOrder.DOWNRIGHT);
				break;
				case DOWNLEFT:		this.setMissile(new Projectile(this, this.getLorann().getY()-1, this.getLorann().getX()+1));
									this.getMissile().setDirection(ControllerOrder.UPPERRIGHT);
				break;
				case DOWNRIGHT:		this.setMissile(new Projectile(this, this.getLorann().getY()-1, this.getLorann().getX()-1));
									this.getMissile().setDirection(ControllerOrder.UPPERLEFT);
				break;
				default:
				break;			
			}
			this.getMapGen().PlaceLorann(this.getMissile());		
		}
		System.out.println("Projectile ok on affiche");
		this.setChanged();
		this.notifyObservers();	
	}
	
	public boolean getLorannStatus() {
		return this.getLorann().isAlive();
	}
	
	public synchronized void stopShoot()
	{
		
	}

	public synchronized void AnimateDaemons()
	{
		if(this.getMapGen().getSmartTracker() != null)
		{
			this.getMapGen().getSmartTracker().run();
		}
		if(this.getMapGen().getStupidTracker() != null)
		{
			this.getMapGen().getStupidTracker().run();
		}
		if(this.getMapGen().getBrainLessTracker() != null)
		{
			this.getMapGen().getBrainLessTracker().run();
		}
	}
	
	public synchronized void StopAllDaemons()
	{
		if(this.getMapGen().getSmartTracker() != null)
		{
			this.getMapGen().getSmartTracker().getMoveTimer().stop();
		}
		if(this.getMapGen().getStupidTracker() != null)
		{
			this.getMapGen().getStupidTracker().getMoveTimer().stop();
		}
		if(this.getMapGen().getBrainLessTracker() != null)
		{
			this.getMapGen().getBrainLessTracker().getMoveTimer().stop();
		}	
	}

	public void MoveUP() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUp());
		this.getLorann().setLastLorannMove(ControllerOrder.UP);
		this.MoveLorann(-1,0);		
	}

	public void MoveDW() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDw());
		this.getLorann().setLastLorannMove(ControllerOrder.DOWN);
		this.MoveLorann(1,0);	
	}

	public void MoveLF() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveLf());
		this.getLorann().setLastLorannMove(ControllerOrder.LEFT);
		this.MoveLorann(0,-1);	
	}

	public void MoveRT() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveRt());
		this.getLorann().setLastLorannMove(ControllerOrder.RIGHT);
		this.MoveLorann(0,1);	
	}

	public void MoveUpLf() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUpLf());
		this.getLorann().setLastLorannMove(ControllerOrder.UPPERLEFT);
		this.MoveLorann(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUpRt());
		this.getLorann().setLastLorannMove(ControllerOrder.UPPERRIGHT);
		this.MoveLorann(-1,1);
	}

	public void MoveDwLf() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDwLf());
		this.getLorann().setLastLorannMove(ControllerOrder.DOWNLEFT);
		this.MoveLorann(1,-1);
	}

	public void MoveDwRt() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDwRt());
		this.getLorann().setLastLorannMove(ControllerOrder.DOWNRIGHT);
		this.MoveLorann(1,1);
	}

	public int getScore() 
	{
		return this.score;
	}

	public void setScore(int score) 
	{
		this.score=score;
	}
	
	public ImageIcon getImageElement(int y, int x) 
	{
		return this.MapGen.ElemMtx[y][x].getElemIcon();
	}

	public Dimension getD() 
	{
		return DimensionMap.d;
	}
	
	public int getDimensionMapX() 
	{
		return DimensionMap.X;
	}

	public int getDimensionMapY() 
	{
		return DimensionMap.Y;
	}

	public int getWindowMapWIDTH() 
	{
		return DimensionMap.WINDOW_WIDTH;
	}

	public int getWindowMapHEIGHT() 
	{
		return DimensionMap.WINDOW_HEIGHT;
	}

	public synchronized Hero getLorann() {
		return Lorann;
	}

	public synchronized void setLorann(Hero lorann) {
		Lorann = lorann;
	}

	public synchronized MapGen getMapGen() {
		return MapGen;
	}

	public synchronized Permeabilite getPermea() {
		return permea;
	}

	public synchronized void setPermea(Permeabilite permea) {
		this.permea = permea;
	}


	public MapFinder getMapFinder() {
		return MapFinder;
	}
	
	public DAOHelloWorld getDaohelloworld() {
		return daohelloworld;
	}


	public void setDaohelloworld(DAOHelloWorld daohelloworld) {
		this.daohelloworld = daohelloworld;
	}

	public int getLevelMapOrder() {
		return LevelMapOrder;
	}

	public void setLevelMapOrder(int levelMapOrder) {
		LevelMapOrder = levelMapOrder;
	}


	public Projectile getMissile() {
		return Missile;
	}


	public void setMissile(Projectile missile) {
		Missile = missile;
	}
	
	
	
}
