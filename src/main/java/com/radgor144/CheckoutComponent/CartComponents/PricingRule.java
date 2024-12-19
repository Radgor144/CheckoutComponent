package com.radgor144.CheckoutComponent.CartComponents;

public class PricingRule {
    private String itemName;
    private double price;
    private int itemsForDiscount;
    private double specialPrice;

    public PricingRule(String itemName, double price, int itemsForDiscount, double specialPrice) {
        this.itemName = itemName;
        this.price = price;
        this.itemsForDiscount = itemsForDiscount;
        this.specialPrice = specialPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemsForDiscount() {
        return itemsForDiscount;
    }

    public void setItemsForDiscount(int itemsForDiscount) {
        this.itemsForDiscount = itemsForDiscount;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }
}
