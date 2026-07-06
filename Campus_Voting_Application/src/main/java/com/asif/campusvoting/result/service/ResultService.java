package com.asif.campusvoting.result.service;

import com.asif.campusvoting.result.dto.ResultResponseDto;

import java.util.List;

public interface ResultService {

    List<ResultResponseDto> getResults();

    ResultResponseDto getWinner();
}