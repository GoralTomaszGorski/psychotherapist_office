package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SeleniumChromeTest {
    @Test
    public void openGooglePage() {
        String path = "/home/tomaszgorski/Dokumenty/chromedriver-linux64/chromedriver";
        System.setProperty("webdriver.chrome.driver", path);
        String baseUrl = "https://www.psycholog-krasnik.pl";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.close();
    }
}