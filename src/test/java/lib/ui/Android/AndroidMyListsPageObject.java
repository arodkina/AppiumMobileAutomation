package lib.ui.Android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject
{
    static {
        FOLDER_BY_NAME_TPL = "";
        ARTICLE_BY_TITLE_TPL = "";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}