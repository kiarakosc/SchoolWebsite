import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


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


    // Test case: Verify that the correct product is returned based on the given ID.
    @Test
    public void getSpecificProductWhichExists() {

        APIResponse response = apiRequest.get("/products/8");
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        String responseBody = response.text();
        JsonObject product = JsonParser.parseString(responseBody).getAsJsonObject();

        // Verify that the product's title matches the expected value.
        Assertions.assertEquals(
                "Pierced Owl Rose Gold Plated Stainless Steel Double",
                product.get("title").getAsString(),
                "The product title should match the expected value"
        );
    }


    // Note: For some reason, this website returns a 200 status even when the product does not exist.
    // Test case: Verify that the response returns a 400 status when the product does not exist.
    @Test
    public void getNonExistentProduct() {

        APIResponse response = apiRequest.get("/products/90");
        Assertions.assertEquals(400, response.status(), "Status code should be 400");
    }


    // Test case: Replace the product at an existing ID.
    @Test
    public void replaceExistingProductWithNewProductAtSameID() {

        JsonObject product = new JsonObject();

        product.addProperty("title", "Mens Casual Slim Fit");
        product.addProperty("price", 15.99);
        product.addProperty("description", "hello");
        product.addProperty("category", "men's clothing");
        product.addProperty("image", "https://fakestoreapi.com/img/71YXze0UsLL._AC_UY879_.jpg");
        product.addProperty("rate", 2.1);
        product.addProperty("count", 430);

        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(product.toString());

        APIResponse response = apiRequest.put("/products/1", requestOptions);

        Assertions.assertEquals(200, response.status(), "Status code should be 200");
    }


    // Test case: Change the title of an existing product.
    @Test
    public void changeTitleOnExistingProduct() {
        APIResponse response = apiRequest.get("/products/6");

        String responseBody = response.text();
        JsonObject product = JsonParser.parseString(responseBody).getAsJsonObject();

        product.addProperty("title", "ITEM01");

        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(product.toString());

        APIResponse putResponse = apiRequest.put("/products/6", requestOptions);

        Assertions.assertEquals(200, putResponse.status(), "Status code for PUT should be 200");
    }


    @Test
    public void addNewProductWithAtNewID() {

        JsonObject newProduct = new JsonObject();

        newProduct.addProperty("title", "Mens Casual Slim Fit");
        newProduct.addProperty("price", 15.99);
        newProduct.addProperty("description", "New Item");
        newProduct.addProperty("category", "men's clothing");
        newProduct.addProperty("image", "https://fakestoreapi.com/img/71YXze0UsLL._AC_UY879_.jpg");
        newProduct.addProperty("rate", 2.1);
        newProduct.addProperty("count", 430);

        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(newProduct.toString());

        APIResponse responseOfPostRequest = apiRequest.post("/products", requestOptions);

        Assertions.assertEquals(200, responseOfPostRequest.status(), "Status code should be 200");
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
