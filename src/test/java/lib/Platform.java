package lib;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String IOS_PLATFORM = "ios";
    private static final String ANDROID_PLATFORM = "android";
    private static final String APPIUM_URL= "http://127.0.0.1:4723/";

    private static Platform instance;
    private Platform(){}
    public static Platform getInstance(){
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AppiumDriver(url, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new AppiumDriver(url, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Unsupported platform: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(ANDROID_PLATFORM);
    }

    public boolean isIOS(){
        return isPlatform(IOS_PLATFORM);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/arodkina/Desktop/AppiumMobileAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15 Pro");
        capabilities.setCapability("platformVersion", "17.5");
        capabilities.setCapability("app", "/Users/arodkina/Desktop/AppiumMobileAutomation/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "XCUITest");

        return capabilities;
    }

    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }


}
