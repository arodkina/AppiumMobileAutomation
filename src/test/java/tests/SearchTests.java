package tests;

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

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = SearchPageObjectFactory.get(driver);
        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();
    }

    @Test
    public void testCheckSearchFieldTextPresent(){
        searchPageObject.assertElementHasText(SEARCH_TEXT,"Text is not correct","Search Wikipedia");
    }

    @Test
    public void testCheckCancelSearch(){
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults();
        searchPageObject.clickSearchCancelButton();
        searchPageObject.waitForSearchResultsToDisappear();
    }
}
