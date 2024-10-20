import org.example.Factory;
import org.example.LoginPage;
import org.junit.jupiter.api.Test;



public class LoginPageTest extends Factory {


    @Test
    void logInAisWithRightCredentials() {
        // Create instance of LoginPage and perform login actions
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("5393452", "UpJs2524#*");

        // Verify login was successful
        System.out.println(loginPage.isLoginSuccessful());

    }
}
