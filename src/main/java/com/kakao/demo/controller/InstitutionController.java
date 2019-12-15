package com.kakao.demo.controller;

import com.kakao.demo.service.InstitutionService;
import com.kakao.demo.service.dto.InstitutionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    public ResponseEntity findFinanceInstitutions(Model model) {
        List<InstitutionDto> institutions = institutionService.findInstitutions();

        model.addAttribute("list", institutions);

        return ResponseEntity.ok(model);
    }
}
