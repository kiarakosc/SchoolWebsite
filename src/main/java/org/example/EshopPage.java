package org.example;
import com.microsoft.playwright.*;

public class EshopPage {

    private Page page;

    private Locator eshopButton;
    private Locator searchingBar;
    private Locator searchButton;
    private Locator firstProduct;
    private Locator cartButton;
    private Locator totalCartButton;
    private Locator pokladnaButton;
    private Locator registrationRadioButton;
    private Locator firstContinueButton;
    private Locator firstName;
    private Locator lastName;
    private Locator email;
    private Locator telephone;
    private Locator address;
    private Locator city;
    private Locator postCode;
    private Locator zone;
    private Locator secondContinueButton;
    private Locator shippingMethodButton;
    private Locator agreementButton;
    private Locator paymentMethodButton;
    private Locator confirmButton;

    public EshopPage(Page page) {

        this.page = page;

        eshopButton = page.locator("div.header-custom-links a:has-text('E-shop')");
        searchingBar = page.locator("input[name='search']");
        searchButton = page.locator("button.btn.btn-default.btn-lg");
        firstProduct = page.locator("xpath=//div[contains(@class, 'row')]//div[contains(@class, 'product-layout')][1]//div[contains(@class, 'image')]/a");
        cartButton = page.locator("#button-cart");
        totalCartButton = page.locator("#cart-total");
        pokladnaButton = page.locator("a:has-text('Pokladňa')");
        registrationRadioButton = page.locator("label:has-text('Nákup bez registrácie') input[type='radio']");
        firstContinueButton = page.locator("#button-account");
        firstName = page.locator("#input-payment-firstname");
        lastName = page.locator("#input-payment-lastname");
        email = page.locator("#input-payment-email");
        telephone = page.locator("#input-payment-telephone");
        address = page.locator("#input-payment-address-1");
        city = page.locator("#input-payment-city");
        postCode = page.locator("#input-payment-postcode");
        zone = page.locator("#input-payment-zone");
        secondContinueButton = page.locator("#button-guest");
        shippingMethodButton = page.locator("#button-shipping-method");
        agreementButton = page.locator("input[name='agree']");
        paymentMethodButton = page.locator("#button-payment-method");
        confirmButton = page.locator("#button-confirm");
    }

    public void navigateToEshop() {
        eshopButton.click();
    }

    public void navigateToSearchingBar(String searchingItem) {
        searchingBar.fill(searchingItem);
    }

    public void clickOnSearchButton() {
        searchButton.click();
    }

    public void clickOnFirstProduct() {
        firstProduct.click();
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    public void clickOnTotalCartButton() {
        totalCartButton.click();
    }

    public void clickOnPokladnaButton() {
        pokladnaButton.click();
    }

    public void clickOnRegistrationRadioButton() {
        registrationRadioButton.click();
    }

    //automated waiting command was added
    public void clickOnFirstContinueButton() {
        page.waitForTimeout(100);
        //firstContinueButton.waitFor();
        firstContinueButton.first().click();
    }

    public void fillFirstname(String firstname) {
        firstName.fill(firstname);

    }

    public void fillLastname(String lastname) {
        lastName.fill(lastname);
    }

    public void fillEmail(String email) {
        this.email.fill(email);
    }

    public void fillTelephone(String telephone) {
        this.telephone.fill(telephone);
    }

    public void fillAddress(String address) {
        this.address.fill(address);
    }

    public void fillCity(String city) {
        this.city.fill(city);
    }

    public void fillPostCode(String postCode) {
        this.postCode.fill(postCode);
    }

    public void fillZone(String zone) {
        this.zone.selectOption(zone);
    }

    //automated waiting command was added
    public void clickSecondContinueButton() {
        secondContinueButton.waitFor();
        secondContinueButton.first().click();
    }

    public void clickOnShippingMethodButton() {
        shippingMethodButton.click();
    }

    public void clickOnAgreementButton() {
        agreementButton.click();
    }

    public void clickOnPaymentMethodButton() {
        paymentMethodButton.click();
    }

    public void clickOnConfirmButton() {
        confirmButton.click();
    }


    // Searches for an item and navigates to its product page
    public void searchItem(String item) {
        navigateToEshop();
        navigateToSearchingBar(item);
        clickOnSearchButton();
        clickOnFirstProduct();

    }

    // Adds an item to the cart and proceeds to checkout
    public void addItemToCart() {
        clickOnCartButton();
        clickOnTotalCartButton();
        clickOnPokladnaButton();
    }

    // Fills in the order information
    public void fillOrderInformation(String firstname, String lastname, String emailAddress, String telephoneNumber, String address1, String cityName, String postCode, String optionZone) {
        clickOnRegistrationRadioButton();
        clickOnFirstContinueButton();
        fillFirstname(firstname);
        fillLastname(lastname);
        fillEmail(emailAddress);
        fillTelephone(telephoneNumber);
        fillAddress(address1);
        fillCity(cityName);
        fillPostCode(postCode);
        fillZone(optionZone);
        clickSecondContinueButton();
        clickOnShippingMethodButton();
        clickOnAgreementButton();
        //clickOnPaymentMethodButton();
        //clickOnConfirmButton();

    }


}
