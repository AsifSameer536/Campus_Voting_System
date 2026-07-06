package com.asif.campusvoting.election.repository;

import com.asif.campusvoting.election.entity.Election;
import com.asif.campusvoting.election.entity.ElectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionRepository extends JpaRepository<Election, Long> {

    Optional<Election> findByStatus(ElectionStatus status);

    boolean existsByStatus(ElectionStatus status);
}