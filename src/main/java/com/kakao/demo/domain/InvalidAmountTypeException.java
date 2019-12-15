package com.kakao.demo.domain;

public class InvalidAmountTypeException extends RuntimeException {
    public InvalidAmountTypeException() {
        super("지원 금액은 음수가 될 수 없습니다.");
    }
}
