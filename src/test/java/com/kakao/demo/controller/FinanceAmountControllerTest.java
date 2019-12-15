package com.kakao.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.*;

@AutoConfigureWebTestClient(timeout = "20000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceAmountControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient.get().uri("/api/load").exchange()
                .expectStatus().isOk();
    }

    @Test
    @DirtiesContext
    void findTotalAmountAboutInstitutionByYear() {
        webTestClient.get().uri("/api/find/status").exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.name").value(equalTo("주택금융 공급현황"))
                .jsonPath("$..amountsByYear").exists()
                .jsonPath("$..totalAmount").value(hasSize(13))
                .jsonPath("$..detailAmount").value(hasSize(13))
                .jsonPath("$..year").value(hasItem("2016 년"));
    }

    @Test
    @DirtiesContext
    void findInstitutionOfMaxAmount() {
        webTestClient.get().uri("/api/find/max/institution").exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.year").value(equalTo(2014))
                .jsonPath("$.bank").value(equalTo("주택도시기금"));
    }

    @Test
    @DirtiesContext
    void findStatisticAboutKEB() {
        webTestClient.get().uri("/api/find/statistic/keb").exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.bank").value(equalTo("외환은행"))
                .jsonPath("$.support_amount..year").value(hasSize(2))
                .jsonPath("$.support_amount..amount").value(hasSize(2));
    }

}