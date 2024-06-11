package lib.ui.Android;

import lib.ui.OnboardingPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidOnboardingPageObject extends OnboardingPageObject {

    static {
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        ONBOARDING_TITLE = "id:org.wikipedia:id/primaryTextView";
        ONBOARDING_SCREEN = "id:org.wikipedia:id/primaryTextView";
        NEXT_BUTTON = "id:org.wikipedia:id/fragment_onboarding_forward_button";
        NEW_WAYS_TEXT = "id:org.wikipedia:id/primaryTextView";
        SEARCH_IN_NEARLY_TEXT = "id:org.wikipedia:id/primaryTextView";
        HELP_MAKE_APP_TEXT = "id:org.wikipedia:id/primaryTextView";
        GET_STARTED_BUTTON = "id:org.wikipedia:id/acceptButton";

    }
    public AndroidOnboardingPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
