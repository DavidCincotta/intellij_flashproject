public class Square {

    int x;
    int y;
    int nearBombs;
    boolean beenClicked = false;
    boolean isVisible = false;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }


    public void click( Square[][] board) {
        /*
        if (nearBombs == 0) {

            try {
                //if (board[x + 1][y].nearBombs == 0) board[x + 1][y].click(board);
                board[x + 1][y].setVisible(true); //1
            } catch (Exception e) {
            }
            try {
                //if (board[x + 1][y + 1].nearBombs == 0) board[x + 1][y + 1].click(board);
                board[x + 1][y + 1].setVisible(true); //2
            } catch (Exception e) {
            }
            try {
                //if (board[x + 1][y - 1].nearBombs == 0) board[x + 1][y - 1].click(board);
                board[x + 1][y - 1].setVisible(true); //3
            } catch (Exception e) {
            }
            try {
                //if (board[x - 1][y].nearBombs == 0) board[x - 1][y].click(board);
                board[x - 1][y].setVisible(true); //4
            } catch (Exception e) {
            }
            try {
                //if (board[x - 1][y + 1].nearBombs == 0) board[x - 1][y + 1].click(board);
                board[x - 1][y + 1].setVisible(true); //5
            } catch (Exception e) {
            }
            try {
                //if (board[x - 1][y - 1].nearBombs == 0) board[x - 1][y - 1].click(board);
                board[x - 1][y - 1].setVisible(true); //6
            } catch (Exception e) {
            }
            try {
                //if (board[x][y + 1].nearBombs == 0) board[x][y + 1].click(board);
                board[x][y + 1].setVisible(true); //7
            } catch (Exception e) {
            }
            try {
                //if (board[x][y - 1].nearBombs == 0) board[x][y - 1].click(board);
                board[x][y - 1].setVisible(true); //8
            } catch (Exception e) {
            }
        }
        */
        setVisible(true);
        this.beenClicked = true;

    }

    private void setVisible(boolean visible) {
        isVisible = visible;
    }
}
