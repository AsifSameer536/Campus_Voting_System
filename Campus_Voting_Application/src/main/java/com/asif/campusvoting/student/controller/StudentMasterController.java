package com.asif.campusvoting.student.controller;

import com.asif.campusvoting.student.service.StudentMasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/students")
public class StudentMasterController {

    private final StudentMasterService studentMasterService;

    public StudentMasterController(StudentMasterService studentMasterService) {
        this.studentMasterService = studentMasterService;
    }

    @PostMapping("upload")
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file){
        studentMasterService.uploadStudents(file);
        return ResponseEntity.ok("Students uploaded successfully");
    }
}