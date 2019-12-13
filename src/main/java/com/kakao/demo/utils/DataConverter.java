package com.kakao.demo.utils;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {
    private static final int INSTITUTION_START_INDEX = 2;

    public static List<String> deleteBenchMark(List<String> institutions) {
        return institutions.stream()
                .map(name -> name.replaceAll("\\(억원\\)",""))
                .map(name -> name.replaceAll("1\\)",""))
                .collect(Collectors.toList());
    }

    public static List<String> deleteEmptyValue(List<String> values) {
        return values.stream()
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> extractInstitutionNames(List<String> institutionNames) {
        return institutionNames.subList(INSTITUTION_START_INDEX, institutionNames.size());
    }

}
