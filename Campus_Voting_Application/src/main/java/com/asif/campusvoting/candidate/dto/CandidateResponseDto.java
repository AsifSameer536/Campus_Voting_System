package com.asif.campusvoting.candidate.dto;

import com.asif.campusvoting.candidate.entity.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseDto {

    private Long id;

    private String studentName;

    private String usn;

    private String email;

    private String position;

    private String manifesto;

    private CandidateStatus status;
}