package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Test the login functionality using email and password")
    public void ShouldBeAbleToLoginWithEmailAndPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        boolean isWelcomeDisplayed =
                loginPage
                        .load()
                        .login(ConfigUtils.getInstance().getEmail(), ConfigUtils.getInstance().getPassword())
                        .isWelcomeMessageDisplayed();
        Assert.assertTrue(isWelcomeDisplayed);

    }
}
