package ru.fadeeva.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{
//    @FindBy(xpath = "//div[@class=\"bn\"]//*[local-name() = \"svg\"]")
    @FindBy(xpath = "//div[@class=\"bn\"]//button")
    private List<WebElement> banner;

    @FindBy(xpath = "//span[contains(text(), \"Ваша корзин\")]/../span[contains(text(), \"товар\")]")
    private WebElement cartContains;

    @FindBy(xpath = "//label[@class=\"al2\"]//input")
    private WebElement checkboxChooseAll;

    @FindBy(xpath = "//span[@class=\"al2 a2l\"]")
    private WebElement deleteAll;

    @FindBy(xpath = "//span[@class=\"ui-e7\" and contains(text(),\"Удалить\")]")
    private WebElement sureDeleteBanner;

    @FindBy(xpath = "//h1[contains(text(), \"Корзина пуста\")]")
    private WebElement cartIsEmpty;

    public void closeBanner(){
        if(banner.get(1).isDisplayed()){
            waitElementToBeClicable(banner.get(1));
            banner.get(1).click();
        }
    }

    public void checkAmountOfProducts(String s){
        System.out.println(cartContains.getText());
        Assertions.assertTrue(cartContains.getText().contains(s));
    }

    public void deleteAllProducts(){
        if(!checkboxChooseAll.isSelected()){
          waitElementToBeClicable(checkboxChooseAll);
          checkboxChooseAll.click();
          waitAttributeBecomeAvailable(checkboxChooseAll.findElement(By.xpath("./../../label")), "class", "ui-ba6 ui-ba7 ui-ba8 l1a");
        }
        waitElementToBeClicable(deleteAll);
        deleteAll.click();
        waitElementToBeClicable(sureDeleteBanner);
        sureDeleteBanner.click();
        Assertions.assertTrue(cartIsEmpty.isDisplayed());
    }
}
