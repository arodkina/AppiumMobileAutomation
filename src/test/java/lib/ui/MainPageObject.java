package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;
    public MainPageObject (RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(String locator, String errorMessage, long timeOutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage +"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElement(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage +"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage){
        WebElement element = waitForElement(locator, errorMessage, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeOutInSeconds){

        WebElement element = waitForElement(locator, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage +"\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElement(locator, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe){
        if (driver instanceof AppiumDriver) {
        TouchAction action = new TouchAction((AppiumDriver)driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
        } else {
            System.out.println("Method swipeUp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0);{
            if (alreadySwiped > maxSwipes){
                waitForElement(locator,"Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(String locator, String errorMessage) {

        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElement(locator, errorMessage, 10);
        int left_x = element.getLocation().getX();
        int width = element.getSize().getWidth();
        int middle_y = element.getLocation().getY() + element.getSize().getHeight() / 2;

        if (Platform.getInstance().isAndroid()) {
            new TouchAction((AppiumDriver)driver)
                    .press(PointOption.point(left_x + width - 1, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        } else {
            int offsetX = element.getSize().getWidth() / 2 - 10;

            Actions actions = new Actions(driver);
            actions
                    .moveToElement(element)
                    .clickAndHold()
                    .moveByOffset(-offsetX * 3, 0)
                    .release()
                    .perform();
        }
        } else {
            System.out.println("Method swipeUp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);

        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(String locator, String errorMessage){

        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element " + locator + "supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(String locator, String errorMessage){
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements < 1) {
            String defaultMessage = "An element " + locator + "is not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute (String locator, String attribute, String errorMessage, long timeOut){
        WebElement element = waitForElement(locator, errorMessage, timeOut);
        return element.getAttribute(attribute);
    }

    public List<WebElement> waitForElements(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void assertElementHasText(String locator, String errorMessage, String expectedText) {
        WebElement element = waitForElement(locator, errorMessage, 5);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, actualText, expectedText);
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        }

        if (byType.equals("id")) {
            return By.id(locator);
        }

        if (byType.equals("predicate")) {
            return MobileBy.iOSNsPredicateString(locator);
        }

        if (byType.equals("css")) {
            return By.cssSelector(locator);
        }

        else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator);
        }
    }

    public void swipeUpTillElementAppears(String locator, String errorMessage, int maxSwipes) {
       int alreadySwiped = 0;
       while (!this.isElementDisplayed(locator))
        {
            if (alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementDisplayed(locator));
            }

            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message)
    {
        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElement(locator + "/..", error_message);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMobWeb()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        WebElement element = this.waitForElement(locator, error_message);

        while (!this.isElementDisplayed(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public boolean isElementDisplayed(String locator) {
        int elementLocatedByY = this.waitForElement(locator, "Cannot find element", 10).getLocation().getY();

        if (Platform.getInstance().isMobWeb()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            elementLocatedByY -= Integer.parseInt(js_result.toString());
        }

        int screenSixeByY = driver.manage().window().getSize().getHeight();
        return elementLocatedByY < screenSixeByY;
    }

    public boolean isElementPresent(String locator)
    {
        return getAmountOfElements(locator) > 0;
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        while (!this.isElementDisplayed(locator))
        {
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementDisplayed(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }



    public void tryClickElementWithFewAttempts(String locator, String error_message, int amountOfAttempts)
    {
        int current_attempts = 0;
        boolean needMoreAttempts = true;

        while (needMoreAttempts) {
            try {
                this.waitForElementAndClick(locator, error_message);
                needMoreAttempts = false;
            } catch (Exception e) {
                if (current_attempts > amountOfAttempts) {
                    this.waitForElementAndClick(locator, error_message);
                }
            }

            ++current_attempts;
        }
    }
}
