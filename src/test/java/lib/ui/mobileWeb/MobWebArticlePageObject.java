package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobWebArticlePageObject extends ArticlePageObject {

            static {
                TITLE = "css:#content h1";
                FOOTER_ELEMENT = "css:footer";
                OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
                OPTION_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched button";
            }
        public MobWebArticlePageObject(RemoteWebDriver driver){
            super(driver);
        }
}
