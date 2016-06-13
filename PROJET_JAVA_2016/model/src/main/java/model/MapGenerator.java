package model;

import java.io.*;
import Element.*;
import Element.Motion.MotionElement;
import Element.MotionLess.*;


public class MapGenerator 
{
	public char[][] map; 
	public Element [][] ElementMatrix;
	private String MapName;
	private Element element;
	
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
		int x=0;
		int y=0;
		char AnalyzedCharacter=' ';
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				AnalyzedCharacter = this.map[y][x];
				switch(AnalyzedCharacter)
				{
					case 'P': element = new Stone();			break;
					case 'H': element = new HorizontalBone();	break;
					case 'V': element = new VerticalBone();		break;
					case 'D': element = new OpenGate();			break;
					case 'd': element = new ClosedGate();			break;
					case 'B': element = new Bourse();			break;		  
					case 'E': element = new EnergyBall();		break;		  
					case 'C': element = new CandleStick();		break;
					case 'S': element = new Statue();			break;	
					case 'x': element = new Bourse();			break;
					case '-': element = new Empty();			break;
					case 'T': element = new Tombe();			break;
					case 'I': element = new Rip();				break;
					case 'F': element = new Flacon();			break;
					default : break;		
				}	
				this.ElementMatrix [y][x] = element;
			}
		}		
	}
	
	public void PlaceLorann(MotionElement lorann)
	{
		this.ElementMatrix[lorann.getCurrentY()][lorann.getCurrentX()]=lorann;
	}
	

}
