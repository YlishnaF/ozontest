package ru.fadeeva.framework.base;

import org.junit.jupiter.api.Test;
import ru.fadeeva.framework.data.DataForSearch;


public class OzonHeadphonesTest extends BaseClass{
    @Test
    public void test(){
        pageManager.getStartPage().startSearchProduct("беспроводные наушники");
        pageManager.getSearchResultPage().searchSettings(DataForSearch.PRICE, "10000");
        pageManager.getSearchResultPage().searchSettings("Бренды", "Samsung");
    }
}
