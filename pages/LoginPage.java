package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //הגדרות של האלמנטים של דף כניסת משתמש
    By emailUserInput= By.xpath("//*[@id=\"EmailOrAccountNumber\"]");
    By passwordUserInput= By.xpath("//*[@id=\"Password\"]");
    By SignInBtn= By.xpath("//*[@id=\"SignInNow\"]");


    //פונקציות על האלמנטים בדף כניסת משתמש

    public void enterEmailUserInput(String email)
    { driver.findElement(emailUserInput).sendKeys(email);}
    public String getEmailUserInput()
    { return  driver.findElement(emailUserInput).getAttribute("value");}

    public void enterPasswordUserInput(String password)
    { driver.findElement(passwordUserInput).sendKeys(password);}
    public String getPasswordUserInput()
    { return  driver.findElement(passwordUserInput).getAttribute("value");}

    public void clickSignInBtn()
    { driver.findElement(SignInBtn).click();}

}
