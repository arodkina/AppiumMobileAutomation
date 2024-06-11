package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

abstract public class SearchPageObject extends MainPageObject {

    protected static String SEARCH_INIT_ELEMENT;
    protected static String SEARCH_INPUT;
    protected static String SEARCH_RESULTS;
    protected static String SEARCH_RESULT;
    protected static String CANCEL_BUTTON;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElement(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element");
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Text is not entered", 5);
    }

    public List<WebElement> waitForSearchResults (){
        return this.waitForElements(SEARCH_RESULTS, "Elements are not found", 10);
    }

    public void waitForSearchResultsToDisappear (){
        this.waitForElementNotPresent(SEARCH_RESULTS, "Elements are still found", 10);
    }

    public void clickSearchCancelButton() {
        this.waitForElementAndClick(CANCEL_BUTTON,"Element is not clickable");
    }

    public void clickSearchResult() {
        this.waitForElementAndClick(SEARCH_RESULT, "Element is not clickable");
    }

    public List<WebElement> searchArticle(String searchQuery) {
        this.initSearchInput();
        this.typeSearchLine(searchQuery);
        return this.waitForSearchResults();
    }

    public void assertElementHasText(String locator, String errorMessage, String expectedText) {
        WebElement element = waitForElement(locator, errorMessage, 5);
        String actualText = element.getText();
        assertEquals(errorMessage, expectedText, actualText);
    }
}
