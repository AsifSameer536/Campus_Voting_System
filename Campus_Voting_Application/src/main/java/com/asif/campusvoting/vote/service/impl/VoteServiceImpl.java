package com.asif.campusvoting.vote.service.impl;

import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.common.exception.CandidateNotFoundException;
import com.asif.campusvoting.common.exception.VoteAlreadyCastException;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import com.asif.campusvoting.election.repository.ElectionRepository;
import com.asif.campusvoting.vote.dto.VoteRequestDto;
import com.asif.campusvoting.vote.dto.VoteResponseDto;
import com.asif.campusvoting.vote.entity.Vote;
import com.asif.campusvoting.vote.repository.VoteRepository;
import com.asif.campusvoting.vote.service.VoteService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;

    public VoteServiceImpl(
            VoteRepository voteRepository,
            CandidateRepository candidateRepository,
            UserRepository userRepository,
            ElectionRepository electionRepository) {

        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public VoteResponseDto castVote(
            VoteRequestDto request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        Election election = electionRepository
                .findByStatus(ElectionStatus.ACTIVE)
                .orElseThrow(() ->
                        new RuntimeException("No active election."));

        if (voteRepository.existsByVoterAndElection(user, election)) {
            throw new VoteAlreadyCastException(
                    "You have already voted in this election."
            );
        }

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate not found."));

        if (candidate.getStatus() != CandidateStatus.APPROVED) {
            throw new RuntimeException(
                    "Candidate is not approved."
            );
        }

        if (!candidate.getElection().getId().equals(election.getId())) {
            throw new RuntimeException(
                    "Candidate does not belong to the active election."
            );
        }

        Vote vote = Vote.builder()
                .voter(user)
                .candidate(candidate)
                .election(election)
                .build();

        voteRepository.save(vote);

        return new VoteResponseDto("Vote cast successfully.");
    }
}