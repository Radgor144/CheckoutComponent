package com.radgor144.CheckoutComponent.CartComponents.Validation;

import com.radgor144.CheckoutComponent.CartComponents.PricingRule;
import com.radgor144.CheckoutComponent.Exceptions.CustomCartException;

import java.util.Map;

public class CartValidator {

    public static void validateCartId(int idCart) {
        if (idCart <= 0) {
            throw new CustomCartException("Cart ID must be greater than 0.");
        }
    }

    public static void validateAmount(int amount) {
        if (amount <= 0) {
            throw new CustomCartException("Amount must be greater than 0.");
        }
    }

    public static void validateItemName(String itemName, Map<String, PricingRule> pricingRules) {
        if (itemName == null || itemName.isEmpty()) {
            throw new CustomCartException("Item name cannot be null or empty.");
        }
        if (!pricingRules.containsKey(itemName)) {
            throw new CustomCartException("Product not found: " + itemName);
        }
    }
}

