import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    LoginPage loginPage;

    @Parameters({"username","password"})
    @Test
    public void verifyUserLoginWithCorrectCredentials(String username, String password){
        reportUtility.createATestCase("Verify user Login with correct credentials");
        reportUtility.addTestLog(Status.INFO,"Performing Login");
        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(username,password);
        String expectedTitle = "Guru99 Bank Manager HomePage";
        String actualTitle = cmnDriver.getTitleOfPage();
        reportUtility.addTestLog(Status.INFO,"Comparing expected and actual titles");
        Assert.assertEquals(actualTitle,expectedTitle);
    }
}
