package lib.ui.factories;

import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.ArticlePageObject;
import lib.ui.IOS.IOSArticlePageObject;
import lib.ui.mobileWeb.MobWebArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSArticlePageObject(driver);
        } else {
            return new MobWebArticlePageObject(driver);
        }
    }
}
