package com.kakao.demo.service.dto;

public class TotalPriceOfInstitutionByYear {

    private int year;
    private long institutionId;
    private long sum;

    public TotalPriceOfInstitutionByYear(int year, long institutionId, long sum) {
        this.year = year;
        this.institutionId = institutionId;
        this.sum = sum;
    }

    public int getYear() {
        return year;
    }

    public long getInstitutionId() {
        return institutionId;
    }

    public long getSum() {
        return sum;
    }
}
