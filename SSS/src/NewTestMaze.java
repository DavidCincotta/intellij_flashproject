public class NewTestMaze extends Maze {
	private static final boolean newGrid[][] = {
		{false, true,  false, false, false, true, false},
		{false, true,  false, true,  false,  false,  false},
		{false, true,  false, true,  false, true,  false},
		{false, false,  false, false,  false, true,  false},
		{false, true,  false, false, true, false,  false},
		{false, true,  false,  true,  true,  false,  true},
		{false, true, false, false, true, false, false}
	};

	public NewTestMaze() {
		super();
		grid = newGrid;
	}


}
