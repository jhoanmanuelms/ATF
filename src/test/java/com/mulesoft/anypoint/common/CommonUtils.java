package com.mulesoft.anypoint.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class containing commonly used methods.
 *
 * @author Jhoan Munoz
 */
public class CommonUtils
{
    /**
     * Filters the DOM elements with the given type and find the one that matches the given value
     * for the given attribute.
     *
     * @param webDriver WebDriver with that contains the reference to the DOM.
     * @param elementType Element type that will be searched for.
     * @param attribute Attribute used to find the element.
     * @param value Value used to find the element.
     *
     * @return The {@link WebElement} matching the given criteria. If the element is not found, a
     *         null reference will be returned.
     */
    public static WebElement findElementByAttribute(
        WebDriver webDriver, String elementType, String attribute, String value)
    {
        WebElement element = null;
        for (WebElement tempElement : webDriver.findElements(By.tagName(elementType)))
        {
            if(tempElement.getAttribute(attribute) != null
                && tempElement.getAttribute(attribute).equals(value))
            {
                element = tempElement;
                break;
            }
        }

        return element;
    }
}
