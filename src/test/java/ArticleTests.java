import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticleTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private OnboardingPageObject onboardingPageObject;
    private ArticlePageObject articlePageObject;

    private static final String ARTICLE_TITLE_TEXT = "org.wikipedia:id/view_card_header_title";
    private static final String INPUT_ARTICLE_TITLE = "Appium";
    private static final String LOCATOR_PATTERN = String.format("//android.widget.TextView[@text='%s']", INPUT_ARTICLE_TITLE);

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
        onboardingPageObject = new OnboardingPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);

        onboardingPageObject.skipOnboarding();

    }

    @Test
    public void testCheckViewCardTextPresent(){
        articlePageObject.assertElementHasText(By.id(ARTICLE_TITLE_TEXT),"Text is not correct","Featured article");
    }

    @Test
    public void testCheckTitlePresent() {
        List<WebElement> searchList = searchPageObject.searchArticle(INPUT_ARTICLE_TITLE);
        searchList.get(0).click();
        articlePageObject.assertElementPresent(By.xpath(String.format(LOCATOR_PATTERN)), "Element is not present");
    }
}


