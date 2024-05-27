package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;


public class IOSCoreTestCase extends TestCase{
    protected AppiumDriver driver;
    private static String URL= "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15 Pro");
        capabilities.setCapability("platformVersion", "17.5");
        capabilities.setCapability("app", "/Users/arodkina/Desktop/AppiumMobileAutomation/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "XCUITest");

        driver = new IOSDriver(new URL(URL), capabilities);
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



