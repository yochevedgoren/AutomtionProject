package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.JsonInput;
import org.xml.sax.SAXException;
import pages.*;
import ulitltes.Constans;
import ulitltes.Methods;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {
    //הוספת תמונה לכל טסט כשמצליח לא אמור להיות זה רק לצורך הפרויקט
    private static WebDriver driver;

    //בניית עצם לכל דף(מחלקה)
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    HomeWarePage homeWarePage = new HomeWarePage(driver);

    ProductPage productPage = new ProductPage(driver);
    PaymentPage paymentPage = new PaymentPage(driver);

    //אובייקטים לדוחות
    private static ExtentReports extentReports = new ExtentReports();
    private static ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("index.html");
    String currentTime = String.valueOf(System.currentTimeMillis());


    Methods methods = new Methods();

    @BeforeClass
    public static void beforeClass() throws InterruptedException {
        System.out.println("start");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver(options);
        Thread.sleep(4000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //הגדרת עיצוב הדוח
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("Next Site");
        extentSparkReporter.config().setDocumentTitle("Next Site report");

    }

    //טסט כניסה למערכת
    @Test
    public void a_loginTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest loginTest = extentReports.createTest("login step");

        //כניסה לאתר נקסט באנגלית
        driver.get(methods.getData("WEBSITEADDRESS"));

        //כניסה לדף החשבון שלי וולידציה
        homePage.clickMyAccountBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.LOGIN_URL, driver.getCurrentUrl());
            loginTest.pass("Login to the login page successfully", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            loginTest.fail("Login to login page failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());


        }

    }
    //טסט הכנסת אימייל וסיסמא
    @Test
    public void b_enterEmailAndPasswordTest() throws ParserConfigurationException, IOException, SAXException {
        ExtentTest enterEmailAnaPasswordTest = extentReports.createTest("enter Email And Password step");
        //הכנסת מייל של החשבון וולידציה שאכן נכנס תקין
        String expectedEmail = methods.getData("EMAIL");
        loginPage.enterEmailUserInput(expectedEmail);
        String actualEmail = loginPage.getEmailUserInput();
        enterEmailAnaPasswordTest.info("Expected email: " + expectedEmail + " Actual email: " + actualEmail);

        //הכנסת סיסמא של החשבון וולידציה שאכן נכנס תקין
        String expectedPassword = methods.getData("PASSWORD");
        loginPage.enterPasswordUserInput(expectedPassword);
        String actualPassword = loginPage.getPasswordUserInput();
        enterEmailAnaPasswordTest.info("Expected password: " + expectedPassword+ " Actual password: " + actualPassword);

        //ולידציה
        if ((expectedEmail.equals(actualEmail))&&(expectedPassword.equals(actualPassword)))
            enterEmailAnaPasswordTest.pass("The email and password were entered correctly", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        else
            enterEmailAnaPasswordTest.fail("The email or password were not entered correctly", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

    }

    //טסט כניסה לחשבון שלי
    @Test
    public void c_loginToMyAccountTest() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest loginToMyAccountTest = extentReports.createTest("login To My Account Test");
        //לחיצה על כפתור כניסה וולדציה
        loginPage.clickSignInBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.LOGIN_MY_ACCOUNT_URL, driver.getCurrentUrl());
            loginToMyAccountTest.pass(" Login to my account was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            loginToMyAccountTest.fail("Login to my account was failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
            //ניווט לדף הבית חזרה במקרה כשלון בכניסת משתמש
            driver.navigate().to(methods.getData("WEBSITEADDRESS"));
        }
    }
    //טסט לחיצה על כפתור עיצוב הבית
    @Test
    public void d_homeWareClicksTest() throws InterruptedException {
        ExtentTest homeWareClicksTest = extentReports.createTest("home Ware Clicks step");
        //כניסה לדף עיצוב הבית וולידציה
        homePage.clickHomeWareBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.HOME_WARE_URL, driver.getCurrentUrl());
            homeWareClicksTest.pass("Login to the home ware page was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            homeWareClicksTest.fail(" Login to home ware page failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
    }
    //טסט לחיצה על כפתור אמבטיה
    @Test
    public void e_bathroomClicksTest() throws InterruptedException {
        ExtentTest bathroomClicksTest = extentReports.createTest("bathroom Clicks step");
        //לחיצה על הקישור bathroom בדף עיצוב הבית וולידציה
        homeWarePage.clickBathRoomLink();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.BATHROOM_URL, driver.getCurrentUrl());
            bathroomClicksTest.pass(" Enter to the 'Bathroom' link was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        } catch (AssertionError er) {
            bathroomClicksTest.fail("Enter to the 'Bathroom' link failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
        driver.navigate().to(Constans.HOME_WARE_URL);
    }

    //טסט לחיצה על גינה
    @Test
    public void f_gardenClicksTest() throws InterruptedException {
        ExtentTest gardenClicksTest = extentReports.createTest("garden Clicks step");
        // לחיצה על הקטגוריה garden בדף עיצוב הבית וולידציה
        homeWarePage.clickGardenBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.GARDEN_URL, driver.getCurrentUrl());
            gardenClicksTest.pass("Enter to the 'Garden' category was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        } catch (AssertionError er) {
            gardenClicksTest.fail("Enter to the 'Garden' category failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        }
        driver.navigate().to(Constans.HOME_WARE_URL);

    }
    //טסט חיצה על קטגוריית תינוק
    @Test
    public void g_babyClicksTest() throws InterruptedException {
        ExtentTest babyClicksTest = extentReports.createTest("baby Clicks step");
        //לחיצה על קישור baby בבאנר וולידציה
        homeWarePage.clickBabyBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.BABY_URL, driver.getCurrentUrl());
            babyClicksTest.pass("Enter to the 'Baby' link was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        } catch (AssertionError er) {
            babyClicksTest.fail("Enter to the 'Baby' link failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        }
        driver.navigate().to(Constans.HOME_WARE_URL);
    }

    //טסט החלפת שפה מאנגלית לעברית וולידציה
    @Test
    public void h_changeLanguageTest() throws InterruptedException {
        ExtentTest changeLanguageTest = extentReports.createTest("change Language step");

        homePage.clickLanguageBtn();
        homePage.clickHebrowLanguageBtn();
        homePage.clickShopNowBtn();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.NEXT_SITE_HEBROW_URL, driver.getCurrentUrl());
            changeLanguageTest.pass("Language change was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        } catch (AssertionError er) {
            changeLanguageTest.fail("Language change was failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
    }


    //טסט לחיפוש מוצר ובחירתו עם ולידציה
    @Test
    public void i_productSearchTest() throws InterruptedException {
        ExtentTest productSearchTest = extentReports.createTest("product Search step");

        homePage.enterSearchProductInput(Constans.SEARCH_PRODUCT);
        homePage.clickSearchProductBtn();
        homePage.clickProduct();
        Thread.sleep(4000);
        try {
            Assert.assertEquals(Constans.SEARCH_PRODUCT_URL, driver.getCurrentUrl());
            productSearchTest.pass("Product selection was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            productSearchTest.fail(" Product selection was failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
    }

    //טסט לבחירת צבע ומידה של המוצר
    @Test
    public void j_chooseSizeAndColor() {
        ExtentTest chooseSizeAndColor = extentReports.createTest("choose Size And Color step");

        productPage.chooseSize();
        chooseSizeAndColor.info("Expected size: " + Constans.SIZE_PRODUCT + " Actual size: " + productPage.getSizeChoose());
        productPage.chooseColor();
        chooseSizeAndColor.info("Expected color: " + Constans.COLOR_PRODUCT + " Actual color: " + productPage.getColorChoose());
        if ((productPage.getSizeChoose().equals(Constans.SIZE_PRODUCT)) && (productPage.getColorChoose().equals(Constans.COLOR_PRODUCT)))
            chooseSizeAndColor.pass("The correct size and color is selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        else
            chooseSizeAndColor.fail("The correct size or color is not selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

    }

    //טסט להוספת מוצרים לסל קניות
    @Test
    public void k_addingProductToCartTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest addingProductToCartTest = extentReports.createTest("adding Product To Cart step");

        productPage.clickAddToBagBtn();
        productPage.clickAddToBagBtn();
        productPage.clickViewOrEditBagBtn();
        if (productPage.getAmountProduct().equals("2"))
            addingProductToCartTest.pass("The product has been added twice", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        else
            addingProductToCartTest.fail("The product has not been added twice", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

    }
    //טסט לחיצה על תשלום של ההזמנה
    @Test
    public void l_enterToChekOutTest()
    {
        ExtentTest enterToChekOutTest = extentReports.createTest("Enter To ChekOut step");
        productPage.clickChekOutBtn();
        try {
            Assert.assertEquals(Constans.LOGIN_CHECKOUT_URL, driver.getCurrentUrl());
           enterToChekOutTest.pass("Login to checkout was successful", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            enterToChekOutTest.fail(" Login to checkout was failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
    }
    //טסט תשלום עבור ההזמנה
    //טסט זה הוא סתמי מטעמי אבטחת האתר
    @Test
    public void m_paymentForOrderTest() throws InterruptedException {
        ExtentTest paymentForOrderTest = extentReports.createTest("payment For Order step");

        paymentPage.clickPaymentMethodsRadioBtn();
        paymentPage.enterCardNumberInput(Constans.CARD_NUMBER);
        paymentPage.enterCardholderNameInput(Constans.CARD_HOLDER_NAME);
        paymentPage.enterExpiryMonthInput(Constans.EXPIRY_MONTH);
        paymentPage.enterExpiryYearInput(Constans.EXPIRY_YEAR);
        paymentPage.enterSecurityCodeInput(Constans.SECURITY_CODE);

        if (paymentPage.ifErrorDisplay())
            paymentForOrderTest.info("The error about the wrong card number appears");
        else
            paymentForOrderTest.info("The error about a wrong card number does not appear");

        paymentPage.clickPaymentBtn();
        //בדיקה סתמית של הצלחת התשלום
        try {
            Assert.assertEquals(Constans.PAYMENT_URL, driver.getCurrentUrl());
            paymentForOrderTest.pass("The payment has been made successfully", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());
        } catch (AssertionError er) {
            paymentForOrderTest.fail(" Payment failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures\\" + currentTime, driver)).build());

        }
    }
    @AfterClass
    public static void afterClass() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        extentReports.flush();
    }
}
