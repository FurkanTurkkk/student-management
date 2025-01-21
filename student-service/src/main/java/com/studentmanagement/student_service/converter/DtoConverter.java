package com.studentmanagement.student_service.converter;

import com.studentmanagement.student_service.model.Student;
import org.example.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public StudentDto convert(Student student){
        return new StudentDto(
                student.getFirstName(),
                student.getLastName(),
                student.getTc(),
                student.getPhoneNumber(),
                student.getStudentNumber(),
                student.getBirthDay(),
                student.getEntryDay(),
                student.getCourses()
        );
    }
}
