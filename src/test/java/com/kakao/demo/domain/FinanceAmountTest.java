package com.kakao.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinanceAmountTest {
    private FinanceDate date = FinanceDate.of(2005, 1);
    private Institution institution = Institution.of("국민은행");

    @Test
    @DisplayName("FinanceAmount 객체 생성 테스트")
    public void of() {
        assertDoesNotThrow(() -> {
            int amount = 10;
            new FinanceAmount(amount, date, institution);
        });
    }

    @Test
    @DisplayName("지원금액이 음수일 때 예외를 던지는 로직 테스트")
    public void createInvalidAmountTypeException() {
        assertThrows(InvalidAmountTypeException.class, () -> {
            int exceptionAmount = -1;
            new FinanceAmount(exceptionAmount, date, institution);
        });
    }


}