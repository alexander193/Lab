package com.se.team00.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.se.team00.demo.entity.*;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByFirstName(String firstName);

    StudentEntity findByLastName(String lastName);

    StudentEntity findByStudentId(String studentId);
}
