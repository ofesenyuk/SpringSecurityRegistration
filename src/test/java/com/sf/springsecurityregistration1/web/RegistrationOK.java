package com.sf.springsecurityregistration1.web;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class RegistrationOK {
    public static void main(String[] args) throws Exception {
        FirefoxDriver wd;
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8080/SpringSecurityRegistration1");
        wd.findElement(By.cssSelector("html")).click();
        wd.findElement(By.linkText("Регистрация.")).click();
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("S");
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("Sasha");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("123456");
        wd.findElement(By.id("matchingPassword")).click();
        wd.findElement(By.id("matchingPassword")).clear();
        wd.findElement(By.id("matchingPassword")).sendKeys("123456");
        wd.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        if (!wd.findElement(By.tagName("html")).getText()
                .contains("You are successfully registered")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys("Sasha");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys("123456");
        new Actions(wd).doubleClick(wd.findElement(By.name("submit"))).build().perform();
        if (!wd.findElement(By.tagName("html")).getText().contains("Пользователь : Sasha | Выход")) {
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
