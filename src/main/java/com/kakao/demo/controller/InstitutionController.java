package com.kakao.demo.controller;

import com.kakao.demo.service.InstitutionService;
import com.kakao.demo.service.dto.InstitutionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/api/find/institutions")
    public ResponseEntity<List<InstitutionDto>> findFinanceInstitutions() {
        List<InstitutionDto> institutions = institutionService.findInstitutions();
        return new ResponseEntity<>(institutions, HttpStatus.OK);
    }
}
