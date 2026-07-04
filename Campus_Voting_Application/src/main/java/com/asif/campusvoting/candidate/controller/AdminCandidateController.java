package com.asif.campusvoting.candidate.controller;

import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.service.AdminCandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/candidates")
public class AdminCandidateController {

    private final AdminCandidateService adminCandidateService;

    public AdminCandidateController(AdminCandidateService adminCandidateService) {
        this.adminCandidateService = adminCandidateService;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CandidateResponseDto>> getPendingCandidates() {

        return ResponseEntity.ok(
                adminCandidateService.getPendingCandidates()
        );
    }

    @PutMapping("/{candidateId}/approve")
    public ResponseEntity<String> approveCandidate(
            @PathVariable Long candidateId) {

        return ResponseEntity.ok(
                adminCandidateService.approveCandidate(candidateId)
        );
    }

    @PutMapping("/{candidateId}/reject")
    public ResponseEntity<String> rejectCandidate(
            @PathVariable Long candidateId) {

        return ResponseEntity.ok(
                adminCandidateService.rejectCandidate(candidateId)
        );
    }
}