
// Maze -- implements a very basic maze as a 2-dimensional array of booleans.  
// Each location is either empty (false), and thus passable, or filled (true) and 
// thus impassable.  Locations in the array are given as coordinates starting with 0.
// Location 0,0 is the upper left corner, location 2, 0 is two cells to the right of that,
// and location width - 1, height - 1 is the lower right corner and also the exit for the maze.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Maze {
	private static final int DEFAULT_WIDTH = 7;
	private static final int DEFAULT_HEIGHT = 7;
	
	int height, width;
	
	// the default maze is 7 x 7; true actually means blocked; false means open
	// IMPORTANT: with the grid coded this way, coords are reversed; the first index into the
	// grid is the row and the second is the column.  The code in this class does the job of
	// switching that into x, y coords where x is horizontal and y is vertical as in 
	// standard algebra, but be mindful of all this when modifying or creating your
	// own hard-coded mazes.
	private static final boolean defaultGrid[][] = { 
			{false, true, false, false, false, false, false},
			{false, true, false, true, true, true, false},
			{false, false, false, true, false, true, true},
			{false, true, true, true, false, false, false},
			{false, true, false, false, false, true, false},
			{false, false, false, true, false, true, false},
			{false, true, false, true, false, true, false}
	};
	
	boolean grid[][];
	
	public Maze() {
		grid = defaultGrid;
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	// returns true if the given location is the start square 
	// doesn't check whether coords are valid
	public boolean isStartSquare(MazeCoords c) {
		return (c.x == 0 && c.y == 0);
	}
	
	// returns true if the given location is the end square (can be used to terminate search)
	// doesn't check whether coords are valid
	public boolean isEndSquare(MazeCoords c) {
		return (c.x == (width - 1) && c.y == (height - 1));
	}
	
	// returns true if the given location is a valid location for the maze (can help
	// prune search so you can generate neighboring locations and then call this and
	// discard them without constantly checking the boundaries of the maze)
	public boolean validCoordinates(MazeCoords c) {
		return (( c.x >= 0 && c.x < width ) && ( c.y >= 0 && c.y < height));
	}
	
	// returns true if the given location is passable (i.e., empty, not a wall)
	public boolean isPassable(MazeCoords c) {
		if (! validCoordinates(c)) {
			//System.err.println("Invalid coordinates " + c);
			return false;
		}
		return ! grid[c.y][c.x];
	}
	
	// prints out the maze (with a border)
	public void printMaze() throws Exception{
		for (int i = 0; i < width + 2; i++)
			System.out.print("#");
		System.out.println();
		for (int row = 0; row < height; row++) {
			System.out.print("#");
			for (int col = 0; col < width; col++) {
				MazeCoords coords = new MazeCoords(col, row);
				if (isStartSquare(coords))
					System.out.print("S");
				else if (isEndSquare(coords))
					System.out.print("E");
				else if (isPassable(coords))
					System.out.print(" ");
				else
					System.out.print("#");
			}
			System.out.println("#");
		}
		for (int i = 0; i < width + 2; i++)
			System.out.print("#");
		System.out.println();
	}
	public void printSolvedMaze(ArrayList<MazeCoords> points) throws Exception{
		for (int i = 0; i < width + 2; i++)
			System.out.print("#");
		System.out.println();
		for (int row = 0; row < height; row++) {
			System.out.print("#");
			for (int col = 0; col < width; col++) {
				boolean hasBeenPrinted = false;
				MazeCoords coords = new MazeCoords(col, row);
				for (MazeCoords a :
						points) {
					if (a.y == coords.y && a.x == coords.x && !isStartSquare(coords) && !isEndSquare(coords)) {
						System.out.print("+");
						hasBeenPrinted = true;
					}
				}
				if (isStartSquare(coords))
					System.out.print("S");
				else if (isEndSquare(coords))
					System.out.print("E");
				else if (isPassable(coords) && !hasBeenPrinted)
					System.out.print(" ");
				else if (!isPassable(coords))
					System.out.print("#");
			}
			System.out.println("#");
		}
		for (int i = 0; i < width + 2; i++)
			System.out.print("#");
		System.out.println();
	}
}
