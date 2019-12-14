package com.kakao.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstitutionControllerTest extends FinanceControllerTest{

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient.get().uri("/api/load")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("금융기관 목록 출력하는 요청 테스트")
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