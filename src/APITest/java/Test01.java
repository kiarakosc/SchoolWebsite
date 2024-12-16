import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Test01 {

    private static Playwright playwright;
    private static APIRequestContext apiRequest;


    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        //Kontext API je ako sandbox, v ktorom sa vykonaaju API poziadavky. Je tu moznost nastavit nejake parametre, ktore
        //potom budu pouzite pre vsetky poziadavky. Umoznuje oddelit rozne testy.
        //parametre staci nastavit len raz
        //nový objekt typu NewContextOptions, ktorý slúži na nastavenie kontextu pre API požiadavky

        //NewContextOptions je objekt, ktorý obsahuje nastavenia pre vytvorenie nového API kontextu (APIRequestContext).
        // Tento objekt slúži iba na uchovanie informácií, ktoré sa použijú pri vytvorení kontextu.
        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions()
                //nastavuje zakladnu url pre API poziadavky
                .setBaseURL("https://fakestoreapi.com");
        apiRequest = playwright.request().newContext(options);
    }


    // Test case: Verify that all products are fetched successfully.
    @Test
    public void verifyTheNumberOfProductsViaGET() {

        APIResponse response = apiRequest.get("/products");
        String responseBody = response.text();
        JsonArray products = JsonParser.parseString(responseBody).getAsJsonArray();

        Assertions.assertEquals(20, products.size(), "Size is not 20");
    }

    // Method provides product data for parameterized tests.
    static Stream<Arguments> provideProductData() {
        return Stream.of(
                Arguments.of("8", "Pierced Owl Rose Gold Plated Stainless Steel Double"),
                Arguments.of("9", "WD 2TB Elements Portable External Hard Drive - USB 3.0 "),
                Arguments.of("10", "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s")
        );
    }

    // Test case: Verify that the correct product is returned based on the given ID.
    @ParameterizedTest
    @MethodSource("provideProductData")
    void getSpecificProductWhichExists(String productID, String expectedTitle) {

        APIResponse response = apiRequest.get("/products/" + productID);
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        String responseBody = response.text();
        JsonObject product = JsonParser.parseString(responseBody).getAsJsonObject();

        // Verify that the product's title matches the expected value.
        Assertions.assertEquals(
                expectedTitle,
                product.get("title").getAsString());
    }

    // Method provides product data for parameterized tests.
    private static Stream<JsonObject> productsInformations() {
        JsonObject product1 = new JsonObject();
        product1.addProperty("title", "Mens Casual Slim Fit");
        product1.addProperty("price", 15.99);
        product1.addProperty("description", "hello");
        product1.addProperty("category", "men's clothing");
        product1.addProperty("image", "https://fakestoreapi.com/img/71YXze0UsLL._AC_UY879_.jpg");
        product1.addProperty("rate", 2.1);
        product1.addProperty("count", 430);

        JsonObject product2 = new JsonObject();
        product2.addProperty("title", "Womens Leather Jacket");
        product2.addProperty("price", 49.99);
        product2.addProperty("description", "Nice leather jacket");
        product2.addProperty("category", "women's clothing");
        product2.addProperty("image", "https://fakestoreapi.com/img/someImage.jpg");
        product2.addProperty("rate", 4.5);
        product2.addProperty("count", 150);

        return Stream.of(product1, product2);
    }

    // Test case: Replace the product at an existing ID.
    @ParameterizedTest
    @MethodSource("productsInformations")
    public void replaceExistingProductWithNewProductAtSameID(JsonObject product) {

        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(product.toString());

        APIResponse response = apiRequest.put("/products/1", requestOptions);

        Assertions.assertEquals(200, response.status(), "Status code should be 200");
    }


    @ParameterizedTest
    @MethodSource("productsInformations")
    public void addNewProductWithAtNewID(JsonObject newProduct) {

        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(newProduct.toString());

        APIResponse responseOfPostRequest = apiRequest.post("/products", requestOptions);

        Assertions.assertEquals(200, responseOfPostRequest.status(), "Status code should be 200");
    }


    // Note: For some reason, this website returns a 200 status even when the product does not exist.
    // Test case: Verify that the response returns a 400 status when the product does not exist.
    @Test
    public void getNonExistentProduct() {

        APIResponse response = apiRequest.get("/products/90");
        Assertions.assertEquals(400, response.status(), "Status code should be 400");
    }

    // Note: The website does not persist changes, so this example assumes the product already exists.
    @Test
    public void deleteExistingProduct() {

        APIResponse response = apiRequest.delete("/products/20");
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

    }


    @AfterAll
    public static void teardown() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
