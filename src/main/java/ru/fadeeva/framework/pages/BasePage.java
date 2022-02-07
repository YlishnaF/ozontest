package ru.fadeeva.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fadeeva.framework.managers.DriverManager;
import ru.fadeeva.framework.managers.PageManager;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected PageManager pageManager = PageManager.getInstance();

    public BasePage() {

        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitElementToBeClicable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitElementVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    protected void waitAttributeBecomeAvailable(WebElement webElement, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(webElement, attribute, value));
    }

    protected void changeTextElement(WebElement webElement, String text) {
        ((JavascriptExecutor) driverManager.getDriver()).executeScript("document.querySelector('div.jq-selectbox__select-text').textContent = " + text);
    }

    protected void clickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    protected void waitTextAvailable(WebElement webElement, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public void waitForElementPresent(final By by, int timeout) {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driverManager.getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                WebElement element = webDriver.findElement(by);
                return element != null && element.isDisplayed();
            }
        });
    }

    public void waitElementPresent(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
