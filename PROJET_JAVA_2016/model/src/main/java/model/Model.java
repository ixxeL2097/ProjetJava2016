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

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		this.MapFinder = new MapFinder();
		this.MapGen = new MapGen(this.MapFinder.getMap(0), this);	
		this.Lorann = new Hero(5,10);
		this.MapGen.PlaceLorann(this.Lorann);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getMessage(java.lang.String)
	 */
	public void loadMessage(final String key) 
	{
		try 
		{
			final DAOHelloWorld daoHelloWorld = new DAOHelloWorld(DBConnection.getInstance().getConnection());
			//this.setMessage(daoHelloWorld.find(key).getMessage());
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
		this.Lorann.setElemIcon(this.Lorann.getLorannGIF());
		this.setChanged();
		this.notifyObservers();
	}
	
	public void MoveDaemon(int UP_DWN, int RGT_LFT, Daemon daemon)
	{
		this.permea = this.MapGen.ElemMtx[daemon.getY()+UP_DWN][daemon.getX()+RGT_LFT].getPermea();
		
		if(this.permea == Permeabilite.PENETRABLE)
		{
			this.MapGen.ElemMtx[daemon.getY()+UP_DWN][daemon.getX()+RGT_LFT]=this.MapGen.ElemMtx[daemon.getY()][daemon.getX()];
			this.MapGen.ElemMtx[daemon.getY()][daemon.getX()] = new Empty();
			daemon.setY(daemon.getY()+UP_DWN);
			daemon.setX(daemon.getX()+RGT_LFT);		
		}
		else if(this.permea == Permeabilite.BLOCKING)
		{
			System.out.println("BLOCKED");
			daemon.DefaultDaemonMove();
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void MoveLorann(int UP_DWN, int RGT_LFT) 
	{
		this.permea = this.MapGen.ElemMtx[this.Lorann.getY()+UP_DWN][this.Lorann.getX()+RGT_LFT].getPermea();
		
		if(this.permea != Permeabilite.BLOCKING && this.permea != Permeabilite.TRANSLATABLE && this.permea != Permeabilite.LVLCHANGE && this.permea != Permeabilite.VICTORY && this.permea != Permeabilite.CLOSEDGATE)
		{
			switch(this.permea)
			{
				case ENERGY:
						this.MapGen.UnlockGate();
						this.MapGen.setMapLevel(0);
						break;
				case MONEY:
						this.setScore(this.getScore()+650);
				default:	break;
			}
			this.MapGen.ElemMtx[this.Lorann.getY()+UP_DWN][this.Lorann.getX()+RGT_LFT]=this.MapGen.ElemMtx[this.Lorann.getY()][this.Lorann.getX()];
			this.MapGen.ElemMtx[this.Lorann.getY()][this.Lorann.getX()]	= new Empty();
			this.Lorann.setY(this.Lorann.getY()+UP_DWN);
			this.Lorann.setX(this.Lorann.getX()+RGT_LFT);
		}
		else if(this.permea == Permeabilite.TRANSLATABLE)
		{
			if(this.MapGen.ElemMtx[this.Lorann.getY()+2*UP_DWN][this.Lorann.getX()+2*RGT_LFT].getPermea()==Permeabilite.PENETRABLE)
			{
				this.MapGen.ElemMtx[this.Lorann.getY()+2*UP_DWN][this.Lorann.getX()+2*RGT_LFT]=this.MapGen.ElemMtx[this.Lorann.getY()+UP_DWN][this.Lorann.getX()+RGT_LFT];
				this.MapGen.ElemMtx[this.Lorann.getY()+UP_DWN][this.Lorann.getX()+RGT_LFT]= new Empty();
			}
		}
		else if(this.permea == Permeabilite.LVLCHANGE)
		{
			this.MapGen.ChangeLevelMap(UP_DWN);
		}
		else if(this.permea == Permeabilite.VICTORY)
		{
			this.MapGen.setMapName(this.MapFinder.getMap(this.MapGen.getMapLevel()));
			this.MapGen.ResetWelcomeMenu(this.Lorann);
		}	
		this.setChanged();
		this.notifyObservers();	
	}

	public void MoveUP() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveUp());
		this.MoveLorann(-1,0);		
	}

	public void MoveDW() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveDw());
		this.MoveLorann(1,0);	
	}

	public void MoveLF() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveLf());
		this.MoveLorann(0,-1);	
	}

	public void MoveRT() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveRt());
		this.MoveLorann(0,1);	
	}

	public void MoveUpLf() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveUpLf());
		this.MoveLorann(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveUpRt());
		this.MoveLorann(-1,1);
	}

	public void MoveDwLf() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveDwLf());
		this.MoveLorann(1,-1);
	}

	public void MoveDwRt() 
	{
		this.Lorann.setElemIcon(this.Lorann.getMoveDwRt());
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

	public Hero getLorann() {
		return Lorann;
	}

	public void setLorann(Hero lorann) {
		Lorann = lorann;
	}

	public MapGen getMapGen() {
		return MapGen;
	}
	
	
	
}
