package com.radgor144.CheckoutComponent.CartComponents;

import java.util.Objects;

public class CartItemResponse {
    private String itemName;
    private int amount;
    private double unitPrice;
    private double discountPrice;
    private double totalPrice;

    public CartItemResponse(String itemName, int amount, double unitPrice, double discountPrice, double totalPrice) {
        this.itemName = itemName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.discountPrice = discountPrice;
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemResponse that = (CartItemResponse) o;
        return amount == that.amount &&
                Double.compare(that.unitPrice, unitPrice) == 0 &&
                Double.compare(that.discountPrice, discountPrice) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, amount, unitPrice, discountPrice, totalPrice);
    }
}
