package com.kakao.demo.service.dto;

import com.kakao.demo.utils.DataConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Measures {
    public static final int YEAR_INDEX = 0;
    public static final int MONTH_INDEX = 1;

    private int year;
    private int month;
    private List<Integer> measures;

    private Measures() {
    }

    private Measures(int year, int month, List<Integer> measures) {
        this.year = year;
        this.month = month;
        this.measures = measures;
    }

    public static Measures valueOf(String[] measuresByDate) {
        int year = Integer.parseInt(measuresByDate[YEAR_INDEX]);
        int month = Integer.parseInt(measuresByDate[MONTH_INDEX]);

        List<String> measures = DataConverter.extractInstitutionNames(Arrays.asList(measuresByDate));
        List<Integer> removedSeparatorMeasures = DataConverter.convertStringToInt(measures);

        return new Measures(year, month, removedSeparatorMeasures);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getMeasures() {
        return measures;
    }

    public int getMeasure(int index) {
        return measures.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measures measures1 = (Measures) o;
        return year == measures1.year &&
                month == measures1.month &&
                Objects.equals(measures, measures1.measures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, measures);
    }
}
