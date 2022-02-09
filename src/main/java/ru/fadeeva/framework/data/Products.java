package ru.fadeeva.framework.data;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private String name;
    private int price;
    private static List<Products> products = new ArrayList<>();

    public Products(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public static List<Products> getProducts() {
        return products;
    }


    public static void addToCart(Products product) {
        getProducts().add(product);
    }

    public void removeProduct(String name) {
        for (Products product : products) {
            if (product.getName().equals(name)) {
                getProducts().remove(product);
                return;
            }
        }
    }
}
