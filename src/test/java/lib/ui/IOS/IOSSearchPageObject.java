package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "predicate:name == 'Search Wikipedia'";
        SEARCH_RESULT = "id:Java (programming language)";
        CANCEL_BUTTON = "predicate:name == 'Cancel' AND label == 'Cancel' AND value == 'Cancel'";
    }
    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
