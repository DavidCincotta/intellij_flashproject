
public class SpiralTestMaze extends Maze {
	private static final boolean spiralGrid[][] = { 
		{false, true, false, false, false, false, false},
		{false, true, false, true, true, true, false},
		{false, true, false, true, false, true, false},
		{false, true, false, true, false, true, false},
		{false, true, false, false, false, true, false},
		{false, true, true, true, true, true, false},
		{false, false, false, false, false, false, false}
	};

	public SpiralTestMaze() {
		super();
		grid = spiralGrid;	
	}

	public boolean isEndSquare(MazeCoords c) {
		return (c.x == 4 && c.y == 2);
	}
}
