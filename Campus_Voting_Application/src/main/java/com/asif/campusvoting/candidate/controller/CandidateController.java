package com.asif.campusvoting.candidate.controller;

import com.asif.campusvoting.candidate.dto.CandidateRequestDto;
import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/apply")
    public ResponseEntity<CandidateResponseDto> apply(
            @Valid @RequestBody CandidateRequestDto request,
            Authentication authentication) {

        return ResponseEntity.ok(
                candidateService.apply(
                        request,
                        authentication.getName()
                )
        );
    }
}