package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    public String appURL, browser;
    public int implicitWait;


    @AfterMethod
    public void tearDown() {driver.quit();
    }

    @BeforeMethod
    public void setupDriverAndOpenTestSIte() {
        readConfig("src/test/resources/config.properties");
        setupWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.get(appURL);
        driver.manage().window().maximize();
    }
    private void readConfig(String confFile){
        try {
            FileInputStream fileInputStream = new FileInputStream(confFile);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            appURL = properties.getProperty("testURL");
            browser = properties.getProperty("browser");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        } catch (IOException a){
            System.out.println(a);
        }
    }
    private void setupWebDriver(){
        switch (browser){
            case "chrome":
                driver = setupChromeDriver();
                break;
            case "edge":
                driver = setupEdgeDriver();
                break;
            case "firefox":
                driver = setupFirefoxDriver();
                break;
        }

    }


    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private WebDriver setupEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
