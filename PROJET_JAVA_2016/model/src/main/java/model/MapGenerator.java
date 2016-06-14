package model;

import java.io.*;
import Element.*;
import Element.Motion.MotionElement;
import Element.MotionLess.*;
import Element.MotionLess.Number;


public class MapGenerator 
{
	public char[][] map; 
	public Element [][] ElementMatrix;
	private String MapName;
	private int MapLevel=0;
	
	public MapGenerator(String MapName)
	{
		this.MapName = MapName;
		this.map = new char[DimensionMap.Y][DimensionMap.X];
		this.ElementMatrix = new Element [DimensionMap.Y][DimensionMap.X];
		
		this.CreateMap();
		this.ConsoleMap();
		this.createModel();
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
		int x = 0, y=0;
		FileInputStream fis = null;
		
		try {
	         fis = new FileInputStream(new File(MapName));

	         byte[] buf = new byte[8];					// On crée un tableau de byte pour indiquer le nombre de bytes lus à chaque tour de boucle

	         while ((fis.read(buf)) >= 0) 				// Vaut -1 quand c'est fini Lorsque la lecture du fichier est terminée On sort donc de la boucle
	         {           
	            for (byte bit : buf) 					 // On affiche ce qu'a lu notre boucle au format byte et au format char
	            {
	               System.out.print("\t" + bit + "(" + (char) bit + ")");
	               System.out.println("");
	               if(x<DimensionMap.X && bit != 10 )
	               {
	            	   map [y][x]= (char)bit;
	            	   //DBConnection.addMap(i,x,y,(char)bit);
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
	
	public void createModel()
	{
		int x=0, y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				switch(this.map[y][x])
				{
					case 'P': this.ElementMatrix [y][x] = new Stone();				break;
					case 'H': this.ElementMatrix [y][x] = new HorizontalBone();		break;
					case 'V': this.ElementMatrix [y][x] = new VerticalBone();		break;
					case 'D': this.ElementMatrix [y][x] = new OpenGate();			break;
					case 'U': this.ElementMatrix [y][x] = new ClosedGate();			break;
					case 'B': this.ElementMatrix [y][x] = new Bourse();				break;		  
					case 'E': this.ElementMatrix [y][x] = new EnergyBall();			break;		  
					case 'C': this.ElementMatrix [y][x] = new CandleStick();		break;
					case 'S': this.ElementMatrix [y][x] = new Statue();				break;	
					case 'x': this.ElementMatrix [y][x] = new Bourse();				break;
					case '-': this.ElementMatrix [y][x] = new Empty();				break;
					case 'T': this.ElementMatrix [y][x] = new Tombe();				break;
					case 'I': this.ElementMatrix [y][x] = new Rip();				break;
					case 'F': this.ElementMatrix [y][x] = new Flacon();				break;
					case 'K': this.ElementMatrix [y][x] = new Charger();			break;
					case '+': this.ElementMatrix [y][x] = new Plus();				break;
					case '*': this.ElementMatrix [y][x] = new Minus();				break;
					case '0': this.ElementMatrix [y][x] = new Number(0);			break;
					case '1': this.ElementMatrix [y][x] = new Number(1);			break;
					case '2': this.ElementMatrix [y][x] = new Number(2);			break;
					case '3': this.ElementMatrix [y][x] = new Number(3);			break;
					case '4': this.ElementMatrix [y][x] = new Number(4);			break;
					case '5': this.ElementMatrix [y][x] = new Number(5);			break;
					case '6': this.ElementMatrix [y][x] = new Number(6);			break;
					case '7': this.ElementMatrix [y][x] = new Number(7);			break;
					case '8': this.ElementMatrix [y][x] = new Number(8);			break;
					case '9': this.ElementMatrix [y][x] = new Number(9);			break;				
					default : break;		
				}	
			}
		}		
	}
	
	public void UnlockGate()
	{
		int x=0, y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.ElementMatrix[y][x] instanceof ClosedGate)
				{
					this.ElementMatrix [y][x] = new OpenGate();
				}
				else if(this.ElementMatrix[y][x] instanceof Rip)
				{
					this.ElementMatrix [y][x] = new Empty();
				}
			}
		}	
		
	}
	
	public void ChangeLevelMap(int digit)
	{
		this.MapLevel = this.MapLevel-digit;
		this.ElementMatrix[6][3] = new Number(MapLevel);
	}
	
	public void PlaceLorann(MotionElement lorann)
	{
		this.ElementMatrix[lorann.getCurrentY()][lorann.getCurrentX()]=lorann;
	}
	
	public void ResetWelcomeMenu(MotionElement Lorann)
	{
		this.CreateMap();
		this.createModel();	
		Lorann.setCurrentX(9);
		Lorann.setCurrentY(1);
		this.PlaceLorann(Lorann);
	}

	public int getMapLevel() {
		return MapLevel;
	}

	public void setMapLevel(int mapLevel) {
		MapLevel = mapLevel;
	}

	public String getMapName() {
		return MapName;
	}

	public void setMapName(String mapName) {
		MapName = mapName;
	}
	
	
	
}
