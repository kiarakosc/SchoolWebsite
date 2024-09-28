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
        page.fill("#heslo", "7854");
        //assertEquals("Clicked", page.evaluate("result"));
    }

    @Test
    void orderFromEshop() {
        //  page.locator("a[href='https://eshop.upjs.sk/']").click();
        page.locator("div.header-custom-links a:has-text('E-shop')").click();


        Locator parentDiv = page.locator("div.row");
        Locator childDivs = parentDiv.locator("div.product-layout");
        System.out.println(childDivs);


        List<ElementHandle> divList = childDivs.elementHandles();
        System.out.println(divList.size());
        // divList.get(0).click();
        // page.waitForTimeout(10000);

    }

    @Test
    void calendar() {
        page.locator("#dropdownMenuButton9502").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta']").click();
        page.locator("#dropdownMenuButton678").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta/studium/harm/']:text('Harmonogram akademického roka')").click();
        page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        page.locator("#download").click();


        Download download = page.waitForDownload(() -> {

        });


        Path downloadedFilePath = download.path();

        if (downloadedFilePath != null && downloadedFilePath.toFile().exists()) {
            System.out.println("Download successful: " + downloadedFilePath.toString());
        } else {
            System.out.println("Download failed.");
        }


    }

    @Test
    void findingMail() {
        page.locator("a[href='https://ais2.upjs.sk/ais/start.do']").click();
        page.fill("#login", "5393452");
        page.fill("#heslo", "7854");
        //assertEquals("Clicked", page.evaluate("result"));
    }

}