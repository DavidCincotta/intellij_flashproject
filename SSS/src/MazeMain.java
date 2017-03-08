import sun.invoke.empty.Empty;
import java.util.ArrayList;
import java.util.EmptyStackException;
public class MazeMain {
		public static void main(String args[]) throws Exception{
			//Maze maze = new Maze();
			//Maze maze = new SpiralTestMaze();
			//Maze maze = new RandomMaze(60,60);
			//Maze maze = new BrokenTestMaze();
            Maze maze = new NewTestMaze();
            //Maze maze = new OpenTestMaze();
			maze.printMaze();
            MazeSolver solver = new MazeSolver(maze);
            if (solver.depthFirstSearch(new ArrayList<>(), new MazeCoords(0, 0))){
                System.out.println("Solution Found");
            } else {
                System.out.println("No Solution");
            }
            ArrayList<MazeCoords> finalPoints = new ArrayList<>();
            try{
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    finalPoints.add(solver.finalStack.pop());
                }
            }catch(EmptyStackException e) {
            }
            maze.printSolvedMaze(finalPoints);//+ represents the path traveled
        }
}