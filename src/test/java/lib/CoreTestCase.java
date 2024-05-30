package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase{
    protected AppiumDriver driver;
    private static String URL= "http://127.0.0.1:4723/";
    private static String IOS_PLATFORM = "ios";
    private static String ANDROID_PLATFORM = "android";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds){
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}



