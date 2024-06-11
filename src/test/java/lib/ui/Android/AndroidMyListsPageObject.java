package lib.ui.Android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject
{
    static {
        FOLDER_BY_NAME = "";
        ARTICLE_BY_TITLE = "";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}