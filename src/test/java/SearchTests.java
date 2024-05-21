import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {
    private SearchPageObject SearchPageObject;
    private OnboardingPageObject OnboardingPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        SearchPageObject = new SearchPageObject(driver);
        OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
    }

    @Test
    public void testCheckSearchFieldTextPresent(){
        SearchPageObject.assertElementHasText(By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),"Text is not correct","Search Wikipedia");
    }

    @Test
    public void testCheckCancelSearch(){
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResults();
        SearchPageObject.clickSearchCancelButton();
        SearchPageObject.waitForSearchResultsToDisappear();
    }
}
