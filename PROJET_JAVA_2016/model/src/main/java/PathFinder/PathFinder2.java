package PathFinder;


public class PathFinder2 
{
	private int steps;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private boolean[][] walkable;
	private int [][] distance;
	private int path[][];
	private int WalkLength;
	private int WalkWidth;
	private int DistanceLength;
	private int DistanceWidth;
	
	public PathFinder2()
	{
	}
	
	public int[][] findPath(int startX, int startY, int endX, int endY, boolean[][] walkable) 
	{
		this.setWalkable(walkable);
		this.setEndX(endX);
		this.setEndY(endY);
		this.setStartX(startX);
		this.setStartY(startY);
		this.setWalkLength(this.getWalkableLength());
		this.setWalkWidth(this.getWalkableWidth());
		
		this.initDistanceMtx();
		this.CalculateDistancePath();
		this.GenerateMoveTab();	  
	    return path;
	}
	
	public void initDistanceMtx()
	{
	    this.distance = new int[this.getWalkLength()][this.getWalkWidth()];
	    this.setDistanceLength(this.getDistanceMtxLength());
	    this.setDistanceWidth(this.getDistanceMtxWidth());
	    
	    for(int i=0; i < this.getDistanceLength(); i++) 
	    {
	        for(int j=0; j < this.getDistanceWidth(); j++) 
	        {
	            distance[i][j] = -1;
	        }
	    }
	    distance[this.getEndY()][this.getEndX()] = 0;
	}
	
	public void CalculateDistancePath()
	{
		 this.steps = 1;

		 while(distance[this.getStartY()][this.getStartX()] == -1 ) 
		 {
			 for(int i=0; i < this.getDistanceLength(); i++) 
		        {     	
		            for(int j=0; j < this.getDistanceWidth(); j++) 
		            {            	
		                if(distance[i][j] == steps - 1) 
		                {
		                    if(i < this.getWalkLength() -1) 
		                    {
		                        if(distance[i+1][j] == -1 && walkable[i+1][j]) 
		                        {
		                            distance[i+1][j] = steps;
		                        }
		                    }
		                    if(i > 0) 
		                    {
		                        if(distance[i-1][j] == -1 && walkable[i-1][j]) 
		                        {
		                            distance[i-1][j] = steps;
		                        }
		                    }
		                    if(j < this.getWalkWidth() -1) 
		                    {
		                        if(distance[i][j+1] == -1 && walkable[i][j+1]) 
		                        {
		                            distance[i][j+1] = steps;
		                        }
		                    }
		                    if(j > 0 ) 
		                    {
		                        if(distance[i][j-1] == -1 && walkable[i][j-1]) 
		                        {
		                            distance[i][j-1] = steps;
		                        }
		                    }  
		                    if(j > 0 && i>0) 
		                    {
		                        if(distance[i-1][j-1] == -1 && walkable[i-1][j-1]) 
		                        {
		                            distance[i-1][j-1] = this.steps;
		                        }
		                    }
		                    if(j > 0 && i<this.getWalkLength()-1) 
		                    {
		                        if(distance[i+1][j-1] == -1 && walkable[i+1][j-1]) 
		                        {
		                            distance[i+1][j-1] = this.steps;
		                        }
		                    }
		                    if(i > 0 && j < this.getWalkWidth() - 1) 
		                    {
		                        if(distance[i-1][j+1] == -1 && walkable[i-1][j+1]) 
		                        {
		                            distance[i-1][j+1] = this.steps;
		                        }
		                    }
		                    if(i < this.getWalkLength() - 1 && j < this.getWalkWidth() - 1) 
		                    {
		                        if(distance[i+1][j+1] == -1 && walkable[i+1][j+1]) 
		                        {
		                            distance[i+1][j+1] = this.steps;
		                        }
		                    }
		                } 
		            }     
		        }
			 	this.setSteps(this.getSteps()+1);
		 }
	}
	
	public void GenerateMoveTab()
	{
		int x = 0;
	    int y = 0;
	    int i = 0;
	    
	    this.path = new int[this.getSteps()][2];

	    this.path[0][0] = this.getStartX();
	    this.path[0][1] = this.getStartY();	    

	    while(path[i][1] != this.getEndY() || path[i][0] != this.getEndX()) 
	    {
	        x = path[i][0];
	        y = path[i][1];

	        i++;
	        
	        if(x<this.getWalkWidth()-1 && distance[y][x+1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x+1;
	            path[i][1] = y;
	        }
	        else if(x>0 && distance[y][x-1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x-1;
	            path[i][1] = y;
	        }
	        else if(y<this.getWalkLength()-1 && distance[y+1][x] == distance[y][x] - 1) 
	        {
	            path[i][0] = x;
	            path[i][1] = y+1;
	        }
	        else if(y>0 && distance[y-1][x] == distance[y][x] - 1) 
	        {
	            path[i][0] = x;
	            path[i][1] = y-1;
	        }
	    	else if(x<this.getWalkWidth()-1 && y<this.getWalkLength() - 1 && distance[y+1][x+1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x+1;
	            path[i][1] = y+1;
	        }
	        else if(x>0 && y<this.getWalkLength() - 1 && distance[y+1][x-1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x-1;
	            path[i][1] = y+1;
	        }
	        else if(x<this.getWalkWidth()-1 && y>0 && distance[y-1][x+1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x+1;
	            path[i][1] = y-1;
	        }
	        else if(x>0 && y>0 && distance[y-1][x-1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x-1;
	            path[i][1] = y-1;
	        }
	        
	    }
	    // note that path[i][0] has the X-Coordinate and path[i][1] has the Y-Coordinate!
	    int[][] temp = this.path;
	    i=1;
	    while(i<this.getSteps())
	    {
	    	path[i-1][0]=temp[i][0] - temp[i-1][0];
	    	path[i-1][1]=temp[i][1] - temp[i-1][1];
	    	i++;
	    }
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	public int getWalkableLength()
	{
		return walkable.length;
	}
	public int getWalkableWidth()
	{
		return walkable[0].length;
	}

	public boolean[][] getWalkable() {
		return walkable;
	}

	public void setWalkable(boolean[][] walkable) {
		this.walkable = walkable;
	}

	public int getWalkLength() {
		return WalkLength;
	}

	public void setWalkLength(int length) {
		this.WalkLength = length;
	}

	public int getWalkWidth() {
		return WalkWidth;
	}

	public void setWalkWidth(int width) {
		this.WalkWidth = width;
	}

	public int getDistanceLength() {
		return DistanceLength;
	}

	public void setDistanceLength(int distanceLength) {
		DistanceLength = distanceLength;
	}

	public int getDistanceWidth() {
		return DistanceWidth;
	}

	public void setDistanceWidth(int distanceWidth) {
		DistanceWidth = distanceWidth;
	}
	
	public int getDistanceMtxLength()
	{
		return distance.length;
	}
	public int getDistanceMtxWidth()
	{
		return distance[0].length;
	}

}
