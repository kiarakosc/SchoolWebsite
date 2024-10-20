import com.microsoft.playwright.*;
import org.example.MainPage;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class MainPageTest {

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
    public void calendarIsSuccessfullyFound() {
        MainPage mainpage = new MainPage(page);

        // This will call the navigation method and save the instance of the new tab to this parameter.
        // It is of type Page because assertThat expects only Page, Locator, or APIResponse.
        Page newPage = mainpage.clickOpenHarmonogramButton();
        assertThat(newPage).hasURL("https://intranet.upjs.sk/op/op.Public.php?documentid=9351");
    }

    @Test
    void findCurrentCalendarForSchoolYear(){
        MainPage mainpage = new MainPage(page);

        mainpage.findCalendar();
        calendarIsSuccessfullyFound();

    }


}
