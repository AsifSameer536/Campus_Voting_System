package com.asif.campusvoting.candidate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CandidateRequestDto {

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Manifesto is required")
    private String manifesto;
}