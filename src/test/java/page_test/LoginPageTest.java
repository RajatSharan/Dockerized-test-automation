package page_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class LoginPageTest extends BaseTest
{
    @BeforeMethod
    public void setupTest() {
        loginPageObject = new LoginPageObject(driver); // ✅ Ensure WebDriver is initialized
    }

    //Test class
    //Contains test methods
    //Contains test data
    //Contains test assertions
    //Contains test steps

    //Test data
    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;

    @Test
    public void userLoginTest() {
        loginPageObject = new LoginPageObject(driver);      //Creating object of LoginPageObject
        loginPageObject.userLogin("standard_user", "secret_sauce"); // ✅ Ensure WebDriver is initialized
        productsPageObject = new ProductsPageObject(driver);    //Creating object of ProductsPageObject
        String title = productsPageObject.getTitleOfPage();     //Getting title of the product page
        System.out.println(title);                  //Printing title of the product page

    }
}

