package com.kakao.demo.service.dto;

import java.util.Map;
import java.util.Objects;

public class AmountByYear {
    private String year;
    private long totalAmount;
    private Map<String, Long> detailAmount;

    private AmountByYear() {
    }

    public AmountByYear(String year, long totalAmount, Map<String, Long> detailAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
        this.detailAmount = detailAmount;
    }

    public String getYear() {
        return year;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public Map<String, Long> getDetailAmount() {
        return detailAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountByYear that = (AmountByYear) o;
        return totalAmount == that.totalAmount &&
                Objects.equals(year, that.year) &&
                Objects.equals(detailAmount, that.detailAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, totalAmount, detailAmount);
    }
}
