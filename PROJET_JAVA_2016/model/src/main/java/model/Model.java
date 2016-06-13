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

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		this.message = "";
		this.MapGenerator = new MapGenerator(this.MapName);	
		this.Lorann = new Hero(5,15);
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
		if(this.MapGenerator.ElementMatrix[nextMoveUP_DWN][nextMoveRGT_LFT].getPermea()==Permeabilite.BLOCKING)
		{
			System.out.println("Vous ne pouvez pas avancer.");
		}
		else if(this.MapGenerator.ElementMatrix[nextMoveUP_DWN][nextMoveRGT_LFT].getPermea()==Permeabilite.AGGRO)
		{	
			this.Lorann.setLastX(this.Lorann.getCurrentX());
			this.Lorann.setLastY(this.Lorann.getCurrentY());
			this.Lorann.setCurrentY(nextMoveUP_DWN);
			this.Lorann.setCurrentX(nextMoveRGT_LFT);
			//this.inFight=true;	
			this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()]=this.MapGenerator.ElementMatrix[this.Lorann.getLastY()][this.Lorann.getLastX()];
			this.MapGenerator.ElementMatrix[this.Lorann.getLastY()][this.Lorann.getLastX()]	= new Empty();	
			this.setChanged();
			this.notifyObservers();
		}
		else
		{
			this.Lorann.setLastX(this.Lorann.getCurrentX());
			this.Lorann.setLastY(this.Lorann.getCurrentY());
			this.Lorann.setCurrentY(nextMoveUP_DWN);
			this.Lorann.setCurrentX(nextMoveRGT_LFT);
			//this.inFight=true;	
			this.MapGenerator.ElementMatrix[this.Lorann.getCurrentY()][this.Lorann.getCurrentX()]=this.MapGenerator.ElementMatrix[this.Lorann.getLastY()][this.Lorann.getLastX()];
			this.MapGenerator.ElementMatrix[this.Lorann.getLastY()][this.Lorann.getLastX()]	= new Empty();	
			this.setChanged();
			this.notifyObservers();						
		}
	}

	public void MoveUP() 
	{
		this.MoveLorann(this.Lorann.getCurrentY()-1,this.Lorann.getCurrentX());		
	}

	public void MoveDW() 
	{
		this.MoveLorann(this.Lorann.getCurrentY()+1,this.Lorann.getCurrentX());	
	}

	public void MoveLF() 
	{
		this.MoveLorann(this.Lorann.getCurrentY(),this.Lorann.getCurrentX()-1);	
	}

	public void MoveRT() 
	{
		this.MoveLorann(this.Lorann.getCurrentY(),this.Lorann.getCurrentX()+1);	
	}

}
