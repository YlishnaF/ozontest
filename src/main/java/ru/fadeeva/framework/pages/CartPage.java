package ru.fadeeva.framework.pages;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{
//    @FindBy(xpath = "//div[@class=\"bn\"]//*[local-name() = \"svg\"]")
    @FindBy(xpath = "//div[@class=\"bn\"]//button[@class = \"ui-b3\"]")
    private List<WebElement> banner;

    @FindBy(xpath = "//div[@class=\"a9t\"]")
    private WebElement cartContains;

    public void closeBanner(){
        waitElementToBeClicable(banner.get(1));
        banner.get(1).click();
    }

    public void checkEightProducts(){
        System.out.println(cartContains.getText());
        Assertions.assertTrue(cartContains.getText().contains("8 товаров"));
    }

    public void deleteAllProducts(){

    }
}
