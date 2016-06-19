package model;

import java.io.*;
import Element.*;
import Element.Number;
import Element.Motion.Hero;
import Element.Motion.MotionElement;
import Element.Motion.AutoMotionElem.Daemon.DaemonBlind;
import Element.Motion.AutoMotionElem.Daemon.DaemonRandom;
import Element.Motion.AutoMotionElem.Daemon.DaemonSadistic;
import Element.Motion.AutoMotionElem.Daemon.DaemonTracker;
import Element.MotionLess.*;


public class MapGen
{
	public char[][] map; 
	public Element [][] ElemMtx;
	private String MapName;
	private int MapLevel=0;
	private Model model;
	private DaemonBlind StupidTracker;
	private DaemonTracker SmartTracker;
	private DaemonRandom BrainLessTracker;
	private DaemonSadistic SadisticTracker;
	private Hero Lorann;
	
	public MapGen(int MapNumber, Model model)
	{
		this.setMapLevel(MapNumber);		
		this.setModel(model);
		this.setMapName(this.getModel().getMapFinder().getMap(this.getMapLevel())); 
		this.map = new char[DimensionMap.Y][DimensionMap.X];
		this.ElemMtx = new Element [DimensionMap.Y][DimensionMap.X];
		
		this.CreateMap();
		this.ConsoleMap();
		this.tabMapFromDB();
		this.createModel();
	}
		
	public void CreateMap()
	{
		int x = 0, y=0, i=0;
		String v;
		String s = null;
		FileInputStream fis = null;
		
		try {
	         fis = new FileInputStream(new File(MapName));

	         byte[] buf = new byte[8];	// On crée un tableau de byte pour indiquer le nombre de bytes lus à chaque tour de boucle
	         
	         s = MapName.substring(25, 26);
	         i = Integer.parseInt(s);
	         
	        this.getModel().getDaoMapDb().CheckIfExist(i);
	         v = getModel().getDaoMapDb().getDbV();
	         	         
	         while ((fis.read(buf)) >= 0) 				// Vaut -1 quand c'est fini Lorsque la lecture du fichier est terminée On sort donc de la boucle
	         {           
	            for (byte bit : buf) 					 // On affiche ce qu'a lu notre boucle au format byte et au format char
	            {
	               if(x<DimensionMap.X && bit != 10 )
	               {
	            	   map [y][x]= (char)bit;
	            	   
	            	  if (v != "false")
	            	  {
	            	  this.getModel().getDaoMapDb().addMapDB(i, x, y, (char)bit);
	            	  }
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
		String s, u;
		char c;
        s = MapName.substring(25, 26);
        i = Integer.parseInt(s);
      
        for(x=0; x<19; x++)
        {     	
        	for(y=0; y<11; y++)
        	{        		
        		getModel().getDaoMapDb().DataFromDB(i,x,y);
        		u = getModel().getDaoMapDb().getDbS();
        		c = u.charAt(0);
        		map[y][x] = c;
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
					case '@': this.setLorann(new Hero(this.getModel(),y,x));
							  this.setElemMtx(this.getLorann(), y, x);
							  break;
					case '0': this.setElemMtx(new Number(0), y, x);     																					
							  break;
					case 'A': this.setBrainLessTracker(new DaemonRandom(this.getModel(),y,x)); 		
							  this.setElemMtx(this.getBrainLessTracker(), y, x); 			
							  break;
					case 'B': this.setStupidTracker(new DaemonBlind(this.getModel(),y,x));		
							  this.setElemMtx(this.getStupidTracker(), y, x);				
							  break;
					case 'C': this.setSadisticTracker(new DaemonSadistic(this.getModel(),y,x));		  
							  this.setElemMtx(this.getSadisticTracker(), y, x);
							  break;
					case 'D': this.setSmartTracker(new DaemonTracker(this.getModel(),y,x)); 	
							  this.setElemMtx(this.getSmartTracker(), y, x); 			
							  break;
					default : this.ProduceElement(MotionLessElemFACTORY.getElemenFromCHAR(this.map[y][x]), y, x);	                            			
							  break;		
				}	
			}
		}		
	}
	
	private void ProduceElement(final MotionLessElem element, final int y, final int x) 
	{
		this.ElemMtx [y][x] = element;
	}
	
	public void PlaceMotionElem(MotionElement elem)
	{
		this.setElemMtx(elem, elem.getY(), elem.getX());
	}
	
	public void UnlockGate()
	{
		int x=0, y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.ElemMtx[y][x] == MotionLessElemFACTORY.CLOSEDGATE)	
				{
					this.ElemMtx [y][x] = MotionLessElemFACTORY.OPENGATE;	
				}
				else if(this.ElemMtx[y][x] == MotionLessElemFACTORY.TOMBSTONE)	
				{
					this.ElemMtx [y][x] = MotionLessElemFACTORY.EMPTY;
				}
			}
		}		
	}
	
	public void DestroyAllDaemons()
	{
		this.setBrainLessTracker(null);
		this.setStupidTracker(null);
		this.setSmartTracker(null);	
		this.setSadisticTracker(null);
	}
	
	public void ChangeLevelMap()
	{
		if(this.getModel().getLevelMapOrder()>99)
		{
			this.ElemMtx[6][1] = new Number(this.getModel().getLevelMapOrder()/100);
			this.ElemMtx[6][2] = new Number((this.getModel().getLevelMapOrder()-(this.getModel().getLevelMapOrder()/100)*100)/10);
			this.ElemMtx[6][3] = new Number((this.getModel().getLevelMapOrder()-(this.getModel().getLevelMapOrder()/100)*100)-((this.getModel().getLevelMapOrder()-(this.getModel().getLevelMapOrder()/100)*100)/10)*10);
		}
		else if(this.getModel().getLevelMapOrder()>9)
		{
			this.ElemMtx[6][1] = new Number(0);
			this.ElemMtx[6][2] = new Number(this.getModel().getLevelMapOrder()/10);
			this.ElemMtx[6][3] = new Number(this.getModel().getLevelMapOrder()-(this.getModel().getLevelMapOrder()/10)*10);
		}
		else if(this.getModel().getLevelMapOrder()<10)
		{
			this.ElemMtx[6][2] = new Number(0);
			this.ElemMtx[6][3] = new Number(this.getModel().getLevelMapOrder());
		}	
	}
	
	public void ChangeCurrentMap()
	{
		this.CreateMap();
		this.ConsoleMap();
		this.tabMapFromDB();
		this.createModel();	
	}
	
	public synchronized void AnimateDaemons()
	{
		if(this.getSmartTracker() != null)
		{
			this.getSmartTracker().run();
		}
		if(this.getStupidTracker() != null)
		{
			this.getStupidTracker().run();
		}
		if(this.getBrainLessTracker() != null)
		{
			this.getBrainLessTracker().run();
		}
		if(this.getSadisticTracker() != null)
		{
			this.getSadisticTracker().run();
		}
	}
	
	public synchronized void StopAllDaemons()
	{
		if(this.getSmartTracker() != null)
		{
			this.getSmartTracker().getMoveTimer().stop();
		}
		if(this.getStupidTracker() != null)
		{
			this.getStupidTracker().getMoveTimer().stop();
		}
		if(this.getBrainLessTracker() != null)
		{
			this.getBrainLessTracker().getMoveTimer().stop();
		}	
		if(this.getSadisticTracker() != null)
		{
			this.getSadisticTracker().getMoveTimer().stop();
		}
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
	
	public void resetElemMtx(int y, int x)
	{
		this.setElemMtx(null, y, x);
		this.setElemMtx(MotionLessElemFACTORY.EMPTY, y, x);
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public synchronized Element getElemMtx(int y, int x) {
		return ElemMtx[y][x];
	}

	public synchronized void setElemMtx(Element elemMtx, int y, int x) 
	{
		ElemMtx[y][x] = elemMtx;
	}

	public DaemonBlind getStupidTracker() {
		return StupidTracker;
	}

	public void setStupidTracker(DaemonBlind stupidTracker) {
		StupidTracker = stupidTracker;
	}

	public DaemonTracker getSmartTracker() {
		return SmartTracker;
	}

	public void setSmartTracker(DaemonTracker smartTracker) {
		SmartTracker = smartTracker;
	}

	public DaemonRandom getBrainLessTracker() {
		return BrainLessTracker;
	}

	public void setBrainLessTracker(DaemonRandom brainLessTracker) {
		BrainLessTracker = brainLessTracker;
	}

	public Hero getLorann() {
		return Lorann;
	}

	public void setLorann(Hero lorann) {
		Lorann = lorann;
	}

	public DaemonSadistic getSadisticTracker() {
		return SadisticTracker;
	}

	public void setSadisticTracker(DaemonSadistic sadisticTracker) {
		SadisticTracker = sadisticTracker;
	}


}
