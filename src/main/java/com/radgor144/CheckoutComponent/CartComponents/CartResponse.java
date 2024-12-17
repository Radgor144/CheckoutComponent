package com.radgor144.CheckoutComponent.CartComponents;

import java.util.List;

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
}

