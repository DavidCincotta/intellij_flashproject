import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    Maze maze;
    public MazeSolver(Maze maze){
        this.maze = maze;
    }
    public boolean depthFirstSearch(ArrayList<MazeCoords> previousNodes, MazeCoords nextNode){
        if (maze.isEndSquare(nextNode)){
            System.out.println("End");
            return true;
        } else if (previousNodes.contains(nextNode)){
            return false;
        }
        previousNodes.add(nextNode);

        ArrayList<MazeCoords> neighbors = new ArrayList<>();
        MazeCoords a = new MazeCoords(nextNode.x,nextNode.y+1);
        MazeCoords ab = new MazeCoords(nextNode.x,nextNode.y-1);
        MazeCoords abc = new MazeCoords(nextNode.x+1,nextNode.y);
        MazeCoords abcd = new MazeCoords(nextNode.x-1,nextNode.y);
        MazeCoords abcde = new MazeCoords(nextNode.x+1,nextNode.y+1);
        MazeCoords abcdef = new MazeCoords(nextNode.x-1,nextNode.y-1);
        MazeCoords abcdefg = new MazeCoords(nextNode.x+1,nextNode.y-1);
        MazeCoords abcdefgh = new MazeCoords(nextNode.x-1,nextNode.y+1);
        if (maze.isPassable(a) && !previousNodes.contains(a)) neighbors.add(a);
        if (maze.isPassable(ab) && !previousNodes.contains(ab)) neighbors.add(ab);
        if (maze.isPassable(abc) && !previousNodes.contains(abc)) neighbors.add(abc);
        if (maze.isPassable(abcd) && && !previousNodes.contains(abcd)) neighbors.add(abcd);
        if (maze.isPassable(abcde)) neighbors.add(abcde);
        if (maze.isPassable(abcdef)) neighbors.add(abcdef);
        if (maze.isPassable(abcdefg)) neighbors.add(abcdefg);
        if (maze.isPassable(abcdefgh)) neighbors.add(abcdefgh);
        for (int i = 0; i < neighbors.size(); i++) {
            if (depthFirstSearch(previousNodes,neighbors.get(i))){
                return true;
            }
        }


        return false;
    }

    public void breadthFirstSearch(){
        /*
        ArrayList<BreadthNode> nodes = new ArrayList<>();
        Stack depth = new Stack();
        MazeCoords start = null,end = null;
        for (int i = 0; i < maze.grid.length; i++) {
            for (int j = 0; j < maze.grid[i].length; j++) {
                if (maze.isStartSquare(new MazeCoords(i,j))) start = new MazeCoords(i,j);
                if (maze.isEndSquare(new MazeCoords(i,j))) end = new MazeCoords(i,j);
            }
        }
        BreadthNode startNode = new BreadthNode(start,true,false);
        startNode.children = getChildren(startNode);

        MazeCoords nextNode = startNode.children.get(0);
        do {
            System.out.print("X: "+nextNode.x+"  "+"Y: "+nextNode.y);
            System.out.println("");
            nextNode = new MazeCoords();
            if (maze.isEndSquare(nextNode)){
                BreadthNode endNode = new BreadthNode(nodes.get(nodes.size()-1),nextNode);
                while(!startNode.coords.equals(nextNode)){
                    System.out.print("X: "+nextNode.x+"  "+"Y: "+nextNode.y);
                }
                break;
            }
        }while(nextNode != end);
    }

    public ArrayList<BreadthNode> getChildren(BreadthNode parent){
        ArrayList<BreadthNode> children = new ArrayList<>();
        try {//1
            if (maze.isPassable(new MazeCoords(parent.coords.x+1,parent.coords.y+1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x+1,parent.coords.y+1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//2
            if (maze.isPassable(new MazeCoords(parent.coords.x+1,parent.coords.y))) {
                children.add(new BreadthNode(parent, new MazeCoords(parent.coords.x+1,parent.coords.y)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//3
            if (maze.isPassable(new MazeCoords(parent.coords.x+1,parent.coords.y-1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x+1,parent.coords.y-1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//4
            if (maze.isPassable(new MazeCoords(parent.coords.x-1,parent.coords.y+1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x-1,parent.coords.y+1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//5
            if (maze.isPassable(new MazeCoords(parent.coords.x-1,parent.coords.y))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x-1,parent.coords.y)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//6
            if (maze.isPassable(new MazeCoords(parent.coords.x-1,parent.coords.y-1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x-1,parent.coords.y-1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//7
            if (maze.isPassable(new MazeCoords(parent.coords.x,parent.coords.y+1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x,parent.coords.y+1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//8
            if (maze.isPassable(new MazeCoords(parent.coords.x,parent.coords.y-1))) {
                children.add(new BreadthNode(parent,new MazeCoords(parent.coords.x,parent.coords.y-1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
        */
    }

}
