package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TasksApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TodoTest extends BaseTest {
    @Test(description = "Should be able to add a new todo correctly")
    public void shouldBeAbleToAddNewTodo(){

        RegisterApi registerApi = new RegisterApi();
        registerApi.register();
        NewTodoPage newTodoPage = new NewTodoPage(getDriver());
        newTodoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
        String actualResult = newTodoPage
                .load()
                .addNewTask("Learn Selenium")
                .getTodoText();
        Assert.assertEquals(actualResult, "Learn Selenium");
    }
    @Test (description = "Should be able to delete a todo correctly")
    public void shouldBeAbleToDeleteTodo(){
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();

        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getToken());


        TodoPage todoPage = new TodoPage(getDriver());
        todoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());

        boolean isNoTodoMessageDisplayed = todoPage
                .load()
                .clickOnDeleteButton()
                .isNoTodoMessageDisplayed();
        Assert.assertTrue(isNoTodoMessageDisplayed);
    }
}
