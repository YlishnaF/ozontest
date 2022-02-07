package ru.fadeeva.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.fadeeva.framework.data.DataForSearch;
import ru.fadeeva.framework.data.Products;

import java.util.List;
import java.util.function.Predicate;

public class SearchResultPage extends BasePage {
    @FindBy(xpath = "//header[contains(text(), \"Категория\")]")
    private WebElement markerPageLoad;

    @FindBy(xpath = "//div[@class=\"rp8 filter-block\"]")
    private List<WebElement> specifySearchBlocks;

    @FindBy(xpath = "//div[@class=\"pr\"]")
    private List<WebElement> additionalFilters;


    @FindBy(xpath = "//div[@class=\"l9h mh\"]")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//span[contains(text(),\"В корзину\")]/../..")
    private List<WebElement> addToCart;

    @FindBy(xpath = "//span[@class=\"f-caption--bold u8b\"]")
    private WebElement amountProductsInCart;

    @FindBy(xpath = "//span[@class=\"jc6 cj7 jc7 c9j f-tsBodyL h1k\"]/span")
    private WebElement nameOfProduct;

    @FindBy(xpath = "//a[@href=\"/cart\"]")
    private WebElement goToCart;

    @FindBy(xpath = "//div[@class=\"ui-l7 ui-l8\"]/span[contains(text(), \"Высокий рейтинг\")]")
    private WebElement reiting;


    public void searchSettings(String specCategory, String value) {
        waitElementVisible(markerPageLoad);
        for (int s = 0; s < specifySearchBlocks.size(); s++) {
            if (specifySearchBlocks.get(s).getText().contains(specCategory)) {
                switch (specCategory) {
                    case (DataForSearch.PRICE) -> {
                        WebElement maxPriceDiv = specifySearchBlocks.get(s).findElements(By.xpath(".//input[@class=\"ui-g1 ui-g2 ui-a8a\"]")).get(1);
                        scrollToElement(specifySearchBlocks.get(s - 1));
                        maxPriceDiv.click();
                        maxPriceDiv.sendKeys(Keys.DELETE);
                        for (int i = 0; i < 7; i++) {
                            maxPriceDiv.sendKeys(Keys.BACK_SPACE);
                        }
                        maxPriceDiv.sendKeys(value);
                    }
                    case (DataForSearch.INTERFACE) -> {
                        WebElement interfaces = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(), \"NFC\")]/../../div[@class=\"ui-b7a\"]"));
                        scrollToElement(specifySearchBlocks.get(s - 1));
                        interfaces.click();
                        waitAttributeBecomeAvailable(interfaces.findElement(By.xpath("./..")), "class", "ui-ba6 ui-ba7 ui-ba8 ui-a9b pu up0");
                    }
                }
            }
        }
    }

    public void popularOrOthersFilters(String s) throws InterruptedException {
//        scrollToElement(amountOfFindProductsInformation);
        //       waitElementToBeClicable(popularAndOtherSettings);
        //       popularAndOtherSettings.click();
        //      waitAttributeBecomeAvailable(popularAndOtherSettings,"aria-expanded", "true");
        for (WebElement filter : additionalFilters) {
            if (filter.getText().contains(s)) {
                filter.click();
                waitAttributeBecomeAvailable(filter.findElement(By.xpath("./label")), "class", "ui-ba6 ui-ab8 ui-ba8 ui-ab7 ui-a9b q9p");
            }
        }
        waitElementPresent(By.xpath("//div[@class=\"ui-l7 ui-l8\"]/span[contains(text(), \"Высокий рейтинг\")]"));
         }

    int index = 0;
    int k = 0;

    public void addEightProductsToCart() {
        for (int i = 0; i < listOfProducts.size(); i++) {
            if(i%2==0) {
                try {
                    clickAdd(i);
                    index++;
                    if (index == 8) {
                        return;
                    }
                } catch (StaleElementReferenceException e) {
                    k++;
                    System.out.println(k);
                    clickAdd(i);
                    index++;
                    if (index == 8) {
                        return;
                    }
                }
            }
        }
    }
    public void goIntoCart(){
        waitElementToBeClicable(goToCart);
        goToCart.click();
    }

    private void clickAdd(int ind) {
        WebElement name = listOfProducts.get(ind).findElement(By.xpath(".//span[@class=\"jc6 cj7 jc7 c9j f-tsBodyL h1k\"]/span"));
        WebElement webElement = listOfProducts.get(ind).findElement(By.xpath(".//span[contains(text(),\"В корзину\")]/../.."));
        if(webElement.isDisplayed()){
            waitElementToBeClicable(webElement);
            clickElement(webElement);
            Products.addToCart(new Products(name.getText()));
            System.out.println(name.getText());
            waitTextAvailable(amountProductsInCart, String.valueOf(Products.getProducts().size()));
        }

    }
}
