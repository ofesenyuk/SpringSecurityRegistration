package com.sf.springsecurityregistration1.web;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class RegistrationExistingUser {
    public static void main(String[] args) throws Exception {
        FirefoxDriver wd;
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8080/SpringSecurityRegistration1/");
        wd.findElement(By.linkText("Регистрация.")).click();
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("Sasha");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("123456");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys();
        wd.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        if (!wd.findElement(By.tagName("html")).getText().contains("size must be between 6 and 45")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("123456");
        new Actions(wd).doubleClick(wd.findElement(By.cssSelector("button[type=\"submit\"]"))).build().perform();
        if (!wd.findElement(By.tagName("html")).getText().contains("Passwords dont match")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.findElement(By.id("matchingPassword")).click();
        wd.findElement(By.id("matchingPassword")).clear();
        wd.findElement(By.id("matchingPassword")).sendKeys("123456");
        wd.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        if (!wd.findElement(By.tagName("html")).getText().contains("There is already user with such username in system")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
