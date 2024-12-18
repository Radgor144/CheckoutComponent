package com.radgor144.CheckoutComponent.util;

import lombok.experimental.UtilityClass;
import org.springframework.test.web.reactive.server.WebTestClient;

@UtilityClass
public class RequestUtil {
    public static WebTestClient.ResponseSpec postAddToCart(WebTestClient webTestClient, int idCart, String item, int amount) {
        return webTestClient
                .post()
                .uri("/api/add?idCart=" + idCart + "&item=" + item + "&amount=" + amount)
                .exchange();
    }
}
