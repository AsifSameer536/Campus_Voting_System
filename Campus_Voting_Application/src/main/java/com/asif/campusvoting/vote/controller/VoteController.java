package com.asif.campusvoting.vote.controller;

import com.asif.campusvoting.vote.dto.VoteRequestDto;
import com.asif.campusvoting.vote.dto.VoteResponseDto;
import com.asif.campusvoting.vote.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteResponseDto> castVote(
            @Valid @RequestBody VoteRequestDto request,
            Authentication authentication) {

        return ResponseEntity.ok(
                voteService.castVote(
                        request,
                        authentication.getName()
                )
        );
    }
}