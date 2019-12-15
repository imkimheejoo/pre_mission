package com.kakao.demo.service.dto;

import java.util.Objects;

public class SupportedAmountOfInstitution {
    private int year;
    private int amount;

    private SupportedAmountOfInstitution() {
    }

    public SupportedAmountOfInstitution(int year, int amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportedAmountOfInstitution that = (SupportedAmountOfInstitution) o;
        return year == that.year &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, amount);
    }
}
