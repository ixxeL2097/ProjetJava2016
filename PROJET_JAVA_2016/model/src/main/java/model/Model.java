package model;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Observable;

import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.Projectile;
import Element.Motion.AutoMotionElem.Daemon.Daemon;
import Element.Motion.AutoMotionElem.Daemon.TrackingBehavior;
import Element.MotionLess.MotionLessElem;
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
	private AutoMotionElem Missile;
	private MapFinder MapFinder;
	private Permeabilite permea;
	private int LevelMapOrder=0;
	private DAOHelloWorld daohelloworld;
	private AutoMotionElem destroyedEnnemy;

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		//this.loadMessage();		
		this.MapFinder = new MapFinder();
		this.MapGen = new MapGen(this.getLevelMapOrder(),this);
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
			this.setDaohelloworld(new DAOHelloWorld(DBConnection.getInstance().getConnection()));
			//this.daohelloworld = new DAOHelloWorld(DBConnection.getInstance().getConnection());			
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
		this.setChanged();
		this.notifyObservers();
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
		else if(this.getPermea() == Permeabilite.BLOCKING && MovingObject instanceof Daemon)
		{
			System.out.println("BLOCKED");
			MovingObject.DefaultDaemonMove();		
		}		
		else if(this.getPermea() == Permeabilite.HERO)
		{
			if(MovingObject instanceof Projectile)
			{
				this.stopShoot();
			}
			else
			{
				System.out.println("T'es MORT!!!");
				this.getMapGen().StopAllDaemons();
				this.getMapGen().DestroyAllDaemons();
				this.getMapGen().getLorann().setAlive(false);
			}
		}
		else if(this.getMapGen().getElemMtx(y,x) instanceof Daemon && MovingObject instanceof Projectile)
		{
			this.stopShoot();
			this.setDestroyedEnnemy((Daemon)this.getMapGen().getElemMtx(y,x));
			this.getDestroyedEnnemy().getMoveTimer().stop();
			this.getMapGen().resetElemMtx(y, x);	
		}
		this.setChanged();
		this.notifyObservers();
	}

	public synchronized void MoveLorann(int UP_DWN, int RGT_LFT) 
	{
		int y, x, y1, x1;
		y=this.getMapGen().getLorann().getY()+UP_DWN;
		x=this.getMapGen().getLorann().getX()+RGT_LFT;
		y1=this.getMapGen().getLorann().getY();
		x1=this.getMapGen().getLorann().getX();
		
		this.setPermea(this.getMapGen().getElemMtx(y,x).getPermea());
		
		this.ActivateDaemonsOnMap();
		
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
			this.stopShoot();
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
		}
		else if(this.getPermea() == Permeabilite.MISSILE)
		{
			this.stopShoot();
		}
		
		this.CheckShootableElem(UP_DWN, RGT_LFT);
		this.setChanged();
		this.notifyObservers();	
	}
	
	public void ActivateDaemonsOnMap()
	{
		if(this.getMapGen().getLorann().isHasMoved()==false && this.getMapGen().getMapLevel() != 0)
		{
			this.getMapGen().getLorann().setHasMoved(true);
			this.getMapGen().AnimateDaemons();
		}
	}
	
	public void CheckShootableElem(int UP_DWN, int RGT_LFT)
	{
		if(this.getMapGen().getElemMtx(this.getMapGen().getLorann().getY()-UP_DWN, this.getMapGen().getLorann().getX()-RGT_LFT) instanceof MotionLessElem)
		{
			if(this.getMapGen().getElemMtx(this.getMapGen().getLorann().getY()-UP_DWN, this.getMapGen().getLorann().getX()-RGT_LFT) == MotionLessElemFACTORY.EMPTY)
			{
				this.getMapGen().getLorann().setShootable(true);
			}
			else{this.getMapGen().getLorann().setShootable(false);}
		}
		else{this.getMapGen().getLorann().setShootable(true);}
	}

	
	public synchronized void LorannIsShooting()
	{
		if(this.getMissile() == null)
		{
			if(this.getMapGen().getLorann().isShootable())
			{
				switch(this.getMapGen().getLorann().getLastLorannMove())
				{
					case UP:			this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()+1, this.getMapGen().getLorann().getX()));
										this.getMissile().setDirection(ControllerOrder.DOWN);
										break;
					case DOWN:			this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()-1, this.getMapGen().getLorann().getX()));
										this.getMissile().setDirection(ControllerOrder.UP);
										break;
					case LEFT:			this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY(), this.getMapGen().getLorann().getX()+1));
										this.getMissile().setDirection(ControllerOrder.RIGHT);
										break;
					case RIGHT:			this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY(), this.getMapGen().getLorann().getX()-1));
										this.getMissile().setDirection(ControllerOrder.LEFT);
										break;
					case UPPERRIGHT:	this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()+1, this.getMapGen().getLorann().getX()-1));
										this.getMissile().setDirection(ControllerOrder.DOWNLEFT);
										break;
					case UPPERLEFT:		this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()+1, this.getMapGen().getLorann().getX()+1));
										this.getMissile().setDirection(ControllerOrder.DOWNRIGHT);
										break;
					case DOWNLEFT:		this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()-1, this.getMapGen().getLorann().getX()+1));
										this.getMissile().setDirection(ControllerOrder.UPPERRIGHT);
										break;
					case DOWNRIGHT:		this.setMissile(new Projectile(this, this.getMapGen().getLorann().getY()-1, this.getMapGen().getLorann().getX()-1));
										this.getMissile().setDirection(ControllerOrder.UPPERLEFT);
										break;
					default:
					break;			
				}
				this.getMapGen().PlaceMotionElem(this.getMissile());	
			}			
		}
		else
		{
			this.getMissile().setIA(new TrackingBehavior(this.getMissile()));
		}
		this.setChanged();
		this.notifyObservers();	
	}
	
	public synchronized void stopShoot()
	{
		if(this.getMissile() != null)
		{
			this.getMissile().getMoveTimer().stop();
			this.getMapGen().setElemMtx(MotionLessElemFACTORY.EMPTY, this.getMissile().getY(), this.getMissile().getX());
			this.setMissile(null);
		}
	}

	public void MoveLorannUP() 
	{
		this.getMapGen().getLorann().MoveUP();
	}

	public void MoveLorannDW() 
	{
		this.getMapGen().getLorann().MoveDW();	
	}

	public void MoveLorannLF() 
	{
		this.getMapGen().getLorann().MoveLF();
	}

	public void MoveLorannRT() 
	{
		this.getMapGen().getLorann().MoveRT();	
	}

	public void MoveLorannUpLf() 
	{
		this.getMapGen().getLorann().MoveUpLf();
	}

	public void MoveLorannUpRt() 
	{
		this.getMapGen().getLorann().MoveUpRt();
	}

	public void MoveLorannDwLf() 
	{
		this.getMapGen().getLorann().MoveDwLf();
	}

	public void MoveLorannDwRt() 
	{
		this.getMapGen().getLorann().MoveDwRt();
	}

	public int getScore() 
	{
		return this.getMapGen().getLorann().getScore();
	}

	public void setScore(int score) 
	{
		this.getMapGen().getLorann().setScore(score);
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


	public AutoMotionElem getMissile() {
		return Missile;
	}


	public void setMissile(Projectile missile) {
		Missile = missile;
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
	
	
	
	
}
