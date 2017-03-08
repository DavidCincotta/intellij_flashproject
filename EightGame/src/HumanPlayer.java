import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
    public int pick(int previousInteger, int sharedSum) {
        String prompt = "Pick 1 or 2 or 3: ";
        if (previousInteger == 3){
            prompt = "Pick 1 or 2: ";
        }
        if (previousInteger ==2) {
            prompt = "Pick 1 or 3: ";
        }
        if (previousInteger == 1) {
            prompt = "Pick 2 or 3: ";
        }
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
        try {
            int value = Integer.parseInt(line);
            if ((value==1 || value==2 || value==3) && value!=previousInteger){

                return value;
            } else{
                pick(sharedSum, previousInteger);
            }
        }catch (Exception e){
            pick(sharedSum, previousInteger);
        }
        System.out.println("pick() Failure");
        return -1;
    }

}
