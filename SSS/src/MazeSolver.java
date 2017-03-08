import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    Maze maze;
    Stack<MazeCoords> finalStack = new Stack<>();
    public MazeSolver(Maze maze){
        this.maze = maze;
    }
    public boolean depthFirstSearch(ArrayList<MazeCoords> previousNodes, MazeCoords nextNode){
        if (maze.isEndSquare(nextNode)){
            //System.out.println("X: " + nextNode.x + " Y: " + nextNode.y);
            finalStack.push(nextNode);
            return true;
        } else if (previousNodes.contains(nextNode)){
            return false;
        }
        previousNodes.add(nextNode);
        finalStack.push(nextNode);
        //System.out.println("X: " + nextNode.x + " Y: " + nextNode.y);
        ArrayList<MazeCoords> neighbors = new ArrayList<>();
        MazeCoords a1 = new MazeCoords(nextNode.x,nextNode.y+1);
        MazeCoords a2 = new MazeCoords(nextNode.x,nextNode.y-1); // neighbors
        MazeCoords a3 = new MazeCoords(nextNode.x+1,nextNode.y);
        MazeCoords a4 = new MazeCoords(nextNode.x-1,nextNode.y);
        if (maze.isPassable(a4) && !a4.isContained(previousNodes)) neighbors.add(a4);
        if (maze.isPassable(a1) && !a1.isContained(previousNodes)) neighbors.add(a1);
        if (maze.isPassable(a2) && !a2.isContained(previousNodes)) neighbors.add(a2);
        if (maze.isPassable(a3) && !a3.isContained(previousNodes)) neighbors.add(a3);
        for (int i = 0; i < neighbors.size(); i++) {

            if (depthFirstSearch(previousNodes,neighbors.get(i))){
                return true;
            }else{
                finalStack.pop();
            }
        }
        return false;
    }
    public void breadthFirstSearch(){
    }

}
