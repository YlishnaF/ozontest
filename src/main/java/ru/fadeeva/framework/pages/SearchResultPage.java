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

    @FindBy(xpath = "//div[contains(@class, \"filter-block\")]")
    private List<WebElement> specifySearchBlocks;

    @FindBy(xpath = "//div[@class=\"pr\"]")
    private List<WebElement> additionalFilters;


    @FindBy(xpath = "//div[contains(@class, \"widget-search-result-container\")]/div/div")
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

    @FindBy(xpath = "//div[@data-widget=\"searchResultsFiltersActive\"]")
    private WebElement myFilters;

    private int index = 0;

    public void searchSettings(String specCategory, String value) {
        waitElementVisible(markerPageLoad);
        for (int s = 0; s < specifySearchBlocks.size(); s++) {
            if (specifySearchBlocks.get(s).getText().contains(specCategory)) {
                switch (specCategory) {
                    case (DataForSearch.PRICE) -> {
                        WebElement maxPriceDiv = specifySearchBlocks.get(s).findElement(By.xpath("//p[contains(text(), \"до\")]/../input"));
                        scrollToElement(specifySearchBlocks.get(s - 1));
                        maxPriceDiv.click();
                     //   maxPriceDiv.sendKeys(Keys.DELETE);
                        maxPriceDiv.clear();
                        for (int i = 0; i < 8; i++) {
                            maxPriceDiv.sendKeys(Keys.BACK_SPACE);
                        }
                        maxPriceDiv.sendKeys(value);
                    }
                    case (DataForSearch.INTERFACE) -> {
                        WebElement interfaces = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(), \"NFC\")]/../../div"));
                        scrollToElement(specifySearchBlocks.get(s - 1));
                        interfaces.click();
                        waitTextAvailable(myFilters, "Беспроводные интерфейсы: NFC");
                    }
                    case ("Высокий рейтинг")->{
                        WebElement highRating = specifySearchBlocks.get(s).findElement(By.xpath(".//div[@value=\"Высокий рейтинг\"]//div"));
                        highRating.click();
                        waitTextAvailable(myFilters, "Высокий рейтинг");
                    }
                    case("Бренды")->{
                        List<WebElement> brands = specifySearchBlocks.get(s).findElements(By.xpath(".//a//span"));
                        WebElement showAll = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(), \"Посмотреть все\")]"));
                        showAll.click();
                        for (WebElement brand:brands) {
                            if(brand.getText().contains(value)){
                                WebElement myBrand  = brand.findElement(By.xpath("./../../div")); myBrand.click();

                            }

                        }
                    }
                }
            }
        }
    }

    public void addEightProductsToCart() {
        for (int i = 0; i < listOfProducts.size(); i++) {
            if(i%2==0) {
                WebElement name = listOfProducts.get(i).findElement(By.xpath(".//a/span/span"));
                WebElement webElement = listOfProducts.get(i).findElement(By.xpath(".//span[contains(text(),\"В корзину\")]/../.."));
                WebElement price = listOfProducts.get(i).findElement(By.xpath(".//span[@class=\"ui-q ui-q2 ui-q5\"]"));
                if(webElement.isDisplayed()){
                    waitElementToBeClicable(webElement);
                    clickElement(webElement);
                    Products.addToCart(new Products(name.getText(), Integer.parseInt(price.getText().replaceAll("\\D+",""))));
                    System.out.println(price.getText().replaceAll("\\D+",""));
                    System.out.println(name.getText());
                    waitTextAvailable(amountProductsInCart, String.valueOf(Products.getProducts().size()));
                    index++;
                    if (index == 8) {
                        return;
                    }
                } else {
                    index=index+2;
                }
            }
        }
    }
    public void chooseBrands(String ...str){

    }
    public void goIntoCart(){
        waitElementToBeClicable(goToCart);
        goToCart.click();
    }
}
