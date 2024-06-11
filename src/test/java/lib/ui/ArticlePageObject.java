package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElement(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options"
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list"
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay"
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button"
        );
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMobWeb()) {
            this.removeArticleFromSavedIfItAdded();
        }

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list");
    }

    public void removeArticleFromSavedIfItAdded()
    {
        if (this.isElementDisplayed(OPTION_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved"
            );
            this.waitForElement(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before."
            );
        }
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link"
            );
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}