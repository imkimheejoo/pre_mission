package com.kakao.demo.service;

import com.kakao.demo.domain.AmountRepository;
import com.kakao.demo.service.dto.AmountByYear;
import com.kakao.demo.service.dto.DetailAmountsOfInstitutionByYear;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class FinanceServiceTest {

    @InjectMocks
    private FinanceService financeService;

    @Mock
    private InstitutionService institutionService;

    @Mock
    private AmountRepository amountRepository;

    @Test
    @DisplayName("년도별 각 기관들의 지원금액을 합산하고 각 기관들의 합산 금액을 찾는 기능 테스트")
    void findTotalAmountOfInstitutionsByYear() {
        List<DetailAmountsOfInstitutionByYear> detailAmountsOfInstitutionByYears
                = Arrays.asList(new DetailAmountsOfInstitutionByYear(2005, "국민은행",1500),
                new DetailAmountsOfInstitutionByYear(2005, "하나은행",1000),
                new DetailAmountsOfInstitutionByYear(2006, "국민은행",1600),
                new DetailAmountsOfInstitutionByYear(2006, "하나은행",1000));
        Map<String, Long> detailAmountBy2005 = new HashMap<>();
        detailAmountBy2005.put("국민은행", 1500L);
        detailAmountBy2005.put("하나은행", 1000L);

        Map<String, Long> detailAmountBy2006 = new HashMap<>();
        detailAmountBy2006.put("국민은행", 1600L);
        detailAmountBy2006.put("하나은행", 1000L);

        given(amountRepository.findTotalPriceGroupByInstitutionAndYear()).willReturn(detailAmountsOfInstitutionByYears);

        List<AmountByYear> expectedAmountByYears = Arrays.asList(
                new AmountByYear("2005 년",2500, detailAmountBy2005),
                new AmountByYear("2006 년",2600, detailAmountBy2006));

        List<AmountByYear> totalAmountsOfInstitutionsByYear = financeService.findTotalAmountOfInstitutionsByYear();
        assertThat(totalAmountsOfInstitutionsByYear).isEqualTo(expectedAmountByYears);
    }
}