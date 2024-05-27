package iOS;

import lib.IOSCoreTestCase;
import lib.ui.IOSOnboardingPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class IOSOnboardingTests extends IOSCoreTestCase {

    private static final String WIKI_LOGO = "id:wikipedia";

    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testPassOnboarding(){

        IOSOnboardingPageObject iosOnboardingPage = new IOSOnboardingPageObject(driver);

        iosOnboardingPage.waitForLearnMoreLink();
        iosOnboardingPage.clickNextButton();

        iosOnboardingPage.waitForNewWayToExploreText();
        iosOnboardingPage.clickNextButton();

        iosOnboardingPage.waitForSearchInNearlyText();
        iosOnboardingPage.clickNextButton();

        iosOnboardingPage.waitForHelpMakeAppText();
        iosOnboardingPage.clickGetStartedButton();

        iosOnboardingPage.waitForElement(WIKI_LOGO, "Mainpage is not opened",5);
    }
}
