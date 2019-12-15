package com.kakao.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void loadFinanceData() {
        webTestClient.get().uri("/api/load").exchange()
                .expectStatus().isOk();
    }

    @Test
    void findTotalAmountAboutInstitutionByYear() {
        webTestClient.get().uri("/api/find/status").exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.name").value(equalTo("주택금융 공급현황"))
                .jsonPath("$..amountsByYear").exists()
                .jsonPath("$..totalAmount").value(hasItem(304069))
                .jsonPath("$..detailAmount").value(hasSize(13))
                .jsonPath("$..year").value(hasItem("2016 년"));
    }
}