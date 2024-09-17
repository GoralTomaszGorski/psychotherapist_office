package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class SeleniumChromeTest {
    @Test
    public void openGooglePage() {

        String path = "/home/tomaszgorski/Dokumenty/chromedriver-linux64/chromedriver";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }
}
