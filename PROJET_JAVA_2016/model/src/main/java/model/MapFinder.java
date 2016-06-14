package model;

import java.util.HashMap;

public class MapFinder 
{
	private HashMap<Integer, String> MapRanking; 
	private String WelcomeMap = "C:/ProjetJava/Map/MAP_accueil.txt";
	private String lvl1Map = "C:/ProjetJava/Map/MAP_lvl1.txt";
	private String lvl2Map = "C:/ProjetJava/Map/MAP_lvl2.txt";
	private String lvl3Map = "C:/ProjetJava/Map/MAP_lvl3.txt";
	
	public MapFinder()
	{
		this.MapRanking = new HashMap<Integer, String>();
		this.addMap(0, WelcomeMap);
		this.addMap(1, lvl1Map);
		this.addMap(2, lvl2Map);
		this.addMap(3, lvl3Map);
		
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
