/**
 * Created by block7 on 10/7/16.
 */
import org.jsoup.nodes.*;
import org.jsoup.Jsoup;

import java.util.regex.Pattern;

public class Parser {
    public Parser() {
    }
    String parse(String[] strArgs){
        String str = "";
        for (String element :
                strArgs) {
            str = str +" "+ parse(element);
        }
        return str;
    }
    String parse(String str) {
        //System.out.println(str);
        String[] specialChar = {",", ".", ":", ";", "|", "!", "?", "/", "]", "["};
        for (String element :
                specialChar) {
            str = str.replaceAll(Pattern.quote(element), "");
        }
        str = str.replaceAll("-"," ");
        return str;
    }
}