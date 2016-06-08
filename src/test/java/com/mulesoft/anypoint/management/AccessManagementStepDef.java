package com.mulesoft.anypoint.management;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Step definitions for Access Management section in AnyPoint platform.
 *
 * @author Jhoan Munoz
 */
public class AccessManagementStepDef
{
    /** CSS class used to get the primary button through CSS selector. */
    private static final String BUTTON_PRIMARY_CSS_CLASS = ".button-primary";

    /** URL of the home page loaded after a successful login. */
    private static final String HOME_PAGE_URL = "https://anypoint.mulesoft.com/home/#/";

    /** URL of the login page. */
    private static final String LOGIN_PAGE_URL = "https://anypoint.mulesoft.com/login";

    /** Name of the text input for the user name. */
    private static final String PASSWORD_FIELD_NAME = "password";

    /** Name of the text input for the password. */
    private static final String USERNAME_FIELD_NAME = "username";

    /** Selenium web driver used to interact with the browser. */
    private static WebDriver webDriver;

    /**
     * Step definition to open the login page.
     */
    @Given("^I open the login page$")
    public void givenIOpenTheURL()
    {
        getWebDriver().get(LOGIN_PAGE_URL);
    }

    /**
     * Step definition to sign in the application.
     *
     * @param userName User name used to sign in.
     * @param password Password corresponding to the given user name
     *
     * @throws Throwable In case one of the components is not found.
     */
    @Given("^I sign in with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void givenISignIn(String userName, String password)
    throws Throwable
    {
        getWebDriver().findElement(By.name(USERNAME_FIELD_NAME)).sendKeys(userName);
        getWebDriver().findElement(By.name(PASSWORD_FIELD_NAME)).sendKeys(password);
        getWebDriver().findElement(By.cssSelector(BUTTON_PRIMARY_CSS_CLASS)).click();
        assertThat(getWebDriver().getCurrentUrl()).isEqualTo(HOME_PAGE_URL);
    }

    /**
     * Returns the {@link WebDriver} instance. In case it's not initialized, the method creates a
     * {@link FirefoxDriver}.
     *
     * @return A {@link WebDriver} instance.
     */
    private WebDriver getWebDriver()
    {
        if (webDriver == null)
        {
            webDriver = new FirefoxDriver();
        }

        return webDriver;
    }
}
