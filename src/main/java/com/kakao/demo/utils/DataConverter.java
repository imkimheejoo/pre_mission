package com.kakao.demo.utils;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {
    private static final int INSTITUTION_START_INDEX = 2;
    private static final String THOUSAND_SEPARATOR = ",";
    private static final String BLANK = "";

    // TODO: 13/12/2019 replace 2번하지 않고 파싱하는 작업 필요
    public static List<String> deleteBenchMark(List<String> institutions) {
        return institutions.stream()
                .map(name -> name.replaceAll("\\(억원\\)", ""))
                .map(name -> name.replaceAll("1\\)", ""))
                .collect(Collectors.toList());
    }

    public static List<String> deleteEmptyValue(List<String> values) {
        return values.stream()
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> extractInstitutionValues(List<String> institutionNames) {
        return institutionNames.subList(INSTITUTION_START_INDEX, institutionNames.size());
    }

    public static List<Integer> convertInputToRealResult(List<String> financeStatusByDate) {
        return financeStatusByDate.stream()
                .map(result -> result.replaceAll(THOUSAND_SEPARATOR, BLANK))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
