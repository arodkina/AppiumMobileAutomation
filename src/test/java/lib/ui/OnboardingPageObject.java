package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OnboardingPageObject extends MainPageObject {

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";
    private static final String ONBOARDING_TITLE = "org.wikipedia:id/primaryTextView";
    private static final String ONBOARDING_SCREEN = "org.wikipedia:id/primaryTextView";


    public void skipOnboarding() {
        this.waitForElementAndClick(By.id(SKIP_BUTTON), "Element is not present");
    }

    public String getOnboardingTitle() {
        WebElement element = waitForElement(By.id(ONBOARDING_TITLE), "Cannot find element", 5);
        return element.getText();
    }

    public void swipeOnboardingScreenToLeft(){
        this.swipeElementToLeft(By.id(ONBOARDING_SCREEN),"Cannot swipe element");
    }
}
