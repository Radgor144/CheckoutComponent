package com.radgor144.CheckoutComponent.CheckoutControllerTests;

import com.radgor144.CheckoutComponent.Exceptions.ErrorResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@SpringBootTest(properties =
        "spring.cloud.openfeign.client.config.weather-client.url=http://localhost:${wiremock.server.port}",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
public class shouldReturnBadRequestWhenUserProvidesInvalidArgumentsForAdd {

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest(name = "{0}")
    @MethodSource("shouldReturn400ErrorWhenInvalidParameterIsProvidedToAddItemToCartMethodSource")
    public void happypath(String testName, int idCart, String item, int amount, String expectedErrorMessage) {
        //given

        stubFor(post("/api/add?idCart=" + idCart + "&item=" + item + "&amount=" + amount)
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(expectedErrorMessage))
        );

        //when
        var result = webTestClient
                .post()
                .uri("/api/add?idCart=" + idCart + "&item=" + item + "&amount=" + amount)
                .exchange();

        //then
        result
                .expectStatus().isEqualTo(400)
                .expectBody(ErrorResponse.class)
                .isEqualTo(new ErrorResponse(expectedErrorMessage));
    }

    public static Stream<Arguments> shouldReturn400ErrorWhenInvalidParameterIsProvidedToAddItemToCartMethodSource() {
        return Stream.of(
                Arguments.of("IdCart - Invalid", -1, "A", 1, "Error while user entered invalid arguments: addToCart.idCart: IdCart must be greater than or equal to 1"),
                Arguments.of("Empty item name", 1, "", 1, "Error while user entered invalid arguments: addToCart.item: Item name must be between 1 and 40 characters, addToCart.item: Item name cannot be empty"),
                Arguments.of("Amount - Invalid", 1, "A", -1, "Error while user entered invalid arguments: addToCart.amount: Amount must be greater than or equal to 1"),
                Arguments.of("Product not found", 1, "NotFound", 1, "Product not found: NotFound")
        );
    }
}
