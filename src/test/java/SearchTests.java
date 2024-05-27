import lib.CoreTestCase;
import lib.ui.AndroidOnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    private SearchPageObject searchPageObject;
    private AndroidOnboardingPageObject onboardingPageObject;

    private static final String SEARCH_TEXT = "xpath://android.widget.TextView[@text='Search Wikipedia']";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
        onboardingPageObject = new AndroidOnboardingPageObject(driver);
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
