import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.AndroidOnboardingPageObject;
import org.junit.Test;

public class OnboardingTests extends CoreTestCase {
    private AndroidOnboardingPageObject onboardingPageObject;
    private static final String WIKI_LOGO = "id:org.wikipedia:id/main_toolbar_wordmark";
    private static final String ACCEPT_BUTTON = "id:org.wikipedia:id/acceptButton";

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = new AndroidOnboardingPageObject(driver);
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

        onboardingPageObject.waitForElementAndClick(ACCEPT_BUTTON, "Element is not clickable");
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElement(WIKI_LOGO, "Mainpage is not opened",5);
    }
}
