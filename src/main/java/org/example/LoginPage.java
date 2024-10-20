package org.example;
import com.microsoft.playwright.*;


// Represents the login page and the operations that can be performed on it
public class LoginPage {
    //Why page needs to be private? encapsulation
    private Page page;

    //Locators for elements

    //Locator for button which will redirects to Ais web Login
    private Locator Ais2Button;
    //Submit button for Loginning to Ais web app
    private Locator submitButton;
    //Locators for fields to input Login credentials
    private Locator loginInputSelectorName;
    private Locator loginInputSelectorPassword;


    // Constructor that initializes the page and locators
    public LoginPage(Page page) {
        this.page = page;

        Ais2Button = this.page.locator("a[href='https://ais2.upjs.sk/ais/start.do']");
        submitButton = this.page.locator("#login-form-submit-btn");
        loginInputSelectorName = this.page.locator("#login");
        loginInputSelectorPassword = this.page.locator("#heslo");
    }


    public void navigateToLoginPage() {
        Ais2Button.click();
    }

    public void enterUsername(String username) {
        loginInputSelectorName.fill(username);
    }

    public void enterPassword(String password) {
       loginInputSelectorPassword.fill(password);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmitButton();
    }

}