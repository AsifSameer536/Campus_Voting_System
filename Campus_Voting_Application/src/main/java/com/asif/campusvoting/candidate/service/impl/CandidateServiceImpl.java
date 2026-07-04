package com.asif.campusvoting.candidate.service.impl;

import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.candidate.service.CandidateService;
import org.springframework.stereotype.Service;
import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.candidate.dto.CandidateRequestDto;
import com.asif.campusvoting.candidate.dto.CandidateResponseDto;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.candidate.service.CandidateService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public CandidateServiceImpl(
            CandidateRepository candidateRepository,
            UserRepository userRepository) {

        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CandidateResponseDto apply(
            CandidateRequestDto request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        if (candidateRepository.existsByUser_Id(user.getId())) {
            throw new RuntimeException("You have already applied.");
        }

        Candidate candidate = Candidate.builder()
                .user(user)
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