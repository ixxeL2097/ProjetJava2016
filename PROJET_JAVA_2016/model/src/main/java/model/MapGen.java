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


public class MapGen implements IMapGen
{
	public Element [][] ElemMtx;
	private String MapName;
	private int MapLevel=0;
	private Model model;
	private ArrayList<AutoMotionElem> MobilList;
	private Hero Lorann;
	private MapCreator mapcreator;
	
	public MapGen(int MapNumber, Model model)
	{
		this.setMapLevel(MapNumber);		
		this.setModel(model);
		this.setMapName(this.getModel().getMapFinder().getMap(this.getMapLevel())); 
		this.mapcreator = new MapCreator(this);
		this.ElemMtx = new Element [DimensionMap.Y][DimensionMap.X];
		this.MobilList = new ArrayList<AutoMotionElem>();	
		this.getMapcreator().CreateMap();
		this.getMapcreator().ConsoleMap();
		//this.getMapcreator().tabMapFromDB();
		this.createModel();
	}
	
	public void createModel()
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
	
	private void ProduceElement(final MotionLessElem element, final int y, final int x) 
	{
		this.ElemMtx [y][x] = element;
	}
	
	public void PlaceMotionElem(final MotionElement elem, int y, int x)
	{
		this.setElemMtx(elem, y, x);
		elem.setState(this, y, x);
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
		this.getMapcreator().CreateMap();
		this.getMapcreator().ConsoleMap();
		//this.getMapcreator().tabMapFromDB();
		this.createModel();	
	}
	
	public void DestroyAllMobil()
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			this.getMobilList().set(x, null);
		}
	}
	
	public void AnimateDemons()
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) instanceof Demon && this.getMobilList().get(x) != null)
			{
				this.getMobilList().get(x).run();
			}
		}		
	}
	
	public void StopAllDemons()
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) instanceof Demon && this.getMobilList().get(x) != null)
			{
				this.getMobilList().get(x).getMoveTimer().stop();
			}
		}
	}
	
	public Projectile returnMissil()
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) instanceof Projectile && this.getMobilList().get(x) != null)
			{
				return (Projectile)this.getMobilList().get(x);
			}
		}
		return null;
	}
	
	public void setMissilNull()
	{
		for(int x=0; x<this.getMobilList().size(); x++)
		{
			if(this.getMobilList().get(x) instanceof Projectile && this.getMobilList().get(x) != null)
			{
				this.getMobilList().set(x, null);
			}
		}
	}
	
	public synchronized void LorannIsShooting()
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
	
	public synchronized void stopShoot()
	{
		if(this.returnMissil() != null)
		{
			this.returnMissil().getMoveTimer().stop();
			this.resetElemMtx(this.returnMissil().getY(), this.returnMissil().getX());
			this.setMissilNull();
		}
	}
	
	public void CheckShootableElem(int UP_DWN, int RGT_LFT)
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
	
	public void ActivateDaemonsOnMap()
	{
		if(this.getLorann().isHasMoved()==false && this.getMapLevel() != 0)
		{
			this.getLorann().setHasMoved(true);
			this.AnimateDemons();
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
