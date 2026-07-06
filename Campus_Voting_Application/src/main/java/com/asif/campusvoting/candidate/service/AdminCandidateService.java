package com.asif.campusvoting.candidate.service;

import com.asif.campusvoting.candidate.dto.CandidateResponseDto;

import java.util.List;

public interface AdminCandidateService {

    List<CandidateResponseDto> getPendingCandidates();

    String approveCandidate(Long candidateId);

    String rejectCandidate(Long candidateId);


}