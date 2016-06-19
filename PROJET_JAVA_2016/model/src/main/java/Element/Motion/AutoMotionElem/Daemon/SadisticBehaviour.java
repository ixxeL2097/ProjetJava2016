package Element.Motion.AutoMotionElem.Daemon;

import Element.Permeabilite;
import Element.Motion.AutoMotionElem.AutoMotionElem;
import Element.Motion.AutoMotionElem.IArtificialIntelligence;
import PathFinder.PathFinder;
import model.DimensionMap;

public class SadisticBehaviour implements IArtificialIntelligence
{
	private AutoMotionElem Mobil;
	private PathFinder path;
	private boolean[][] walkable;
	private int[][] pathWay;
	
	public SadisticBehaviour(AutoMotionElem Mobil)
	{
		this.setMobil(Mobil);
		this.walkable = new boolean [DimensionMap.Y][DimensionMap.X];
		this.pathWay = new int [DimensionMap.Y][DimensionMap.X]; 
		this.path = new PathFinder();
	}
	
	public void AutoMove() 
	{
		this.UpdatePosition();
	}
	
	public void UpdatePosition()
	{
		int x = this.getMobil().getX();
		int y = this.getMobil().getY();
		int k = this.getMobil().getModel().getMapGen().getLorann().getX();
		int j = this.getMobil().getModel().getMapGen().getLorann().getY();
		
		this.GenerateBooleanMtx();
		this.setPathWay(this.getPath().findPath(x, y, k, j, this.getWalkable()));
		if(this.getPathWay() != null && this.getPath().getSteps()>2)
		{
			this.getMobil().getModel().MoveDaemon(pathWay[0][1], pathWay[0][0], this.getMobil());
		}
		else
		{
			this.getMobil().SadisticDaemonMove();
		}
	}
	
	public void GenerateBooleanMtx()
	{
		int x=0,y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.getMobil().getModel().getMapGen().getElemMtx(y, x).getPermea()==Permeabilite.PENETRABLE || this.getMobil().getModel().getMapGen().getElemMtx(y, x).getPermea()==this.getMobil().getPermea())
				{
					this.setWalkableValue(true, y, x);
				}
				else
				{
					this.setWalkableValue(false, y, x);
				}
			}
		}
	}
	
	public void setWalkableValue(boolean value, int y, int x)
	{
		this.walkable[y][x]=value;
	}

	public synchronized PathFinder getPath() {
		return path;
	}

	public void setPath(PathFinder path) {
		this.path = path;
	}

	public synchronized boolean[][] getWalkable() {
		return walkable;
	}

	public void setWalkable(boolean[][] walkable) {
		this.walkable = walkable;
	}

	public int[][] getPathWay() {
		return pathWay;
	}

	public synchronized void setPathWay(int[][] pathWay) {
		this.pathWay = pathWay;
	}

	public AutoMotionElem getMobil() {
		return Mobil;
	}

	public void setMobil(AutoMotionElem mobil) {
		Mobil = mobil;
	}



}
