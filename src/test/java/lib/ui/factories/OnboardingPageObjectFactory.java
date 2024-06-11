package lib.ui.factories;

import lib.Platform;
import lib.ui.Android.AndroidOnboardingPageObject;
import lib.ui.IOS.IOSOnboardingPageObject;
import lib.ui.OnboardingPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OnboardingPageObjectFactory {

    public static OnboardingPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidOnboardingPageObject(driver);
        } else {
            return new IOSOnboardingPageObject(driver);
        }
    }
}
