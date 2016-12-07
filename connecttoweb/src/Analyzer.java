import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by David on 10/26/16.
 */
public class Analyzer {
    ArrayList<String> sortedDictionaryString = new ArrayList<String>();
    ArrayList<Integer> sortedDictionaryInt = new ArrayList<Integer>();
    HashMap<String, Integer> countOfWords = new HashMap<String, Integer>();
    int totalWords=sortedDictionaryInt.size();

    Analyzer(String inputString) {
        String[] arrayString = inputString.split(" ");
        for (String element :
                arrayString) {
            if (countOfWords.containsKey(element.toLowerCase())){
                int numberOfWords = countOfWords.get(element.toLowerCase())+1;
                countOfWords.remove(element.toLowerCase());
                countOfWords.put(element.toLowerCase(), numberOfWords);
            } else {
                countOfWords.put(element.toLowerCase(), 1);
            }
        }
        countOfWords.remove("");
        sortForMostCommon();
        for (int element :
                sortedDictionaryInt) {
            totalWords += element;
        }
    }
    void printTotals(){
        for (int i= 0; i<sortedDictionaryInt.size(); i++) {
            BigDecimal percentOfText = new BigDecimal( (double) sortedDictionaryInt.get(i)*100/(double)totalWords);
            String percentofTextShortString = percentOfText.toPlainString().substring(0,9);
            System.out.println("\"" + sortedDictionaryString.get(i) + "\" was used " + sortedDictionaryInt.get(i) + " times and is "+percentofTextShortString+"% of the text");
        }
        System.out.println(sortedDictionaryString.size()+" total words that appear at least once");
        System.out.println(totalWords+" total words in the text");
    }


    void sortForMostCommon(){

        String dicstring = countOfWords.toString();
        dicstring = dicstring.substring(1,dicstring.length()-1);
        dicstring = dicstring.replaceAll(",","");
        String[] temp = dicstring.split(" ");
        //System.out.println(dicstring);
        for (String element :
                temp) {
            String[] a= element.split("=");
            sortedDictionaryString.add(a[0]);
            sortedDictionaryInt.add(Integer.parseInt(a[1]));
        }
        int tempInt;
        String tempString;
        int n = sortedDictionaryInt.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (sortedDictionaryInt.get(j-1) > sortedDictionaryInt.get(j)) {
                    tempInt = sortedDictionaryInt.get(j-1);
                    sortedDictionaryInt.set(j-1, sortedDictionaryInt.get(j));
                    sortedDictionaryInt.set(j, tempInt);
                    tempString = sortedDictionaryString.get(j-1);
                    sortedDictionaryString.set(j-1, sortedDictionaryString.get(j));
                    sortedDictionaryString.set(j, tempString);
                }

            }
        }
        //System.out.println(sortedDictionaryInt);
        //System.out.println(sortedDictionaryString);
    }
    void printDictionary(){
        for (String element :
                countOfWords.keySet()) {
            System.out.print("\n"+element);
        }
    }
}
