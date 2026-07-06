package com.asif.campusvoting.vote.repository;

import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByVoterAndElection(User voter, Election election);

    long countByCandidate(Candidate candidate);
}