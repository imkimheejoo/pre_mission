package com.kakao.demo.service;

import com.kakao.demo.domain.InstitutionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class InstitutionServiceTest {

    @InjectMocks
    private InstitutionService institutionService;

    @Mock
    private InstitutionRepository institutionRepository;

    @Test
    @DisplayName("입력정보 중 기관들을 찾아 저장하는 기능 테스트")
    void saveInstitutions() {
        List<String> inputs = Arrays.asList("년도", "월", "주택도시기금1)(억원)", "국민은행(억원)", "우리은행(억원)", "신한은행(억원)",
                "한국시티은행(억원)", "하나은행(억원)", "농협은행/수협은행(억원)", "외환은행(억원)", "기타은행(억원)", "", "");

        List<InstitutionDto> expectedResults = Arrays.asList(
                new InstitutionDto("주택도시기금"),
                new InstitutionDto("국민은행"),
                new InstitutionDto("우리은행"),
                new InstitutionDto("신한은행"),
                new InstitutionDto("한국시티은행"),
                new InstitutionDto("하나은행"),
                new InstitutionDto("농협은행/수협은행"),
                new InstitutionDto("외환은행"),
                new InstitutionDto("기타은행"));

        List<InstitutionDto> institutionDtos = institutionService.saveInstitutions(inputs);

        assertThat(institutionDtos.size()).isEqualTo(expectedResults.size());
        for (int i = 0; i < institutionDtos.size(); i++) {
            assertThat(institutionDtos.get(i)).isEqualTo(expectedResults.get(i));
        }

    }
}