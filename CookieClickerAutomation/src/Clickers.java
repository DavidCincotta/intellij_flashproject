import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class Clickers implements Runnable {
    private Thread t;
    private String threadNumber;
    private String[] emails = {"aaaaaa@aaa.com", "aaaaa@aaa.com", "aaaa@aa.com", "a@aa.com", "aaaaaaaaaa@aaaa.com", "aaa@a.com", "aaaa@aaaa.com", "aaaaaaa@aa.com", "aaaa@aaaaaaaa.com", "aaa@aaaaaa.com"};
    private WebElement menue;
    private WebDriver driver;

    Clickers(WebDriver driver, int threadNumber) {
        this.threadNumber = Integer.toString(threadNumber);
        this.driver = driver;
        menue = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/button"));
    }

    public void run() {
        try {
            menue.click();
            Thread.sleep(100);
            WebElement profile = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[2]/span/div/div"));
            profile.click();
            Thread.sleep(1000);
            WebElement usernameMenue = driver.findElement(By.name("Username"));
            WebElement emailMenue = driver.findElement(By.name("Email"));
            WebElement passwordMenue = driver.findElement(By.name("Password"));
            String username = "h";
            usernameMenue.sendKeys(username);
            emailMenue.sendKeys(emails[Integer.parseInt(threadNumber)]);
            String password = "hhhhhh";
            passwordMenue.sendKeys(password);
            WebElement loginMenue = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div[4]/button/div/div/span"));
            loginMenue.click();
            Thread.sleep(100);
            menue.click();
            Thread.sleep(100);
            WebElement cookieMenue = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[1]/span/div/div"));
            cookieMenue.click();
            Thread.sleep(100);
            while (true) {
                WebElement cookie = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[4]/button"));
                cookie.click();
                Thread.sleep((long) (Math.random() * 4000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + threadNumber + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadNumber);
        if (t == null) {
            t = new Thread(this, threadNumber);
            t.start();
        }
    }
}
