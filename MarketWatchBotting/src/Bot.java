import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import java.util.HashMap;
import java.util.List;

public class Bot {
    HashMap<String, Integer> tickerHoldings = new HashMap<>();
    private WebDriver driver;
    public Bot(String userName, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "../../downloads/chromedriver");
        //download Geckodriver for firefox, and download firefox
        //keep it in downloads, it will reference it there in this context
        driver = new ChromeDriver();
        driver.get("http://www.marketwatch.com/game/stockmarketbottingcompetition");
        getWebElementID("profilelogin").click();
        Thread.sleep(500);

        getWebElementCLASS("login_id").sendKeys(userName);
        getWebElementCLASS("login_pw").sendKeys(password);
        getWebElementID("submitButton").click();
        //logs onto your account every time
        log("1");
        //closePopUp();
        Thread.sleep(5000);
        driver.get("http://www.howthemarketworks.com/accounting/openpositions");
        log("2");
        fillTicketHoldings();
    }

    public WebElement getWebElementID(String id) {
        return driver.findElement(By.id(id));
    }

    public void log(Object m) {
        System.out.println(m);
    }


    private void fillTicketHoldings() {

    }

    public WebElement getWebElementXPATH(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getWebElementLINK(String link) {
        return driver.findElement(By.linkText(link));
    }

    public WebElement getWebElementPARTIALLINK(String link) {
        return driver.findElement(By.partialLinkText(link));

    }
    public WebElement getWebElementCLASS(String classText){
        return driver.findElement(By.className(classText));
    }
    public List<WebElement> getWebElementsClass(String classText){
        return driver.findElements(By.className(classText));
    }
}
