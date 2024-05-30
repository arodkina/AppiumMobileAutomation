import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.OnboardingPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticleTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private OnboardingPageObject onboardingPageObject;
    private ArticlePageObject articlePageObject;

    private static final String ARTICLE_TITLE_TEXT = "id:org.wikipedia:id/view_card_header_title";
    private static final String INPUT_ARTICLE_TITLE = "Appium";
    private static final String LOCATOR_PATTERN = String.format("xpath://android.widget.TextView[@text='%s']", INPUT_ARTICLE_TITLE);

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = SearchPageObjectFactory.get(driver);
        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);

        onboardingPageObject.skipOnboarding();

    }

    @Test
    public void testCheckViewCardTextPresent(){
        articlePageObject.assertElementHasText(ARTICLE_TITLE_TEXT,"Text is not correct","Featured article");
    }

    @Test
    public void testCheckTitlePresent() {
        List<WebElement> searchList = searchPageObject.searchArticle(INPUT_ARTICLE_TITLE);
        searchList.get(0).click();
        articlePageObject.assertElementPresent(LOCATOR_PATTERN, "Element is not present");
    }
}


