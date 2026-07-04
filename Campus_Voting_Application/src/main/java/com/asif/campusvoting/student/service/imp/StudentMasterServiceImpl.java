package com.asif.campusvoting.student.service.imp;

import com.asif.campusvoting.common.exception.FileValidationException;
import com.asif.campusvoting.student.entity.StudentMaster;
import com.asif.campusvoting.student.repository.StudentMasterRepository;
import com.asif.campusvoting.student.service.StudentMasterService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentMasterServiceImpl implements StudentMasterService {

    private final StudentMasterRepository studentMasterRepository;

    public StudentMasterServiceImpl(StudentMasterRepository studentMasterRepository) {
        this.studentMasterRepository = studentMasterRepository;
    }

    @Override
    public void uploadStudents(MultipartFile file) {

        validateFile(file);
        List<StudentMaster> students = parseCsv(file);
        saveStudents(students);
    }
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileValidationException("Uploaded file is empty.");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".csv")) {
            throw new FileValidationException("Only CSV files are allowed.");
        }
    }

    private List<StudentMaster> parseCsv(MultipartFile file){
        List<StudentMaster> students = new ArrayList<>();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

                CSVParser csvParser = new CSVParser(
                        reader,
                        CSVFormat.DEFAULT
                                .builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .build())
        ) {

            for (CSVRecord record : csvParser) {

                StudentMaster student = StudentMaster.builder()
                        .usn(record.get("USN"))
                        .name(record.get("NAME"))
                        .email(record.get("EMAIL"))
                        .department(record.get("DEPARTMENT"))
                        .year(Integer.parseInt(record.get("YEAR")))
                        .build();

                students.add(student);
            }

            return students;

            } catch (IOException e) {
                throw new RuntimeException("Failed to process CSV file.", e);
            }
    }
    private void saveStudents(List<StudentMaster> students){
        studentMasterRepository.saveAll(students);

    }

}
