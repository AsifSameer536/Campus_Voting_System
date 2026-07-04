package com.asif.campusvoting.student.repository;

import com.asif.campusvoting.student.entity.StudentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentMasterRepository extends JpaRepository<StudentMaster, Long> {

    Optional<StudentMaster> findByUsn(String usn);

}
