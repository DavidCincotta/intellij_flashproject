public class EightMain {

    public static final int N_GAMES = 2;

    public static void main(String[] args){

        for (int i = 0; i < N_GAMES; i++) {
            int humWins = 0;
            int compWins = 0;
            int sharedSum = 0;
            int previousChoice = 0;
            Player comp = new ComputerPlayer();
            Player hum = new HumanPlayer();
            boolean turnChange = false;

            while (sharedSum <8){
                System.out.println("Sum: " + sharedSum);
                if (turnChange) {
                    previousChoice = comp.pick(previousChoice, sharedSum);
                    System.out.println("Computer Picked: " + previousChoice);
                    sharedSum += previousChoice;
                    if (sharedSum == 8){
                        compWins++;
                        System.out.println("Computer Wins! Comp got 8.");
                    } else if (sharedSum > 8){
                        humWins++;
                        System.out.println("Human Wins! Comp went over.");
                    }
                } else {
                    previousChoice = hum.pick(previousChoice, sharedSum);
                    System.out.println("Human Picked: " + previousChoice);
                    sharedSum += previousChoice;
                    if (sharedSum == 8){
                        humWins++;
                        System.out.println("Human Wins! Hum got 8.");
                    } else if (sharedSum > 8){
                        compWins++;
                        System.out.println("Computer Wins! Hum went over.");
                    }
                }
                turnChange = !turnChange;
            }
            System.out.println("Total Games: " + N_GAMES + " Total Human Wins: " + humWins + " Total Computer Wins: " + compWins);
        }
    }
}
