package com.asif.campusvoting.result.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResultResponseDto {

    private Long candidateId;

    private String candidateName;

    private String position;

    private long votes;
}