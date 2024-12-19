package com.radgor144.CheckoutComponent.CartComponents;

import java.util.List;
import java.util.Objects;

public class CartResponse {
    private int cartId;
    private List<CartItemResponse> items;
    private double totalAmount;

    public CartResponse(int cartId, List<CartItemResponse> items, double totalAmount) {
        this.cartId = cartId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartResponse that = (CartResponse) o;
        return cartId == that.cartId &&
                Double.compare(that.totalAmount, totalAmount) == 0 &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, items, totalAmount);
    }
}

