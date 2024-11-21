//package org.example;
//
//import com.microsoft.playwright.*;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//
//
//
//public class Factory {
//
//    static Playwright playwright;
//    public static Browser browser;
//
//    BrowserContext context;
//    protected Page page;
//
//    @BeforeAll
//    static void launchBrowser() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//    }
//
//    @AfterAll
//    static void closeBrowser() {
//        playwright.close();
//    }
//
//    @BeforeEach
//    void createContextAndPage() {
//        context = browser.newContext();
//        page = context.newPage();
//        page.navigate("https://www.upjs.sk/");
//    }
//
//    @AfterEach
//    void closeContext() {
//        context.close();
//    }
//
//}
