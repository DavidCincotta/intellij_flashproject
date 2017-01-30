public class Square {
    int x;
    int y;
    int nearBombs;
    boolean beenClicked;
    boolean isBomb;

    public Square(int x, int y, int nearBombs, boolean beenClicked, boolean isBomb) {
        this.x = x;
        this.y = y;
        this.nearBombs = nearBombs;
        this.beenClicked = beenClicked;
        this.isBomb = isBomb;
    }

    public void setBeenClicked(boolean beenClicked) {
        this.beenClicked = beenClicked;
    }
    public boolean clickBomb(){
        if (isBomb && beenClicked){
            return true;
        }
        return false;
    }

}
