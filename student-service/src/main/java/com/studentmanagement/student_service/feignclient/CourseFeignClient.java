package com.studentmanagement.student_service.feignclient;

import org.example.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "course-service",url = "http://localhost:8081/api/v1/course")
public interface CourseFeignClient {

    @PutMapping("/{courseId}/add-student")
    public ResponseEntity<CourseDto> addStudentOfCourse(@PathVariable("courseId")String courseId,
                                                        @RequestBody String studentNumber);

}
