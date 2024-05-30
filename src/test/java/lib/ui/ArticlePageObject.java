package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public static String TITLE;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle() {
        WebElement title = waitForElement(TITLE, "Cannot find title");
        if (Platform.getInstance().isAndroid()) {
            return title.getText();
    }
        else {
        return title.getAttribute("name");
        }
    }
}
