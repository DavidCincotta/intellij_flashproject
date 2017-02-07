

// main class for Mini-Maze Project; all it currently does is create a maze
// object and print it out

import java.util.ArrayList;

public class MazeMain {
		public static void main(String args[]) throws Exception{
			Maze maze = new Maze();
			maze.printMaze();
            MazeSolver solver = new MazeSolver(maze);
            solver.depthFirstSearch(new ArrayList<>(),new MazeCoords(0,0));
		}
}
