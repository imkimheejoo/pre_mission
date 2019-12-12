package com.kakao.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
class FinanceServiceTest {

    @InjectMocks
    private FinanceService financeService;

    @Test
    @DisplayName("파일 불러오기")
    void loadFinanceStatus() {
        assertDoesNotThrow(() -> financeService.loadFinanceStatus());
    }
}