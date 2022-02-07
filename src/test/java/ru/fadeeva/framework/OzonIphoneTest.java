package ru.fadeeva.framework;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.base.BaseClass;
import ru.fadeeva.framework.data.DataForSearch;

public class OzonIphoneTest extends BaseClass {
    @Test
    public void test() throws InterruptedException {
        pageManager.getStartPage().startSearchProduct("iphone");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.PRICE, "100000");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.INTERFACE, "NFC");
        pageManager.getSearchResultPage().popularOrOthersFilters("Высокий рейтинг");
        pageManager.getSearchResultPage().addEightProductsToCart();
        pageManager.getSearchResultPage().goIntoCart();
        pageManager.getCartPage().closeBanner();
        pageManager.getCartPage().checkEightProducts();

    }
}
