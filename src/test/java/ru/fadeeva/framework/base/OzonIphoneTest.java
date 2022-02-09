package ru.fadeeva.framework.base;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.base.BaseClass;
import ru.fadeeva.framework.data.DataForSearch;

public class OzonIphoneTest extends BaseClass {
    @Test
    public void test()  {
        pageManager.getStartPage().startSearchProduct("iphone");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.PRICE, "100000");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.INTERFACE, "NFC");
        pageManager.getSearchResultPage().searchSettings("Высокий рейтинг", "non");
        pageManager.getSearchResultPage().addProductsToCart("8");
        pageManager.getSearchResultPage().goIntoCart();
        pageManager.getCartPage().closeBanner();
        pageManager.getCartPage().checkAmountOfProducts();
        pageManager.getCartPage().checkAllProductsInCart();
        pageManager.getCartPage().deleteAllProducts();
    }
}
