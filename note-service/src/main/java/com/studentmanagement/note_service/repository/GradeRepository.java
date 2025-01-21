package com.studentmanagement.note_service.repository;

import com.studentmanagement.note_service.model.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends MongoRepository<Grade,Long> {
    Optional<Grade> findByStudentIdAndCourseId(String studentId,String courseId);
}
