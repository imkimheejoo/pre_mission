package com.kakao.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findFinanceData() {
        webTestClient.get().uri("/api/load").exchange()
                .expectStatus().isOk();
    }

    @Test
    void findTotalAmountAboutInstitutionByYear() {
        webTestClient.get().uri("/api/load").exchange()
                .expectStatus().isOk();

        WebTestClient.BodyContentSpec bodyContentSpec = webTestClient.get().uri("/api/find/status").exchange()
                .expectStatus().isOk().expectBody();

        bodyContentSpec
                .jsonPath("$.name").value(equalTo("주택금융 공급현황"))
                .jsonPath("$..amountsByYear").exists()
                .jsonPath("$..year").exists()
                .jsonPath("$..totalAmount").exists()
                .jsonPath("$..detailAmount").exists();
    }
}