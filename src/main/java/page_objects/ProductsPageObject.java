package page_objects;

import genraic_keyword.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPageObject extends WebElementInteractions {

    public ProductsPageObject(WebDriver driver) {
        super(driver); // Pass WebDriver to parent class
    }

    private final By getTitleOfProductPage= By.xpath("//span[contains(text(),'Products')]");     //Locating title of the product page
    private final By getTextOfFirstItem= By.xpath("//a[@id='item_4_title_link']/div");     //Locating product name

    public String getTitleOfPage(){
        return retriveTextData(getTitleOfProductPage);         //Retrieving title of the product page
    }

    public String getTextOfFirstItem(){
        return retriveTextData(getTextOfFirstItem);         //Retrieving text of the first item
    }


}
