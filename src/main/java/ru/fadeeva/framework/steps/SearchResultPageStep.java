package ru.fadeeva.framework.steps;

import io.cucumber.java.ru.Допустим;
import ru.fadeeva.framework.managers.PageManager;

public class SearchResultPageStep {
    PageManager pageManager = PageManager.getInstance();
    @Допустим("Установить фильтр лоя продуктов {string}, {string}")
    public void установить_фильтр_лоя_продуктов(String string, String string2) {
        pageManager.getSearchResultPage().searchSettings(string,string2);
    }

    @Допустим("Добавить восемь четных продуктов в корзину")
    public void добавить_8_четных_продуктов_в_корзину() {
        pageManager.getSearchResultPage().addEightProductsToCart();
    }
}
