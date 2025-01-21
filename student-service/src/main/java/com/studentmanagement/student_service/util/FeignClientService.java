package com.studentmanagement.student_service.util;

import com.studentmanagement.student_service.feignclient.CourseFeignClient;
import org.example.CourseDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final CourseFeignClient courseFeignClient;

    public FeignClientService(CourseFeignClient courseFeignClient) {
        this.courseFeignClient = courseFeignClient;
    }

    public CourseDto addStudentOfCourse(String courseId, String studentNumber){
        return courseFeignClient.addStudentOfCourse(courseId,studentNumber).getBody();
    }
}
