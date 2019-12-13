package com.kakao.demo.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FinanceDate {
    private String year;
    private String month;

    protected FinanceDate() {
    }

    public FinanceDate(String year, String month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinanceDate that = (FinanceDate) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }
}
