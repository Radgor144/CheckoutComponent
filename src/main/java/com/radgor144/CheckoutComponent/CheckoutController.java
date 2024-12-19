package com.radgor144.CheckoutComponent;

import com.radgor144.CheckoutComponent.CartComponents.CartResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam @Valid @Min(value = 1, message = "IdCart must be greater than or equal to 1") int idCart,
                                       @RequestParam @Valid @NotBlank(message = "Item name cannot be empty") @Size(min = 1, max = 40, message = "Item name must be between 1 and 40 characters")  String item,
                                       @RequestParam @Valid @Min(value = 1, message = "Amount must be greater than or equal to 1") int amount) {
        return ResponseEntity.ok(checkoutService.addToCart(idCart, item, amount));
    }

    @GetMapping("/scan")
    public ResponseEntity<CartResponse> scan(@RequestParam @Valid @Min(value = 1, message = "IdCart must be greater than or equal to 1") int idCart) {
        return ResponseEntity.ok(checkoutService.getCartDetails(idCart));
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeFromCart(@RequestParam @Valid @Min(value = 1, message = "IdCart must be greater than or equal to 1") int idCart,
                                         @RequestParam @Valid @NotBlank(message = "Item name cannot be empty") @Size(min = 1, max = 40, message = "Item name must be between 1 and 40 characters")  String item,
                                         @RequestParam @Valid @Min(value = 1, message = "Amount must be greater than or equal to 1") int amount) {
        return checkoutService.removeFromCart(idCart, item, amount);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestParam @Valid @Min(value = 1, message = "IdCart must be greater than or equal to 1") int idCart) {
        checkoutService.clearCart(idCart);
        return ResponseEntity.ok("Cart cleared successfully.");
    }
}
