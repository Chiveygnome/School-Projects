import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

//Quick note about the algorithm: you have to use the path of the input file or else the program will not find your file.  you have been warned!!
public class paths {
	static LinkedList<Boolean>[] adj = new LinkedList[10];//the adjacency list
	static boolean North;//variables to determine the direction of movement
	static boolean South;
	static boolean East;
	static boolean West;
	static int numOfCases;//various global variable for use in the program
	static int i;
	public static void InitGraph()//initialize the graph and variables note: only use once
	{
		for(int k = 0;k<10;k++)
		{
			if(adj[k] == null) adj[k] = new LinkedList();//if the current nod is null then initialize
			for(int j = 0;j<10;j++)
			{
				adj[k].add(true);//set all to true
			}
		}
		North = South = East = West = false;
	}
	public static void RefreshGraph()//use this method to refresh the graph before a new data set is loaded in
	{
		for(int k = 0; k<10;k++)
		{
			for(int j = 0;j<10;j++)
			{
				if(!adj[k].get(j)) adj[k].set(j, true);//set all false entries to true
			}
		}
	}
	
	public static void FindPath(String name) throws FileNotFoundException//this is the "main"function
	{
		InitGraph();
		File in = new File(name);//get file
		Scanner s = new Scanner(in);//open file
		numOfCases = s.nextInt();//get the number of cases from the file 
		for(int j = 0;j < numOfCases;j++)//iterate the amount of cases
		{
			System.out.println("Data set "+(j+1)+":\n");
			int badIntersections = s.nextInt();//get the number of invalid intersections
			while(badIntersections>0)
			{
				adj[s.nextInt()].set(s.nextInt(), false);//set the invalid intersection to false
				badIntersections--;
			}
			int numOfTest = s.nextInt();//next get the number of trips being preformed in this data set
			for(int k = 0;k<numOfTest;k++)
			{
				int arg1 = s.nextInt(),arg2 = s.nextInt(),arg3 = s.nextInt(), arg4 = s.nextInt();//set the coordinates
				i = compute(arg1,arg2,arg3,arg4);//go the actual algorithm
				if(i == -1)
				{
					System.err.println("Somehow you messed up.  This is all your fault.  Please try again but with less failure.");
					return;
				}
				System.out.println("	Test case "+(k+1)+": Nick can take "+i+" perfect paths.");
				North = South = East = West = false;//set direction variables back to false
			}
			RefreshGraph();//refresh the adjacency lists
		}
		s.close();//close the scanner
	}
	public static int compute(int startX, int startY, int endX, int endY)
	{
		if(startX == endX && startY == endY) return 1;//if the coordinates are equal then return 1
		else if(!adj[startX].get(startY)) return 0;//else if in an invalid location, return 0
		if(startX<endX) //set the directional variables for this iteration.
		{
			West = true;
		}
		if(startX>endX) 
		{
			East = true;
		}
		if(startY<endY) 
		{
			North = true;
		}
		if(startY>endY) 
		{
			South = true;
		}
		//choose the direction that the algorthim will take, based on the directional variables 
		if(West && North) return compute1(startX+1,startY,endX,endY) + compute1(startX,startY+1,endX,endY);
		//if the point is above and to the right, recursively backtrack to both above and the right
		else if(East && North) return compute1(startX-1,startY,endX,endY) + compute1(startX,startY+1,endX,endY);
		//if the point is above and to the left, recursively backtrack to both above and the left
		else if(West && South) return compute1(startX+1,startY,endX,endY) + compute1(startX,startY-1,endX,endY);
		//if the point is below and to the right, recursively backtrack to both below and the right
		else if(East && South)return compute1(startX-1,startY,endX,endY) + compute1(startX,startY-1,endX,endY);
		//if the point is below and to the left, recursively backtrack to both below and the left
		else return -1;
	}
	public static int compute1(int startX,int startY,int endX,int endY)
	{
		//This is the recursive helper function of the algorithm.  It continues to search for all possible perfect paths until it hits an the goal.  The way it is designed, the algorithm
		//should not go out of bounds. When it enters an invalid location, it returns 0.  When it enters the goal location, it returns 1.
		if(startX == endX && startY == endY) return 1;
		if(!adj[startX].get(startY)) return 0;
		else if(North && (startX==endX && startY<endY)) return compute1(startX,startY+1,endX,endY);
		//if the piont is directly above, go up
		else if(South && (startX==endX && startY>endY)) return compute1(startX,startY-1,endX,endY);
		//if the point is directly below, go down
		else if(West && (startY==endY && startX<endX)) return compute1(startX+1,startY,endX,endY);
		//if the point is directly to the right, go right
		else if(East && (startY==endY && startX>endX)) return compute1(startX-1,startY,endX,endY);
		//if the point is directly to the left, go left
		else if((West && North) && (startX<endX && startY<endY)) return compute1(startX+1,startY,endX,endY) + compute1(startX,startY+1,endX,endY);
		//if the point is above and to the right, recursively backtrack to both above and the right
		else if((East && North) && (startX>endX && startY<endY)) return compute1(startX-1,startY,endX,endY) + compute1(startX,startY+1,endX,endY);
		//if the point is above and to the left, recursively backtrack to both above and the left
		else if((West && South) && (startX<endX && startY>endY)) return compute1(startX+1,startY,endX,endY) + compute1(startX,startY-1,endX,endY);
		//if the point is below and to the right, recursively backtrack to both below and the right
		else if((East && South) && (startX>endX && startY>endY)) return compute1(startX-1,startY,endX,endY) + compute1(startX,startY-1,endX,endY);
		//if the point is below and to the left, recursively backtrack to both below and the left
		else return 0;//else if it gets this far, you've done something wrong.  Please reevaluate your life.
	}
	public static void main(String args[]) throws FileNotFoundException
	{
		FindPath("src/input.txt");//hard coded the input file in
	}
}
