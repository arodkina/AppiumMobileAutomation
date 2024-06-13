package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
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

    public void setUp() throws Exception {
        super.setUp();

        searchPageObject = SearchPageObjectFactory.get(driver);
        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);

        onboardingPageObject.skipOnboarding();

    }

    @Test
    @Epic("Tests for articles")
    @Features(value = {@Feature(value="Search"), @Feature(value="Articles")})
    @DisplayName("Check that article title is correct")
    @Step("Starting test testCheckViewCardTextPresent")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckViewCardTextPresent(){
        articlePageObject.assertElementHasText(ARTICLE_TITLE_TEXT,"Text is not correct","Featured article");
    }

    @Test
    @Epic("Tests for articles")
    @Features(value = {@Feature(value="Search"),  @Feature(value="Articles")})
    @DisplayName("Check that article title is displayed")
    @Step("Starting test testCheckTitlePresent")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckTitlePresent() {
        List<WebElement> searchList = searchPageObject.searchArticle(INPUT_ARTICLE_TITLE);
        searchList.get(0).click();
        articlePageObject.assertElementPresent(LOCATOR_PATTERN, "Element is not present");
    }
}


