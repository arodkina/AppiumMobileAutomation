package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.factories.OnboardingPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import static lib.ui.OnboardingPageObject.*;

public class OnboardingTests extends CoreTestCase {
    private OnboardingPageObject onboardingPageObject;
    private static final String WIKI_LOGO = "id:org.wikipedia:id/main_toolbar_wordmark";

    public void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
    }

    @Test
    @Epic("Tests for Onboarding")
    @Features(value = {@Feature(value="Onboarding")})
    @DisplayName("Swipe onboarding screens")
    @Step("Starting test testSwipeOnboarding")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSwipeOnboarding(){
        String firstTitle = onboardingPageObject.getOnboardingTitle(ONBOARDING_TITLE);

        if (Platform.getInstance().isAndroid()) {
            Assert.assertEquals("Wrong title is shown", "The Free Encyclopedia\n" +
                    "…in over 300 languages", firstTitle);
        } else {
            Assert.assertEquals("Wrong title is shown", "The free encyclopedia", firstTitle);
        }

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String secondTitle = onboardingPageObject.getOnboardingTitle(NEW_WAYS_TEXT);
        Assert.assertEquals("Wrong title is shown", "New ways to explore", secondTitle);

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String thirdTitle = onboardingPageObject.getOnboardingTitle(SEARCH_IN_NEARLY_TEXT);
        Assert.assertEquals("Wrong title is shown", "Reading lists with sync", thirdTitle);

        onboardingPageObject.swipeOnboardingScreenToLeft();
        String fourthTitle = onboardingPageObject.getOnboardingTitle(HELP_MAKE_APP_TEXT);
        Assert.assertEquals("Wrong title is shown", "Send anonymous data", fourthTitle);

        onboardingPageObject.waitForElementAndClick(GET_STARTED_BUTTON, "Element is not clickable");
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElement(WIKI_LOGO, "Mainpage is not opened",10);
    }

    @Test
    @Epic("Tests for Onboarding")
    @Features(value = {@Feature(value="Onboarding")})
    @DisplayName("Passing onboarding screens")
    @Step("Starting test testPassOnboarding")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassOnboarding(){

        if (Platform.getInstance().isAndroid() || (Platform.getInstance().isMobWeb())){
            return;
        }

        onboardingPageObject.waitForLearnMoreLink();
        onboardingPageObject.clickNextButton();

        onboardingPageObject.waitForNewWayToExploreText();
        onboardingPageObject.clickNextButton();

        onboardingPageObject.waitForSearchInNearlyText();
        onboardingPageObject.clickNextButton();

        onboardingPageObject.waitForHelpMakeAppText();
        onboardingPageObject.clickGetStartedButton();

        onboardingPageObject.waitForElement(WIKI_LOGO, "Mainpage is not opened",5);
    }
}
