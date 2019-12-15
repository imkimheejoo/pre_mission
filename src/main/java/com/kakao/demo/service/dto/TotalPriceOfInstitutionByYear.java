package com.kakao.demo.service.dto;

public class TotalPriceOfInstitutionByYear {

    private int year;
    private String name;
    private long sum;

    public TotalPriceOfInstitutionByYear(int year, String name, long sum) {
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
}
