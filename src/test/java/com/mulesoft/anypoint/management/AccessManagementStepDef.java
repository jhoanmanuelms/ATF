package com.mulesoft.anypoint.management;

import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AccessManagementStepDef
{
    private static final String HOME_PAGE_URL = "https://anypoint.mulesoft.com/login";
    private static WebDriver webDriver;

    @Given("^I open the login page$")
    public void givenIOpenTheURL()
    {
        getWebDriver().get(HOME_PAGE_URL);
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
