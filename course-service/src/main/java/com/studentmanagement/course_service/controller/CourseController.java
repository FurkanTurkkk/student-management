package com.studentmanagement.course_service.controller;

import com.studentmanagement.course_service.request.RequestForCreateCourse;
import com.studentmanagement.course_service.service.CourseService;
import org.example.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{courseId}/add-student")
    public ResponseEntity<CourseDto> addStudentOfCourse(@PathVariable("courseId")String courseId,
                                                     @RequestBody String studentNumber){
        return ResponseEntity.ok(courseService.addStudentOfCourse(courseId,studentNumber));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") String courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @DeleteMapping("/{courseId}/delete")
    public ResponseEntity<String> deleteCourseById(@PathVariable("courseId") String courseId){
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok("Course deleted successfully ! ");
    }

}
