package lib.ui.mobileWeb;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobWebMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE = "";
        REMOVE_FROM_SAVED_BUTTON = "";
    }

    public MobWebMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}