package com.asif.campusvoting.result.service.impl;

import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import com.asif.campusvoting.candidate.repository.CandidateRepository;
import com.asif.campusvoting.common.exception.ElectionNotFoundException;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import com.asif.campusvoting.election.repository.ElectionRepository;
import com.asif.campusvoting.result.dto.ResultResponseDto;
import com.asif.campusvoting.result.service.ResultService;
import com.asif.campusvoting.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;
    private final ElectionRepository electionRepository;

    public ResultServiceImpl(
            CandidateRepository candidateRepository,
            VoteRepository voteRepository,
            ElectionRepository electionRepository) {

        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public List<ResultResponseDto> getResults() {

        Election election = electionRepository
                .findByStatus(ElectionStatus.ACTIVE)
                .orElseThrow(() ->
                        new ElectionNotFoundException("No active election found."));

        return candidateRepository
                .findByElectionAndStatus(election, CandidateStatus.APPROVED)
                .stream()
                .map(candidate -> ResultResponseDto.builder()
                        .candidateId(candidate.getId())
                        .candidateName(candidate.getUser().getStudentMaster().getName())
                        .position(candidate.getPosition())
                        .votes(voteRepository.countByCandidate(candidate))
                        .build())
                .sorted(Comparator.comparing(ResultResponseDto::getVotes).reversed())
                .toList();
    }

    @Override
    public ResultResponseDto getWinner() {

        return getResults()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No results available."));
    }
}