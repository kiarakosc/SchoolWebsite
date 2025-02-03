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
    public void giveAllProducts() {

        // Send a GET request to the endpoint that returns all products.
        APIResponse response = apiRequest.get("/products");

        // Verify the HTTP response status. 200 (OK) is expected.
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        // The response object is an instance of the APIResponse class.
        // Convert the response to a string for further processing.
        String responseBody = response.text();
        System.out.println(responseBody);

        // Parse the response body into a JSON array.
        JsonArray products = JsonParser.parseString(responseBody).getAsJsonArray();

        // Verify that the size of the JSON array matches the expected size (20).
        Assertions.assertEquals(20, products.size(), "Size should be 20");
    }

    // Test case: Verify that the correct product is returned based on the given ID.
    @Test
    public void giveSpecificProductWhichExists() {
        // Send a GET request to the endpoint to fetch the desired product by ID (ID = 8).
        APIResponse response = apiRequest.get("/products/8");

        // Verify that the HTTP response status is 200 (OK) to ensure the product exists and the test can proceed.
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        // Convert the response body to a string for further processing.
        // The APIResponse object does not directly support JSON operations.
        String responseBody = response.text();

        // Convert the responseBody string to a JsonObject.
        // A JsonObject allows interaction with specific fields ( title, id, ...) in the JSON response.
        JsonObject product = JsonParser.parseString(responseBody).getAsJsonObject();

        // Verify that the product's title matches the expected value.
        // I assumed that the "title" attribute is unique for each product.
        Assertions.assertEquals(
                "Pierced Owl Rose Gold Plated Stainless Steel Double",
                product.get("title").getAsString(),
                "The product title should match the expected value"
        );
    }


    // Note: For some reason, this website returns a 200 status even when the product does not exist.
    // Test case: Verify that the response returns a 404 status when the product does not exist.
    @Test
    public void getNonExistentProduct() {
        APIResponse response = apiRequest.get("/products/90");

        Assertions.assertEquals(404, response.status(), "Status code is 404");
    }


    // Test case: Replace the product at an existing ID.
    @Test
    public void replaceProduct() {
        // Create a new JSON object to represent the replacement product.
        JsonObject product = getTestProductJson();

        // Specify headers to inform the server about the request's content type.
        // The "Content-Type: application/json" header tells the server that the request body is in JSON format.
        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(product.toString());

        // Use the PUT method to replace the existing product at ID 1 with the new JSON object.
        APIResponse response = apiRequest.put("/products/1", requestOptions);

        // Check the response status code to ensure the replacement was successful.
        Assertions.assertEquals(200, response.status(), "Status code should be 200");
    }

    public void getProduct() {
        // Send a GET request to retrieve details of the chosen product (ID = 6).
        APIResponse response = apiRequest.get("/products/6");

        // Check if the response status is 200 (OK) to ensure the product exists and the test can continue.
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        //return product;
    }

    // Test case: Change the title of an existing product.
    @Test
    public void changingProductTitleForExistingProductReturnSuccess() {
        // Send a GET request to retrieve details of the chosen product (ID = 6).
        APIResponse response = apiRequest.get("/products/6");

        // Convert the response body to a string for further processing.
        String responseBody = response.text();

        // Parse the response body into a JSON object.
        JsonObject product = JsonParser.parseString(responseBody).getAsJsonObject();

        // Change the "title" field of the product to "ITEM01".
        RequestOptions requestOptions = getRequestOptions(product);

        // Send a PUT request to update the product with the modified JSON object.
        APIResponse putResponse = apiRequest.put("/products/6", requestOptions);

        // Check if the response status for the PUT request is 200 (OK), indicating the update was successful.
        Assertions.assertEquals(200, putResponse.status(), "Status code for PUT should be 200");
    }


    private static RequestOptions getRequestOptions(final JsonObject product) {
        product.addProperty("title", "ITEM01");

        // Specify headers for the PUT request.
        return RequestOptions.create()
                             .setHeader("Content-Type", "application/json")
                             .setData(product.toString());
    }


    // Test case: Add a new product with a new ID.
    @Test
    public void addProductWith() {
        // Create a JSON object to represent the new product.
        JsonObject product = getTestProductJson();

        // Specify the headers for the request.
        RequestOptions requestOptions = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(product.toString());

        // Send a POST request to add the new product.
        APIResponse response = apiRequest.post("/products", requestOptions);


        System.out.println(response.status());
        System.out.println(response.text());

        // Check the response status to ensure the product was successfully added.
        Assertions.assertEquals(200, response.status(), "Status code should be 200");
    }


    // Test case: Delete an existing product.
    @Test
    public void deleteProduct() {

        // Note: The website does not persist changes, so this example assumes the product already exists.
        // Uncomment the following code to add a product if needed for testing.
    /*
    JsonObject product = new JsonObject();
    product.addProperty("title", "Mens Casual Slim Fit");
    product.addProperty("price", 15.99);
    product.addProperty("description", "New Item");
    product.addProperty("category", "men's clothing");
    product.addProperty("image", "https://fakestoreapi.com/img/71YXze0UsLL._AC_UY879_.jpg");
    product.addProperty("rate", 2.1);
    product.addProperty("count", 430);

    RequestOptions requestOptions = RequestOptions.create()
            .setHeader("Content-Type", "application/json")
            .setData(product.toString());

    APIResponse response = apiRequest.post("/products", requestOptions);
    System.out.println(response.status());
    System.out.println(response.text());
    Assertions.assertEquals(200, response.status(), "Status code should be 200");
    */

        // Endpoint for deleting a product with ID = 20.
        APIResponse response = apiRequest.delete("/products/20");


        System.out.println(response.status());

        // Check if the delete operation was successful (status code 200 is expected).
        Assertions.assertEquals(200, response.status(), "Status code should be 200");

        // Verify the product is no longer accessible.
        response = apiRequest.get("/products/20");
        System.out.println(response.status());
    }

    private static JsonObject getTestProductJson() {
        JsonObject product = new JsonObject();

        // Add properties to the JSON object.
        product.addProperty("title", "Mens Casual Slim Fit");
        product.addProperty("price", 15.99);
        product.addProperty("description", "hello");
        product.addProperty("category", "men's clothing");
        product.addProperty("image", "https://fakestoreapi.com/img/71YXze0UsLL._AC_UY879_.jpg");
        product.addProperty("rate", 2.1);
        product.addProperty("count", 430);
        return product;
    }

    @AfterAll
    public static void teardown() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
