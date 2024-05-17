import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

    private WebElement waitForElement(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
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
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Element is not present");
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Element is not clickable");
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Grass", "Text is not entered", 10);
        waitForElements(By.id("org.wikipedia:id/page_list_item_title"), "Elements are not found", 10);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Element is not clickable");
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),"Element is still shown", 10);
    }

    @Test
    public void testSwipeArticle(){
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Element is not clickable");
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Grass", "Text is not entered", 10);
        waitForElements(By.id("org.wikipedia:id/page_list_item_title"), "Elements are not found", 10);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Element is not clickable");
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),"Element is still shown", 10);
    }

    @Test
    public void testSwipeOnboarding(){
        String firstTitle = getOnboardingTitle();
        Assert.assertEquals("Wrong title is shown", "The Free Encyclopedia\n" +
                "…in over 300 languages", firstTitle);

        swipeOnboardingScreenToLeft();
        String secondTitle = getOnboardingTitle();
        Assert.assertEquals("Wrong title is shown", "New ways to explore", secondTitle);

        swipeOnboardingScreenToLeft();
        String thirdTitle = getOnboardingTitle();
        Assert.assertEquals("Wrong title is shown", "Reading lists with sync", thirdTitle);

        swipeOnboardingScreenToLeft();
        String fourthTitle = getOnboardingTitle();
        Assert.assertEquals("Wrong title is shown", "Send anonymous data", fourthTitle);

        waitForElementAndClick(By.id("org.wikipedia:id/acceptButton"), "Element is not clickable");
        Assert.assertTrue(driver.findElement(By.id("org.wikipedia:id/main_toolbar")).isDisplayed());
    }

    @Test
    public void testCheckTitlePresent() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Element is not present");
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"), "Element is not clickable");
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Text is not entered", 10);
        List<WebElement> searchList = waitForElements(By.id("org.wikipedia:id/page_list_item_title"), "Elements are not found", 10);
        searchList.get(0).click();
        assertElementPresent(By.xpath("//android.webkit.WebView[@text='Appium']"), "Element is not present");
    }


    private void swipeOnboardingScreenToLeft(){
        swipeElementToLeft(By.id("org.wikipedia:id/scrollViewContainer"),"Cannot swipe element");
    }

    private String getOnboardingTitle() {
        WebElement element = waitForElement(By.id("org.wikipedia:id/primaryTextView"), "Cannot find element", 5);
        return element.getText();
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0);{
            if (alreadySwiped > maxSwipes){
                waitForElement(by,"Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElement(by, errorMessage, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String errorMessage){
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element " + by.toString() + "supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private void assertElementPresent(By by, String errorMessage){
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements < 1) {
            String defaultMessage = "An element " + by.toString() + "is not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute (By by, String attribute, String errorMessage, long timeOut){
        WebElement element = waitForElement(by, errorMessage, timeOut);
        return element.getAttribute(attribute);
    }

    private List<WebElement> waitForElements(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
        //driver.runAppInBackground(30);
        //driver.rotate(ScreenOrientation.LANDSCAPE);


    private void assertElementHasText(By by, String errorMessage, String expectedText) {
        WebElement element = waitForElement(by, errorMessage, 5);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, actualText, expectedText);
    }
}
