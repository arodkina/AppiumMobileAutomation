package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Search Wikipedia']";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
        CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    }
    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
