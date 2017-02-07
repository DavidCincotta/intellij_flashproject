
public class RandomMaze extends Maze {

	public RandomMaze(int height, int width) {
		this.height = height;
		this.width = width;
		grid = new boolean[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) 
				grid[i][j] = true;	
		tunnelFrom(new MazeCoords(0,0), 0, height * width + 1);		
	}
	
	public int randomX() {
		return (int) (Math.random() * width);
	}
	
	public int randomY() {
		return (int) (Math.random() * height);		
	}
	
	public MazeCoords randomSquare() {
		return new MazeCoords(randomX(), randomY());		
	}
	
	// return MazeCoords for a random square that is empty (false in grid)
	public MazeCoords randomEmptySquare() {
		while (true) {
			MazeCoords square = randomSquare();
			if (! grid[square.y][square.x])
				return square;
		}
	}
	
	// returns the number of empty neighbors (false in grid) looking up/right/down/left
	// for the given coordinates
	public int numberEmptyNeighbors(MazeCoords coords) {
		int totalEmpty = 0;
		MazeCoords up = new MazeCoords(coords.x, coords.y - 1);
		if (validCoordinates(up) && ! grid[up.y][up.x])
			totalEmpty++;
		MazeCoords right = new MazeCoords(coords.x + 1, coords.y);
		if (validCoordinates(right) && ! grid[right.y][right.x])
			totalEmpty++;
		MazeCoords down = new MazeCoords(coords.x, coords.y + 1);
		if (validCoordinates(down) && ! grid[down.y][down.x])
			totalEmpty++;
		MazeCoords left = new MazeCoords(coords.x - 1, coords.y);
		if (validCoordinates(left) && ! grid[left.y][left.x])
			totalEmpty++;
	
		return totalEmpty;
	}
	
	public boolean okToTunnelTo(MazeCoords coords) {
		return validCoordinates(coords) && (numberEmptyNeighbors(coords) == 1);
	}
	
	public boolean tunnelFrom(MazeCoords coords, int currentDepth, int maxDepth) {
		grid[coords.y][coords.x] = false; // this square is now empty
		if (isEndSquare(coords))
			return true;
		if (currentDepth >= maxDepth)
			return false;
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:  // up
				MazeCoords up = new MazeCoords(coords.x, coords.y - 1);
				if (okToTunnelTo(up)) {
					tunnelFrom(up, currentDepth + 1, maxDepth);
				}
				break;
			case 1: // right
				MazeCoords right = new MazeCoords(coords.x + 1, coords.y);
				if (okToTunnelTo(right)) {
					tunnelFrom(right, currentDepth + 1, maxDepth);
				}
				break;		
			case 2:
				MazeCoords down = new MazeCoords(coords.x, coords.y + 1);
				if (okToTunnelTo(down)) {
					tunnelFrom(down, currentDepth + 1, maxDepth);
				}
				break;
			case 3:
				MazeCoords left = new MazeCoords(coords.x - 1, coords.y);
				if (okToTunnelTo(left)) {
					tunnelFrom(left, currentDepth + 1, maxDepth);
				}
				break;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
