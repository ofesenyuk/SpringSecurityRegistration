package com.sf.springsecurityregistration1.web;

import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class CreateDeleteAnnouncementJunit {
    FirefoxDriver wd;
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void CreateDeleteAnnouncementJunit() {
        wd.get("http://localhost:8080/SpringSecurityRegistration1/logout.html");
        wd.findElement(By.linkText("Регистрация.")).click();
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.xpath("//div[@id='login-box']/form/table/tbody/tr[1]/td[2]")).click();
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys("Sasha");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys("123456");
        wd.findElement(By.name("submit")).click();
        wd.findElement(By.linkText("Посмотреть объявления")).click();
        wd.findElement(By.linkText("Создать новое объявление")).click();
        wd.findElement(By.id("title")).click();
        wd.findElement(By.id("title")).clear();
        wd.findElement(By.id("title")).sendKeys("Обучение");
        wd.findElement(By.id("content")).click();
        wd.findElement(By.id("content")).clear();
        wd.findElement(By.id("content")).sendKeys("Обучаю вождению на своём автомобиле");
        if (!wd.findElement(By.xpath("//select[@id='header']//option[4]")).isSelected()) {
            wd.findElement(By.xpath("//select[@id='header']//option[4]")).click();
        }
        wd.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        if (!wd.findElement(By.tagName("html")).getText().contains("Обучение")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.findElement(By.linkText("Обучение")).click();
        if (!wd.findElement(By.id("header")).getAttribute("value").equals("услуги")) {
            System.out.println("verifyElementValue failed");
        }
        if (!wd.findElement(By.id("content")).getAttribute("value").equals("Обучаю вождению на своём автомобиле")) {
            System.out.println("verifyElementValue failed");
        }
        wd.findElement(By.linkText("Удалить")).click();
    }
    
    @After
    public void tearDown() {
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
