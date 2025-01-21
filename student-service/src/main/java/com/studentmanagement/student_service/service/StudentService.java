package com.studentmanagement.student_service.service;

import com.studentmanagement.student_service.converter.DtoConverter;
import com.studentmanagement.student_service.model.Student;
import com.studentmanagement.student_service.repository.StudentRepository;
import com.studentmanagement.student_service.request.RequestForCreateStudent;
import com.studentmanagement.student_service.util.FeignClientService;
import org.example.CourseDto;
import org.example.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DtoConverter converter;
    private final FeignClientService feignClientService;

    public StudentService(StudentRepository studentRepository, DtoConverter converter, FeignClientService feignClientService) {
        this.studentRepository = studentRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
    }


    public StudentDto createStudent(RequestForCreateStudent request) {
        checkStudentForRegister(request.getTc(),request.getPhoneNumber());
        Student student=saveStudent(new Student(
                request.getFirstname(),
                request.getLastname(),
                request.getTc(),
                request.getPhoneNumber(),
                request.getBirthday()
        ));
        return converter.convert(student);
    }

    @Transactional
    public void addCourseForStudent(String studentId, String courseId) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student could not found by id : "+studentId));
        CourseDto courseDto=feignClientService.addStudentOfCourse(courseId,student.getStudentNumber());
        student.getCourses().add(courseDto.getName());
        saveStudent(student);
    }

    public StudentDto findStudentByStudentNumber(String studentNumber) {
        Optional<Student> student=studentRepository.findByStudentNumber(studentNumber);
        if(student.isPresent()){
            return converter.convert(student.get());
        }
        throw new RuntimeException("Student could not found by student number : "+studentNumber);
    }

    private Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    private void checkStudentForRegister(String tc,String phoneNumber){
        Optional<Student> student=studentRepository.findByTcAndPhoneNumber(tc,phoneNumber);
        if (student.isPresent()){
            throw new RuntimeException("This student already exist");
        }
    }

}
