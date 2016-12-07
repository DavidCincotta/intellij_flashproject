/**
 * Created by block7 on 9/29/16.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.lang.*;
import java.util.ArrayList;

public class URLConnectionReader {

    public static void main(String[] args) {
        try {
            ArrayList<String> plays = new ArrayList<String>();
            String mitHomePageURL = "http://shakespeare.mit.edu/index.html";
            Document homepage = Jsoup.connect(mitHomePageURL).get();
            Elements links = homepage.select("a[href]");
            for (Element element :
                    links) {
                if (element.toString().contains("/index.html\"")){
                    //System.out.println(element.toString());
                    plays.add("http://shakespeare.mit.edu/"+element.toString().substring(element.toString().indexOf("=\"")+2,element.toString().indexOf("index.html\">"))+"full.html");
                }
            }
            Parser parser = new Parser();
            String allShakespearePlays = "";
            for (String element :
                    plays) {
                Document play = Jsoup.connect(element).get();
                allShakespearePlays = allShakespearePlays + play.text();
            }
            Analyzer hamlet = new Analyzer(parser.parse(allShakespearePlays));
            hamlet.printTotals();
        } catch (Exception mue) {
            System.out.println(mue+"\n"+mue.getCause()+"\n"+mue.getMessage());

        }
    }
}