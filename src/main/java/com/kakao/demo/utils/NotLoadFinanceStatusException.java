package com.kakao.demo.utils;

public class NotLoadFinanceStatusException extends RuntimeException {
    public NotLoadFinanceStatusException(Exception cause) {
        super(cause);
    }
}
