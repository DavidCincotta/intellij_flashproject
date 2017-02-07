
// very simple class to hold a pair of coords for the maze

public class MazeCoords {
	int x, y;
	public MazeCoords() {
		x = y = 0;
	}

	public MazeCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
