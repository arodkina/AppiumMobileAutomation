package lib.ui.factories;

import lib.Platform;
import lib.ui.Android.AndroidSearchPageObject;
import lib.ui.IOS.IOSSearchPageObject;
import lib.ui.SearchPageObject;
import lib.ui.mobileWeb.MobWebSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSSearchPageObject(driver);
        } else {
            return new MobWebSearchPageObject(driver);
        }
    }
}
