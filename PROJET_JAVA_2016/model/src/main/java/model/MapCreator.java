package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapCreator 
{
	IMapGen mapgen;
	public char[][] map; 
	
	public MapCreator(IMapGen mapgen)
	{
		this.setMapgen(mapgen);
		this.map = new char[DimensionMap.Y][DimensionMap.X];
	}
	
	public void ConsoleMap()
	{
		int x =0 ,y = 0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			x=0;
			System.out.println("");
			for(x=0; x<DimensionMap.X; x++)
			{
				System.out.print(map[y][x]);
			}
		}
	}
		
	public void CreateMap()
	{
		int x = 0, y=0, i=0;
		String v;
		String s = null;
		String t;
		FileInputStream fis = null;
		
		try {
	         fis = new FileInputStream(new File(this.getMapgen().getMapName()));
	         t = this.getMapgen().getMapName();
	         byte[] buf = new byte[8];	// On crée un tableau de byte pour indiquer le nombre de bytes lus à chaque tour de boucle
	        
	         int length = t.length();
	         System.out.println("\n" + length);
	         
/*	         if (length == 30)
	         {        
	        	 s = this.getMapgen().getMapName().substring(25, 26);
	         }
	         else if (length == 31)
	         {
	        	 s = this.getMapgen().getMapName().substring(25, 27);
	         }
	         else if (length == 32)
	         {
	        	 s = this.getMapgen().getMapName().substring(25, 28);
	         }
	         i = Integer.parseInt(s);*/
	         
	        //this.getMapgen().getModel().getDaoMapDb().CheckIfExist(i);
	         //v = this.getMapgen().getModel().getDaoMapDb().getDbV();
	         	         
	         while ((fis.read(buf)) >= 0) 				// Vaut -1 quand c'est fini Lorsque la lecture du fichier est terminée On sort donc de la boucle
	         {           
	            for (byte bit : buf) 					 // On affiche ce qu'a lu notre boucle au format byte et au format char
	            {
	               if(x<DimensionMap.X && bit != 10 )
	               {
	            	   map [y][x]= (char)bit;
	            	   
	            	 /* if (v != "false")
	            	  {
	            	  this.getMapgen().getModel().getDaoMapDb().addMapDB(i, x, y, (char)bit);
	            	  }*/
	            	   x++;
	               }
	               else if(y<DimensionMap.Y-1 && bit != 10)
	               {
	            	   y++;
	            	   x=0;
	            	   map [y][x] = (char)bit;
	               }
	            }
	            buf = new byte[8];  				  //Nous réinitialisons le buffer à vide au cas où les derniers byte lus ne soient pas un multiple de 8 Ceci permet d'avoir un buffer vierge à chaque lecture et ne pas avoir de doublon en fin de fichier
	         }
	         System.out.println("Copie terminée !");
		}
		  catch (FileNotFoundException e) 
		  {
	         e.printStackTrace();
	      } 
		  catch (IOException e) 
		  {
	         e.printStackTrace();
	      } 
		  finally 
		  {
	         try 
	         {
	            if (fis != null)
	               fis.close();
	         } 
	         catch (IOException e) 
	         {
	            e.printStackTrace();
	         }     
		  } 
	}
	
	public void tabMapFromDB()
	{
		int i=0, x=0, y=0;
		String s = null, u, t;
		char c;
        t = this.getMapgen().getMapName();
		int length = t.length();
        System.out.println("\n" + length);
        
        if (length == 30){
        
        s = this.getMapgen().getMapName().substring(25, 26);
        }
        else if (length == 31){
       	 s = this.getMapgen().getMapName().substring(25, 27);
        }
        else if (length == 32){
       	 s = this.getMapgen().getMapName().substring(25, 28);
        }
		
        i = Integer.parseInt(s);
      
        for(x=0; x<19; x++)
        {     	
        	for(y=0; y<11; y++)
        	{        		
        		this.getMapgen().getModel().getDaoMapDb().DataFromDB(i,x,y);
        		u = this.getMapgen().getModel().getDaoMapDb().getDbS();
        		c = u.charAt(0);
        		map[y][x] = c;
        	}        
        }   
	}

	public IMapGen getMapgen() {
		return mapgen;
	}

	public void setMapgen(IMapGen mapgen) {
		this.mapgen = mapgen;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}
	
	public char getMapChar(int y, int x)
	{
		return this.map[y][x];
	}

	
	
}
