package com.radgor144.CheckoutComponent.CartComponents;

public class CartItem {
    private String itemName;
    private int amount;

    public CartItem(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}