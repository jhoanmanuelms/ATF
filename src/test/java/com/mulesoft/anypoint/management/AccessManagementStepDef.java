package com.mulesoft.anypoint.management;

import static org.assertj.core.api.Assertions.assertThat;

import com.mulesoft.anypoint.common.CommonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
    /** URL of the pages used in the step definitions. */
    private static final String ACCOUNTS_PAGE_URL = "https://anypoint.mulesoft.com/accounts";
    private static final String HOME_PAGE_URL = "https://anypoint.mulesoft.com/home";
    private static final String LOGIN_PAGE_URL = "https://anypoint.mulesoft.com/login";

    /** Attributes or properties used to select elements from the DOM. */
    private static final String ADD_ROLE_UI_SREF = "cs.core.roles.list.new";
    private static final String BUTTON_PRIMARY_CSS_CLASS = ".button-primary";
    private static final String DATA_NAME_ATTR = "data-name";
    private static final String LOADING_MSG_ATTR_NAME = "loading-message";
    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String PLACEHOLDER_ATTR_NAME = "placeholder";
    private static final String USERNAME_FIELD_NAME = "username";
    private static final String UI_SREF_ATTR_NAME = "ui-sref";

    /** HTML types used to filter DOM elements. */
    private static final String BUTTON_ELEMENT_TYPE = "button";
    private static final String INPUT_ELEM_TYPE = "input";
    private static final String LINK_ELEM_TYPE = "a";

    /** Values used to select DOM elements. */
    private static final String ROLE_NAME_PLACEHOLDER = "Role name";
    private static final String ROLE_DESC_PLACEHOLDER = "Role description";
    private static final String ADDING_ROLE_LOAD_MSG = "Adding role";
    private static final String ACCOUNTS_DATA_NAME = "accounts";

    /**
     * Map containing the pages expected after the user selects one of the Access Management options.
     * These are later used to verify the user has landed to the expected page.
     */
    private static final Map<String, String> EXPECTED_LANDING_PAGES =
        new HashMap<String, String>()
        {{
            put("Roles", "/roles/list");
        }};


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
        WebDriver driver = getWebDriver();
        driver.findElement(By.name(USERNAME_FIELD_NAME)).sendKeys(userName);
        driver.findElement(By.name(PASSWORD_FIELD_NAME)).sendKeys(password);
        driver.findElement(By.cssSelector(BUTTON_PRIMARY_CSS_CLASS)).click();
        assertThat(driver.getCurrentUrl()).contains(HOME_PAGE_URL);
    }

    /**
     * Step definition to go to the Access Management section.
     */
    @When("^I go to Access Management section$")
    public void whenIGoToAccessManagementSection()
    {
        CommonUtils.findElementByAttribute(
            getWebDriver(), LINK_ELEM_TYPE, DATA_NAME_ATTR, ACCOUNTS_DATA_NAME).click();
        assertThat(getWebDriver().getCurrentUrl()).contains(ACCOUNTS_PAGE_URL);
    }

    /**
     * Step definition to go to the specified section inside Access Management module.
     *
     * @param sectionName Section the user wants to go to.
     */
    @When("^I go to \"([^\"]*)\" section$")
    public void whenIGoToRolesSection(String sectionName)
    {
        getWebDriver().findElement(By.partialLinkText(sectionName)).click();
        assertThat(getWebDriver().getCurrentUrl()).contains(EXPECTED_LANDING_PAGES.get(sectionName));
    }

    /**
     * Step definition to add a role.
     *
     * @param roleName Name for the new role.
     * @param roleDescription Description for the new role.
     */
    @When("^I add a role with name \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void whenIAddRole(String roleName, String roleDescription)
    {
        WebDriver driver = getWebDriver();
        CommonUtils.findElementByAttribute(
            getWebDriver(), LINK_ELEM_TYPE, UI_SREF_ATTR_NAME, ADD_ROLE_UI_SREF).click();
        CommonUtils.findElementByAttribute(
            driver, INPUT_ELEM_TYPE, PLACEHOLDER_ATTR_NAME, ROLE_NAME_PLACEHOLDER).sendKeys(roleName);
        CommonUtils.findElementByAttribute(
            driver,
            INPUT_ELEM_TYPE,
            PLACEHOLDER_ATTR_NAME,
            ROLE_DESC_PLACEHOLDER).sendKeys(roleDescription);
        CommonUtils.findElementByAttribute(
            driver, BUTTON_ELEMENT_TYPE, LOADING_MSG_ATTR_NAME, ADDING_ROLE_LOAD_MSG).click();
    }

    /**
     * Verifies that the role with the given name exists.
     *
     * @param roleName Name of the role to verify.
     */
    @Then("^I expect the role \"([^\"]*)\" was created$")
    public void thenIExpecteRoleCreated(String roleName)
    {
        getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertThat(getWebDriver().findElement(By.partialLinkText(roleName))).isNotNull();
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
