package com.mulesoft.anypoint.management;

import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AccessManagementStepDef
{
    private static final String BUTTON_PRIMARY_CSS_CLASS = ".button-primary";
    private static final String HOME_PAGE_URL = "https://anypoint.mulesoft.com/login";
    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String USERNAME_FIELD_NAME = "username";

    private static WebDriver webDriver;

    @Given("^I open the login page$")
    public void givenIOpenTheURL()
    {
        getWebDriver().get(HOME_PAGE_URL);
    }

    @Given("^I sign in with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void givenITypeTheUserName(String userName, String password)
    throws Throwable
    {
        getWebDriver().findElement(By.name(USERNAME_FIELD_NAME)).sendKeys(userName);
        getWebDriver().findElement(By.name(PASSWORD_FIELD_NAME)).sendKeys(password);
        getWebDriver().findElement(By.cssSelector(BUTTON_PRIMARY_CSS_CLASS)).click();
    }

    private WebDriver getWebDriver()
    {
        if (webDriver == null)
        {
            webDriver = new FirefoxDriver();
        }

        return webDriver;
    }
}
