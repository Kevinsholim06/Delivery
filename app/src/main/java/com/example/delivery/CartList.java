package com.example.delivery;

import java.util.List;

public class CartList {

    private static List<Product> cartList;

    public static void setCartList(List<Product> list) {
        cartList = list;
    }

    public static List<Product> getCartList() {
        return cartList;
    }
}
