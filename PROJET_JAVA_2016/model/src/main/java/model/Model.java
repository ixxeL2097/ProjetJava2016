package model;

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
	private String message;
	private MapGenerator MapGenerator;
	private String MapName = "C:/ProjetJava/Map/MAP_lvl2.txt";
	private MotionElement Lorann;
	private MapFinder MapFinder;

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		this.message = "";
		this.MapFinder = new MapFinder();
		this.MapGenerator = new MapGenerator(this.MapFinder.getMap(0));	
		this.Lorann = new Hero(5,10);
		this.MapGenerator.PlaceLorann(this.Lorann);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getMessage()
	 */
	public String getMessage() 
	{
		return this.message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *          the new message
	 */
	private void setMessage(final String message) 
	{
		this.message = message;
		this.setChanged();
		this.notifyObservers();
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
			this.setMessage(daoHelloWorld.find(key).getMessage());
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
		switch(this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT].getPermea())
		{
			case BLOCKING:	
					System.out.println("Vous ne pouvez pas avancer.");		break;
			case PENETRABLE:
					this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT]=this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()];
					this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()]	= new Empty();
					this.Lorann.setCurrentY(this.Lorann.getCurrentY()+nextMoveUP_DWN);
					this.Lorann.setCurrentX(this.Lorann.getCurrentX()+nextMoveRGT_LFT);
				break;
			case TRANSLATABLE:	
					if(this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+2*nextMoveUP_DWN][this.Lorann.getCurrentX()+2*nextMoveRGT_LFT].getPermea()==Permeabilite.PENETRABLE)
					{
						this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+2*nextMoveUP_DWN][this.Lorann.getCurrentX()+2*nextMoveRGT_LFT]=this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT];
						this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT]= new Empty();
					}
				break;
			case ENERGY:
					this.MapGenerator.UnlockGate();
					this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()+nextMoveUP_DWN][this.Lorann.getCurrentX()+nextMoveRGT_LFT]=this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()];
					this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()]	= new Empty();
					this.Lorann.setCurrentY(this.Lorann.getCurrentY()+nextMoveUP_DWN);
					this.Lorann.setCurrentX(this.Lorann.getCurrentX()+nextMoveRGT_LFT);
					this.MapGenerator.setMapLevel(0);
				break;
			case LVLCHANGE:
					this.MapGenerator.ChangeLevelMap(nextMoveUP_DWN);
				break;
			case OPENEDGATE:
					this.MapGenerator.setMapName(this.MapFinder.getMap(this.MapGenerator.getMapLevel()));
					this.MapGenerator.ResetWelcomeMenu(this.Lorann);
				break;
			default:	break;
		}
		this.setChanged();
		this.notifyObservers();	
	}

	public void MoveUP() 
	{
		this.MoveLorann(-1,0);		
	}

	public void MoveDW() 
	{
		this.MoveLorann(1,0);	
	}

	public void MoveLF() 
	{
		this.MoveLorann(0,-1);	
	}

	public void MoveRT() 
	{
		this.MoveLorann(0,1);	
	}

	public void MoveUpLf() 
	{
		this.MoveLorann(-1,-1);
	}

	public void MoveUpRt() 
	{
		this.MoveLorann(-1,1);
	}

	public void MoveDwLf() 
	{
		this.MoveLorann(1,-1);
	}

	public void MoveDwRt() 
	{
		this.MoveLorann(1,1);
	}

}
