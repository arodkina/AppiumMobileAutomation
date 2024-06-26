package lib.ui.factories;

import lib.Platform;
import lib.ui.Android.AndroidNavigationUI;
import lib.ui.IOS.IOSNavigationUI;
import lib.ui.NavigationUI;

import lib.ui.mobileWeb.MobWebNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory
{
    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MobWebNavigationUI(driver);
        }
    }
}