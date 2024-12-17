package com.radgor144.CheckoutComponent;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final int idCart;
    private final List<Item> items;

    public Cart(int idCart) {
        this.idCart = idCart;
        this.items = new ArrayList<>();
    }

    public void addItem(Item newItem) {
        for (Item item : items) {
            if (item.getItemName().equals(newItem.getItemName())) {
                item.setAmount(item.getAmount() + newItem.getAmount());
                return;
            }
        }
        items.add(newItem);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getIdCart() {
        return idCart;
    }
}
