package com.kakao.demo.service.dto;

import java.util.Objects;

public class DetailAmountsOfInstitutionByYear implements Comparable<DetailAmountsOfInstitutionByYear> {
    private int year;
    private String name;
    private long sum;

    private DetailAmountsOfInstitutionByYear() {
    }

    public DetailAmountsOfInstitutionByYear(int year, String name, long sum) {
        this.year = year;
        this.name = name;
        this.sum = sum;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public int compareTo(DetailAmountsOfInstitutionByYear o) {
        return Long.compare(o.sum, this.sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailAmountsOfInstitutionByYear that = (DetailAmountsOfInstitutionByYear) o;
        return year == that.year &&
                sum == that.sum &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, name, sum);
    }
}
