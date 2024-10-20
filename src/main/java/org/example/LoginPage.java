package org.example;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

// Represents the login page and the operations that can be performed on it
public class LoginPage {

    // Playwright Page object used to interact with the web page
    private Page page;

    //Why page needs to be private? encapsulation

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

        Ais2Button = page.locator("a[href='https://ais2.upjs.sk/ais/start.do']");
        submitButton = page.locator("#login-form-submit-btn");
        loginInputSelectorName = page.locator("#login");
        loginInputSelectorPassword = page.locator("#heslo");
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

    // Checks if the login was successful by verifying the URL of the current page
    public boolean isLoginSuccessful() {
        return page.url().contains("/ais/apps/student/sk/");
    }

}