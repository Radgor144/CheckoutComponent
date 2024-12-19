package com.radgor144.CheckoutComponent.util;

import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import com.radgor144.CheckoutComponent.CartComponents.PricingRule;
import com.radgor144.CheckoutComponent.CheckoutService;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CreateCartUtil {

    public static CartItemResponse createCart(CheckoutService checkoutService, String item, int amount) {
        Map<String, PricingRule> pricingRules = checkoutService.initializePricingRules();
        PricingRule rule = pricingRules.get(item);
        return new CartItemResponse(item, amount, rule.getPrice(), rule.getSpecialPrice(), checkoutService.calculateTotalPrice(rule, amount));
    }
}
