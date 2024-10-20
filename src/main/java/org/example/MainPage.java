package org.example;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;


public class MainPage extends Factory {
    private Page page;
    private Page newPage;

    private Locator oUniverziteButton;
    private Locator prirodovedeckaFakultaButton;
    private Locator studentiButton;
    private Locator harmonogramButton;
    private Locator openHarmonogramButton;

    public MainPage(Page page) {
        this.page = page;

        oUniverziteButton = page.locator("#dropdownMenuButton9502");
        prirodovedeckaFakultaButton = page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta']");
        studentiButton = page.locator("#dropdownMenuButton678");
        harmonogramButton = page.locator("a[href='https://www.upjs.sk/prirodovedecka-fakulta/studium/harm/']:text('Harmonogram akademického roka')");
        openHarmonogramButton = page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]");

    }

    public void clickoUniverziteButton() {
        oUniverziteButton.click();
    }
    public void clickPrirodovedeckaFakultaButton() {
        prirodovedeckaFakultaButton.click();
    }
    public void clickStudentiButton() {
        studentiButton.click();
    }
    public void clickHarmonogramButton() {
        harmonogramButton.click();
    }
    public void clickOpenHarmonogramButton() {
        newPage = page.waitForPopup(() -> {
            page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        });

        newPage.navigate(newPage.url());
        newPage.waitForLoadState();
    }

    public boolean calendarIsSuccessfullyFound(){
        return newPage.url().contains("https://intranet.upjs.sk/op/op.Public.php?documentid=9351");
    }

    public void findCalendar(){
        clickoUniverziteButton();
        clickPrirodovedeckaFakultaButton();
        clickStudentiButton();
        clickHarmonogramButton();
        clickOpenHarmonogramButton();
    }



}
