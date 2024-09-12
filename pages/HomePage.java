package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HomePage {
    WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //הגדרות של האלמנטים של דף הבית
    By myAccountBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[2]/div[2]/div/a/span");
    By homeWareBtn= By.xpath("//*[@id=\"meganav-link-7\"]");
    By languageBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[6]/button/img");
    By hebrowLanguageBtn=By.xpath("//*[@id=\"header-country-selector-wrapper\"]/div/div[3]/div/div[4]/div/button[1]");
    By shopNowBtn=By.xpath("//*[@id=\"header-country-selector-wrapper\"]/div/div[3]/div/div[5]/button");
    By searchProductInput=By.xpath("//*[@id=\"header-big-screen-search-box\"]");

    By searchProductBtn= By.xpath("//*[@id=\"header-search-form\"]/button");

    By product= By.xpath("//*[@id=\"platform_modernisation_product_summary_D67450\"]/div/div[1]/div[1]/div/div/div[1]/a/img");

    //פונקציות על האלמנטים בדף הבית
    public void clickMyAccountBtn()
    { driver.findElement(myAccountBtn).click();}

    public void clickHomeWareBtn() {
        Actions action = new Actions(driver);
        WebElement btn = driver.findElement(homeWareBtn);
        action.doubleClick(btn).perform();
    }

    public void clickLanguageBtn()
    { driver.findElement(languageBtn).click();}

    public void clickHebrowLanguageBtn()
    { driver.findElement(hebrowLanguageBtn).click();}

    public void clickShopNowBtn()
    { driver.findElement(shopNowBtn).click();}

    public void enterSearchProductInput(String valueSearch)
    { driver.findElement(searchProductInput).sendKeys(valueSearch);}

    public void clickSearchProductBtn()
    { driver.findElement(searchProductBtn).click();}

    public void clickProduct()
    {

        driver.findElement(product).click();
    }
}
