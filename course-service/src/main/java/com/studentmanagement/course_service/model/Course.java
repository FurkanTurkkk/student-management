package com.studentmanagement.course_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getStudents() {
        return students;
    }
}
