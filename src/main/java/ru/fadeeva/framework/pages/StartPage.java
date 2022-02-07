package ru.fadeeva.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage{
    @FindBy(xpath = "//input[@placeholder =\"Искать на Ozon\"]")
    protected WebElement searchLine;

    public void startSearchProduct(String name) throws InterruptedException {
        waitElementToBeClicable(searchLine);
        searchLine.click();
        searchLine.sendKeys(name);
        searchLine.submit();
    }
}
