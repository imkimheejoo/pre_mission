package com.kakao.demo.utils;

import com.kakao.demo.service.NotLoadFinanceStatusException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static final String FILE_PATH = "static/data.csv";

    public static List<String[]> loadCsvFile() {
        List<String[]> data = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(FILE_PATH);
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(resource.getFile())));
            String[] s;

            while ((s = reader.readNext()) != null) {
                data.add(s);
            }
        } catch (IOException | CsvValidationException e) {
            throw new NotLoadFinanceStatusException(e);
        }
        return data;
    }
}
