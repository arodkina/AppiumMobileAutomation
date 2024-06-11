package lib.ui.IOS;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}