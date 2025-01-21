package com.studentmanagement.course_service.service;

import com.studentmanagement.course_service.converter.CourseDtoConverter;
import com.studentmanagement.course_service.model.Course;
import com.studentmanagement.course_service.repository.CourseRepository;
import com.studentmanagement.course_service.request.RequestForCreateCourse;
import org.example.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;
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
        checkCourseForRegister(request.getCourseName());
        Course course=new Course(request.getCourseName());
        return converter.convert(courseRepository.save(course));
    }

    public void deleteCourseById(java.lang.String courseId) {
        Course course=findCourseById(courseId);
        courseRepository.delete(course);
    }

    public CourseDto getCourseById(java.lang.String courseId) {
        Course course=findCourseById(courseId);
        return converter.convert(course);
    }

    public CourseDto addStudentOfCourse(String courseId, String studentNumber) {
        Course course=findCourseById(courseId);
        List<String> studentNumberList=course.getStudents();
        if(studentNumberList.contains(studentNumber)){
            throw new RuntimeException("This student already take this course ! ");
        }
        course.getStudents().add(studentNumber);
        return converter.convert(courseRepository.save(course));
    }

    private Course findCourseById(java.lang.String courseId){
        Optional<Course> course=courseRepository.findById(courseId);
        if(course.isPresent()){
            return course.get();
        }

        throw new RuntimeException("Course could not found by course id : "+courseId);
    }

    private void checkCourseForRegister(String courseName){
        Optional<Course> course=courseRepository.findByName(courseName);
        if(course.isPresent()){
            throw new IllegalArgumentException("Already exist course by course name : "+courseName);
        }
    }

}
