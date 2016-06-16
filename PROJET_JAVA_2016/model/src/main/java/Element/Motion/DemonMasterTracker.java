package Element.Motion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import Element.Motion.MotionElement;
import PathFinder.PathFinder2;
import model.DimensionMap;
import model.Model;

public class DemonMasterTracker extends MotionElement implements Runnable, ActionListener
{
	private Model model;
	private Timer MoveTimer;
	private PathFinder2 path;
	private boolean[][] walkable;
	private int[][] pathWay;

	public DemonMasterTracker(int x, int y, Model model) 
	{
		super("C:/ProjetJava/Sprite/monster_1.png", Permeabilite.TRACKER);
		
		this.setModel(model); 
		this.MoveTimer=new Timer(350,this);
		this.X=x;
		this.Y=y;
		this.walkable = new boolean [DimensionMap.Y][DimensionMap.X];
		this.pathWay = new int [DimensionMap.Y][DimensionMap.X]; 
		this.path = new PathFinder2();
	}

	public void actionPerformed(ActionEvent e) 
	{
		int x = this.getX();
		int y = this.getY();
		int k = this.getModel().getLorann().getX();
		int j = this.getModel().getLorann().getY();
		
		this.GenerateBooleanMtx();
		this.setPathWay(this.getPath().findPath(x, y, k, j, this.getWalkable()));
		this.getModel().MoveDaemon(pathWay[0][1], pathWay[0][0], this);
	}

	public void run() 
	{
		this.MoveTimer.start();
	}
	
	public void GenerateBooleanMtx()
	{
		int x=0,y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.getModel().getMapGen().getElemMtx(y, x).getPermea()==Permeabilite.PENETRABLE || this.getModel().getMapGen().getElemMtx(y, x).getPermea()==Permeabilite.TRACKER)
				{
					this.walkable[y][x]=true;
				}
				else
				{
					this.walkable[y][x]=false;
				}
			}
		}
	}

	public synchronized Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}

	public synchronized PathFinder2 getPath() {
		return path;
	}

	public void setPath(PathFinder2 path) {
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

	public Timer getMoveTimer() {
		return MoveTimer;
	}

	public void setMoveTimer(Timer moveTimer) {
		MoveTimer = moveTimer;
	}
	
	

	

}