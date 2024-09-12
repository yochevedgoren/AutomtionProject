package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //הגדרות של האלמנטים של דף תשלום

   By paymentMethodsRadioBtn= By.id("card_option");
    By cardNumberInput= By.xpath("cardNumber");

    By cardholderNameInput=By.id("cardholderName");
    By expiryMonthInput=By.id("expiryMonth");
    By expiryYearInput=By.id("expiryYear");
    By securityCodeInput=By.id("securityCode");
    By errorNumberCard= By.xpath("//*[@id=\"cardNumber-hint\"]");
    By paymentBtn= By.xpath("//*[@id=\"submitButton\"]");

    //פונקציות על האלמנטים בדף תשלום


    public void clickPaymentMethodsRadioBtn()
    {
        driver.findElement(paymentMethodsRadioBtn).click();
    }
    public boolean ifErrorDisplay()
    {
       if(driver.findElement(errorNumberCard).isDisplayed())
           return true;
       return  false;
    }

    public void enterCardNumberInput(String cardNumber)
    { driver.findElement(cardNumberInput).sendKeys(cardNumber);}


    //עשיתי קודם מחיקה כי קיים לי ערך שמור בשדה זה
    public void enterCardholderNameInput(String cardholderName)
    {
        driver.findElement(cardholderNameInput).sendKeys(Keys.CONTROL,"A");
        driver.findElement(cardholderNameInput).sendKeys(Keys.DELETE);
        driver.findElement(cardholderNameInput).sendKeys(cardholderName);}
    public void enterExpiryMonthInput(String expiryMonth)
    { driver.findElement(expiryMonthInput).sendKeys(expiryMonth);}
    public void enterExpiryYearInput(String expiryYear)
    { driver.findElement(expiryYearInput).sendKeys(expiryYear);}
    public void enterSecurityCodeInput(String securityCode)
    { driver.findElement(securityCodeInput).sendKeys(securityCode);}
    public void clickPaymentBtn()
    {
        driver.findElement(paymentBtn).click();
    }
}
