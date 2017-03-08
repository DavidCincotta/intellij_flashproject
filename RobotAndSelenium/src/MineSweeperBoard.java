import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class MineSweeperBoard {
    private int width, height, bombs;
    private WebDriver driver;
    public String[][] boardString;
    public int[][] board;

    public MineSweeperBoard(WebDriver driver) {
        this.driver = driver;
        this.width = 30;
        this.height = 16;
        this.bombs = 99;
        boardString = new String[height][width];
        board = new int[height][width];
        driver.findElement(By.id("8_15")).click();

        update();
    }

    void updateBoardString() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    boardString[i][j] = driver.findElement(By.id((i+1) + "_" + (j+1))).getAttribute("class");
                }catch (Exception e){
                    boardString[i][j] = "square blank";
                }
            }
        }
    }

    void update() {
        updateBoardString();
        Stack<Integer> stack = new Stack<>();
        sun.misc.Queue<Integer> queue = new sun.misc.Queue<>();
        for (String[] a :
                boardString) {
            for (String b :
                    a) {
                if (b.equals("square blank")) {
                    stack.push(8);
                } else if (b.equals("square open0")) {
                    stack.push(0);
                } else if (b.equals("square open1")) {
                    stack.push(1);
                } else if (b.equals("square open2")) {
                    stack.push(2);
                } else if (b.equals("square open3")) {
                    stack.push(3);
                } else if (b.equals("square open4")) {
                    stack.push(4);
                } else if (b.equals("square open5")) {
                    stack.push(5);
                } else if (b.equals("square open6")) {
                    stack.push(6);
                }
            }
        }
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                try {
                    board[i][j] = stack.pop();
                }catch (Exception e){}
            }
        }
    }


    ArrayList<Point> getNeighbors(int y, int x){
        ArrayList<Point> neighbors = new ArrayList<>();
        try{
            board[x+1][y] = board[x+1][y];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x+1][y-1] = board[x+1][y-1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x+1][y+1] = board[x+1][y+1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x][y+1] = board[x][y+1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x][y-1] = board[x][y-1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x-1][y-1] = board[x-1][y-1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x-1][y+1] = board[x-1][y+1];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}
        try{
            board[x-1][y] = board[x-1][y];
            neighbors.add(new Point(x, y));
        }catch (Exception e){}

        return neighbors;
    }
    void autoCompleteSquare(int column, int row){
        if (board[column][row]<8) {
            ArrayList<Point> neighbors = getNeighbors(row, column);
            int numberRemaining = board[column][row];
            for (Point a :
                    neighbors) {
                if (board[(int)a.getY()][(int)a.getX()] == 9) numberRemaining--;
            }
            if (numberRemaining ==0){
                for (Point a :
                        neighbors) {
                    if (board[(int) a.getY()][(int) a.getX()] == 8) {
                        driver.findElement(By.id(a.getY()+"_"+a.getX())).click();
                    }
                }
            }
        }
    }
}
