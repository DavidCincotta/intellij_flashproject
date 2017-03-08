public class ComputerPlayer extends Player {
    @Override
    public int pick(int previousInteger, int sharedSum) {
        if (sharedSum == 5 && previousInteger!= 3){
            return 3;
        } else if (sharedSum == 7 && previousInteger !=1){
            return 1;
        } else if (sharedSum == 6 && previousInteger != 2){
            return 2;
        } else {
            int val = getRand();
            while(val == previousInteger){
                val = getRand();
            }
            return val;
        }
    }
    public int getRand(){
        return ((int) (Math.random() * 3)) + 1;
    }
    public int aiPick(int previousInteger, int sharedSum){
        return -1;
    }
}
