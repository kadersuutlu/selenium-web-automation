package org.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

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
