package Element.Motion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Element.Permeabilite;
import PathFinder.PathFinder2;
import model.Model;

public class DaemonTest extends MotionElement implements Runnable, ActionListener {
	
		
		public static String SPRITE = "monster_3.png";
		public String type = "Demon";
		public int vie = 1;
		public Model model;
		public Timer MoveTimer;
		public boolean[][] walkable;
		public PathFinder2 pathfinder;
		public int[][] movelist;

		public DaemonTest(Model model, int y , int x) 
		{
			super("C:/ProjetJava/Sprite/monster_3.png", Permeabilite.PENETRABLE);
			this.model = model;
			this.MoveTimer=new Timer(300,this);
			
			this.pathfinder = new PathFinder2();
			this.run();

		}

		public void actionPerformed(ActionEvent e)
		{
			this.movelist = this.pathfinder.findPath(this.x, this.y, this.model.lorann.x, this.model.lorann.y,this.toBool(this.model.mapo));
			//this.model.MoveDemon(movelist[0][0], movelist[0][1], this);
		}

		public void run() {
			// TODO Auto-generated method stub
			this.MoveTimer.start();
		}
		
		public boolean[][] toBool()
		{
			boolean[][] walkable = new boolean[12][20];
			int i=0;
			int j=0;
			while(j<11){
				while(i<20){
					if(map.map[j][i].perm==Permeability.PASS)
					{
						nmap[j][i]=true;
					}
					else
					{
						nmap[j][i]=false;
					}
					i++;
				}
				i=0;
				j++;
			}
			return nmap;
		}

	}

}
