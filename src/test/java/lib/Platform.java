package lib;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String IOS_PLATFORM = "ios";
    private static final String ANDROID_PLATFORM = "android";
    private static final String APPIUM_URL= "http://127.0.0.1:4723/";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    private static Platform instance;
    private Platform(){}
    public static Platform getInstance(){
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AppiumDriver(url, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new AppiumDriver(url, this.getIOSDesiredCapabilities());
        } else if (this.isMobWeb()) {
            return new ChromeDriver(this.getMobWebChromeOPtions());
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

    public boolean isMobWeb(){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "nexus");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", " ");

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

    private ChromeOptions getMobWebChromeOPtions() {
        Map <String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map <String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=360,640");

        return chromeOptions;
    }

    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }


}
