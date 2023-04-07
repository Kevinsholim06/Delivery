package com.example.delivery;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static final Map<Product, CartEntry> cartMap = new HashMap<Product, CartEntry>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product("Coke", res.getDrawable(R.drawable.soda),
                    "500ml Cold coke", 1));
            catalog.add(new Product("French fries", res.getDrawable(R.drawable.frenchfries),
                    "1 dish of french fries commonly chips", 1));
            catalog.add(new Product("Coffee", res.getDrawable(R.drawable.coffee),
                    "1 cup of Roasted and ground coffee", 1));
            catalog.add(new Product("Rice", res.getDrawable(R.drawable.rice),
                    "1 plate of boiled rice", 1));
            catalog.add(new Product("Pizza", res.getDrawable(R.drawable.pizza),
                    "Extra large with beef and peperoni toppings", 1));
            catalog.add(new Product("Nyama Choma", res.getDrawable(R.drawable.nyamachoma),
                    "Goat meat roasted", 1));
            catalog.add(new Product("Burger", res.getDrawable(R.drawable.burger),
                    "Delicious beef and cheese burger with lettuce and tomato", 1));
        }

        return catalog;
    }

    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        CartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new CartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        CartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }
        CartList.setCartList(cartList);

        return cartList;
    }
}

