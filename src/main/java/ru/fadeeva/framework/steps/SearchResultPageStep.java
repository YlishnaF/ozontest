package ru.fadeeva.framework.steps;

import io.cucumber.java.ru.Допустим;
import ru.fadeeva.framework.managers.PageManager;

import java.util.List;

public class SearchResultPageStep {
    PageManager pageManager = PageManager.getInstance();

    @Допустим("Установить фильтр для поиска продуктов")
    public void установить_фильтр_для_поиска_продуктов(List<List<String>> list) {
        for (List<String> data: list) {
            pageManager.getSearchResultPage().searchSettings(data.get(0), data.get(1));
        }


    }

    @Допустим("Добавить продукты в корзину {string}")
    public void добавить_продукты_в_корзину(String string) {
        pageManager.getSearchResultPage().addProductsToCart(string);
    }

    @Допустим("Перейти в корзину")
    public void перейти_в_корзину() {
        pageManager.getSearchResultPage().goIntoCart();
    }
}
