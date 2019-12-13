package com.kakao.demo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Test
    @DisplayName("파일 불러오기")
    void loadCsvFile() {
        assertDoesNotThrow(DataLoader::loadCsvFile);
    }
}