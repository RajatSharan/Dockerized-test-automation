package page_test;

import base.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    //Base test class to be extended by all test classes
    //Contains common methods to be used across all test classes
    //Contains common setup and teardown methods

    protected WebDriver driver;
    protected String browser;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;


    @BeforeTest
    public void setUpTest() {
        //Method to setup the driver
        //This method will be executed before each test
        //This method will setup the driver based on the browser

        browser = AppConstants.browserName;         //Get browser name from AppConstants
        if (browser.equalsIgnoreCase("chrome")) {//Set up chrome driver
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                //Set up local driver
                //chromeOptions.addArguments("--remote-allow-origin");
                WebDriverManager.chromedriver().driverVersion("132.0.6834.197").setup();
                chromeOptions = new ChromeOptions();    //Instantiate chrome options
                driver = new ChromeDriver();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            //Set up firefox driver
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                //Set up local driver
                firefoxOptions.addArguments("--remote-allow-origin");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
            }
        } else {
            System.out.println("Invalid browser name");
        }
    }

        @AfterTest
        public void tearDownTest() {
            //Method to close the driver
            //This method will be executed after each test
            //This method will close the driver
           if(driver!=null) {
               driver.quit();
           }
        }
}
