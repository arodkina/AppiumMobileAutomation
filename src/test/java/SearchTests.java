import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {
    private SearchPageObject searchPageObject;
    private OnboardingPageObject onboardingPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
        onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboarding();
    }

    @Test
    public void testCheckSearchFieldTextPresent(){
        searchPageObject.assertElementHasText(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Text is not correct","Search Wikipedia");
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
