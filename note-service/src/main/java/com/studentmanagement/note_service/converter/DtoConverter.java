package com.studentmanagement.note_service.converter;

import com.studentmanagement.note_service.model.Grade;
import com.studentmanagement.note_service.util.FeignClientService;
import org.example.GradeDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    private final FeignClientService feignClientService;

    public DtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public GradeDto convert(Grade grade){
        String studentNumber=feignClientService.findStudentByStudentId(grade.getStudentId()).getStudentNumber();
        String courseName=feignClientService.findCourseByCourseId(grade.getCourseId()).getName();
        return new GradeDto(
                studentNumber,
                courseName,
                grade.getGrade(),
                grade.getLetterGrade()
        );
    }
}
