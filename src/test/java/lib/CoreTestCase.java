package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;
    private static String URL= "http://127.0.0.1:4723/";
    private static String IOS_PLATFORM = "ios";
    private static String ANDROID_PLATFORM = "android";

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiPageMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to Portrait mode")
    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to Landscape mode")
    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open wiki page for mobile web (do nothing for Android and ios)")
    protected void openWikiPageMobileWeb() {
        if (Platform.getInstance().isMobWeb()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background (do nothing for mobile web")
    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/enviroment.properties");
            props.setProperty("Enviroment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://allurereport.org/docs/#_environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure file");
            e.printStackTrace();
        }
    }
}



