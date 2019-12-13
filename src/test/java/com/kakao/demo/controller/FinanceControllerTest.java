package com.kakao.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void findFinanceStatus() {
        webTestClient.get().uri("/api/load").exchange()
                .expectStatus().isOk();
    }
}