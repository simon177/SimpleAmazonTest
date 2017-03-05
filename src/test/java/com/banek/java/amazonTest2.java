package com.banek.java;
import org.apache.bcel.generic.RETURN;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by simon on 05.03.2017.
 */

public class amazonTest2 {
    private static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void addingToAcart(){
        driver.get("http://www.amazon.com");
        WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
        dropdown.click();
        Select Books = new Select(dropdown);
        Books.selectByVisibleText("Books");
        WebElement searchbox = driver.findElement(By.id("twotabsearchtextbox"));
        searchbox.sendKeys("Selenium");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
        button.click();
        WebElement firstEl = driver.findElement(By.xpath("//*[@id=\"result_0\"]/div/div/div/div[2]/div[2]/div[1]/a/h2"));
        String title2 = firstEl.getAttribute("data-attribute");
        firstEl.click();
        String title = driver.findElement(By.id("productTitle")).getText();
        Assert.assertEquals(title,title2);
        WebElement button2 = driver.findElement(By.id("add-to-cart-button"));
        button2.click();
        Assert.assertEquals("Added to Cart",driver.findElement(By.xpath("//*[@id=\"huc-v2-order-row-confirm-text\"]/h1")).getText());
        WebElement cart = driver.findElement(By.id("nav-cart"));
        cart.click();
        WebElement list = driver.findElement(By.className("a-list-item"));
        list.getText().contains(title);
    }
    @Test
    public void loginTestCase(){
        driver.get("http://www.amazon.com");
        WebElement button1 = driver.findElement(By.id("nav-link-accountList"));
        button1.click();
        WebElement username = driver.findElement(By.id("ap_email"));
        username.sendKeys("automation.user2015@gmail.com");
        WebElement password = driver.findElement(By.id("ap_password"));
        password.sendKeys("@ut0m@t!0n");
        WebElement loginButton = driver.findElement(By.id("signInSubmit"));
        loginButton.click();
        WebElement myAccount = driver.findElement(By.id("nav-link-accountList"));
        myAccount.getText().contains("Automation");
        Actions action = new Actions(driver);
        action.moveToElement(myAccount).build().perform();
        WebElement signOut = driver.findElement(By.id("nav-item-signout"));
        signOut.click();
        String signIn = driver.findElement(By.xpath("//*[@id=\"a-page\"]/div[1]/div[3]/div/div/form/div/div/div/h1")).getText();
        Assert.assertEquals("Sign in",signIn);
    }

}
