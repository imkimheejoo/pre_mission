package com.kakao.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;

@AutoConfigureWebTestClient(timeout = "20000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstitutionControllerTest extends FinanceAmountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient.get().uri("/api/load")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DirtiesContext
    void findFinanceInstitutions() {
        List<String> expectedInstitutionNames =
                Arrays.asList("우리은행", "신한은행", "하나은행", "농협은행/수협은행", "외환은행", "기타은행");

        WebTestClient.BodyContentSpec bodyContentSpec = webTestClient.get().uri("/api/find/institutions")
                .exchange()
                .expectStatus().isOk()
                .expectBody();

        for (String institutionName : expectedInstitutionNames) {
            bodyContentSpec.jsonPath("$..name").value(hasItem(institutionName));
        }
    }
}