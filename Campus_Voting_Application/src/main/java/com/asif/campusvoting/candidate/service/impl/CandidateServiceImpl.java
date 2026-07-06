package com.asif.campusvoting.candidate.service.impl;

import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.candidate.dto.CandidateRequestDto;
import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.candidate.service.CandidateService;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import com.asif.campusvoting.election.repository.ElectionRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;

    public CandidateServiceImpl(
            CandidateRepository candidateRepository,
            UserRepository userRepository,
            ElectionRepository electionRepository) {

        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public CandidateResponseDto apply(
            CandidateRequestDto request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        if (user.getStudentMaster() == null) {
            throw new IllegalStateException(
                    "Only students can apply as candidates."
            );
        }

        Election election = electionRepository
                .findByStatus(ElectionStatus.ACTIVE)
                .orElseThrow(() ->
                        new RuntimeException("No active election found."));

        if (candidateRepository.existsByUserAndElection(user, election)) {
            throw new RuntimeException(
                    "You have already applied for this election."
            );
        }

        Candidate candidate = Candidate.builder()
                .user(user)
                .election(election)
                .position(request.getPosition())
                .manifesto(request.getManifesto())
                .status(CandidateStatus.PENDING)
                .build();

        candidateRepository.save(candidate);

        return CandidateResponseDto.builder()
                .id(candidate.getId())
                .studentName(user.getStudentMaster().getName())
                .usn(user.getStudentMaster().getUsn())
                .email(user.getEmail())
                .position(candidate.getPosition())
                .manifesto(candidate.getManifesto())
                .status(candidate.getStatus())
                .build();
    }
}