package org.example;

import java.util.List;

public class CourseDto {

    private java.lang.String name;
    private List<String> students;
    private int totalStudent;

    public CourseDto() {
    }

    public CourseDto(java.lang.String name, List<String> students) {
        this.name = name;
        this.students = students;
        this.totalStudent=students.size();
    }

    public java.lang.String getName() {
        return name;
    }

    public List<String> getStudents() {
        return students;
    }

    public int getTotalStudent() {
        return totalStudent;
    }
}
