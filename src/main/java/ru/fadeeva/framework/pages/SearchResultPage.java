package ru.fadeeva.framework.pages;

import io.cucumber.java.mn.Харин;
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

    @FindBy(xpath = "//a[@href=\"/cart\"]/span[1]")
    private WebElement amountProductsInCart;

//    @FindBy(xpath = "//span[@class=\"jc6 cj7 jc7 c9j f-tsBodyL h1k\"]/span")
//    private WebElement nameOfProduct;

    @FindBy(xpath = "//a[@href=\"/cart\"]")
    private WebElement goToCart;

//    @FindBy(xpath = "//div[@class=\"ui-l7 ui-l8\"]/span[contains(text(), \"Высокий рейтинг\")]")
//    private WebElement reiting;

    @FindBy(xpath = "//div[@data-widget=\"searchResultsFiltersActive\"]")
    private WebElement myFilters;

//    @FindBy(xpath = "//span[contains(text(), \"Ничего не нашлось\")]")
//    private WebElement noBrandFind;

    private int index = 0;

   public void searchSettings(String specCategory, String value) {
        waitElementVisible(markerPageLoad);
//        for (WebElement block : specifySearchBlocks) {
//            if (block.getText().contains(specCategory)) {
//                switch (specCategory) {
//                    case (DataForSearch.PRICE) -> {
//                        WebElement maxPriceDiv = block.findElement(By.xpath("//p[contains(text(), \"до\")]/../input"));
//                        scrollWithOffset(block, 0, -150);
//                        maxPriceDiv.click();
//                        maxPriceDiv.clear();
//                        for (int i = 0; i < 8; i++) {
//                            maxPriceDiv.sendKeys(Keys.BACK_SPACE);
//                        }
//                        maxPriceDiv.sendKeys(value);
//                    }
//                    case (DataForSearch.INTERFACE) -> {
//                        WebElement interfaces = block.findElement(By.xpath(".//span[contains(text(), \"NFC\")]/../../div"));
//                        scrollWithOffset(block, 0, -150);
//                        interfaces.click();
//                        waitTextAvailable(myFilters, "Беспроводные интерфейсы: NFC");
//                    }
//                    case ("Высокий рейтинг") -> {
//                        WebElement highRating = block.findElement(By.xpath(".//div[@value=\"Высокий рейтинг\"]//div"));
//                        highRating.click();
//                        waitTextAvailable(myFilters, "Высокий рейтинг");
//                    }
//                    case ("Бренды") -> {
//                        if (block.getText().contains("Посмотреть все")) {
//                            WebElement showAll = block.findElement(By.xpath(".//span[contains(text(), \"Посмотреть все\")]"));
//                            showAll.click();
//                        }
//                        WebElement searchBrandLine = block.findElement(By.xpath(".//input"));
//                        for (int i = 0; i < 20; i++) {
//                            searchBrandLine.sendKeys(Keys.BACK_SPACE);
//                        }
//                        searchBrandLine.sendKeys(value);
//                        if (!block.getText().contains(value)) {
//                            return;
//                        }
//                        WebElement choseBrand = block.findElement(By.xpath(".//span[contains(text(),\"" + value + "\")]/../../div"));
//                        waitElementToBeClicable(choseBrand);
//                        choseBrand.click();
//                        waitTextAvailable(myFilters, value);
//                    }
//                }
//            }
//
//        }
        for (int s = 0; s < specifySearchBlocks.size(); s++) {
            if (specifySearchBlocks.get(s).getText().contains(specCategory)) {
                switch (specCategory) {
                    case (DataForSearch.PRICE) -> {
                        WebElement maxPriceDiv = specifySearchBlocks.get(s).findElement(By.xpath("//p[contains(text(), \"до\")]/../input"));
                        scrollWithOffset(specifySearchBlocks.get(s),0, -150);
                        maxPriceDiv.click();
                        maxPriceDiv.clear();
                        for (int i = 0; i < 8; i++) {
                            maxPriceDiv.sendKeys(Keys.BACK_SPACE);
                        }
                        maxPriceDiv.sendKeys(value);
                    }
                    case (DataForSearch.INTERFACE) -> {
                        WebElement interfaces = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(), \"NFC\")]/../../div"));
                        scrollWithOffset(specifySearchBlocks.get(s),0, -150);
                        interfaces.click();
                        waitTextAvailable(myFilters, "Беспроводные интерфейсы: NFC");
                    }
                    case ("Высокий рейтинг")->{
                        WebElement highRating = specifySearchBlocks.get(s).findElement(By.xpath(".//div[@value=\"Высокий рейтинг\"]//div"));
                        highRating.click();
                        waitTextAvailable(myFilters, "Высокий рейтинг");
                    }
                    case("Бренды")->{
                        if(specifySearchBlocks.get(s).getText().contains("Посмотреть все")){
                            WebElement showAll = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(), \"Посмотреть все\")]"));
                            showAll.click();
                        }
                        WebElement searchBrandLine = specifySearchBlocks.get(s).findElement(By.xpath(".//input"));
                        for (int i = 0; i < 20; i++) {
                            searchBrandLine.sendKeys(Keys.BACK_SPACE);
                        }
                        searchBrandLine.sendKeys(value);
                        if(!specifySearchBlocks.get(s).getText().contains(value)){
                            return;
                        }
                        WebElement choseBrand = specifySearchBlocks.get(s).findElement(By.xpath(".//span[contains(text(),\"" + value + "\")]/../../div"));
                        waitElementToBeClicable(choseBrand);
                        choseBrand.click();
                        waitTextAvailable(myFilters, value);
                    }
                }
            }
        }
    }

    public void addProductsToCart(String s) {
        for (int i = 1; i <= listOfProducts.size(); i++) {
            if (i % 2 == 0) {
                WebElement name = listOfProducts.get(i-1).findElement(By.xpath(".//a/span/span"));
                WebElement webElement = listOfProducts.get(i-1).findElement(By.xpath(".//span[contains(text(),\"В корзину\")]/../.."));
                WebElement price = listOfProducts.get(i-1).findElement(By.xpath("./div[3]/div[not(contains(@style,'background-color'))]//span[contains(text(), \"₽\")][1]"));
                if (webElement.isDisplayed()) {
                    waitElementToBeClicable(webElement);
                    clickElement(webElement);
                    Products.addToCart(new Products(name.getText(), Integer.parseInt(price.getText().replaceAll("\\D+", ""))));
                    System.out.println(price.getText().replaceAll("\\D+", ""));
                    System.out.println(name.getText());
                    waitTextAvailable(amountProductsInCart, String.valueOf(Products.getProducts().size()));
                    index++;
                    if (!s.equals("unlim") && index == Integer.parseInt(s)) {
                        index =0;
                        return;
                    }
                } else {
                    i = i + 2;
                }
            }
        }
    }

    public void goIntoCart() {
        waitElementToBeClicable(goToCart);
        goToCart.click();
    }
}
