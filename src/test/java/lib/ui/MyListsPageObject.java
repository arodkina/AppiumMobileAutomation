package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME,
            ARTICLE_BY_TITLE,
            ARTICLES_LIST,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedAricleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getSavedAricleXpathByTitle(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder
        );

    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedAricleXpathByTitle(article_title);
        this.waitForElement(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }


    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedAricleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedAricleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved."
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMobWeb()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getSavedArticleCount() {
        List<WebElement> articles = this.waitForElements(ARTICLES_LIST, "No list of articles present", 10);
        return articles.size();
    }
}