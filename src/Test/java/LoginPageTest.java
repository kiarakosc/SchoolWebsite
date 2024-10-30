import com.microsoft.playwright.*;
import org.example.LoginPage;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPageTest {

    static Playwright playwright;
    public static Browser browser;

    BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://www.upjs.sk/");
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    //Assert was added.
    // Checks if the login was successful by verifying the URL of the current page
    public void isLoginSuccessful() {
        assertThat(page).hasURL("https://ais2.upjs.sk/ais/apps/student/sk/");

    }

    @Test
    void logInAisWithRightCredentials() {
        // Create instance of LoginPage and perform login actions
        LoginPage loginPage = new LoginPage(page);


        loginPage.navigateToLoginPage();
        loginPage.login("5393452", "UpJs2524#*");
        isLoginSuccessful();

    }
}
