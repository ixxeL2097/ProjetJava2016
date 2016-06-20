package model;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Observable;

import Element.Permeabilite;
import Element.Motion.Hero;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.Projectile;
import Element.Motion.AutoMotionElem.Daemon.Demon;
import Element.MotionLess.MotionLessElemFACTORY;

import javax.swing.ImageIcon;


import contract.IModel;
import contract.IPlayer;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model extends Observable implements IModel 
{
	/** The message. */
	private MapGen MapGen;
	private MapFinder MapFinder;
	private Permeabilite permea;
	private int LevelMapOrder=0;
	private DAOMapDB daomapdb;
	private AutoMotionElem destroyedEnnemy;

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		//this.loadDB();		
		this.MapFinder = new MapFinder();
		this.MapGen = new MapGen(this.getLevelMapOrder(),this);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getMessage(java.lang.String)
	 */
	public void loadDB() 
	{
		try 
		{
			System.out.println("\n");
			System.out.println("Connection Database ...");	
			this.setDaoMapDb(new DAOMapDB(DBConnection.getInstance().getConnection()));
						
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
		this.getMapGen().getLorann().setElemIcon(this.getMapGen().getLorann().getLorannGIF());
		this.notifyView();	
	}
	
	public synchronized void MoveDaemon(int UP_DWN, int RGT_LFT, AutoMotionElem MovingObject)
	{
		int y, x, y1, x1;
		y=MovingObject.getY()+UP_DWN;
		x=MovingObject.getX()+RGT_LFT;
		y1=MovingObject.getY();
		x1=MovingObject.getX();

		this.setPermea(this.getMapGen().getElemMtx(y,x).getPermea());
		
		if(this.getPermea() == Permeabilite.PENETRABLE)
		{
			this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y1,x1 ), y, x);
			this.getMapGen().setElemMtx(MotionLessElemFACTORY.EMPTY, y1, x1);
			MovingObject.setY(MovingObject.getY()+UP_DWN);
			MovingObject.setX(MovingObject.getX()+RGT_LFT);		
		}
		else if(this.getPermea() == Permeabilite.BLOCKING && MovingObject instanceof Demon)
		{
			System.out.println("BLOCKED");
			MovingObject.DefaultDaemonMove();		
		}		
		else if(this.getPermea() == Permeabilite.HERO)
		{
			if(MovingObject instanceof Projectile)
			{
				this.getMapGen().stopShoot();
				System.out.println("On a reset le missile");
			}
			else
			{
				System.out.println("T'es MORT!!!");
				this.getMapGen().StopAllDaemons();
				this.getMapGen().DestroyAllDaemons();
				this.getMapGen().getLorann().setAlive(false);
			}
		}
		else if(this.getMapGen().getElemMtx(y,x) instanceof Demon && MovingObject instanceof Projectile)
		{
			this.getMapGen().stopShoot();
			this.setDestroyedEnnemy((Demon)this.getMapGen().getElemMtx(y,x));
			this.getDestroyedEnnemy().getMoveTimer().stop();
			this.getMapGen().resetElemMtx(y, x);
			this.setScore(this.getScore()+300);
		}
		this.notifyView();	
	}

	public synchronized void MoveLorann(int UP_DWN, int RGT_LFT) 
	{
		int y, x, y1, x1;
		y=this.getMapGen().getLorann().getY()+UP_DWN;
		x=this.getMapGen().getLorann().getX()+RGT_LFT;
		y1=this.getMapGen().getLorann().getY();
		x1=this.getMapGen().getLorann().getX();
		
		this.setPermea(this.getMapGen().getElemMtx(y,x).getPermea());
		
		this.getMapGen().ActivateDaemonsOnMap();
		
		if(this.getPermea() ==Permeabilite.ENERGY || this.getPermea() ==Permeabilite.MONEY || this.getPermea() ==Permeabilite.PENETRABLE )
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
			this.getMapGen().getLorann().setY(y);
			this.getMapGen().getLorann().setX(x);
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
			this.getMapGen().stopShoot();
			this.getMapGen().StopAllDaemons();
			this.getMapGen().DestroyAllDaemons();
			this.getMapGen().setMapLevel(this.getLevelMapOrder());
			this.getMapGen().setMapName(this.getMapFinder().getMap(this.getMapGen().getMapLevel()));
			this.getMapGen().ChangeCurrentMap();				
			this.getMapGen().getLorann().setHasMoved(false);		
		}	
		else if(this.getPermea() == Permeabilite.TRACKER || this.getPermea() == Permeabilite.ENEMY || this.getPermea() == Permeabilite.CLOSEDGATE || this.getPermea() == Permeabilite.DEATH)
		{
			System.out.println("T'es MORT!!!");
			this.getMapGen().StopAllDaemons();
			this.getMapGen().DestroyAllDaemons();
			this.getMapGen().getLorann().setAlive(false);
			System.out.println(this.getMapGen().getLorann().isAlive());
		}
		else if(this.getPermea() == Permeabilite.MISSILE)
		{
			this.getMapGen().stopShoot();
		}		
		this.getMapGen().CheckShootableElem(UP_DWN, RGT_LFT);
		this.notifyView();	
	}
	
	public void askLorannToShoot()
	{
		this.getMapGen().LorannIsShooting();
	}
	
	public void notifyView()
	{
		this.setChanged();
		this.notifyObservers();
	}

	public int getScore() 
	{
		return this.getMapGen().getLorann().getScore();
	}

	public void setScore(int score) 
	{
		Hero.setScore(score);
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
	
	public DAOMapDB getDaoMapDb() {
		return daomapdb;
	}

	public void setDaoMapDb(DAOMapDB daomapdb) {
		this.daomapdb = daomapdb;
	}

	public int getLevelMapOrder() {
		return LevelMapOrder;
	}

	public void setLevelMapOrder(int levelMapOrder) {
		LevelMapOrder = levelMapOrder;
	}

	public boolean getLorannStatus() {
		return this.getMapGen().getLorann().isAlive();
	}

	public AutoMotionElem getDestroyedEnnemy() {
		return destroyedEnnemy;
	}

	public void setDestroyedEnnemy(AutoMotionElem destroyedEnnemy) {
		this.destroyedEnnemy = destroyedEnnemy;
	}

	public int getLives() 
	{
		return this.getMapGen().getLorann().getLives();
	}
	
	public IPlayer getPlayer()
	{
		return this.getMapGen().getLorann();
	}
	
	
	
	
}
