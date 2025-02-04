package org.example;
import com.microsoft.playwright.*;



public class MainPage {

    private Page page;

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

    // In this method, after clicking on the locator, a new tab in the browser is opened, which becomes a new instance of the Page class.
    // Since this will be used in another method, I will access the new instance without creating
    // a global parameter by calling it from the navigation method.
    public Page clickOpenHarmonogramButton() {
        Page newPage = page.waitForPopup(() -> {
            page.locator("//h6[contains(text(), 'Harmonogram akademického roka 2024/2025')]").click();
        });

        //Waiting for new tab to be fully load.
        newPage.waitForLoadState();
        return newPage;
    }


    public void findCalendar() {
        clickoUniverziteButton();
        clickPrirodovedeckaFakultaButton();
        clickStudentiButton();
        clickHarmonogramButton();
        clickOpenHarmonogramButton();
        //page.waitForTimeout(1000);

    }


}
