package com.asif.campusvoting.election.controller;

import com.asif.campusvoting.election.dto.ElectionRequestDto;
import com.asif.campusvoting.election.dto.ElectionResponseDto;
import com.asif.campusvoting.election.service.AdminElectionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/elections")
public class AdminElectionController {

    private final AdminElectionService adminElectionService;

    public AdminElectionController(AdminElectionService adminElectionService) {
        this.adminElectionService = adminElectionService;
    }

    @PostMapping
    public ResponseEntity<ElectionResponseDto> createElection(
            @Valid @RequestBody ElectionRequestDto request) {

        return ResponseEntity.ok(
                adminElectionService.createElection(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<ElectionResponseDto>> getAllElections() {

        return ResponseEntity.ok(
                adminElectionService.getAllElections()
        );
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<String> startElection(@PathVariable Long id) {

        return ResponseEntity.ok(
                adminElectionService.startElection(id)
        );
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<String> endElection(@PathVariable Long id) {

        return ResponseEntity.ok(
                adminElectionService.endElection(id)
        );
    }
}