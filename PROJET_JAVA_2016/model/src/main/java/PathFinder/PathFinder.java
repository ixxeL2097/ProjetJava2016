package PathFinder;

public class PathFinder 
{
	
	/*private boolean[][] walkable = new boolean [5][5];
	private int startX=0;
	private int startY=0;
	private int endX=3;
	private int endY=3;*/
	private int steps=1;
	//private int[][] pathResult;
	
	public PathFinder()
	{
		/*int x=0,y=0;
		for(y=0; y<5; y++)
		{
			for(x=0; x<5; x++)
			{
				this.walkable[y][x]=true;
			}
		}
		this.walkable[1][2]=false;
		this.walkable[2][1]=false;
		this.walkable[2][2]=false;
		this.walkable[3][1]=false;

		pathResult = this.findPath(startX, startY, endX, endY, walkable);
	
		for(y=0; y<steps; y++)
		{
			for(x=0; x<2; x++)
			{
				System.out.print(pathResult[y][x]);
			}
			System.out.println("");
		}*/
	}
	
	
	
	
	
	public int[][] findPath(int startX, int startY, int endX, int endY, boolean[][] walkable) 
	{
	    /* first we get the the width and length of the map */
	    int length = walkable.length;
	    int width = walkable[0].length;
	    System.out.println(length);
	    System.out.println(width);

	    /* now we initialize an int array and fill it with -1 */
	    int[][] distance = new int[length][width];
	    for(int i=0; i < distance.length; i++) 
	    {
	        for(int j=0; j < distance[i].length; j++) 
	        {
	            distance[i][j] = -1;
	        }
	    }
	    /* our target point is (endX,endY) so there is distance = 0 */
	    distance[endY][endX] = 0;
	    /* we need to store the steps from the end point to the next Tiles in a variable */
	    this.steps = 1;

	    /* now we'll mark every tile how much steps there are from the current Point to the end point until we hit the start point*/
	    while(distance[startY][startX] == -1) 
	    {
	    	//System.out.println("steps "+steps);
	        for(int i=0; i < distance.length; i++) 
	        {
	            for(int j=0; j < distance[i].length; j++) 
	            {
	                /* check if the current Tile is have the distance of the last marked Tile */
	                if(distance[i][j] == steps - 1) 
	                {
	                    /* check if the Tile right from the current Tile is valid */
	                    if(i < width - 1) 
	                    {
	                        /* check if that Tile is not marked and if it is walkable*/
	                        if(distance[i+1][j] == -1 && walkable[i+1][j]) 
	                        {
	                            /* not marked & walkable, so we can mark it */
	                            distance[i+1][j] = steps;
	                        }
	                    }

	                    /* check if the Tile left from the current Tile is valid */
	                    if(i > 0) 
	                    {
	                        /* check if that Tile is not marked and if it is walkable*/
	                        if(distance[i-1][j] == -1 && walkable[i-1][j]) 
	                        {
	                            /* not marked & walkable, so we can mark it */
	                            distance[i-1][j] = steps;
	                        }
	                    }

	                    /* check if the Tile underneath the current Tile is valid */
	                    if(j < length - 1) 
	                    {
	                        /* check if that Tile is not marked and if it is walkable*/
	                        if(distance[i][j+1] == -1 && walkable[i][j+1]) 
	                        {
	                            /* not marked & walkable, so we can mark it */
	                            distance[i][j+1] = steps;
	                        }
	                    }

	                    /* check if the Tile above the current Tile is valid */
	                    if(j > 0) 
	                    {
	                        /* check if that Tile is not marked and if it is walkable*/
	                        if(distance[i][j-1] == -1 && walkable[i][j-1]) 
	                        {
	                            /* not marked & walkable, so we can mark it */
	                            distance[i][j-1] = steps;
	                        }
	                    }
	                }
	            }
	        }
	        /* increment steps after each map check */
	        steps++;
	    }
	    System.out.println(steps);
		int j=0,f=0;
		for(j=0; j<5; j++)
		{
			for(f=0; f<5; f++)
			{
				System.out.println(distance[j][f]);
			}
		}	

	    /* now we have checked every Tile, so we can start making a path */
	    int[][] path = new int[steps][2];
	    /* we start with the start point */
	    path[0][0] = startX;
	    path[0][1] = startY;

	    int i = 0;
	    /* now we go on until we hit the end point */
	    while(path[i][0] != endX || path[i][1] != endY) 
	    {
	        /* get current tile coordinates */
	        int x = path[i][0];
	        int y = path[i][1];

	        /* we have everything from the current tile so increment i */
	        i++;

	        /* check if the field right from the current field is one step away */
	        if(x<width-1 && distance[y][x+1] == distance[y][x] - 1) 
	        {
	            /* that tile is one step away, so take it into the path */
	            path[i][0] = x+1;
	            path[i][1] = y;
	        }
	        /* check if the field left from the current field is one step away */
	        else if(x>0 && distance[y][x-1] == distance[y][x] - 1) 
	        {
	            /* that tile is one step away, so take it into the path */
	            path[i][0] = x-1;
	            path[i][1] = y;
	        }
	        /* check if the field underneath the current field is one step away */
	        else if(y<length-1 && distance[y+1][x] == distance[y][x] - 1) 
	        {
	            /* that tile is one step away, so take it into the path */
	            path[i][0] = x;
	            path[i][1] = y+1;
	        }
	        /* check if the field above the current field is one step away */
	        else if(y>0 && distance[y-1][x] == distance[y][x] - 1) 
	        {
	            /* that tile is one step away, so take it into the path */
	            path[i][0] = x;
	            path[i][1] = y-1;
	        }
	    }
	    /* now we should have the finished path array */
	    // note that path[i][0] has the X-Coordinate and path[i][1] has the Y-Coordinate!
	    //this.pathResult = new int[steps][2];
	    
	    
	    
	    
	    /*System.out.println();
	    int x=0;
	    int y=0;
	    while(y<5){
	    	while(x<5){
	    		System.out.print(distance[y][x]);
	    		x++;
	    	}
	    	System.out.println("");
	    	x=0;
	    	y++;
	    }*/
	    int [][] temp = path;
	    int k=1;
	    while(k<steps)
	    {
	    	path[k-1][0] = temp[k][0] - temp[k-1][0];
	    	path[k-1][1] = temp[k][1] - temp[k-1][1]; 
	    	k++;
	    }
	    return path;
	}

}
