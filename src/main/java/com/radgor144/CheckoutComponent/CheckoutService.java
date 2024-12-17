package com.radgor144.CheckoutComponent;

import com.radgor144.CheckoutComponent.CartComponents.CartItem;
import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import com.radgor144.CheckoutComponent.CartComponents.CartResponse;
import com.radgor144.CheckoutComponent.CartComponents.CartService;
import com.radgor144.CheckoutComponent.CartComponents.PricingRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {
    private final CartService cartService;
    private final Map<String, PricingRule> pricingRules;

    @Autowired
    public CheckoutService(CartService cartService) {
        this.cartService = cartService;
        this.pricingRules = initializePricingRules();
    }

    public CartItemResponse addToCart(int idCart, String itemName, int amount) {
        PricingRule rule = pricingRules.get(itemName);
        if (rule == null) throw new RuntimeException("Product not found: " + itemName);

        CartItem existingCartItem = cartService.findItemInCart(idCart, itemName);
        if (existingCartItem != null) {
            existingCartItem.setAmount(existingCartItem.getAmount() + amount);
        } else {
            cartService.addItemToCart(idCart, new CartItem(itemName, amount));
        }

        return createCartItemResponse(rule, itemName, amount);
    }

    public CartResponse getCartDetails(int idCart) {
        List<CartItem> cartItems = cartService.getCartItems(idCart);
        List<CartItemResponse> itemResponses = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : cartItems) {
            PricingRule rule = pricingRules.get(cartItem.getItemName());
            double totalPrice = calculateTotalPrice(rule, cartItem.getAmount());

            CartItemResponse response = createCartItemResponse(rule, cartItem.getItemName(), cartItem.getAmount(), totalPrice);
            itemResponses.add(response);
            totalAmount += totalPrice;
        }

        return new CartResponse(idCart, itemResponses, totalAmount);
    }

    public ResponseEntity removeFromCart(int idCart, String itemName, int amount) {
        CartItem existingCartItem = cartService.findItemInCart(idCart, itemName);

        if (existingCartItem == null) {
            throw new RuntimeException("Item not found in cart: " + itemName);
        }

        if (existingCartItem.getAmount() < amount) {
            throw new RuntimeException("Not enough quantity to remove: " + itemName);
        }

        existingCartItem.setAmount(existingCartItem.getAmount() - amount);

        if (existingCartItem.getAmount() == 0) {
            cartService.removeItemFromCart(idCart, itemName);
        }
        return null;
    }


    private CartItemResponse createCartItemResponse(PricingRule rule, String itemName, int amount) {
        return createCartItemResponse(rule, itemName, amount, calculateTotalPrice(rule, amount));
    }

    private CartItemResponse createCartItemResponse(PricingRule rule, String itemName, int amount, double totalPrice) {
        return new CartItemResponse(itemName, amount, rule.getPrice(), rule.getSpecialPrice(), totalPrice);
    }

    private double calculateTotalPrice(PricingRule rule, int amount) {
        int promoSets = amount / rule.getItemsForDiscount();
        int remainingItems = amount % rule.getItemsForDiscount();
        return (promoSets * rule.getSpecialPrice()) + (remainingItems * rule.getPrice());
    }

    private Map<String, PricingRule> initializePricingRules() {
        Map<String, PricingRule> rules = new HashMap<>();
        rules.put("A", new PricingRule("A", 40, 3, 90));
        rules.put("B", new PricingRule("B", 10, 2, 15));
        rules.put("C", new PricingRule("C", 30, 4, 80));
        rules.put("D", new PricingRule("D", 25, 2, 47));
        return rules;
    }
}
