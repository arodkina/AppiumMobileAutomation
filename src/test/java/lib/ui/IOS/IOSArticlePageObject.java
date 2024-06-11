package lib.ui.IOS;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE= "";
    }
    public IOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
