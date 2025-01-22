package com.studentmanagement.note_service.service;

import com.studentmanagement.note_service.converter.DtoConverter;
import com.studentmanagement.note_service.model.Grade;
import com.studentmanagement.note_service.repository.GradeRepository;
import com.studentmanagement.note_service.request.RequestForCreateNote;
import org.example.GradeDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final DtoConverter converter;

    public GradeService(GradeRepository gradeRepository, DtoConverter converter) {
        this.gradeRepository = gradeRepository;
        this.converter = converter;
    }

    public GradeDto addGradeOfStudent(String courseId, String studentId, RequestForCreateNote request) {
        Optional<Grade> registeredGrade=gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        if(registeredGrade.isPresent()){
            Grade existingGrade=registeredGrade.get();
            existingGrade.updateGrade(request.getGrade());
            existingGrade.upgradeLetterGrade(existingGrade.getGrade());
            gradeRepository.save(existingGrade);
            return converter.convert(existingGrade);
        }
        Grade grade=new Grade(request.getGrade(),studentId,courseId);
        gradeRepository.save(grade);
        return converter.convert(grade);
    }

}
