package com.studentmanagement.course_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;

    private String name;
    private List<String> students;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
        this.students=new ArrayList<>();
    }

    public Course(String name, List<String> students) {
        this.name = name;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
