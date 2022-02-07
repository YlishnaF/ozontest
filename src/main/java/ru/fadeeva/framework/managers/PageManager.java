package ru.fadeeva.framework.managers;

import ru.fadeeva.framework.pages.CartPage;
import ru.fadeeva.framework.pages.SearchResultPage;
import ru.fadeeva.framework.pages.StartPage;

public class PageManager {
    private static PageManager INSTANCE =null;
    private StartPage startPage;
    private SearchResultPage searchResultPage;
    private CartPage cartPage;

    private PageManager(){

    }
    public static PageManager getInstance(){
        if(INSTANCE==null){
            INSTANCE= new PageManager();
        }
        return INSTANCE;
    }
    public StartPage getStartPage(){
        if(startPage==null){
            startPage=new StartPage();
        }
        return startPage;
    }
    public SearchResultPage getSearchResultPage(){
        if(searchResultPage==null){
            searchResultPage= new SearchResultPage();
        }
        return searchResultPage;
    }

    public CartPage getCartPage(){
        if(cartPage==null){
            cartPage=new CartPage();
        }
        return cartPage;
    }


}
