package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobWebSearchPageObject extends SearchPageObject {

        static {
            SEARCH_INIT_ELEMENT = "css:button#searchIcon";
            SEARCH_INPUT = "css:input#searchInput";
            SEARCH_RESULT = "css:.actionable.mw-mf-page-list.thumbs > li[title='Java']";
            CANCEL_BUTTON = "css:.header-action .cdx-button--weight-quiet";
            SEARCH_RESULTS = ".results";
        }
        public MobWebSearchPageObject(RemoteWebDriver driver){
            super(driver);
        }
}
