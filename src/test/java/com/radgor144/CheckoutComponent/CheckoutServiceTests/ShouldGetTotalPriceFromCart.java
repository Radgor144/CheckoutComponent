package com.radgor144.CheckoutComponent.CheckoutServiceTests;

import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import com.radgor144.CheckoutComponent.CheckoutService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ShouldGetTotalPriceFromCart {

    @Autowired
    private CheckoutService checkoutService;

    @ParameterizedTest
    @MethodSource("provideCartDetails")
    public void shouldGetTotalPriceFromCart(int idCart, String item, int amount) {
        // given
        CartItemResponse expectedData = checkoutService.addToCart(idCart, item, amount);

        // when
        var result = checkoutService.getCartDetails(idCart);

        // then
        assertEquals(expectedData.getTotalPrice(), result.getTotalAmount(), 0.01);

    }

    public static Stream<Arguments> provideCartDetails() {
        return Stream.of(
                Arguments.of(1, "B", 4),
                Arguments.of(2, "B", 3),
                Arguments.of(3, "A", 5),
                Arguments.of(4, "C", 2),
                Arguments.of(5, "D", 6)
        );
    }
}
