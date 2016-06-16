package model;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Observable;

import Element.Permeabilite;
import Element.Motion.*;
import Element.MotionLess.Empty;

import javax.swing.ImageIcon;

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
	private MapFinder MapFinder;
	private Permeabilite permea;
	private int score=0;
	private DemonOMG DaemonMaster;
	private DAOHelloWorld daohelloworld;

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		//this.loadMessage();		
		this.MapFinder = new MapFinder();
		this.MapGen = new MapGen(this.getMapFinder().getMap(3), this);	
		this.Lorann = new Hero(5,10);
		this.MapGen.PlaceLorann(this.getLorann());
		this.DaemonMaster = new DemonOMG(15, 6, this);
		this.MapGen.PlaceLorann(this.DaemonMaster);
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
	
	public synchronized void MoveDaemon(int UP_DWN, int RGT_LFT, MotionElement daemon)
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
			this.getMapGen().setElemMtx(new Empty(), y1, x1);
			daemon.setY(daemon.getY()+UP_DWN);
			daemon.setX(daemon.getX()+RGT_LFT);		
		}
		else if(this.permea == Permeabilite.BLOCKING)
		{
			System.out.println("BLOCKED");
			//daemon.DefaultDaemonMove();
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
		
		if(this.getPermea() != Permeabilite.BLOCKING && this.getPermea() != Permeabilite.TRANSLATABLE && this.getPermea() != Permeabilite.LVLCHANGE && this.getPermea() != Permeabilite.VICTORY && this.getPermea() != Permeabilite.CLOSEDGATE)
		{
			switch(this.getPermea())
			{
				case ENERGY:
						this.getMapGen().UnlockGate();
						this.getMapGen().setMapLevel(0);
						break;
				case MONEY:
						this.setScore(this.getScore()+650);
				default:	break;
			}
			this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y1,x1), y, x);
			this.getMapGen().setElemMtx(new Empty(), y1, x1);
			this.getLorann().setY(y);
			this.getLorann().setX(x);
		}
		else if(this.permea == Permeabilite.TRANSLATABLE)
		{
			if(this.getMapGen().getElemMtx(y+UP_DWN,x+RGT_LFT).getPermea()==Permeabilite.PENETRABLE)
			{
				this.getMapGen().setElemMtx(this.getMapGen().getElemMtx(y,x), y+UP_DWN, x+RGT_LFT);
				this.getMapGen().setElemMtx(new Empty(), y, x);
			}
		}
		else if(this.permea == Permeabilite.LVLCHANGE)
		{
			this.getMapGen().ChangeLevelMap(UP_DWN);
		}
		else if(this.permea == Permeabilite.VICTORY)
		{
			this.getMapGen().setMapName(this.getMapFinder().getMap(this.getMapGen().getMapLevel()));
			this.getMapGen().ResetWelcomeMenu(this.getLorann());
		}	
		this.setChanged();
		this.notifyObservers();	
		this.DaemonMaster.run();
	}

	public void MoveUP() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUp());
		this.MoveLorann(-1,0);		
	}

	public void MoveDW() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDw());
		this.MoveLorann(1,0);	
	}

	public void MoveLF() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveLf());
		this.MoveLorann(0,-1);	
	}

	public void MoveRT() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveRt());
		this.MoveLorann(0,1);	
	}

	public void MoveUpLf() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUpLf());
		this.MoveLorann(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveUpRt());
		this.MoveLorann(-1,1);
	}

	public void MoveDwLf() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDwLf());
		this.MoveLorann(1,-1);
	}

	public void MoveDwRt() 
	{
		this.getLorann().setElemIcon(this.getLorann().getMoveDwRt());
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

	
	
}
