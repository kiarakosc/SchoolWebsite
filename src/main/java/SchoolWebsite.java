import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolWebsite {
    static Playwright playwright;
    static Browser browser;


    BrowserContext context;
    Page page;

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



    @Test
    void logInAis() {
        page.locator("a[href='https://ais2.upjs.sk/ais/start.do']").click();
        page.fill("#login", "5393452");
        page.fill("#heslo", "UpJs2524#*");
        page.locator("#login-form-submit-btn").click();
        assertEquals("https://ais2.upjs.sk/ais/apps/student/sk/", page.url());

    }


    @Test
    void orderFromEshop() {
        page.locator("div.header-custom-links a:has-text('E-shop')").click();
        page.fill("input[name='search']", "pero");
        page.locator("button.btn.btn-default.btn-lg").click();

        //page.locator("xpath=//div[contains(., 'Guľôčkové pero s názvom univerzity')]").click();
        page.locator("xpath=//div[contains(@class, 'row')]//div[contains(@class, 'product-layout')][1]//div[contains(@class, 'image')]/a").click();
        page.locator("#button-cart").click();
        page.locator("#cart-total").click();
        page.locator("a:has-text('Pokladňa')").click();

        page.locator("label:has-text('Nákup bez registrácie') input[type='radio']").click();
        page.locator("input[value='Pokračovať']").first().click();







        page.waitForTimeout(100000);

    }

    @Test
    void calendar() {
        page.locator("#dropdownMenuButton9502").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta']").click();
        page.locator("#dropdownMenuButton678").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta/studium/harm/']:text('Harmonogram akademického roka')").click();


        page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        Page newPage = context.waitForPage(() -> {
            page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        });

        assertEquals("https://intranet.upjs.sk/op/op.Public.php?documentid=9351", newPage.url());


    }

    //vybrat prvy objekt
    @Test
    void findingMail() {
        page.locator("#search-query-input").click();
        page.locator("#search-scope-employees").click();
        page.fill("#search-query-input", "Juraj Sebej");
        page.locator("#search-query-submit").click();

        page.locator("xpath=//table[@class='min-w-full']//tr[1]/td[2]").click();




        page.waitForTimeout(10000);
    }

}