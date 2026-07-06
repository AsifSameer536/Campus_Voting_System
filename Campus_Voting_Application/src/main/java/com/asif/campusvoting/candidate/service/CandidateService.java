package com.asif.campusvoting.candidate.service;

import com.asif.campusvoting.candidate.dto.CandidateRequestDto;
import com.asif.campusvoting.candidate.dto.CandidateResponseDto;

public interface CandidateService {

    CandidateResponseDto apply(
            CandidateRequestDto request,
            String email
    );
}