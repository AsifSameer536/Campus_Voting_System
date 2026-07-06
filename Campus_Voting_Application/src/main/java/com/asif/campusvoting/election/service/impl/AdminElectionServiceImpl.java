package com.asif.campusvoting.election.service.impl;

import com.asif.campusvoting.common.exception.ElectionNotFoundException;
import com.asif.campusvoting.election.dto.ElectionRequestDto;
import com.asif.campusvoting.election.dto.ElectionResponseDto;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import com.asif.campusvoting.election.repository.ElectionRepository;
import com.asif.campusvoting.election.service.AdminElectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminElectionServiceImpl implements AdminElectionService {

    private final ElectionRepository electionRepository;

    public AdminElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Override
    public ElectionResponseDto createElection(ElectionRequestDto request) {

        Election election = Election.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(ElectionStatus.UPCOMING)
                .build();

        electionRepository.save(election);

        return ElectionResponseDto.builder()
                .id(election.getId())
                .title(election.getTitle())
                .description(election.getDescription())
                .startTime(election.getStartTime())
                .endTime(election.getEndTime())
                .status(election.getStatus())
                .build();
    }

    @Override
    public List<ElectionResponseDto> getAllElections() {

        return electionRepository.findAll()
                .stream()
                .map(election -> ElectionResponseDto.builder()
                        .id(election.getId())
                        .title(election.getTitle())
                        .description(election.getDescription())
                        .startTime(election.getStartTime())
                        .endTime(election.getEndTime())
                        .status(election.getStatus())
                        .build())
                .toList();
    }

    @Override
    public String startElection(Long electionId) {

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() ->
                        new ElectionNotFoundException("Election not found."));

        if (election.getStatus() == ElectionStatus.COMPLETED) {
            throw new RuntimeException("Completed election cannot be started.");
        }

        if (electionRepository.existsByStatus(ElectionStatus.ACTIVE)) {
            throw new RuntimeException("Another election is already active.");
        }

        election.setStatus(ElectionStatus.ACTIVE);

        electionRepository.save(election);

        return "Election started successfully.";
    }

    @Override
    public String endElection(Long electionId) {

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() ->
                        new ElectionNotFoundException("Election not found."));

        if (election.getStatus() != ElectionStatus.ACTIVE) {
            throw new RuntimeException("Election is not active.");
        }

        election.setStatus(ElectionStatus.COMPLETED);

        electionRepository.save(election);

        return "Election ended successfully.";
    }


}