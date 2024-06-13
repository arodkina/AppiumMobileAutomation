package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "css:.cdx-button.cdx-button--action-progressive",
            LOGIN_INPUT = "css:input#wpName1",
            PASSWORD_INPUT = "css:input#wpPassword1",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElement(LOGIN_BUTTON, "Cannot find auth button.", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button.");
    }


    @Step("Input login and password")
    public void enterLogInData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input.", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input.", 5);
    }

    @Step("Click submit")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button.");
    }
}
