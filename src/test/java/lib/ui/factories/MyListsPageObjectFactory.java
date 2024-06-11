package lib.ui.factories;

import lib.Platform;
import lib.ui.Android.AndroidMyListsPageObject;
import lib.ui.IOS.IOSMyListsPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.mobileWeb.MobWebMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory
{
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSMyListsPageObject(driver);
        } else {
            return new MobWebMyListsPageObject(driver);
        }
    }
}