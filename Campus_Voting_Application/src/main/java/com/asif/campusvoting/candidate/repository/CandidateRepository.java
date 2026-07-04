package com.asif.campusvoting.candidate.repository;

import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.candidate.entity.CandidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByUser_Id(Long userId);

    List<Candidate> findByStatus(CandidateStatus status);

    boolean existsByUser_Id(Long userId);
}