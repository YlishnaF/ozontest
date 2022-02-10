package ru.fadeeva.framework.steps;

import io.cucumber.java.ru.Допустим;
import ru.fadeeva.framework.managers.PageManager;

public class CartPageStep {
    PageManager pageManager = PageManager.getInstance();

    @Допустим("^Закрыть баннер$")
    public void закрыть_баннер() {
        pageManager.getCartPage().closeBanner();
    }

    @Допустим("^Проверить количество продуктов в корзине$")
    public void проверить_количество_продуктов_в_корзине() {
        pageManager.getCartPage().checkAmountOfProducts();
    }

    @Допустим("^Проверить что все добавленные продукты в корзине$")
    public void проверить_что_все_добавленные_продукты_в_корзине() {
        pageManager.getCartPage().checkAllProductsInCart();
    }
    @Допустим("^Удалить из корзины все продукты$")
    public void удалить_из_корзины_все_продукты() {
        pageManager.getCartPage().deleteAllProducts();
    }
}
