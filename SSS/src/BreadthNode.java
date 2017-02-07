import java.util.ArrayList;

public class BreadthNode {
    boolean start = false;
    boolean end = false;
    BreadthNode parent = null;
    MazeCoords coords;
    ArrayList<BreadthNode> children = new ArrayList<>();

    public BreadthNode(BreadthNode parent, MazeCoords coords) {
        this.parent = parent;
        this.coords = coords;
    }

    public BreadthNode(MazeCoords coords, boolean start, boolean end) {
        this.coords = coords;
        this.start = start;
        this.end = end;
    }
}
