package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
