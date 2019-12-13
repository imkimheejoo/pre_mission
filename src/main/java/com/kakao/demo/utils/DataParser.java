package com.kakao.demo.utils;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    public static List<String> deleteBenchMark(List<String> institutions) {
        return institutions.stream()
                .map(name -> name.replaceAll("\\(억원\\)",""))
                .map(name -> name.replaceAll("1\\)",""))
                .collect(Collectors.toList());
    }
}
