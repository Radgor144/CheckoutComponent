package com.radgor144.CheckoutComponent;

public class Item {
    private String itemName;
    private double price;
    private int itemsForDiscount;
    private double specialPrice;
    private int amount;

    public Item(String itemName, double price, int itemsForDiscount, double specialPrice) {
        this.itemName = itemName;
        this.price = price;
        this.itemsForDiscount = itemsForDiscount;
        this.specialPrice = specialPrice;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemsForDiscount() {
        return itemsForDiscount;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
