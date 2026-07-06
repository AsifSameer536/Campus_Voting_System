package com.asif.campusvoting.vote.entity;

import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.candidate.entity.Candidate;
import com.asif.campusvoting.election.entity.Election;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "election_id")
    private Election election;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User voter;

    @CreationTimestamp
    private LocalDateTime votedAt;
}