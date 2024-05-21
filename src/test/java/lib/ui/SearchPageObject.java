package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text='Search Wikipedia']";
    private static final String SEARCH_INPUT = "org.wikipedia:id/search_src_text";
    private static final String SEARCH_RESULTS = "org.wikipedia:id/page_list_item_title";
    private static final String CANCEL_BUTTON = "org.wikipedia:id/search_close_btn";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElement(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element");
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), searchLine, "Text is not entered", 5);
    }

    public List<WebElement> waitForSearchResults (){
        return this.waitForElements(By.id(SEARCH_RESULTS), "Elements are not found", 10);
    }

    public void waitForSearchResultsToDisappear (){
        this.waitForElementNotPresent(By.id(SEARCH_RESULTS), "Elements are still found", 10);
    }

    public void clickSearchCancelButton() {
        this.waitForElementAndClick(By.id(CANCEL_BUTTON),"Element is not clickable");
    }

    public List<WebElement> searchArticle(String searchQuery) {
        this.initSearchInput();
        this.typeSearchLine(searchQuery);
        return this.waitForSearchResults();
    }

    public void assertElementHasText(By by, String errorMessage, String expectedText) {
        WebElement element = waitForElement(by, errorMessage, 5);
        String actualText = element.getText();
        assertEquals(errorMessage, expectedText, actualText);
    }
}
