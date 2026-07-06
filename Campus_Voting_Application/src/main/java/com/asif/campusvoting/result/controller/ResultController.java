package com.asif.campusvoting.result.controller;

import com.asif.campusvoting.result.dto.ResultResponseDto;
import com.asif.campusvoting.result.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public ResponseEntity<List<ResultResponseDto>> getResults() {

        return ResponseEntity.ok(
                resultService.getResults()
        );
    }

    @GetMapping("/winner")
    public ResponseEntity<ResultResponseDto> getWinner() {

        return ResponseEntity.ok(
                resultService.getWinner()
        );
    }
}