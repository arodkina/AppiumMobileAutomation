package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IOSOnboardingPageObject extends MainPageObject {

    public IOSOnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "predicate:name == 'Skip' AND label == 'Skip' AND value == 'Skip'";
    private static final String ONBOARDING_TITLE = "id:The free encyclopedia";
    private static final String LEARN_MORE_LINK = "predicate:name == 'Learn more about Wikipedia' AND label == 'Learn more about Wikipedia' AND value == 'Learn more about Wikipedia'";
    private static final String NEXT_BUTTON = "predicate:name == 'Next' AND label == 'Next' AND value == 'Next'";
    private static final String NEW_WAYS_TEXT = "id:New ways to explore";
    private static final String SEARCH_IN_NEARLY_TEXT = "id:Search in nearly 300 languages";
    private static final String HELP_MAKE_APP_TEXT = "id:Help make the app better";
    private static String GET_STARTED_BUTTON = "predicate:name == 'Get started' AND label == 'Get started' AND type == 'XCUIElementTypeButton'";


    public void skipOnboarding() {
        this.waitForElementAndClick(SKIP_BUTTON, "Element is not present");
    }

    public String getOnboardingTitle() {
        WebElement element = waitForElement(ONBOARDING_TITLE, "Cannot find element", 5);
        return element.getText();
    }

    public void waitForLearnMoreLink(){
        this.waitForElement(LEARN_MORE_LINK, "Cannot find Learn more link", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_BUTTON, "Cannot find and click Next button");
    }

    public void waitForNewWayToExploreText(){
        this.waitForElement(NEW_WAYS_TEXT, "Cannot find text", 10);
    }

    public void waitForSearchInNearlyText(){
        this.waitForElement(SEARCH_IN_NEARLY_TEXT, "Cannot find Search In Nearly Text ", 10);
    }

    public void waitForHelpMakeAppText() {
        this.waitForElement(HELP_MAKE_APP_TEXT, "Cannot find Help Make App better", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click Get Started button");
    }

}
