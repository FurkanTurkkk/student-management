package com.studentmanagement.course_service.repository;

import com.studentmanagement.course_service.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Optional<Course> findByName(String name);
}
