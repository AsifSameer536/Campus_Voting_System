package com.asif.campusvoting.vote.service;

import com.asif.campusvoting.vote.dto.VoteRequestDto;
import com.asif.campusvoting.vote.dto.VoteResponseDto;

public interface VoteService {

    VoteResponseDto castVote(
            VoteRequestDto request,
            String email
    );
}