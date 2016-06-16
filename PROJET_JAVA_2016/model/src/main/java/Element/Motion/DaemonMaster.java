package Element.Motion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import Element.Permeabilite;
import PathFinder.PathFinder;
import PathFinder.PathFinder2;
import model.DimensionMap;
import model.Model;

public class DaemonMaster extends MotionElement implements Runnable, ActionListener
{

	private Timer MoveTimer;
	private Model model;
	public boolean[][] walkable;
	public PathFinder2 pathfinder;
	public int[][] path;

	public DaemonMaster(Model model, int y , int x) 
	{
		super("C:/ProjetJava/Sprite/monster_3.png", Permeabilite.PENETRABLE);
		
		this.setModel(model); 
		this.X=x;
		this.Y=y;
		this.MoveTimer = new Timer(600,this);
		this.walkable = new boolean [DimensionMap.Y][DimensionMap.X];
		this.path = new int [DimensionMap.Y][DimensionMap.X]; 
		this.pathfinder = new PathFinder2();
		
	}

	public void run() 
	{
		this.MoveTimer.start();
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.GenerateBooleanMtx();
		System.out.println("x "+this.getX()+" y "+this.getY());
		this.path = this.pathfinder.findPath(this.getX(), this.getLastY(), this.getModel().getLorann().getX(), this.getModel().getLorann().getY(), this.walkable);
	}
	
	public void PathFindLorann()
	{
		this.GenerateBooleanMtx();
		System.out.println("ok boolean");
		System.out.println(this.getX()+" "+this.getY()+" "+this.getModel().getLorann().getX()+" "+this.getModel().getLorann().getY());
		this.pathfinder.findPath(this.getX(), this.getLastY(), this.getModel().getLorann().getX(), this.getModel().getLorann().getY(), this.walkable);
		System.out.println("ok path");
		System.out.println();
	    int x=0;
	    int y=0;
	    while(y<DimensionMap.Y){
	    	while(x<DimensionMap.X){
	    		System.out.print(path[y][x]);
	    		x++;
	    	}
	    	System.out.println("");
	    	x=0;
	    	y++;
	    }
	}
	
	public void GenerateBooleanMtx()
	{
		int x=0,y=0;
		for(y=0; y<DimensionMap.Y; y++)
		{
			for(x=0; x<DimensionMap.X; x++)
			{
				if(this.getModel().getMapGen().getElemMtx(y, x).getPermea()==Permeabilite.PENETRABLE)
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
	
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
