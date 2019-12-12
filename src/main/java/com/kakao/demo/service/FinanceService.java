package com.kakao.demo.service;

import com.kakao.demo.domain.AmountRepository;
import com.kakao.demo.domain.FinanceStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FinanceService {
    private static final String FILE_PATH = "static/data.csv";

    private final AmountRepository amountRepository;

    public FinanceService(final AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }

    public FinanceStatus loadFinanceStatus() {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);
            return new FinanceStatus(content);
        } catch (IOException e) {
            throw new NotLoadFinanceStatusException(e);
        }
    }
}
