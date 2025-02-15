package page_objects;

import genraic_keyword.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends WebElementInteractions {
     private final By usernameTextField= By.id("user-name");                //Locating username text field
     private final By passwordTextField= By.id("password");                 //Locating password text field
     private final By loginBtn= By.id("login-button"); //Locating login button

     public LoginPageObject(WebDriver driver) {
          super(driver);
     }

     public ProductsPageObject userLogin(String username, String password) {

          goToApplication("https://www.saucedemo.com/"); //Navigating to the application
          sendText(usernameTextField,username);       //Entering username
          sendText(passwordTextField,password);       //Entering password
          clickElement(loginBtn);                     //Clicking on login button
          return new ProductsPageObject(driver);      //Returning ProductsPageObject
     }


}
