package org.example;

import java.util.List;

public class CourseDto {

    private String name;
    private List<String> students;

    public CourseDto() {
    }

    public CourseDto(String name, List<String> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<String> getStudents() {
        return students;
    }
}
