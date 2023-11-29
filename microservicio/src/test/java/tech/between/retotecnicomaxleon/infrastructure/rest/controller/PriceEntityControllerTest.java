package tech.between.retotecnicomaxleon.infrastructure.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import tech.between.retotecnicomaxleon.infrastructure.dto.AuthenticationRequest;
import tech.between.retotecnicomaxleon.infrastructure.dto.AuthenticationResponse;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PriceEntityControllerTest {
    @Autowired
    WebTestClient webTestClient;

    String token;

    @BeforeEach
    void init() {
        token = getAuthToken();
    }

    @Test
    void testScenario1() {

        byte[] body = webTestClient.get()
                .uri("/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBody();

        PriceResponse price = Util.convertBytesToResponse(body, PriceResponse.class);
        price.getPriceFinal();
    }

    @Test
    void testScenario2() {
        token = getAuthToken();
        webTestClient.get()
                .uri("/prices?applicationDate=2020-06-14-16.00.00&productId=35455&brandId=1")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void testScenario3() {

        webTestClient.get()
                .uri("/prices?applicationDate=2020-06-14-21.00.00&productId=35455&brandId=1")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void testScenario4() {
        webTestClient.get()
                .uri("/prices?applicationDate=2020-06-15-10.00.00&productId=35455&brandId=1")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void testScenario5() {
        webTestClient.get()
                .uri("/prices?applicationDate=2020-06-16-21.00.00&productId=35455&brandId=1")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    private String getAuthToken() {
        String authUrl = "/auth/authenticate";

        AuthenticationRequest request = new AuthenticationRequest("max", "123456");

        byte[] body = webTestClient.post()
                .uri(authUrl)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBody();


        return Util.convertBytesToResponse(body, AuthenticationResponse.class).getJwt();
    }
}