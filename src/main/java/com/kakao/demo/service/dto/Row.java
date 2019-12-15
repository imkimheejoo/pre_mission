package com.kakao.demo.service.dto;

import java.util.List;
import java.util.Objects;

public class Row {
    private int year;
    private int month;
    private List<Integer> measures;

    private Row() {
    }

    public Row(int year, int month, List<Integer> measures) {
        this.year = year;
        this.month = month;
        this.measures = measures;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getAmounts() {
        return measures;
    }

    public int getMeasure(int index) {
        return measures.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row1 = (Row) o;
        return year == row1.year &&
                month == row1.month &&
                Objects.equals(measures, row1.measures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, measures);
    }
}
