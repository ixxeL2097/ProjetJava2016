package model;

import java.util.ArrayList;

import Element.*;
import Element.Number;
import Element.Motion.Hero;
import Element.Motion.MotionElemFACTORY;
import Element.Motion.MotionElement;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.Projectile;
import Element.Motion.AutoMotionElem.ShootingBehavior;
import Element.Motion.AutoMotionElem.Daemon.Demon;
import Element.Motion.AutoMotionElem.Daemon.TrackingBehavior;
import Element.MotionLess.*;
import contract.ControllerOrder;


public class MapGen implements IMapGen				// main class of elements's instanciation. MapGen is used to create all elements, moving or not
{
	public Element [][] ElemMtx;
	private String MapName;
	private int MapLevel=0;
	private Model model;
	private ArrayList<AutoMotionElem> MobilList;
	private Hero Lorann;
	private MapCreator mapcreator;
	
	public MapGen(int MapNumber, Model model)		// constructor instanciate a new arraylist of motion elements that will store demons and lorann's fireball
	{
		this.setMapLevel(MapNumber);		
		this.setModel(model);
		this.setMapName(this.getModel().getMapFinder().getMap(this.getMapLevel())); 
		this.mapcreator = new MapCreator(this);
		this.ElemMtx = new Element [DimensionMap.Y][DimensionMap.X];
		this.MobilList = new ArrayList<AutoMotionElem>();	
		this.getMapcreator().CreateMap();
		this.getMapcreator().ConsoleMap();
		this.getMapcreator().tabMapFromDB();
		this.createModel();
	}
	
	public void createModel()			// model creation using motionless and motion FACTORIES
	{
		int x=0, y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				switch(this.getMapcreator().getMapChar(y, x))	
				{
					case '@': this.PlaceMotionElem(MotionElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);
							  this.setLorann((Hero)this.getElemMtx(y, x));
							  break;
					case '0': this.setElemMtx(new Number(0), y, x);     																					
							  break;
					case 'A': this.PlaceMotionElem(MotionElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);
							  this.getMobilList().add((Demon)this.getElemMtx(y, x));
							  break;
					case 'B': this.PlaceMotionElem(MotionElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);	
							  this.getMobilList().add((Demon)this.getElemMtx(y, x));
							  break;
					case 'C': this.PlaceMotionElem(MotionElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);
							  this.getMobilList().add((Demon)this.getElemMtx(y, x));
							  break;
					case 'D': this.PlaceMotionElem(MotionElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);
							  this.getMobilList().add((Demon)this.getElemMtx(y, x));
							  break;
					default : this.ProduceElement(MotionLessElemFACTORY.getElemenFromCHAR(this.getMapcreator().getMapChar(y, x)), y, x);	                            			
							  break;		
				}	
			}
		}		
	}
	
	private void ProduceElement(final MotionLessElem element, final int y, final int x) 		// set a motionless element in the element matrix
	{
		this.ElemMtx [y][x] = element;
	}
	
	public void PlaceMotionElem(final MotionElement elem, int y, int x)		// set a motion element in the element matrix
	{
		this.setElemMtx(elem, y, x);
		elem.setState(this, y, x);
	}
	
	public void UnlockGate()										// unlock all gates when lorann is collecting energyball
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
	
	public void ChangeCurrentMap()						// method used to change the current map of the game
	{
		this.getMapcreator().CreateMap();
		this.getMapcreator().ConsoleMap();
		this.getMapcreator().tabMapFromDB();
		this.createModel();	
	}
	
	public void DestroyAllMobil()						// set all motion elements of the arraylist to null
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			this.getMobilList().set(x, null);
		}
	}
	
	public void AnimateDemons()							// initialize demon's move
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) != null && this.getMobilList().get(x) instanceof Demon)
			{
				this.getMobilList().get(x).run();
			}
		}		
	}
	
	public void StopAllDemons()						// stop all demons when 
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) != null && this.getMobilList().get(x) instanceof Demon)
			{
				this.getMobilList().get(x).getMoveTimer().stop();
			}
		}
	}
	
	public Projectile returnMissil()			// return lorann's fireball in contained in the arraylist
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) != null && this.getMobilList().get(x) instanceof Projectile)
			{
				return (Projectile)this.getMobilList().get(x);
			}
		}
		return null;
	}
	
	public void setMissilNull()				// set lorann's fireball to null
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) instanceof Projectile && this.getMobilList().get(x) != null)
			{
				this.getMobilList().set(x, null);
			}
		}
	}
	
	public synchronized void LorannIsShooting()		// initalize lorann's fireball if its not null and if the location is permeable for creating fireball
	{
		if(this.returnMissil()==null)
		{
			if(this.getLorann().isShootable())
			{
				switch(this.getLorann().getLastLorannMove())
				{
					case UP:			this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()+1, this.getLorann().getX());
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()+1, this.getLorann().getX()));
										this.returnMissil().setDirection(ControllerOrder.DOWN);
										break;
					case DOWN:			this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()-1, this.getLorann().getX());
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()-1, this.getLorann().getX()));
										this.returnMissil().setDirection(ControllerOrder.UP);
										break;
					case LEFT:			this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY(), this.getLorann().getX()+1);
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY(), this.getLorann().getX()+1));
										this.returnMissil().setDirection(ControllerOrder.RIGHT);
										break;
					case RIGHT:			this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY(), this.getLorann().getX()-1);
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY(), this.getLorann().getX()-1));
										this.returnMissil().setDirection(ControllerOrder.LEFT);
										break;
					case UPPERRIGHT:	this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()+1, this.getLorann().getX()-1);
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()+1, this.getLorann().getX()-1));
										this.returnMissil().setDirection(ControllerOrder.DOWNLEFT);
										break;
					case UPPERLEFT:		this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()+1, this.getLorann().getX()+1);
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()+1, this.getLorann().getX()+1));
										this.returnMissil().setDirection(ControllerOrder.DOWNRIGHT);
										break;
					case DOWNLEFT:		this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()-1, this.getLorann().getX()+1);				
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()-1, this.getLorann().getX()+1));
										this.returnMissil().setDirection(ControllerOrder.UPPERRIGHT);
										break;
					case DOWNRIGHT:		this.PlaceMotionElem(MotionElemFACTORY.PROJECTILE, this.getLorann().getY()-1, this.getLorann().getX()-1);
										this.getMobilList().add((Projectile)this.getElemMtx(this.getLorann().getY()-1, this.getLorann().getX()-1));
										this.returnMissil().setDirection(ControllerOrder.UPPERLEFT);
										break;
					default:
					break;			
				}
				this.returnMissil().setIA(new ShootingBehavior(this.returnMissil()));
				this.returnMissil().getMoveTimer().start();
			}			
		}
		else
		{
			this.returnMissil().setIA(new TrackingBehavior(this.returnMissil(),0));
		}
		this.getModel().notifyView();
	}
	
	public synchronized void stopShoot()			// stops lorann's fireball when hiting a demon or when lorann collects the fireball
	{
		if(this.returnMissil() != null)
		{
			this.returnMissil().getMoveTimer().stop();
			this.resetElemMtx(this.returnMissil().getY(), this.returnMissil().getX());
			this.setMissilNull();
		}
	}
	
	public void CheckShootableElem(int UP_DWN, int RGT_LFT)				// set the lorann's shootable boolean value based on element's permeability
	{
		if(this.getLorann().CheckAvailablePosition(UP_DWN, RGT_LFT))
		{
			if(this.getElemMtx(this.getLorann().getY()-UP_DWN, this.getLorann().getX()-RGT_LFT) instanceof MotionLessElem)
			{
				if(this.getElemMtx(this.getLorann().getY()-UP_DWN, this.getLorann().getX()-RGT_LFT) == MotionLessElemFACTORY.EMPTY)
				{
					this.getLorann().setShootable(true);
				}
				else{this.getLorann().setShootable(false);}
			}
			else{this.getLorann().setShootable(true);}
		}
		else
		{this.getLorann().setShootable(false);}	
	}
	
	public void ActivateDaemonsOnMap()			// activate all demons on map when lorann is moving
	{
		if(this.getLorann().isHasMoved()==false && this.getMapLevel() != 0)
		{
			this.getLorann().setHasMoved(true);
			this.AnimateDemons();
		}
	}	
	
	public void ChangeLevelMap()				// method used to change the map level in the main start map
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
	
	public void resetElemMtx(int y, int x)				// reset an element to empty
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

	public Hero getLorann() {
		return Lorann;
	}

	public void setLorann(Hero lorann) {
		Lorann = lorann;
	}

	public MapCreator getMapcreator() {
		return mapcreator;
	}

	public void setMapcreator(MapCreator mapcreator) {
		this.mapcreator = mapcreator;
	}

	public ArrayList<AutoMotionElem> getMobilList() {
		return MobilList;
	}

	public void setMobilList(ArrayList<AutoMotionElem> mobilList) {
		MobilList = mobilList;
	}
}
