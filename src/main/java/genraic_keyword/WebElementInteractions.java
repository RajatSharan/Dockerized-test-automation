package genraic_keyword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebElementInteractions {
   protected WebDriver driver;
    public WebElementInteractions(WebDriver driver) {
        this.driver = driver;
    }
    //Custom methods to interact with web elements
    public void clickElement(By locator) {                      //Method to click on an element
        driver.findElement(locator).click();                    //Clicking on the element
    }
    public void sendText(By locator, String text) {             //Method to enter text in an element
        driver.findElement(locator).sendKeys(text);             //Entering text in the element
    }
    public void goToApplication(String url) {
        if(driver !=null){
            driver.get(url);//Method to navigate to the application
        }
        else{
            throw new NullPointerException("Webdriver instance is null");//Navigating to the application
        }
    }
    public String retriveTextData(By locator) {                     //Method to retrieve text from an element
       return driver.findElement(locator).getText();               //Retrieving text from the element
    }


}
