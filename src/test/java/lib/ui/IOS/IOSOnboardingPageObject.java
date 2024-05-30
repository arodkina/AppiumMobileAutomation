package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IOSOnboardingPageObject extends OnboardingPageObject {

    static {
        SKIP_BUTTON = "predicate:name == 'Skip' AND label == 'Skip' AND value == 'Skip'";
        ONBOARDING_TITLE = "id:The free encyclopedia";
        LEARN_MORE_LINK = "predicate:name == 'Learn more about Wikipedia' AND label == 'Learn more about Wikipedia' AND value == 'Learn more about Wikipedia'";
        NEXT_BUTTON = "predicate:name == name == 'Next' AND label == 'Next' AND type == 'XCUIElementTypeButton'";
        NEW_WAYS_TEXT = "id:New ways to explore";
        SEARCH_IN_NEARLY_TEXT = "id:Search in nearly 300 languages";
        HELP_MAKE_APP_TEXT = "id:Help make the app better";
        GET_STARTED_BUTTON = "predicate:name == 'Get started' AND label == 'Get started' AND type == 'XCUIElementTypeButton'";
        ONBOARDING_SCREEN = "xpath://XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView";
    }

    public IOSOnboardingPageObject(AppiumDriver driver){
        super(driver);
    }

}
