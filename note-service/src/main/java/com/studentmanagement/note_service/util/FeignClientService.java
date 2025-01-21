package com.studentmanagement.note_service.util;

import com.studentmanagement.note_service.feignclient.CourseFeignClient;
import com.studentmanagement.note_service.feignclient.StudentFeignClient;
import org.example.CourseDto;
import org.example.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final StudentFeignClient studentFeignClient;
    private final CourseFeignClient courseFeignClient;

    public FeignClientService(StudentFeignClient studentFeignClient, CourseFeignClient courseFeignClient) {
        this.studentFeignClient = studentFeignClient;
        this.courseFeignClient = courseFeignClient;
    }

    public StudentDto findStudentByStudentId(String studentId){
        return studentFeignClient.findStudentByStudentId(studentId).getBody();
    }

    public CourseDto findCourseByCourseId(String courseId){
        return courseFeignClient.getCourseById(courseId).getBody();
    }
}
