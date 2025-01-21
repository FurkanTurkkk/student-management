package com.studentmanagement.note_service.request;

public class RequestForFindGrade {

    private String studentNumber;

    public RequestForFindGrade(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
