package com.studentmanagement.course_service.service;

import com.studentmanagement.course_service.converter.CourseDtoConverter;
import com.studentmanagement.course_service.model.Course;
import com.studentmanagement.course_service.repository.CourseRepository;
import com.studentmanagement.course_service.request.RequestForCreateCourse;
import org.example.CourseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseDtoConverter converter;

    public CourseService(CourseRepository courseRepository, CourseDtoConverter converter) {
        this.courseRepository = courseRepository;
        this.converter = converter;
    }


    public CourseDto createNewCourse(RequestForCreateCourse request) {
        Course course=findCourseByName(request.getCourseName());
        return converter.convert(course);
    }

    private Course findCourseByName(String courseName){
        Optional<Course> course=courseRepository.findByName(courseName);
        if(course.isPresent()){
            throw new IllegalArgumentException("Already exist course by course name : "+courseName);
        }
        return courseRepository.save(new Course(courseName));
    }
}
