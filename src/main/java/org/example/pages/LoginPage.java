package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By userNameInput=By.id("user-name");
    private By passwordInput=By.id("password");
    private By loginButton=By.id("login-button");

    private By errorMessage=By.cssSelector("[data-test='error']");

    @Step("Kullanıcı adı girildi: {userName}")
    public void enterUserName(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput)).sendKeys(userName);
    }

    @Step("Şifre girildi")
    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Giriş yap butonuna tıklandı")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Login yapıldı")
    public void login(String username,String password){
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}