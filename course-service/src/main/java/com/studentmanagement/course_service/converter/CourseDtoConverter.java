package com.studentmanagement.course_service.converter;

import com.studentmanagement.course_service.model.Course;
import org.example.CourseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoConverter {

    public CourseDto convert(Course course){
        return new CourseDto(course.getName(),course.getStudents());
    }
}
