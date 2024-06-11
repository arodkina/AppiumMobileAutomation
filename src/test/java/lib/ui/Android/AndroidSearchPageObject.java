package lib.ui.Android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Search Wikipedia']";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
        CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
