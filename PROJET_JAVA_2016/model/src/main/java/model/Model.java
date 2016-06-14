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
	private MapGenerator MapGenerator;
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
		this.MapGenerator = new MapGenerator(this.MapFinder.getMap(0));	
		this.Lorann = new Hero(5,10);
		this.MapGenerator.PlaceLorann(this.Lorann);
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

	public ImageIcon getImageElement(int y, int x) 
	{
		return this.MapGenerator.ElementMatrix[y][x].getElemIcon();
	}

	public void MoveLorann(int nextMoveUP_DWN, int nextMoveRGT_LFT) 
	{
		this.permea = this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT].getPermea();
		
		if(this.permea != Permeabilite.BLOCKING && this.permea != Permeabilite.TRANSLATABLE && this.permea != Permeabilite.LVLCHANGE && this.permea != Permeabilite.OPENEDGATE && this.permea != Permeabilite.CLOSEDGATE)
		{
			switch(this.permea)
			{
				case ENERGY:
						this.MapGenerator.UnlockGate();
						this.MapGenerator.setMapLevel(0);
						break;
				case MONEY:
						this.setScore(this.getScore()+650);
						System.out.println(this.getScore());
				default:	break;
			}
			this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT]=this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()];
			this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()]	= new Empty();
			this.Lorann.setCurrentY(this.Lorann.getCurrentY()+nextMoveUP_DWN);
			this.Lorann.setCurrentX(this.Lorann.getCurrentX()+nextMoveRGT_LFT);
		}
		else if(this.permea == Permeabilite.TRANSLATABLE)
		{
			if(this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+2*nextMoveUP_DWN][this.Lorann.getCurrentX()+2*nextMoveRGT_LFT].getPermea()==Permeabilite.PENETRABLE)
			{
				this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+2*nextMoveUP_DWN][this.Lorann.getCurrentX()+2*nextMoveRGT_LFT]=this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT];
				this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT]= new Empty();
			}
		}
		else if(this.permea == Permeabilite.LVLCHANGE)
		{
			this.MapGenerator.ChangeLevelMap(nextMoveUP_DWN);
		}
		else if(this.permea == Permeabilite.OPENEDGATE)
		{
			this.MapGenerator.setMapName(this.MapFinder.getMap(this.MapGenerator.getMapLevel()));
			this.MapGenerator.ResetWelcomeMenu(this.Lorann);
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

	public Dimension getD() 
	{
		return DimensionMap.d;
	}

	public void setLorannGIF() 
	{
		this.Lorann.setElemIcon(this.Lorann.getLorannGIF());
		this.setChanged();
		this.notifyObservers();
	}
	
}
