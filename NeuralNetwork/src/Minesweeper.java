import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Minesweeper{
    public int width= 30;
    public int height = 16;
    private int bombs = 99;
    int[][] backgroundBoard = new int[width+2][height+2];
    Square[][] board = new Square[width][height];
    boolean gameActive = true;
    public Minesweeper() {
        this.width = 30;
        this.height = 16;
        this.bombs = 99;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] =  new Square(i,j);
            }
        }
        createBoard();
        printBoard();
        printBackgroundBoard();
        int[] randomPoint = randomBlankPoint();
        board[randomPoint[0]][randomPoint[1]].click(board);
        printBoard();
    }

    public double explorationFittness() {
        double exploration = -1;
        System.out.println("GAME OVER");
        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[k].length; l++) {
                if (board[k][l].isVisible) exploration ++;
            }
        }
        exploration = exploration / (height * width);
        System.out.println("Exploration: " + exploration);
        return exploration;
    }

    public int[] randomBlankPoint(){
        ArrayList<int[]> blankPoints = new ArrayList<>();
        for (int i = 1; i < backgroundBoard.length-1; i++) {
            for (int j = 1; j < backgroundBoard[i].length-1; j++) {
                if (backgroundBoard[i][j] == 0){
                    int[] point = {i-1,j-1};
                    blankPoints.add(point);
                }
            }
        }
        return blankPoints.get((int)Math.floor(blankPoints.size()*Math.random()));
    }
    private void createBoard() {
        for (int i = 0; i < bombs; i++) {
            int x, y;
            do {
                y =  (int) Math.floor(Math.random() * (height + 2));
                x =  (int) Math.floor(Math.random() * (width + 2));
            } while (y == 0 || y == height + 1 || x == 0 || x == width+1 || backgroundBoard[x][y] == 9);

            backgroundBoard[x][y] = 9;

        }
        for (int i = 1; i < backgroundBoard.length-1; i++) {
            for (int j = 1; j < backgroundBoard[i].length-1; j++) {
                if (backgroundBoard[i][j] != 9) {
                    int nearBombs = 0;
                    if (backgroundBoard[i + 1][j] == 9) nearBombs++;     //1
                    if (backgroundBoard[i + 1][j - 1] == 9) nearBombs++; //2
                    if (backgroundBoard[i + 1][j + 1] == 9) nearBombs++; //3
                    if (backgroundBoard[i][j + 1] == 9) nearBombs++;     //4
                    if (backgroundBoard[i][j - 1] == 9) nearBombs++;     //5
                    if (backgroundBoard[i - 1][j] == 9) nearBombs++;     //6
                    if (backgroundBoard[i - 1][j + 1] == 9) nearBombs++; //7
                    if (backgroundBoard[i - 1][j - 1] == 9) nearBombs++; //8
                    board[i-1][j-1].setNearBombs(nearBombs);
                    backgroundBoard[i][j] = nearBombs;
                } else{
                    board[i-1][j-1].setNearBombs(Integer.MAX_VALUE);
                }
            }
        }
    }
    public void printBackgroundBoard(){
        for (int i = 0; i < backgroundBoard[0].length; i++) {
            for (int j = 0; j < backgroundBoard.length; j++) {
                if (backgroundBoard[j][i] == 9){
                    System.out.print("* ");
                } else {
                    System.out.print(backgroundBoard[j][i] + " ");
                }
            }
            System.out.println("");
        }
    }
    public void printBoard(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (board[j][i].isVisible) {
                    System.out.print(backgroundBoard[j + 1][i + 1] + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println("");

        }
    }



    public static int getInt(String prompt) {
        String line = null;
        Console c = System.console();
        if (c != null) {
            line = c.readLine(prompt);
        } else {
            System.out.print(prompt);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                //Ignore
            }
        }
        return Integer.parseInt(line);
    }
}