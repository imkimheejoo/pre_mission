package com.kakao.demo.service.dto;

import java.util.Objects;

public class InstitutionOfTheHighestAmount {
    private int year;
    private String institution;

    public InstitutionOfTheHighestAmount(int year, String institution) {
        this.year = year;
        this.institution = institution;
    }

    public int getYear() {
        return year;
    }

    public String getInstitution() {
        return institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionOfTheHighestAmount that = (InstitutionOfTheHighestAmount) o;
        return year == that.year &&
                Objects.equals(institution, that.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, institution);
    }
}
