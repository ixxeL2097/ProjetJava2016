package model;

import java.util.HashMap;

public class MapFinder 
{
	private HashMap<Integer, String> MapRanking; 
	private String lvl0Map = "C:/ProjetJava/Map/MAP_lvl0.txt";
	private String lvl1Map = "C:/ProjetJava/Map/MAP_lvl1.txt";
	private String lvl2Map = "C:/ProjetJava/Map/MAP_lvl2.txt";
	private String lvl3Map = "C:/ProjetJava/Map/MAP_lvl3.txt";
	private String lvl4Map = "C:/ProjetJava/Map/MAP_lvl4.txt";
	private String lvl5Map = "C:/ProjetJava/Map/MAP_lvl5.txt";
	private String lvl6Map = "C:/ProjetJava/Map/MAP_lvl6.txt";
	private String lvl7Map = "C:/ProjetJava/Map/MAP_lvl7.txt";
	private String lvl8Map = "C:/ProjetJava/Map/MAP_lvl8.txt";
	private String lvl9Map = "C:/ProjetJava/Map/MAP_lvl9.txt";
	private String lvl10Map = "C:/ProjetJava/Map/MAP_lvl10.txt";
	private String lvl11Map = "C:/ProjetJava/Map/MAP_lvl11.txt";
	private String lvl12Map = "C:/ProjetJava/Map/MAP_lvl12.txt";
	private String lvl13Map = "C:/ProjetJava/Map/MAP_lvl13.txt";
	
	
	
	
	
	

	
	public MapFinder()
	{
		this.MapRanking = new HashMap<Integer, String>();
	/*	int x=0;
		for(x=0; x<14; x++)
		{
			this.addMap(x, MapPath);
		}*/
		this.addMap(0, lvl0Map);
		this.addMap(1, lvl1Map);
		this.addMap(2, lvl2Map);
		this.addMap(3, lvl3Map);
		this.addMap(4, lvl4Map);
		this.addMap(5, lvl5Map);
		this.addMap(6, lvl6Map);
		this.addMap(7, lvl7Map);
		this.addMap(8, lvl8Map);
		this.addMap(9, lvl9Map);
		this.addMap(10, lvl10Map);
		this.addMap(11, lvl11Map);
		this.addMap(12, lvl12Map);
		this.addMap(13, lvl13Map);
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
