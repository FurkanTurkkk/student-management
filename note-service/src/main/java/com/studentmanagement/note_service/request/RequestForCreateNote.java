package com.studentmanagement.note_service.request;

public class RequestForCreateNote {

    private int grade;

    public RequestForCreateNote(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
