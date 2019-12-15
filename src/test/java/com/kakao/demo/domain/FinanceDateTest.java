package com.kakao.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinanceDateTest {
    private int year = 2005;
    private int month = 3;

    @Test
    @DisplayName("FinanceDate 객체 생성 테스트")
    public void of() {
        assertDoesNotThrow(() -> {
            FinanceDate.of(year, month);
        });
    }

    @Test
    @DisplayName("'월'이 1~12이 아닐 때 예외를 던지는 로직 테스트")
    public void generateInvalidFinanceDateTypeException() {
        assertThrows(InvalidFinanceDateTypeException.class, () -> {
            int exceptionMonth = 13;
            FinanceDate.of(year, exceptionMonth);
        });

        assertThrows(InvalidFinanceDateTypeException.class, () -> {
            int exceptionMonth = 0;
            FinanceDate.of(year, exceptionMonth);
        });
    }

    @Test
    @DisplayName("'년'이 0미만일 때 예외를 던지는 로직 테스트")
    public void generateInvalidFinanceDateTypeException2() {
        assertThrows(InvalidFinanceDateTypeException.class, () -> {
            int exceptionYear = -1;
            FinanceDate.of(exceptionYear, month);
        });
    }
}