package com.radgor144.CheckoutComponent;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final Map<Integer, Cart> carts;

    public CartService() {
        this.carts = new HashMap<>();
    }

    public void addItemToCart(int idCart, Item newItem) {
        if (!carts.containsKey(idCart)) {
            carts.put(idCart, new Cart(idCart));
        }
        Cart cart = carts.get(idCart);
        cart.addItem(newItem);
    }

    public List<Item> getCartById(int idCart) {
        if (!carts.containsKey(idCart)) {
            throw new RuntimeException("Not found idCart: " + idCart);
        }
        return carts.get(idCart).getItems();
    }
}
