package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobWebArticlePageObject extends ArticlePageObject {

        static {
            TITLE = "css:.mw-page-title-main";
        }
        public MobWebArticlePageObject(RemoteWebDriver driver){
            super(driver);
        }
}
