package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement userNameInput=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordInput=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

        userNameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        File directory = new File("screenshots");
        if(!directory.exists()){
            directory.mkdir();
        }

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File targetFile=new File("screenshots/saucedemo_screenshot.png");

        Files.copy(srcFile.toPath(),targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Screenshot taken successfully"+targetFile.getAbsolutePath());

        driver.quit();
    }
}