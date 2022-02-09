package ru.fadeeva.framework.base;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.data.DataForSearch;


public class OzonHeadphonesTest extends BaseClass{
    @Test
    public void test(){
        pageManager.getStartPage().startSearchProduct("беспроводные наушники");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.PRICE, "10000");
        pageManager.getSearchResultPage().searchSettings("Бренды", "Samsung");
        pageManager.getSearchResultPage().searchSettings("Бренды", "Beats");
        pageManager.getSearchResultPage().searchSettings("Бренды", "Xiaomi");
        pageManager.getSearchResultPage().searchSettings("Высокий рейтинг", "non");
        pageManager.getSearchResultPage().addProductsToCart("unlim");
        pageManager.getSearchResultPage().goIntoCart();
        pageManager.getCartPage().closeBanner();
        pageManager.getCartPage().checkAllProductsInCart();
        pageManager.getCartPage().checkAmountOfProducts();
        pageManager.getCartPage().deleteAllProducts();
    }
}
