package model;

import java.sql.SQLException;
import java.util.Observable;

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
	private String MapName = "MAP_lvl1.txt";

	/**
	 * Instantiates a new model.
	 */
	public Model() 
	{
		this.message = "";
		this.MapGenerator = new MapGenerator(this.MapName);	
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

}
