package com.asif.campusvoting.candidate.service.impl;

import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.candidate.service.AdminCandidateService;
import com.asif.campusvoting.common.exception.CandidateNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCandidateServiceImpl implements AdminCandidateService {

    private final CandidateRepository candidateRepository;

    public AdminCandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<CandidateResponseDto> getPendingCandidates() {

        return candidateRepository.findByStatus(CandidateStatus.PENDING)
                .stream()
                .map(candidate -> CandidateResponseDto.builder()
                        .id(candidate.getId())
                        .studentName(candidate.getUser().getStudentMaster().getName())
                        .usn(candidate.getUser().getStudentMaster().getUsn())
                        .email(candidate.getUser().getEmail())
                        .position(candidate.getPosition())
                        .manifesto(candidate.getManifesto())
                        .status(candidate.getStatus())
                        .build())
                .toList();
    }


    @Override
    public String approveCandidate(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate not found."));

        candidate.setStatus(CandidateStatus.APPROVED);

        candidateRepository.save(candidate);

        return "Candidate approved successfully.";
    }

    @Override
    public String rejectCandidate(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate not found."));

        candidate.setStatus(CandidateStatus.REJECTED);

        candidateRepository.save(candidate);

        return "Candidate rejected successfully.";
    }
}