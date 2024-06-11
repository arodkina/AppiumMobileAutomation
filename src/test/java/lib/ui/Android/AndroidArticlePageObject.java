package lib.ui.Android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
