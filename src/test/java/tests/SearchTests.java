package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.OnboardingPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    private SearchPageObject searchPageObject;
    private OnboardingPageObject onboardingPageObject;

    private static final String SEARCH_TEXT = "xpath://android.widget.TextView[@text='Search Wikipedia']";

    public void setUp() throws Exception {
        super.setUp();

        searchPageObject = SearchPageObjectFactory.get(driver);
        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();
    }

    @Test
    @Epic("Tests for Search")
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that search field text is displayed")
    @Step("Starting test testCheckSearchFieldTextPresent")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchFieldTextPresent(){
        searchPageObject.assertElementHasText(SEARCH_TEXT,"Text is not correct","Search Wikipedia");
    }

    @Test
    @Epic("Tests for Search")
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that searching can be cancelled")
    @Step("Starting test testCheckCancelSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckCancelSearch(){
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults();
        searchPageObject.clickSearchCancelButton();
        searchPageObject.waitForSearchResultsToDisappear();
    }
}
