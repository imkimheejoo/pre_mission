package com.kakao.demo.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FinanceDate {
    private static final int MIN_YEAR = 0;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;

    private int year;
    private int month;

    protected FinanceDate() {
    }

    private FinanceDate(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public static FinanceDate of(int year, int month) {
        checkValidDate(year, month);
        return new FinanceDate(year, month);
    }

    private static void checkValidDate(int year, int month) {
        if (isInValidYear(year) || isInValidMonth(month)) {
            throw new InvalidFinanceDateTypeException();
        }
    }

    private static boolean isInValidYear(int year) {
        return year < MIN_YEAR;
    }

    private static boolean isInValidMonth(int month) {
        return MIN_MONTH > month || MAX_MONTH < month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
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
