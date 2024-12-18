package com.radgor144.CheckoutComponent.CheckoutControllerTests;

import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static com.radgor144.CheckoutComponent.util.RequestUtil.postAddToCart;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class shouldAddItemToCartSuccessfully {

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @MethodSource("provideCartItemsMethodSource")
    void shouldAddItemToCartSuccessfully(int idCart, String item, int amount, double unitPrice, double discountPrice, double totalPrice) {
        //given
        CartItemResponse cartItemResponse = new CartItemResponse(item, amount, unitPrice, discountPrice, totalPrice);

        //when
        var result = postAddToCart(webTestClient, idCart, item, amount);

        //then
        result
                .expectBody(CartItemResponse.class)
                .consumeWith(response -> {
                            assertEquals(cartItemResponse, response.getResponseBody());
                            response.getStatus().isSameCodeAs(HttpStatusCode.valueOf(200));
                        }
                );
    }

    public static Stream<Arguments> provideCartItemsMethodSource() {
        return Stream.of(
                Arguments.of(1, "A", 1, 40.0, 90.0, 40.0),
                Arguments.of(2, "B", 1, 10.0, 15.0, 10.0),
                Arguments.of(3, "C", 1, 30.0, 80.0, 30.0),
                Arguments.of(4, "D", 1, 25.0, 47.0, 25.0),
                // item with discount amount
                Arguments.of(5, "A", 3, 40.0, 90.0, 90.0),
                Arguments.of(6, "B", 2, 10.0, 15.0, 15.0),
                Arguments.of(7, "C", 4, 30.0, 80.0, 80.0),
                Arguments.of(8, "D", 2, 25.0, 47.0, 47.0),
                // item with discount amount + 1
                Arguments.of(9, "A", 4, 40.0, 90.0, 130.0),
                Arguments.of(10, "B", 3, 10.0, 15.0, 25.0),
                Arguments.of(11, "C", 5, 30.0, 80.0, 110.0),
                Arguments.of(12, "D", 3, 25.0, 47.0, 72.0),
                // item with the same idcarts
                Arguments.of(13, "A", 3, 40.0, 90.0, 90.0),
                Arguments.of(13, "B", 2, 10.0, 15.0, 15.0),
                Arguments.of(13, "C", 4, 30.0, 80.0, 80.0),
                Arguments.of(13, "D", 2, 25.0, 47.0, 47.0)
        );
    }
}
