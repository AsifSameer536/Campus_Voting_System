package com.asif.campusvoting.student.service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentMasterService {

    void uploadStudents(MultipartFile file);
}
