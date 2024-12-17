package com.radgor144.CheckoutComponent;

import com.radgor144.CheckoutComponent.CartComponents.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam int idCart, @RequestParam String item, @RequestParam int amount) {
        return ResponseEntity.ok(checkoutService.addToCart(idCart, item, amount));
    }

    @GetMapping("/scan")
    public ResponseEntity<CartResponse> scan(@RequestParam int idCart) {
        return ResponseEntity.ok(checkoutService.getCartDetails(idCart));
    }

}
