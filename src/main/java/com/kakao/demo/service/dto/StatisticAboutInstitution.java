package com.kakao.demo.service.dto;

import java.util.List;
import java.util.Objects;

public class StatisticAboutInstitution {
    private String institution;
    private List<SupportedAmountOfInstitution> supportedAmount;

    private StatisticAboutInstitution() {
    }

    public StatisticAboutInstitution(String institution, List<SupportedAmountOfInstitution> supportedAmount) {
        this.institution = institution;
        this.supportedAmount = supportedAmount;
    }

    public String getInstitution() {
        return institution;
    }

    public List<SupportedAmountOfInstitution> getSupportedAmount() {
        return supportedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticAboutInstitution that = (StatisticAboutInstitution) o;
        return Objects.equals(institution, that.institution) &&
                Objects.equals(supportedAmount, that.supportedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institution, supportedAmount);
    }
}
