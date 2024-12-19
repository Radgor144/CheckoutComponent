package com.radgor144.CheckoutComponent.CheckoutServiceTests;

import com.radgor144.CheckoutComponent.CartComponents.CartItemResponse;
import com.radgor144.CheckoutComponent.CheckoutService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.radgor144.CheckoutComponent.util.CreateCartUtil.createCart;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ShoudAddItemsToCartSuccessfully {

    @Autowired
    private CheckoutService checkoutService;

    String item = "B";
    int amount = 2;
    double totalPrice = 15;

    @ParameterizedTest
    @MethodSource("provideCartItems")
    public void shouldAddItemsToCartWithCorrectPriceCalculation(int idCart, String item, int amount) {
        //given
        CartItemResponse expectedCartItemResponse = createCart(checkoutService, item, amount);

        //when
        var result = checkoutService.addToCart(idCart, item, amount);

        //then
        assertEquals(expectedCartItemResponse, result);
    }

    public static Stream<Arguments> provideCartItems() {
        return Stream.of(
                Arguments.of(1, "B", 2),
                Arguments.of(2, "A", 3),
                Arguments.of(3, "C", 1),
                Arguments.of(4, "D", 5),
                Arguments.of(5, "B", 4)
        );
    }
}
