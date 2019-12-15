package com.kakao.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FinanceAmountTest {

    @Test
    @DisplayName("지원금액이 음수일 때 예외를 던지는 로직 테스트")
    public void createInvalidAmountTypeException() {
        assertThrows(InvalidAmountTypeException.class, () ->
        {
            FinanceDate date = FinanceDate.of(2005, 1);
            Institution institution = Institution.of("국민은행");
            new FinanceAmount(-1, date, institution);
        });
    }


}