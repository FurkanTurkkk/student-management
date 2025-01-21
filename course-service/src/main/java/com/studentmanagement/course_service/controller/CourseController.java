package com.studentmanagement.course_service.controller;

import com.studentmanagement.course_service.request.RequestForCreateCourse;
import com.studentmanagement.course_service.service.CourseService;
import org.example.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add-course")
    public ResponseEntity<CourseDto> addCourse(@RequestBody RequestForCreateCourse request){
        return ResponseEntity.ok(courseService.createNewCourse(request));
    }

}
