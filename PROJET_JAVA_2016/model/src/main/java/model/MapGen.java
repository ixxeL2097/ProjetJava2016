package model;

import java.io.*;
import Element.*;
import Element.Number;
import Element.Motion.DaemonMasterTracker;
import Element.Motion.DaemonRandom;
import Element.Motion.DaemonTracker;
import Element.Motion.MotionElement;
import Element.MotionLess.*;


public class MapGen
{
	public char[][] map; 
	public Element [][] ElemMtx;
	private String MapName;
	private int MapLevel=0;
	private Model model;
	private DaemonTracker StupidTracker;
	private DaemonMasterTracker SmartTracker;
	private DaemonRandom BrainLessTracker;
	
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
	         
	         this.getModel().getDaohelloworld().verifExist(i);
	         v = getModel().getDaohelloworld().getDbV();
	         
	         
	         while ((fis.read(buf)) >= 0) 				// Vaut -1 quand c'est fini Lorsque la lecture du fichier est terminée On sort donc de la boucle
	         {           
	            for (byte bit : buf) 					 // On affiche ce qu'a lu notre boucle au format byte et au format char
	            {
	               if(x<DimensionMap.X && bit != 10 )
	               {
	            	   map [y][x]= (char)bit;
	            	   
	            	   if (v != "false"){
	            	   this.getModel().getDaohelloworld().addMapBdd(i, x, y, (char)bit);
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

	
	public void tabMapFromDB(){
		int i=0, x=0, y=0;
		String s, u;
		char c;
        s = MapName.substring(25, 26);
        i = Integer.parseInt(s);

        
        for(x=0; x<19; x++){
        	
        	for(y=0; y<11; y++){
        		
        		getModel().getDaohelloworld().DataFromDB(i,x,y);
        		u = getModel().getDaohelloworld().getDbS();
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
					case '0': this.ElemMtx [y][x] = new Number(0);																				break;
					case 'w': this.BrainLessTracker = new DaemonRandom(this.getModel(),y,x); this.ElemMtx [y][x]=this.BrainLessTracker;			break;
					case 'x': this.StupidTracker = new DaemonTracker(this.getModel(),y,x); this.ElemMtx [y][x]=this.StupidTracker;				break;
					case 'z': this.SmartTracker = new DaemonMasterTracker(this.getModel(),y,x); this.ElemMtx [y][x]=this.SmartTracker;			break;
					default : this.ProduceElement(MotionLessElemFACTORY.getElemenFromCHAR(this.map[y][x]), y, x);	                            break;		
				}	
			}
		}		
	}
	
	private void ProduceElement(final MotionLessElem element, final int y, final int x) 
	{
		this.ElemMtx [y][x] = element;
	}
	
	public void UnlockGate()
	{
		int x=0, y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.ElemMtx[y][x] == MotionLessElemFACTORY.CLOSEDGATE)	//instanceof ClosedGate
				{
					this.ElemMtx [y][x] = MotionLessElemFACTORY.OPENGATE;	
				}
				else if(this.ElemMtx[y][x] == MotionLessElemFACTORY.RIP)	//instanceof Rip
				{
					this.ElemMtx [y][x] = MotionLessElemFACTORY.EMPTY;
				}
			}
		}		
	}
	
	public void DestroyDaemons()
	{
		this.setBrainLessTracker(null);
		this.setStupidTracker(null);
		this.setSmartTracker(null);		
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
	
	public void PlaceLorann(MotionElement elem)
	{
		this.ElemMtx[elem.getY()][elem.getX()]=elem;
	}
	
	public void ResetWelcomeMenu(MotionElement Lorann)
	{
		this.CreateMap();
		//this.tabMapFromDB();
		this.createModel();	
		Lorann.setX(9);
		Lorann.setY(1);
		this.PlaceLorann(Lorann);
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

	public DaemonTracker getStupidTracker() {
		return StupidTracker;
	}

	public void setStupidTracker(DaemonTracker stupidTracker) {
		StupidTracker = stupidTracker;
	}

	public DaemonMasterTracker getSmartTracker() {
		return SmartTracker;
	}

	public void setSmartTracker(DaemonMasterTracker smartTracker) {
		SmartTracker = smartTracker;
	}

	public DaemonRandom getBrainLessTracker() {
		return BrainLessTracker;
	}

	public void setBrainLessTracker(DaemonRandom brainLessTracker) {
		BrainLessTracker = brainLessTracker;
	}


	
	
	
	
	
}
