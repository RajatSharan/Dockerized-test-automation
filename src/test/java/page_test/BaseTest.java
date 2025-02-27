package page_test;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static utills.ExtentReportHelper.getReportObject;

public class BaseTest {
    protected WebDriver driver;
    protected String browser;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUpTest(@Optional String browserName, ITestResult iTestResult) {
        if (browserName != null) {
            browser = browserName;
        } else {
            browser = AppConstants.browserName;
        }

        if (browser.equalsIgnoreCase("chrome")) {
            chromeOptions = new ChromeOptions();
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                WebDriverManager.chromedriver().driverVersion("132.0.6834.197").setup();
                driver = new ChromeDriver(chromeOptions); // Using class-level variable
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                chromeOptions.setPlatformName("Linux");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid URL for Remote WebDriver", e);
                }
            } else {
                logger.error("Platform not supported");
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            firefoxOptions = new FirefoxOptions();
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions); // Using class-level variable
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid URL for Remote WebDriver", e);
                }
            } else {
                logger.error("Platform not supported");
            }
        } else {
            logger.info("Browser name is: " + browser);
        }

        ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Test started: " + LocalDateTime.now());
    }

    @AfterMethod
    public void tearDownTest(ITestResult iTestResult) throws IOException {
        if (iTestResult.isSuccess()) {
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + " is Successful", ExtentColor.GREEN));
        } else {
            testLogger.get().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + " is Failed", ExtentColor.RED));
            String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName() + ".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.converting_Base64(screenshot)).build());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void flushTestReport() {
        reports.flush();
    }
}
