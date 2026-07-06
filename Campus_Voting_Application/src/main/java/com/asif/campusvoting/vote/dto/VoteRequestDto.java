package com.asif.campusvoting.vote.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDto {

    @NotNull
    private Long candidateId;
}