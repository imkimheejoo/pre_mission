package com.kakao.demo.utils;

import com.kakao.demo.service.dto.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataConverterTest {

    @Test
    @DisplayName("불러온 파일을 적절한 레코드로 추출하는 기능 테스트")
    void extractRows() {
        String[] input = new String[]{"2005", "1", "1,019", "1,000,846", "82", "9", "0"};
        String[] input2 = new String[]{"2005", "2", "1000", "1,000,000", "2,000", "9", "0"};
        List<String[]> inputs = Arrays.asList(input, input2);

        List<Row> expectedRows = Arrays.asList(
                new Row(2005, 1, Arrays.asList(1019, 1000846, 82, 9, 0)),
                new Row(2005, 2, Arrays.asList(1000, 1000000, 2000, 9, 0)));

        List<Row> rows = DataConverter.extractRows(inputs);

        assertThat(rows).isEqualTo(expectedRows);
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
    @DisplayName("측정 날짜를 제외하고 측정값을 추출하는 기능 테스트")
    void parseRow() {
        String[] input = new String[]{"2005", "1", "1,019", "1,000,846", "82", "9", "0"};
        List<Integer> expectedAmounts = Arrays.asList(1019, 1000846, 82, 9, 0);

        List<Row> rows = DataConverter.extractRows(Collections.singletonList(input));
        Row row = rows.get(0);

        assertThat(rows.size()).isEqualTo(1);
        assertThat(row.getYear()).isEqualTo(2005);
        assertThat(row.getMonth()).isEqualTo(1);
        assertThat(row.getAmounts()).isEqualTo(expectedAmounts);
    }
}