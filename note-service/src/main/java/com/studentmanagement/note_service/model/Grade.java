package com.studentmanagement.note_service.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "grades")
public class Grade {

    @Id
    private String id;

    private int grade;
    private String letterGrade;
    private String studentId;
    private String courseId;

    public Grade() {
    }

    public Grade(int grade, String studentId, String courseId) {
        this.grade = grade;
        this.studentId = studentId;
        this.courseId = courseId;
        this.letterGrade=calculateLetterGrade(grade);
    }

    public String getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void updateGrade(int grade){
        this.grade=grade;
    }

    private static String calculateLetterGrade(int grade){
        if(grade<0){
            throw new RuntimeException("Grade can not negative");
        }
        if(grade>=80 && grade<=100){
            return "AA";
        }
        else if (grade>=60 && grade<80){
            return "BB";
        }
        else if (grade>=40 && grade <60) {
            return "CC";
        }
        else {
            return "FF";
        }
    }

    public void upgradeLetterGrade(int grade){
        this.letterGrade= calculateLetterGrade(grade);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(studentId, grade.studentId) && Objects.equals(courseId, grade.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
