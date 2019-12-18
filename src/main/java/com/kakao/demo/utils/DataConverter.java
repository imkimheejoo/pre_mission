package com.kakao.demo.utils;

import com.kakao.demo.service.dto.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {
    private static final int INSTITUTION_START_INDEX = 2;
    private static final String THOUSAND_SEPARATOR = ",";
    private static final String BLANK = "";
    private static final int YEAR_INDEX = 0;
    private static final int MONTH_INDEX = 1;

    public static List<Row> extractRows(List<String[]> inputs) {
        List<Row> rows = new ArrayList<>();

        for (String[] input : inputs) {
            rows.add(parseRow(input));
        }
        return rows;
    }

    private static Row parseRow(String[] input) {
        int year = Integer.parseInt(input[YEAR_INDEX]);
        int month = Integer.parseInt(input[MONTH_INDEX]);

        List<String> amounts = extractSubValue(Arrays.asList(input));
        List<Integer> removedSeparatorOfAmount = convertStringToInt(amounts);

        return new Row(year, month, removedSeparatorOfAmount);
    }

    private static List<Integer> convertStringToInt(List<String> amountsByDate) {
        return amountsByDate.stream()
                .map(result -> result.replaceAll(THOUSAND_SEPARATOR, BLANK))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> extractSubValue(List<String> row) {
        List<String> filteredInstitutions = row.subList(INSTITUTION_START_INDEX, row.size());
        List<String> realInstitutions = deleteEmptyValue(filteredInstitutions);

        return deleteBenchMark(realInstitutions);
    }

    private static List<String> deleteEmptyValue(List<String> row) {
        return row.stream()
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    // TODO: 13/12/2019 replace 2번하지 않고 파싱하는 작업 필요
    private static List<String> deleteBenchMark(List<String> institutions) {
        return institutions.stream()
                .map(name -> name.replaceAll("\\(억원\\)", BLANK))
                .map(name -> name.replaceAll("1\\)", BLANK))
                .collect(Collectors.toList());
    }
}
