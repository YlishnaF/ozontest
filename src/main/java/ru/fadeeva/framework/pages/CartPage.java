package ru.fadeeva.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.fadeeva.framework.data.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    @FindBy(xpath = "//div[contains(@style, \"padding-top: calc(40.3846%);\")]/..//button")
    private List<WebElement> banner;

    @FindBy(xpath = "//span[contains(text(), \"Ваша корзин\")]/../span[contains(text(), \"товар\")]")
    private WebElement cartContains;

    @FindBy(xpath = "//label[contains(text(), \"Выбрать все\")]//input")
    private WebElement checkboxChooseAll;

    @FindBy(xpath = "//span[contains(text(), \"Удалить выбранные\")]")
    private WebElement deleteChosen;

    @FindBy(xpath = "//div[contains(text(), \"Удаление товаров\")]/..//span[contains(text(), \"Удалить\")]")
    private WebElement sureDeleteBanner;

    @FindBy(xpath = "//h1[contains(text(), \"Корзина пуста\")]")
    private WebElement cartIsEmpty;

    @FindBy(xpath = "//div[@id=\"split-Main-0\"]//span[@style=\"color: rgb(0, 26, 52);\"]")
    private List<WebElement> listOfProductsName;

    @FindBy(xpath = "//span[text()=\"Добавить компанию\"]")
    private WebElement bannerMarker;

    public void closeBanner() {
        if(isElementPresent(By.xpath("//span[text()=\"Добавить компанию\"]"))){
            waitElementToBeClicable(banner.get(1));
            banner.get(1).click();
        }
    }

    public void checkAmountOfProducts() {
        System.out.println(cartContains.getText());
        Assertions.assertTrue(cartContains.getText().contains(String.valueOf(Products.getProducts().size())));
    }

    public void checkAllProductsInCart() {
        List<String> namesFromCart = new ArrayList<>();
        for (WebElement product : listOfProductsName) {
            namesFromCart.add(product.getText());
        }
        List<String> namesFromProducts = Products.getProducts().stream().map(Products::getName).collect(Collectors.toList());
        Assertions.assertTrue(namesFromProducts.containsAll(namesFromCart));
    }

    public void deleteAllProducts() {
        if (!checkboxChooseAll.isSelected()) {
            waitElementToBeClicable(checkboxChooseAll);
            checkboxChooseAll.click();
            waitElementSelected(checkboxChooseAll);
        }
        waitElementToBeClicable(deleteChosen);
        deleteChosen.click();
        waitElementToBeClicable(sureDeleteBanner);
        sureDeleteBanner.click();
        Assertions.assertTrue(cartIsEmpty.isDisplayed());
        Products.setProducts(new ArrayList<>());
    }

}
