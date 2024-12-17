package com.radgor144.CheckoutComponent;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {

    private final CartService cartService;
    private final Map<String, Item> pricingRules;

    public CheckoutService(CartService cartService) {
        this.cartService = cartService;

        this.pricingRules = new HashMap<>();
        pricingRules.put("A", new Item("A", 40, 3, 90));
        pricingRules.put("B", new Item("B", 10, 2, 15));
        pricingRules.put("C", new Item("C", 30, 4, 80));
        pricingRules.put("D", new Item("D", 25, 2, 47));
    }

    public Item addToCart(int idCart, String itemName, int amount) {
        if (!pricingRules.containsKey(itemName)) {
            throw new RuntimeException("Not found product: " + itemName);
        }

        Item ruleItem = pricingRules.get(itemName);

        Item newItem = new Item(itemName, ruleItem.getPrice(), ruleItem.getItemsForDiscount(), ruleItem.getSpecialPrice());
        newItem.setAmount(amount);

        cartService.addItemToCart(idCart, newItem);
        return newItem;
    }

    public List<Item> getCartItems(int idCart) {
        return cartService.getCartById(idCart);
    }
}
