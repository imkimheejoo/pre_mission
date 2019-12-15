package com.kakao.demo.service;

public class NotFoundAmountException extends RuntimeException {
    public NotFoundAmountException() {
        super("해당하는 금액을 찾지 못했습니다.");
    }
}
