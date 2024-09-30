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


    //pridat assert
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


         page.waitForTimeout(10000);

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

    @Test
    void findingMail() {
        page.locator("#search-query-input").click();
        page.locator("#search-scope-employees").click();
        page.fill("#search-query-input", "Juraj Sebej");
        page.locator("#search-query-submit").click();

        Locator container = page.locator("div.overflow-hidden");
        Locator trElements = container.locator("tr");
        List<String> trTexts = trElements.allTextContents();
        System.out.println(trTexts.size());
//        for (ElementHandle row : rows) {
//
//            ElementHandle emailCell = row.querySelector("td:nth-child(2)");
//
//
//            String email = emailCell.textContent();
//            System.out.println(email);
//        }

        page.waitForTimeout(10000);
    }

}