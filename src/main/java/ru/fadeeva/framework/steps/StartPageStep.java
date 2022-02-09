package ru.fadeeva.framework.steps;

import io.cucumber.java.ru.Допустим;
import ru.fadeeva.framework.managers.PageManager;

public class StartPageStep {
    PageManager pageManager = PageManager.getInstance();
    @Допустим("Начать поиск продукта по имени {string}")
    public void начать_поиск_продукта_по_имени(String string) {
        pageManager.getStartPage().startSearchProduct(string);
    }

}
