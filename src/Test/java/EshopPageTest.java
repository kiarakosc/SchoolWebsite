import com.microsoft.playwright.*;
import org.example.EshopPage;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EshopPageTest {

    static Playwright playwright;
    public static Browser browser;

    BrowserContext context;
    protected  Page page;

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
    public void isOrderSuccessful() {
        assertThat(page).hasURL("https://eshop.upjs.sk/index.php?route=checkout/success");
    }


    @Test
    void orderingItemFromEshop(){
        EshopPage eshopPage = new EshopPage(page);

        eshopPage.searchItem("pero");
        eshopPage.addItemToCart();
        eshopPage.fillOrderInformation("Kiara", "Kosc", "kiara.kosc@gmail.com","0944194343",  "Moyzesova 56","Kosice", "04001", "2927");
        isOrderSuccessful();
    }
}
