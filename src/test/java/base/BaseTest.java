package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless","false"));
        if(isHeadless){
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}