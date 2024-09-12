package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //הגדרות של האלמנטים על מוצר
    By selectColor=By.cssSelector("[id*='dk_container_Colour-']");
    By color= By.xpath("//*[@id=\"dk_container_Colour-2001\"]/div/ul/li[1]/a");
    By selectSize=By.cssSelector("[id*='dk_container_Size-']");
    By size= By.xpath("//*[@id=\"dk_container_Size-D67-450\"]/div/ul/li[4]/a");
    By addToBagBtn= By.xpath("//*[@id=\"Style2001\"]/section/div[4]/div[5]/div[4]/div/a[1]");

    By checkOutBtn= By.xpath("//*[@id=\"pageWidth\"]/div[5]/div[3]/a[3]");
    By viewOrEditBagBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[4]/div[2]/div/div/div[2]/div/div/div[3]/div[1]/a");

    By amountProduct= By.cssSelector("[id*='dk_container_Qty_']");
    By continueRegisterBtn=By.xpath("//*[@id=\"Next\"]/div[2]/a/div");


    //פונקציות על האלמנטים על מוצר

    public void chooseColor()
    {
        driver.findElement(selectColor).click();
        driver.findElement(color).click();
    }
    public void chooseSize()
    {
        driver.findElement(selectSize).click();
        driver.findElement(size).click();
    }
    public void clickAddToBagBtn()
    {
        driver.findElement(addToBagBtn).click();
    }

  public String getColorChoose()
  {
      return  driver.findElement(selectColor).getText();
  }
    public String getSizeChoose()
    {
        return  driver.findElement(selectSize).getText();
    }
    public void clickChekOutBtn()
    {  driver.findElement(checkOutBtn).click();}

    public void clickViewOrEditBagBtn()
    {  driver.findElement(viewOrEditBagBtn).click();}

    public String getAmountProduct()
    {
        return  driver.findElement(amountProduct).getText();

    }

    public void clickContinueRegisterBtn()
    {
        driver.findElement(continueRegisterBtn).click();
    }

}
