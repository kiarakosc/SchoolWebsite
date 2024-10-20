package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

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


        page.locator("xpath=//div[contains(@class, 'row')]//div[contains(@class, 'product-layout')][1]//div[contains(@class, 'image')]/a").click();
        page.locator("#button-cart").click();
        page.locator("#cart-total").click();
        page.locator("a:has-text('Pokladňa')").click();

        page.locator("label:has-text('Nákup bez registrácie') input[type='radio']").click();
        page.waitForTimeout(100);
        page.locator("#button-account").first().click();
        page.fill("#input-payment-firstname", "Kiara");
        page.fill("#input-payment-lastname", "Kosc");
        page.fill("#input-payment-email", "kiara.kosc@gmail.com");
        page.fill("#input-payment-telephone", "0944194343");
        page.fill("#input-payment-address-1", "Moyzesova 56");
        page.fill("#input-payment-city", "Kosice");
        page.fill("#input-payment-postcode", "04001");
        page.locator("#input-payment-zone").selectOption("2927");
        page.waitForTimeout(100);
        page.locator("#button-guest").first().click();
        page.locator("#button-shipping-method").first().click();
        page.locator("input[name='agree']").click();
        page.locator("#button-payment-method").click();
        page.locator("#button-confirm").click();


        page.waitForTimeout(1000);

        assertEquals("https://eshop.upjs.sk/index.php?route=checkout/success", page.url());


    }

    @Test
    void calendar() {
        page.locator("#dropdownMenuButton9502").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta']").click();
        page.locator("#dropdownMenuButton678").click();
        page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta/studium/harm/']:text('Harmonogram akademického roka')").click();

        page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();

        Page newPage = page.waitForPopup(() -> {
            page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        });
        newPage.waitForLoadState();

        assertEquals("https://intranet.upjs.sk/op/op.Public.php?documentid=9351", newPage.url());


    }


    @Test
    void findingMail() {
        page.locator("#search-query-input").click();
        page.locator("#search-scope-employees").click();
        page.fill("#search-query-input", "Juraj Sebej");
        page.locator("#search-query-submit").click();

        page.locator("xpath=//table[@class='min-w-full']//tr[1]/td[2]").click();


//        page.waitForSelector("//table[@class='table table-sm borderless']//tr[1]/td[3]/a");
//        //Locator mail = page.locator("table.table-sm.borderless td a[href^='mailto:']");
//        Locator mail = page.locator("xpath=//table[@class='table table-sm borderless']//tr[1]/td[3]/a");
//        String email = mail.innerText();
//        System.out.println(email);


        System.out.println(page.locator("//table[@class='table table-sm borderless']//tr[1]/td[3]/a").textContent());


    }

}