import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class OnboardingTests extends CoreTestCase {
    private OnboardingPageObject onboardingPageObject;
    private static final String WIKI_LOGO = "org.wikipedia:id/main_toolbar_wordmark";

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = new OnboardingPageObject(driver);
    }

    @Test
    public void testSwipeOnboarding(){
        String firstTitle = onboardingPageObject.getOnboardingTitle();
        assertEquals("Wrong title is shown", "The Free Encyclopedia\n" +
                "…in over 300 languages", firstTitle);

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String secondTitle = onboardingPageObject.getOnboardingTitle();
        assertEquals("Wrong title is shown", "New ways to explore", secondTitle);

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String thirdTitle = onboardingPageObject.getOnboardingTitle();
        assertEquals("Wrong title is shown", "Reading lists with sync", thirdTitle);

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String fourthTitle = onboardingPageObject.getOnboardingTitle();
        assertEquals("Wrong title is shown", "Send anonymous data", fourthTitle);

        onboardingPageObject.waitForElementAndClick(By.id("org.wikipedia:id/acceptButton"), "Element is not clickable");
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElement(By.id(WIKI_LOGO), "Mainpage is not opened",5);
    }
}
