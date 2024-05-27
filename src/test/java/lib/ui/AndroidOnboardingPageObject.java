package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class AndroidOnboardingPageObject extends MainPageObject {

    public AndroidOnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    private static final String ONBOARDING_TITLE = "id:org.wikipedia:id/primaryTextView";
    private static final String ONBOARDING_SCREEN = "id:org.wikipedia:id/primaryTextView";


    public void skipOnboarding() {
        this.waitForElementAndClick(SKIP_BUTTON, "Element is not present");
    }

    public String getOnboardingTitle() {
        WebElement element = waitForElement(ONBOARDING_TITLE, "Cannot find element", 5);
        return element.getText();
    }

    public void swipeOnboardingScreenToLeft(){
        this.swipeElementToLeft(ONBOARDING_SCREEN,"Cannot swipe element");
    }
}
