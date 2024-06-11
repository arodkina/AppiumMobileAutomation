package lib.ui.mobileWeb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobWebNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }

    public MobWebNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
