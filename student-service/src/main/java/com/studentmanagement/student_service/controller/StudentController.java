package com.studentmanagement.student_service.controller;

import com.studentmanagement.student_service.request.RequestForCreateStudent;
import com.studentmanagement.student_service.service.StudentService;
import org.example.StudentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add-student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody RequestForCreateStudent request){
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PutMapping("/{studentId}/add-course/{courseId}")
    public ResponseEntity<String> addCourseForStudent(@PathVariable("studentId")String studentId,
                                                      @PathVariable("courseId")String courseId){
        studentService.addCourseForStudent(studentId,courseId);
        return ResponseEntity.ok("Course added successfully");
    }

    @GetMapping("/find-student")
    public ResponseEntity<StudentDto> findStudentByStudentNumber(@RequestBody String studentNumber){
        return ResponseEntity.ok(studentService.findStudentByStudentNumber(studentNumber));
    }

}
