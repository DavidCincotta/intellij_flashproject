import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class TestingMain{
    public static void main(String[] args)  {
        /*System.out.println(fibonacci(4));
        System.out.println(stripString("You can't always get what you want, buddy!!"));
        printStars(3);
        System.out.println(createRandWord());*/
        //System.out.println(10000 % 10.1);
        generateEmails();
    }
    public static void generateEmails(){
        System.out.print("{");
        for (int i = 0; i < 10; i++) {
            System.out.print("\"");
            for (int j = 0; j <1+ (int) (Math.random()*15); j++) {
                System.out.print("a");
            }
            System.out.print("@");
            for (int j = 0; j < 1+(int) (Math.random()*15); j++) {
                System.out.print("a");
            }
            System.out.print(".com");
            System.out.print("\",");
        }
        System.out.print("}");
    }
    //random coding tasks
    public static int fibonacci(int n){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(1);
        for (int i = 0; i < 1000; i++) {
            a.add(a.get(i)+a.get(i+1));
        }
        return a.get(n-1);
    }
    public static String stripString(String a){
        return a.replaceAll("!","").replaceAll(",","").replaceAll("'","").toLowerCase();
    }
    public static void printStars(int maxWidth){
        int numberInRow = 1;
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < numberInRow; j++) {
                System.out.print("*");
            }
            numberInRow++;
            System.out.println("");
        }
        numberInRow--;
        numberInRow--;
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < numberInRow; j++) {
                System.out.print("*");
            }
            numberInRow--;
            System.out.println("");
        }
    }
    public static String createRandWord(){
        String[] wordArray = {"","","","",""};
        String[] vowels = {"a","e","o","i","u","y"};
        String[] constants = {"q","w","r","t","p","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
        wordArray[0] = constants[(int)(Math.random()*constants.length)];
        wordArray[1] = vowels[(int)(Math.random()*vowels.length)];
        wordArray[2] = constants[(int)(Math.random()*constants.length)];
        wordArray[3] = vowels[(int)(Math.random()*vowels.length)];
        wordArray[4] = constants[(int)(Math.random()*constants.length)];
        String a = "";
        for (String b :
                wordArray) {
            a+=b;
        }
        return a;
    }
}