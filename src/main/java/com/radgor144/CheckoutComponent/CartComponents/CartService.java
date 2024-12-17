package com.radgor144.CheckoutComponent.CartComponents;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final Map<Integer, Cart> carts = new HashMap<>();

    public CartItem findItemInCart(int idCart, String itemName) {
        Cart cart = carts.get(idCart);
        if (cart == null) {
            return null;
        }
        return cart.getItems().stream()
                .filter(item -> item.getItemName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public void removeItemFromCart(int idCart, String itemName) {
        Cart cart = carts.get(idCart);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getItemName().equals(itemName));
        }
    }

    public boolean cartExists(int idCart) {
        return carts.containsKey(idCart);
    }

    public void clearCart(int idCart) {
        if (carts.containsKey(idCart)) {
            carts.get(idCart).getItems().clear();
        }
    }



    public void addItemToCart(int idCart, CartItem cartItem) {
        Cart cart = carts.computeIfAbsent(idCart, id -> new Cart(id));
        cart.addItem(cartItem);
    }

    public List<CartItem> getCartItems(int idCart) {
        if (!carts.containsKey(idCart)) {
            throw new RuntimeException("No cart found with id: " + idCart);
        }
        return carts.get(idCart).getItems();
    }
}
