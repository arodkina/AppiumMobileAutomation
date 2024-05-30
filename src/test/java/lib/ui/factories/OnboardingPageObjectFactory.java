package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.Android.AndroidOnboardingPageObject;
import lib.ui.ArticlePageObject;
import lib.ui.IOS.IOSArticlePageObject;
import lib.ui.IOS.IOSOnboardingPageObject;
import lib.ui.OnboardingPageObject;

public class OnboardingPageObjectFactory {

    public static OnboardingPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidOnboardingPageObject(driver);
        } else {
            return new IOSOnboardingPageObject(driver);
        }
    }
}
