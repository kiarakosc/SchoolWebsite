import org.example.EshopPage;
import org.example.Factory;
import org.junit.jupiter.api.Test;

public class EshopPageTest extends Factory {

    @Test
    void orderingItemFromEshop(){
        EshopPage eshopPage = new EshopPage(page);

        eshopPage.searchItem("pero");
        eshopPage.addItemToCart();
        eshopPage.fillOrderInformation("Kiara", "Kosc", "kiara.kosc@gmail.com","0944194343",  "Moyzesova 56","Kosice", "04001", "2927");

        System.out.println(eshopPage.isOrderSuccessful());
    }
}
