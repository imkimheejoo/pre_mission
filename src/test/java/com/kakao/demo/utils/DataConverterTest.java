package com.kakao.demo.utils;

import com.kakao.demo.service.dto.Measures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataConverterTest {

    @Test
    @DisplayName("단위 없애는 테스트")
    void deleteBenchMark() {
        List<String> inputs = Arrays.asList("주택도시기금1)(억원)", "국민은행(억원)", "우리은행(억원)", "신한은행(억원)",
                "한국시티은행(억원)", "하나은행(억원)", "농협은행/수협은행(억원)", "외환은행(억원)", "기타은행(억원)");

        List<String> expectedInputs = Arrays.asList("주택도시기금", "국민은행", "우리은행", "신한은행",
                "한국시티은행", "하나은행", "농협은행/수협은행", "외환은행", "기타은행");

        assertThat(DataConverter.deleteBenchMark(inputs)).isEqualTo(expectedInputs);
    }

    @Test
    @DisplayName("기관이름들만 추출하는 기능 테스트")
    void extractInstitutionNames() {
        List<String> inputs = Arrays.asList("년도", "월", "주택도시기금1)(억원)", "국민은행(억원)", "우리은행(억원)", "신한은행(억원)",
                "한국시티은행(억원)", "하나은행(억원)", "농협은행/수협은행(억원)", "외환은행(억원)", "기타은행(억원)");

        List<String> expectedInputs = Arrays.asList("주택도시기금", "국민은행", "우리은행", "신한은행",
                "한국시티은행", "하나은행", "농협은행/수협은행", "외환은행", "기타은행");

        List<String> results = DataConverter.extractInstitutionNames(inputs);

        assertThat(results).isEqualTo(expectedInputs);
    }

    @Test
    @DisplayName("문자형 숫자값을 숫자로 변환하는 기능 테스트")
    void convertStringToInt() {
        List<String> financeHousings = Arrays.asList("2017", "2,995", "0,0", "-1");
        List<Integer> expectedFinanceHousings = Arrays.asList(2017, 2995, 0, -1);

        assertThat(DataConverter.convertStringToInt(financeHousings)).isEqualTo(expectedFinanceHousings);
    }

    @Test
    @DisplayName("측정 날짜를 제외하고 측정값을 추출하는 기능 테스트")
    void extractMeasures() {
        String[] input = new String[]{"2005", "1", "1,019", "1,000,846", "82", "9", "0"};
        List<Integer> expectedMeasuresResult = Arrays.asList(1019, 1000846, 82, 9, 0);

        List<Measures> measures = DataConverter.extractMeasures(Collections.singletonList(input));

        Measures measuresResult = measures.get(0);

        assertThat(measures.size()).isEqualTo(1);
        assertThat(measuresResult.getYear()).isEqualTo(2005);
        assertThat(measuresResult.getMonth()).isEqualTo(1);
        assertThat(measuresResult.getMeasures()).isEqualTo(expectedMeasuresResult);

    }
}