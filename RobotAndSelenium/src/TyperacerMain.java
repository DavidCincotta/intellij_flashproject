/**
 * Created by David on 10/30/16.
 */
/**
 * Created by David on 10/29/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TyperacerMain{
    static Robot bot;
    static WebDriver driver;
    public static void main(String[] args) throws Exception {
        bot = new Robot();
        TYPERACER(0, 0);//115 approaches 100 wpm
        //whenPlayingSomeOne("http://play.typeracer.com/?rt=1eorgmpm2onmp",115);
        //mineSweeper();
        //System.out.println(MouseInfo.getPointerInfo().getLocation());
    }

    public static void mineSweeper(){
        System.setProperty("webdriver.gecko.driver", "../../downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://minesweeperonline.com/");

        MineSweeperBoard board = new MineSweeperBoard(driver);
        for (int[] a :
                board.board) {
            for (int b :
                    a) {
                System.out.print(b+", ");
            }
            System.out.println("");
        }
        while (true){
            for (int i = 0; i < board.board.length; i++) {
                for (int j = 0; j < board.board[i].length; j++) {
                    board.autoCompleteSquare(i,j);
                }
            }
        }

        //driver.close();driver.quit();

    }
    private static void TYPERACER(int practiceint, int timeDelay) {
        System.setProperty("webdriver.gecko.driver", "../../downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://play.typeracer.com/");
        try {
            bot.keyPress(KeyEvent.VK_META);
            bot.keyPress(KeyEvent.VK_SPACE);
            bot.keyRelease(KeyEvent.VK_META);
            bot.keyRelease(KeyEvent.VK_SPACE);
            Thread.sleep(50);
            type("Firefox", 0);
            bot.keyPress(KeyEvent.VK_ENTER);
            bot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(500);
            int vertical = 0;
            if (practiceint == 0) {
                vertical = 320;
                driver.findElement(By.linkText("Enter a typing race")).click();
            } else {
                vertical = 390;
                driver.findElement(By.linkText("Practice")).click();
            }
            System.out.println("In Race");
            ArrayList<String> typeRacerBoxString = new ArrayList();
            double time =8000;// = waitForTimeNoPractice(driver)*100000;
            Thread.sleep(6000);
            java.util.List<WebElement> elements = driver.findElements(By.tagName("span"));
            recursiveCheck(driver);
            Thread.sleep(2000);
            for (WebElement element :
                    elements) {
                String elementvalue = element.getSize().toString();
                String elementText = element.getText();
                String elementTagName = element.getTagName();
                String elementClass = element.getClass().toString();
                try {
                    if (elementvalue.equals("(42, 32)")) {
                        String times = elementText.replaceAll(":", ".");
                        time = Double.parseDouble(times) * 100000;
                    }
                } catch (Exception e) {
                }
                if (!elementText.equals("Auto-updating") && !elementText.equals("in") && !elementText.equals("") && !elementText.equals("Advertisement:") && !elementText.equals("(hide)") && !elementText.equals(" ") && (!elementText.contains(":") || elementText.length() > 4) && !elementClass.contains("gwt-InlineLable")) {
                    System.out.println("THIS IS THE STARTING TEXT: " + elementText);
                    if (elementText.length() >= 10) {
                        typeRacerBoxString.add(" "+elementText);
                    }else {
                        typeRacerBoxString.add(elementText);
                    }
                }
                System.out.println(elementvalue + "   " + elementTagName + "    " + elementText);
            }
            String typeRacerBox="";
            for (String a :
                    typeRacerBoxString) {
                typeRacerBox = typeRacerBox +a;
            }
            if (practiceint != 0) {
                typeRacerBox = typeRacerBox.substring(typeRacerBox.indexOf("competition.") + 12);
            }
            bot.mouseMove(500,500);
            leftClick(0);//click out of url
            log(typeRacerBox);
            Thread.sleep((long) (time+2000));
            System.out.println("Time: " + (time));
            type(typeRacerBox, timeDelay);
        } catch (Exception e) {
            System.out.println(e);
        }
        bot.delay(4000);
        driver.quit();
        System.out.println("Done");
    }

    private static void secondaryType(String typeRacerBox, int timeDelay) throws InterruptedException {
        Thread.sleep(1000);
        String[] a = typeRacerBox.split("");
        for (String b : //not working
                a) {
            Thread.sleep(timeDelay);
            //driver.findElement(By.xpath("//*[@id=\"gwt-uid-20\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/input")).sendKeys(b);
        }

    }

    public static void recursiveCheck(WebDriver driver) throws org.openqa.selenium.NoSuchElementException, InterruptedException {
        if (driver.findElements(By.xpath("/html/body/div[4]/div/table/tbody/tr/td/table/tbody/tr/td[3]/div/span")).size() == 0){
            Thread.sleep(50);
        }else{
            if (driver.findElement(By.xpath("/html/body/div[4]/div/table/tbody/tr/td/table/tbody/tr/td[3]/div/span")).getText().contains("0")){

            }else {
                recursiveCheck(driver);
            }
        }
    }
    private static void whenPlayingSomeOne(String url, int timeDelay) {
        System.setProperty("webdriver.gecko.driver", "../../downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        System.out.println("before Robot");
        try {
            bot.keyPress(KeyEvent.VK_META);
            bot.keyPress(KeyEvent.VK_SPACE);
            bot.keyRelease(KeyEvent.VK_META);
            bot.keyRelease(KeyEvent.VK_SPACE);
            Thread.sleep(50);
            type("Firefox", 2);
            bot.keyPress(KeyEvent.VK_ENTER);
            bot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(50);
            driver.findElement(By.partialLinkText("join race")).click();
            Thread.sleep(600);
            bot.waitForIdle();
            System.out.println("In Race");
            ArrayList<String> typeRacerBoxString = new ArrayList();
            double time =5000;// = waitForTimeNoPractice(driver)*100000;
            Thread.sleep(3000);
            java.util.List<WebElement> elementsss = driver.findElements(By.tagName("span"));
            for (WebElement element :
                    elementsss) {
                String elementvalue = element.getSize().toString();
                String elementText = element.getText();
                String elementTagName = element.getTagName();
                String elementClass = element.getClass().toString();
                try {
                    if (elementvalue.equals("(42, 32)")) {
                        String times = elementText.replaceAll(":", ".");
                        time = Double.parseDouble(times) * 100000;
                    }
                } catch (Exception e) {
                }
                if (!elementText.equals("Auto-updating") && !elementText.equals("in") && !elementText.equals("") && !elementText.equals("Advertisement:") && !elementText.equals("(hide)") && !elementText.equals(" ") && (!elementText.contains(":") || elementText.length() > 4) && !elementClass.contains("gwt-InlineLable")) {
                    System.out.println("THIS IS THE STARTING TEXT: " + elementText);
                    if (elementText.length() >= 10) {
                        typeRacerBoxString.add(" "+elementText);
                    }else {
                        typeRacerBoxString.add(elementText);
                    }
                }
                System.out.println(elementvalue + "   " + elementTagName + "    " + elementText);
            }
            String typeRacerBox="";
            for (String a :
                    typeRacerBoxString) {
                typeRacerBox = typeRacerBox +a;
            }
            Thread.sleep((int) (time + 1000));
            type(typeRacerBox, timeDelay);

        } catch (Exception e) {
            System.out.println(e);
        }
        bot.delay(4000);
        driver.quit();
        System.out.println("Done");

    }
    private static double waitForTimeNoPractice(WebDriver driver) throws Exception {
        Thread.sleep(3500);
        try {
            String aaa = driver.findElement(By.xpath("html/body/div[5]/div/table/tbody/tr/td/table/tbody/tr/td[3]/div/span")).getText().replaceAll(":", ".");
            return Double.parseDouble(aaa);
        }catch (Exception e){
            return 5;
        }
    }

    private static void leftClick(int delayInt) {

        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.delay(delayInt);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.delay(delayInt);
    }

    public static boolean isUpperCase(String a) {
        return (a.equals("A") || a.equals("B") || a.equals("C") || a.equals("D") || a.equals("E") || a.equals("F") || a.equals("H") || a.equals("I") || a.equals("G") || a.equals("J") || a.equals("K") || a.equals("L") || a.equals("M") || a.equals("N") || a.equals("O") || a.equals("P") || a.equals("Q") || a.equals("R") || a.equals("S") || a.equals("T") || a.equals("U") || a.equals("V") || a.equals("W") || a.equals("X") || a.equals("Y") || a.equals("Z"));
    }

    public static void log(Object aMsg) {
        System.out.println(String.valueOf(aMsg));
    }

    public static void type(String a, int timeDelay) throws InterruptedException {
        String[] b = a.split("");
        for (String c:
             b) {
            if (isUpperCase(c)) {
                c = c.toLowerCase();
                bot.keyPress(KeyEvent.VK_SHIFT);
                switch (c) {
                    case "a":
                        bot.keyPress(KeyEvent.VK_A);
                        bot.keyRelease(KeyEvent.VK_A);
                        break;
                    case "b":
                        bot.keyPress(KeyEvent.VK_B);
                        bot.keyRelease(KeyEvent.VK_B);
                        break;
                    case "c":
                        bot.keyPress(KeyEvent.VK_C);
                        bot.keyRelease(KeyEvent.VK_C);
                        break;
                    case "d":
                        bot.keyPress(KeyEvent.VK_D);
                        bot.keyRelease(KeyEvent.VK_D);
                        break;
                    case "e":
                        bot.keyPress(KeyEvent.VK_E);
                        bot.keyRelease(KeyEvent.VK_E);
                        break;
                    case "f":
                        bot.keyPress(KeyEvent.VK_F);
                        bot.keyRelease(KeyEvent.VK_F);
                        break;
                    case "g":
                        bot.keyPress(KeyEvent.VK_G);
                        bot.keyRelease(KeyEvent.VK_G);
                        break;
                    case "h":
                        bot.keyPress(KeyEvent.VK_H);
                        bot.keyRelease(KeyEvent.VK_H);
                        break;
                    case "i":
                        bot.keyPress(KeyEvent.VK_I);
                        bot.keyRelease(KeyEvent.VK_I);
                        break;
                    case "j":
                        bot.keyPress(KeyEvent.VK_J);
                        bot.keyRelease(KeyEvent.VK_J);
                        break;
                    case "k":
                        bot.keyPress(KeyEvent.VK_K);
                        bot.keyRelease(KeyEvent.VK_K);
                        break;
                    case "l":
                        bot.keyPress(KeyEvent.VK_L);
                        bot.keyRelease(KeyEvent.VK_L);
                        break;
                    case "m":
                        bot.keyPress(KeyEvent.VK_M);
                        bot.keyRelease(KeyEvent.VK_M);
                        break;
                    case "n":
                        bot.keyPress(KeyEvent.VK_N);
                        bot.keyRelease(KeyEvent.VK_N);
                        break;
                    case "o":
                        bot.keyPress(KeyEvent.VK_O);
                        bot.keyRelease(KeyEvent.VK_O);
                        break;
                    case "p":
                        bot.keyPress(KeyEvent.VK_P);
                        bot.keyRelease(KeyEvent.VK_P);
                        break;
                    case "q":
                        bot.keyPress(KeyEvent.VK_Q);
                        bot.keyRelease(KeyEvent.VK_Q);
                        break;
                    case "r":
                        bot.keyPress(KeyEvent.VK_R);
                        bot.keyRelease(KeyEvent.VK_R);
                        break;
                    case "s":
                        bot.keyPress(KeyEvent.VK_S);
                        bot.keyRelease(KeyEvent.VK_S);
                        break;
                    case "t":
                        bot.keyPress(KeyEvent.VK_T);
                        bot.keyRelease(KeyEvent.VK_T);
                        break;
                    case "u":
                        bot.keyPress(KeyEvent.VK_U);
                        bot.keyRelease(KeyEvent.VK_U);
                        break;
                    case "v":
                        bot.keyPress(KeyEvent.VK_V);
                        bot.keyRelease(KeyEvent.VK_V);
                        break;
                    case "w":
                        bot.keyPress(KeyEvent.VK_W);
                        bot.keyRelease(KeyEvent.VK_W);
                        break;
                    case "x":
                        bot.keyPress(KeyEvent.VK_X);
                        bot.keyRelease(KeyEvent.VK_X);
                        break;
                    case "y":
                        bot.keyPress(KeyEvent.VK_Y);
                        bot.keyRelease(KeyEvent.VK_Y);
                        break;
                    case "z":
                        bot.keyPress(KeyEvent.VK_Z);
                        bot.keyRelease(KeyEvent.VK_Z);
                        break;
                    default:
                        break;
                }
                bot.keyRelease(KeyEvent.VK_SHIFT);
            } else {
                switch (c) {
                    case "a":
                        bot.keyPress(KeyEvent.VK_A);
                        bot.keyRelease(KeyEvent.VK_A);
                        break;
                    case "b":
                        bot.keyPress(KeyEvent.VK_B);
                        bot.keyRelease(KeyEvent.VK_B);
                        break;
                    case "c":
                        bot.keyPress(KeyEvent.VK_C);
                        bot.keyRelease(KeyEvent.VK_C);
                        break;
                    case "d":
                        bot.keyPress(KeyEvent.VK_D);
                        bot.keyRelease(KeyEvent.VK_D);
                        break;
                    case "e":
                        bot.keyPress(KeyEvent.VK_E);
                        bot.keyRelease(KeyEvent.VK_E);
                        break;
                    case "f":
                        bot.keyPress(KeyEvent.VK_F);
                        bot.keyRelease(KeyEvent.VK_F);
                        break;
                    case "g":
                        bot.keyPress(KeyEvent.VK_G);
                        bot.keyRelease(KeyEvent.VK_G);
                        break;
                    case "h":
                        bot.keyPress(KeyEvent.VK_H);
                        bot.keyRelease(KeyEvent.VK_H);
                        break;
                    case "i":
                        bot.keyPress(KeyEvent.VK_I);
                        bot.keyRelease(KeyEvent.VK_I);
                        break;
                    case "j":
                        bot.keyPress(KeyEvent.VK_J);
                        bot.keyRelease(KeyEvent.VK_J);
                        break;
                    case "k":
                        bot.keyPress(KeyEvent.VK_K);
                        bot.keyRelease(KeyEvent.VK_K);
                        break;
                    case "l":
                        bot.keyPress(KeyEvent.VK_L);
                        bot.keyRelease(KeyEvent.VK_L);
                        break;
                    case "m":
                        bot.keyPress(KeyEvent.VK_M);
                        bot.keyRelease(KeyEvent.VK_M);
                        break;
                    case "n":
                        bot.keyPress(KeyEvent.VK_N);
                        bot.keyRelease(KeyEvent.VK_N);
                        break;
                    case "o":
                        bot.keyPress(KeyEvent.VK_O);
                        bot.keyRelease(KeyEvent.VK_O);
                        break;
                    case "p":
                        bot.keyPress(KeyEvent.VK_P);
                        bot.keyRelease(KeyEvent.VK_P);
                        break;
                    case "q":
                        bot.keyPress(KeyEvent.VK_Q);
                        bot.keyRelease(KeyEvent.VK_Q);
                        break;
                    case "r":
                        bot.keyPress(KeyEvent.VK_R);
                        bot.keyRelease(KeyEvent.VK_R);
                        break;
                    case "s":
                        bot.keyPress(KeyEvent.VK_S);
                        bot.keyRelease(KeyEvent.VK_S);
                        break;
                    case "t":
                        bot.keyPress(KeyEvent.VK_T);
                        bot.keyRelease(KeyEvent.VK_T);
                        break;
                    case "u":
                        bot.keyPress(KeyEvent.VK_U);
                        bot.keyRelease(KeyEvent.VK_U);
                        break;
                    case "v":
                        bot.keyPress(KeyEvent.VK_V);
                        bot.keyRelease(KeyEvent.VK_V);
                        break;
                    case "w":
                        bot.keyPress(KeyEvent.VK_W);
                        bot.keyRelease(KeyEvent.VK_W);
                        break;
                    case "x":
                        bot.keyPress(KeyEvent.VK_X);
                        bot.keyRelease(KeyEvent.VK_X);
                        break;
                    case "y":
                        bot.keyPress(KeyEvent.VK_Y);
                        bot.keyRelease(KeyEvent.VK_Y);
                        break;
                    case "z":
                        bot.keyPress(KeyEvent.VK_Z);
                        bot.keyRelease(KeyEvent.VK_Z);
                        break;
                    case "/":
                        bot.keyPress(KeyEvent.VK_SLASH);
                        bot.keyRelease(KeyEvent.VK_SLASH);
                        break;
                    case "\'":
                        bot.keyPress(KeyEvent.VK_QUOTE);
                        bot.keyRelease(KeyEvent.VK_QUOTE);
                        break;
                    case ";":
                        bot.keyPress(KeyEvent.VK_SEMICOLON);
                        bot.keyRelease(KeyEvent.VK_SEMICOLON);
                        break;
                    case "(":
                        bot.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS);
                        bot.keyRelease(KeyEvent.VK_RIGHT_PARENTHESIS);
                        break;
                    case ")":
                        bot.keyPress(KeyEvent.VK_LEFT_PARENTHESIS);
                        bot.keyRelease(KeyEvent.VK_LEFT_PARENTHESIS);
                        break;
                    case " ":
                        bot.keyPress(KeyEvent.VK_SPACE);
                        bot.keyRelease(KeyEvent.VK_SPACE);
                        break;
                    case ",":
                        bot.keyPress(KeyEvent.VK_COMMA);
                        bot.keyRelease(KeyEvent.VK_COMMA);
                        break;
                    case ".":
                        bot.keyPress(KeyEvent.VK_PERIOD);
                        bot.keyRelease(KeyEvent.VK_PERIOD);
                        break;
                    case "1":
                        bot.keyPress(KeyEvent.VK_1);
                        bot.keyRelease(KeyEvent.VK_1);
                        break;
                    case "2":
                        bot.keyPress(KeyEvent.VK_2);
                        bot.keyRelease(KeyEvent.VK_2);
                        break;
                    case "3":
                        bot.keyPress(KeyEvent.VK_3);
                        bot.keyRelease(KeyEvent.VK_3);
                        break;
                    case "4":
                        bot.keyPress(KeyEvent.VK_4);
                        bot.keyRelease(KeyEvent.VK_4);
                        break;
                    case "5":
                        bot.keyPress(KeyEvent.VK_5);
                        bot.keyRelease(KeyEvent.VK_5);
                        break;
                    case "6":
                        bot.keyPress(KeyEvent.VK_6);
                        bot.keyRelease(KeyEvent.VK_6);
                        break;
                    case "7":
                        bot.keyPress(KeyEvent.VK_7);
                        bot.keyRelease(KeyEvent.VK_7);
                        break;
                    case "8":
                        bot.keyPress(KeyEvent.VK_8);
                        bot.keyRelease(KeyEvent.VK_8);
                        break;
                    case "9":
                        bot.keyPress(KeyEvent.VK_9);
                        bot.keyRelease(KeyEvent.VK_9);
                        break;
                    case "0":
                        bot.keyPress(KeyEvent.VK_0);
                        bot.keyRelease(KeyEvent.VK_0);
                        break;
                    case "":
                        break;
                    case "-":
                        bot.keyPress(KeyEvent.VK_MINUS);
                        bot.keyRelease(KeyEvent.VK_MINUS);
                        break;
                    default:
                        bot.keyPress(KeyEvent.VK_SHIFT);
                        switch (c) {
                            case ":":
                                bot.keyPress(KeyEvent.VK_SEMICOLON);
                                bot.keyRelease(KeyEvent.VK_SEMICOLON);
                                break;
                            case "\"":
                                bot.keyPress(KeyEvent.VK_QUOTE);
                                bot.keyRelease(KeyEvent.VK_QUOTE);
                                break;
                            case "!":
                                bot.keyPress(KeyEvent.VK_1);
                                bot.keyRelease(KeyEvent.VK_1);
                                break;
                            case "@":
                                bot.keyPress(KeyEvent.VK_2);
                                bot.keyRelease(KeyEvent.VK_2);
                                break;
                            case "#":
                                bot.keyPress(KeyEvent.VK_3);
                                bot.keyRelease(KeyEvent.VK_3);
                                break;
                            case "$":
                                bot.keyPress(KeyEvent.VK_4);
                                bot.keyRelease(KeyEvent.VK_4);
                                break;
                            case "%":
                                bot.keyPress(KeyEvent.VK_5);
                                bot.keyRelease(KeyEvent.VK_5);
                                break;
                            case "^":
                                bot.keyPress(KeyEvent.VK_6);
                                bot.keyRelease(KeyEvent.VK_6);
                                break;
                            case "&":
                                bot.keyPress(KeyEvent.VK_7);
                                bot.keyRelease(KeyEvent.VK_7);
                                break;
                            case "*":
                                bot.keyPress(KeyEvent.VK_8);
                                bot.keyRelease(KeyEvent.VK_8);
                                break;
                            case "(":
                                bot.keyPress(KeyEvent.VK_9);
                                bot.keyRelease(KeyEvent.VK_9);
                                break;
                            case ")":
                                bot.keyPress(KeyEvent.VK_0);
                                bot.keyRelease(KeyEvent.VK_0);
                                break;
                            case "+":
                                bot.keyPress(KeyEvent.VK_EQUALS);
                                bot.keyRelease(KeyEvent.VK_EQUALS);
                                break;
                            case "{":
                                bot.keyPress(KeyEvent.VK_BRACELEFT);
                                bot.keyRelease(KeyEvent.VK_BRACELEFT);
                                break;
                            case "}":
                                bot.keyPress(KeyEvent.VK_BRACERIGHT);
                                bot.keyRelease(KeyEvent.VK_BRACERIGHT);
                                break;
                            case ">":
                                bot.keyPress(KeyEvent.VK_PERIOD);
                                bot.keyRelease(KeyEvent.VK_PERIOD);
                                break;
                            case "<":
                                bot.keyPress(KeyEvent.VK_COMMA);
                                bot.keyRelease(KeyEvent.VK_COMMA);
                                break;
                            case "?":
                                bot.keyPress(KeyEvent.VK_SLASH);
                                bot.keyRelease(KeyEvent.VK_SLASH);
                                break;
                            default:
                                log("**" + c + "** is not defined in switch statement");
                                break;
                        }
                        bot.keyRelease(KeyEvent.VK_SHIFT);
                        break;
                }
            }
        Thread.sleep(timeDelay);
        }

    }
}