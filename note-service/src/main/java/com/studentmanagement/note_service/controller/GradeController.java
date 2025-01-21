package com.studentmanagement.note_service.controller;

import com.studentmanagement.note_service.request.RequestForCreateNote;
import com.studentmanagement.note_service.service.GradeService;
import org.example.GradeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/course-id/{courseId}/student-id/{studentId}")
    public ResponseEntity<GradeDto> addGradeOfStudent(@PathVariable("courseId")String courseId,
                                                      @PathVariable("studentId")String studentId,
                                                      @RequestBody RequestForCreateNote request){
        return ResponseEntity.ok(gradeService.addGradeOfStudent(courseId,studentId,request));
    }
}
