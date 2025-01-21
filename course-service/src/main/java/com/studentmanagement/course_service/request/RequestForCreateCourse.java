package com.studentmanagement.course_service.request;

public class RequestForCreateCourse {

    private String courseName;

    public RequestForCreateCourse(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}
