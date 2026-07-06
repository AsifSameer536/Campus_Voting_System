package com.asif.campusvoting.election.service;

import com.asif.campusvoting.election.dto.ElectionRequestDto;
import com.asif.campusvoting.election.dto.ElectionResponseDto;

import java.util.List;

public interface AdminElectionService {

    ElectionResponseDto createElection(ElectionRequestDto request);

    List<ElectionResponseDto> getAllElections();

    String startElection(Long electionId);

    String endElection(Long electionId);
}