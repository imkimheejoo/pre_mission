package com.kakao.demo.domain;

public class InvalidFinanceDateTypeException extends RuntimeException {
    public InvalidFinanceDateTypeException() {
        super("적절한 년/월이 아닙니다.");
    }
}
