package com.asif.campusvoting.candidate.service.impl;

import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.candidate.service.AdminCandidateService;
import com.asif.campusvoting.common.exception.CandidateNotFoundException;
import com.asif.campusvoting.common.exception.ElectionNotFoundException;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import com.asif.campusvoting.election.repository.ElectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCandidateServiceImpl implements AdminCandidateService {

    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public AdminCandidateServiceImpl(
            CandidateRepository candidateRepository,
            ElectionRepository electionRepository) {

        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public List<CandidateResponseDto> getPendingCandidates() {

        Election election = electionRepository
                .findByStatus(ElectionStatus.ACTIVE)
                .orElseThrow(() ->
                        new ElectionNotFoundException("No active election found."));

        return candidateRepository
                .findByElectionAndStatus(
                        election,
                        CandidateStatus.PENDING
                )
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