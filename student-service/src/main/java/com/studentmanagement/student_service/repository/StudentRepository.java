package com.studentmanagement.student_service.repository;

import com.studentmanagement.student_service.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    Optional<Student> findByTcAndPhoneNumber(String tc,String phoneNumber);
    Optional<Student> findByStudentNumber(String studentNumber);
}
