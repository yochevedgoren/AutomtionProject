package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomeWarePage {
    WebDriver driver;

    public HomeWarePage(WebDriver driver) {
        this.driver = driver;
    }

    //הגדרות של האלמנטים של דף עיצוב הבית
    By bathRoomBtn= By.linkText("Bathroom");
    By gardenBtn= By.xpath("//*[@id=\"multi-9-teaser-5946210-1_item_5\"]/div/a");

    By babyBtn= By.xpath("//*[@id=\"meganav-link-4\"]");

    //פונקציות על האלמנטים בדף עיצוב הבית

    public void clickBathRoomLink()
    { driver.findElement(bathRoomBtn).click();}

    public void clickGardenBtn()
    { driver.findElement(gardenBtn).click();}

    public void clickBabyBtn()
    {
        Actions action = new Actions(driver);
        WebElement btn =  driver.findElement(babyBtn);
        action.doubleClick(btn).perform();


    }
}
