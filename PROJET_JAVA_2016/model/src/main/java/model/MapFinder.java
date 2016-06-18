package model;

import java.util.HashMap;

public class MapFinder 
{
	private HashMap<Integer, String> MapRanking; 
	private String BodyPathText = "C:/ProjetJava/Map/MAP_lvl";
	private String Extension =".txt";
	private int MapNumber = 100;
	
	public MapFinder()
	{
		this.MapRanking = new HashMap<Integer, String>();
		int x=0;
		for(x=0; x<MapNumber; x++)
		{
			this.addMap(x, BodyPathText+x+Extension);
		}
	}
	
	public void addMap(int index, String MapPath)
	{
		this.MapRanking.put(index, MapPath);
	}
	
	public void removeMap(int index, String MapPath)
	{
		this.MapRanking.remove(index, MapPath);
	}
	
	public void removeMapReference(int index)
	{
		this.MapRanking.remove(index);
	}
	
	public String getMap(int key)
	{
		return this.MapRanking.get(key);
	}
	
}
