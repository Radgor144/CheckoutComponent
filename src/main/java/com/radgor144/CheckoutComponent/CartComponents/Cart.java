package com.radgor144.CheckoutComponent.CartComponents;




import java.util.ArrayList;
import java.util.List;


public class Cart {
    private final int idCart;
    private final List<CartItem> items;

    public Cart(int idCart) {
        this.idCart = idCart;
        this.items = new ArrayList<>();
    }

    public int getIdCart() {
        return idCart;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }
}
