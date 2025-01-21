package com.studentmanagement.note_service.feignclient;

import org.example.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service",url = "http://localhost:8082/api/v1/student")
public interface StudentFeignClient {
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> findStudentByStudentId(@PathVariable("studentId")String studentId);
}
