package com.radgor144.CheckoutComponent;

import com.radgor144.CheckoutComponent.CartComponents.CartItem;
import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import com.radgor144.CheckoutComponent.CartComponents.CartResponse;
import com.radgor144.CheckoutComponent.CartComponents.CartService;
import com.radgor144.CheckoutComponent.CartComponents.PricingRule;
import com.radgor144.CheckoutComponent.CartComponents.Validation.CartValidator;
import com.radgor144.CheckoutComponent.Exceptions.CustomCartException;
import org.springframework.beans.factory.annotation.Autowired;
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
        CartValidator.validateCartId(idCart);
        CartValidator.validateAmount(amount);
        CartValidator.validateItemName(itemName, pricingRules);

        PricingRule rule = pricingRules.get(itemName);
        CartItem existingCartItem = cartService.findItemInCart(idCart, itemName);

        if (existingCartItem != null) {
            existingCartItem.setAmount(existingCartItem.getAmount() + amount);
        } else {
            cartService.addItemToCart(idCart, new CartItem(itemName, amount));
        }

        return createCartItemResponseWithCalculatedPrice(rule, itemName, amount);
    }

    public CartResponse getCartDetails(int idCart) {
        CartValidator.validateCartId(idCart);

        List<CartItem> cartItems = cartService.getCartItems(idCart);
        List<CartItemResponse> itemResponses = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : cartItems) {
            PricingRule rule = pricingRules.get(cartItem.getItemName());
            double totalPrice = calculateTotalPrice(rule, cartItem.getAmount());

            CartItemResponse response = createCartItemResponseWithGivenPrice(rule, cartItem.getItemName(), cartItem.getAmount(), totalPrice);
            itemResponses.add(response);
            totalAmount += totalPrice;
        }

        return new CartResponse(idCart, itemResponses, totalAmount);
    }

    public void removeFromCart(int idCart, String itemName, int amount) {
        CartValidator.validateCartId(idCart);
        CartValidator.validateAmount(amount);
        CartValidator.validateItemName(itemName, pricingRules);

        CartItem existingCartItem = cartService.findItemInCart(idCart, itemName);

        if (existingCartItem == null) {
            throw new CustomCartException("Item not found in cart: " + itemName);
        }

        if (existingCartItem.getAmount() < amount) {
            throw new CustomCartException("Not enough quantity to remove: " + itemName);
        }

        existingCartItem.setAmount(existingCartItem.getAmount() - amount);

        if (existingCartItem.getAmount() == 0) {
            cartService.removeItemFromCart(idCart, itemName);
        }
    }

    public void clearCart(int idCart) {
        CartValidator.validateCartId(idCart);
        if (!cartService.cartExists(idCart)) {
            throw new CustomCartException("Cart not found: " + idCart);
        }
        cartService.clearCart(idCart);
    }


    private CartItemResponse createCartItemResponseWithCalculatedPrice(PricingRule rule, String itemName, int amount) {
        return createCartItemResponseWithGivenPrice(rule, itemName, amount, calculateTotalPrice(rule, amount));
    }

    private CartItemResponse createCartItemResponseWithGivenPrice(PricingRule rule, String itemName, int amount, double totalPrice) {
        return new CartItemResponse(itemName, amount, rule.getPrice(), rule.getSpecialPrice(), totalPrice);
    }

    public double calculateTotalPrice(PricingRule rule, int amount) {
        int promoSets = amount / rule.getItemsForDiscount();
        int remainingItems = amount % rule.getItemsForDiscount();
        return (promoSets * rule.getSpecialPrice()) + (remainingItems * rule.getPrice());
    }

    public Map<String, PricingRule> initializePricingRules() {
        Map<String, PricingRule> rules = new HashMap<>();
        rules.put("A", new PricingRule("A", 40, 3, 90));
        rules.put("B", new PricingRule("B", 10, 2, 15));
        rules.put("C", new PricingRule("C", 30, 4, 80));
        rules.put("D", new PricingRule("D", 25, 2, 47));
        return rules;
    }
}
