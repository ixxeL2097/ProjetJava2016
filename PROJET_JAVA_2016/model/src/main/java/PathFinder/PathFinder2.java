package PathFinder;


public class PathFinder2 {
	

	private int steps;
	
	public PathFinder2()
	{
	}
	
	public int[][] findPath(int startX, int startY, int endX, int endY, boolean[][] walkable) 
	{
	    int length = walkable.length;
	    int width = walkable[0].length;
	    
	    int x=0;
	    int y=0;

	    int[][] distance = new int[length][width];
	    
	    for(int i=0; i < distance.length; i++) 
	    {
	        for(int j=0; j < distance[i].length; j++) 
	        {
	            distance[i][j] = -1;
	        }
	    }

	    distance[endY][endX] = 0;
	    
		/*for(y=0; y<length; y++)
		{
			x=0;
			System.out.println("");
			for(x=0; x<width; x++)
			{
				System.out.print(distance[y][x]);
			}
		}
		System.out.println();
		System.out.println("START:"+"y:"+startY+" x:"+startX+" "+ distance[startY][startX]);
		for(y=0; y<length; y++)
		{
			x=0;
			System.out.println("");
			for(x=0; x<width; x++)
			{
				System.out.print(walkable[y][x]+"  ");
			}
		}*/
		
		
	    this.steps = 1;

	    while(distance[startY][startX] == -1 ) 
	    {
	    	//System.out.println("STEPS :"+steps);
	    	//System.out.println(distance[startY][startX]);
	    	/*for(y=0; y<length; y++)
			{
				x=0;
				System.out.println("");
				for(x=0; x<width; x++)
				{
					System.out.print(distance[y][x]+" ");
				}
			}
	    	System.out.println();	    
	    	System.out.println("--------------------------------------------------------------------------- ");
	    	
	    	for(y=0; y<length; y++)
			{
				x=0;
				System.out.println("");
				for(x=0; x<width; x++)
				{
					System.out.print(walkable[y][x]+" ");
				}
			}
	    	System.out.println();		    
	    	System.out.println("--------------------------------------------------------------------------- ");
	    	System.out.println();
	    	System.out.println(startY+" "+startX);
	    	System.out.println(endY+" "+endX);*/
	        for(int i=0; i < distance.length; i++) 
	        {
	            for(int j=0; j < distance[i].length; j++) 
	            {
	                if(distance[i][j] == steps - 1) 
	                {
	                    if(i < width -1) 
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
	                    if(j < length -1) 
	                    {
	                        if(distance[i][j+1] == -1 && walkable[i][j+1]) 
	                        {
	                            distance[i][j+1] = steps;
	                        }
	                    }
	                    if(j > 0) 
	                    {
	                        if(distance[i][j-1] == -1 && walkable[i][j-1]) 
	                        {
	                            distance[i][j-1] = steps;
	                        }
	                    }
	                }
	            }
	        }
	        steps++;
	    }
	   
	    x = 0;
	    y = 0;
	    int[][] path = new int[steps][2];

	    path[0][0] = startX;
	    path[0][1] = startY;
	    
	    int i = 0;

	    while(path[i][1] != endY || path[i][0] != endX) 
	    {
	        x = path[i][0];
	        y = path[i][1];

	        i++;
	        
	        if(x<width-1 && distance[y][x+1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x+1;
	            path[i][1] = y;
	        }
	        else if(x>0 && distance[y][x-1] == distance[y][x] - 1) 
	        {
	            path[i][0] = x-1;
	            path[i][1] = y;
	        }
	        else if(y<length-1 && distance[y+1][x] == distance[y][x] - 1) 
	        {
	            path[i][0] = x;
	            path[i][1] = y+1;
	        }
	        else if(y>0 && distance[y-1][x] == distance[y][x] - 1) 
	        {
	            path[i][0] = x;
	            path[i][1] = y-1;
	        }
	        
	    }
	    for(y=0; y<steps; y++)
		{
			x=0;
			System.out.println("");
			for(x=0; x<2; x++)
			{
				System.out.print(path[y][x]+" ok");
			}
		}
	    // note that path[i][0] has the X-Coordinate and path[i][1] has the Y-Coordinate!
	    int[][] temp = path;
	    i=1;
	    while(i<steps){
	    	path[i-1][0]=temp[i][0] - temp[i-1][0];
	    	path[i-1][1]=temp[i][1] - temp[i-1][1];
	    	i++;
	    }
	    for(y=0; y<steps; y++)
		{
			x=0;
			System.out.println("");
			for(x=0; x<2; x++)
			{
				System.out.print(path[y][x]+" ");
			}
		}
	    System.out.println();
	    System.out.println("------------------------FINISHED-------------------------------");
	    return path;
	}

}
