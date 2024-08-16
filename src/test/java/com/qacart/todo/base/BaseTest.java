package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookieUtils;
import io.restassured.http.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void setup(){
        WebDriver driver = new DriverFactory().initializaeDriver();
        setDriver(driver);
    }

    @AfterMethod
    public void teardown(){
        getDriver().quit();
    }
    public void injectCookiesToBrowser(List<Cookie> restAssredCookies) {
        List<org.openqa.selenium.Cookie> sleniumCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAssredCookies);
        for (org.openqa.selenium.Cookie cookie : sleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }
}
