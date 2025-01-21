package org.example;

public class GradeDto {

    private String studentNumber;
    private String courseName;
    private int grade;
    private String letterGrade;

    public GradeDto() {
    }

    public GradeDto(String studentNumber, String courseName, int grade, String letterGrade) {
        this.studentNumber = studentNumber;
        this.courseName = courseName;
        this.grade = grade;
        this.letterGrade = letterGrade;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGrade() {
        return grade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
}
