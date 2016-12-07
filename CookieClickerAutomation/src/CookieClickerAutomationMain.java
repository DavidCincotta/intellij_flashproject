/**
 * Created by David on 10/31/16.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
public class CookieClickerAutomationMain {
    public static WebDriver driver;

    public static void main(String[] args) throws WebDriverException, InterruptedException {

        System.setProperty("webdriver.gecko.driver", "../../downloads/geckodriver");

        try {
            for (int i = 0  ; i < 3; i++) {
                WebDriver driver = new FirefoxDriver();
                driver.get("https://communalcookieclicker-59b27.firebaseapp.com/");
                Thread.sleep(3000);
                Clickers clickers = new Clickers(driver, i);
                clickers.start();
            }
        }catch(Exception e) {
            e.printStackTrace();
            driver.close();
            driver.quit();
            System.out.println("close");
        }
    }
    public static double getConstant(){
        double divisor = 1;
        divisor += Math.sin(Math.random());
        return divisor;
    }
    public static int getNumberOfClicks() {
        //if (driver.findElements(By.xpath("//*[@id=\"app\"]/div/div[3]/h2[2]")).size() != 0) {
            return Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/h2[2]")).getText());
        //}
        //return -1;
    }
}