package com.kakao.demo.service;

import com.kakao.demo.domain.FinanceAmountRepository;
import com.kakao.demo.service.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class FinanceAmountServiceTest {

    @InjectMocks
    private FinanceAmountService financeAmountService;

    @Mock
    private InstitutionService institutionService;

    @Mock
    private FinanceAmountRepository financeAmountRepository;

    @Test
    @DisplayName("년도별 각 기관들의 지원금액을 합산하고 각 기관들의 합산 금액을 찾는 기능 테스트")
    void findTotalAmountOfInstitutionsByYear() {
        List<DetailAmountsOfInstitutionByYear> detailAmountsOfInstitutionByYears
                = Arrays.asList(new DetailAmountsOfInstitutionByYear(2005, "국민은행", 1500),
                new DetailAmountsOfInstitutionByYear(2005, "하나은행", 1000),
                new DetailAmountsOfInstitutionByYear(2006, "국민은행", 1600),
                new DetailAmountsOfInstitutionByYear(2006, "하나은행", 1000));
        Map<String, Long> detailAmountBy2005 = new HashMap<>();
        detailAmountBy2005.put("국민은행", 1500L);
        detailAmountBy2005.put("하나은행", 1000L);

        Map<String, Long> detailAmountBy2006 = new HashMap<>();
        detailAmountBy2006.put("국민은행", 1600L);
        detailAmountBy2006.put("하나은행", 1000L);

        given(financeAmountRepository.findTotalAmountGroupByInstitutionAndYear()).willReturn(detailAmountsOfInstitutionByYears);

        List<AmountsByYear> expectedAmountsByYears = Arrays.asList(
                new AmountsByYear("2005 년", 2500, detailAmountBy2005),
                new AmountsByYear("2006 년", 2600, detailAmountBy2006));

        List<AmountsByYear> totalAmountsOfInstitutionsByYear = financeAmountService.findTotalAmountOfInstitutionsByYear();
        assertThat(totalAmountsOfInstitutionsByYear).isEqualTo(expectedAmountsByYears);
    }

    @Test
    @DisplayName("가장 높은 지원금액을 받는 기관과 그 기관이 받은 년도를 찾는 기능 테스트")
    void findInstitutionAndYearWithTheHighestAmount() {
        int year = 2014;
        String institution = "주택보증기금";
        Object[] record = {year, institution};
        List<Object[]> fetchedRecords = Collections.singletonList(record);

        given(financeAmountRepository.findInstitutionAndYearWithTheHighestAmount()).willReturn(fetchedRecords);

        InstitutionOfTheHighestAmount institutionAndYearWithTheHighestAmount = financeAmountService.findInstitutionAndYearWithTheHighestAmount();

        assertThat(institutionAndYearWithTheHighestAmount.getYear()).isEqualTo(year);
        assertThat(institutionAndYearWithTheHighestAmount.getInstitution()).isEqualTo(institution);
    }

    @Test
    @DisplayName("특정은행 지원금액의 년도별 평균 중 가장 큰 값과 작은 값을 구하는 기능 테스트")
    void findStatisticAboutInstitution() {
        int minIndex = 0;
        int maxIndex = 1;
        List<SupportedAmountOfInstitution> averageAmounts = Arrays.asList(
                new SupportedAmountOfInstitution(2005, 1111),
                new SupportedAmountOfInstitution(2006, 3001),
                new SupportedAmountOfInstitution(2007, 2000));

        given(financeAmountRepository.findAverageAmountByInstitutionName(anyString())).willReturn(averageAmounts);

        StatisticAboutInstitution statistic = financeAmountService.findStatisticAboutInstitution("외환은행");

        assertThat(statistic.getInstitution()).isEqualTo("외환은행");
        assertThat(statistic.getSupportedAmount().get(minIndex)).isEqualTo(new SupportedAmountOfInstitution(2005, 1111));
        assertThat(statistic.getSupportedAmount().get(maxIndex)).isEqualTo(new SupportedAmountOfInstitution(2006, 3001));
    }
}