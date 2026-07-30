package tests;

import base.BaseTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @DataProvider(name="invalidLoginData")
    public Object[][] invalidLoginData(){
        return new Object[][]{
                {"locked_out_user","secret_sauce","locked out"},
                {"standard_user","wrong_password","do not match"},
                {"","secret_sauce","Username is required"},
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginWithInvalidCredentials_shouldShowError(String username, String password, String expectedError){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError));
    }
}