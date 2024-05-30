package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE= "";
    }
    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
