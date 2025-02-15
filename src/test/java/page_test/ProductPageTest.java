package page_test;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class ProductPageTest extends BaseTest {
        LoginPageObject loginPageObject ;
        ProductsPageObject productsPageObject ;



        @Test
        public void titleName(){
            loginPageObject = new LoginPageObject(driver);
            productsPageObject = loginPageObject.userLogin("problem_user", "secret_sauce");
            System.out.println(productsPageObject.getTitleOfPage());
            System.out.println(productsPageObject.getTextOfFirstItem());
        }

}

