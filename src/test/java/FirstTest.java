import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;
    
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","14.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/arodkina/Desktop/AppiumJavaMobileAutomation/AppiumMobileAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Element is not present");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    private WebElement waitForElement(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage +"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage){
        WebElement element = waitForElement(by, errorMessage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElement(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage +"\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElement(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    @Test
    public void testCheckViewCardTextPresent(){
        assertElementHasText(By.id("org.wikipedia:id/view_card_header_title"),"Text is not correct","Featured article");
    }

    @Test
    public void testCheckSearchFieldTextPresent(){
        assertElementHasText(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Text is not correct","Search Wikipedia");
    }

    @Test
    public void testCheckCancelSearch(){
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Element is not clickable");
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Grass", "Text is not entered", 10);
        waitForElements(By.id("org.wikipedia:id/page_list_item_title"), "Elements are not found", 10);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Element is not clickable");
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),"Element is still shown", 10);
    }

    private List<WebElement> waitForElements(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private void assertElementHasText(By by, String errorMessage, String expectedText) {
        WebElement element = waitForElement(by, errorMessage, 5);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, actualText, expectedText);
    }
}
