package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import junit.framework.TestCase;
import java.time.Duration;

public class CoreTestCase extends TestCase{
    protected AppiumDriver driver;
    private static String URL= "http://127.0.0.1:4723/";
    private static String IOS_PLATFORM = "ios";
    private static String ANDROID_PLATFORM = "android";


    @Override
    protected void setUp() throws Exception {

        super.setUp();
        this.getDriver();
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(ANDROID_PLATFORM)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "14.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/arodkina/Desktop/AppiumMobileAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");
        } else if (platform.equals(IOS_PLATFORM)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 15 Pro");
            capabilities.setCapability("platformVersion", "17.5");
            capabilities.setCapability("app", "/Users/arodkina/Desktop/AppiumMobileAutomation/apks/Wikipedia.app");
            capabilities.setCapability("automationName", "XCUITest");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value = " + platform);
        }
        return capabilities;
    }

    private void getDriver() throws Exception {
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        String platform = System.getenv("PLATFORM");

        if (platform.equals(ANDROID_PLATFORM)) {
            driver = new AndroidDriver(new URL(URL), capabilities);
        } else if (platform.equals(IOS_PLATFORM)) {
            driver = new IOSDriver(new URL(URL), capabilities);
        } else {
            throw new Exception("Unsupported platform: " + platform);
        }
    }
}



