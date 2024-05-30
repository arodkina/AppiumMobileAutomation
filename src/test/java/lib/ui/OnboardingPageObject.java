package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

public class OnboardingPageObject extends MainPageObject {

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    public static String SKIP_BUTTON;
    public static String ONBOARDING_TITLE;
    public static String ONBOARDING_SCREEN;
    public static String LEARN_MORE_LINK;
    public static  String NEXT_BUTTON;
    public static  String NEW_WAYS_TEXT;
    public static  String SEARCH_IN_NEARLY_TEXT;
    public static  String HELP_MAKE_APP_TEXT;
    public static String GET_STARTED_BUTTON;


    public void skipOnboarding() {
        this.waitForElementAndClick(SKIP_BUTTON, "Element is not present");
    }

    public String getOnboardingTitle(String locator) {
        WebElement element = waitForElement(locator, "Cannot find element", 15);
        if (Platform.getInstance().isAndroid()) {
            return element.getText();
        } else {
            return element.getAttribute("name");
        }
    }

    public void swipeOnboardingScreenToLeft(){
        this.swipeElementToLeft(ONBOARDING_SCREEN,"Cannot swipe element");
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
