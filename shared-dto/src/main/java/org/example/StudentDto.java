package org.example;

import java.time.LocalDate;
import java.util.List;

public class StudentDto {

    private String firstname;
    private String lastname;
    private String tc;
    private String phoneNumber;
    private String studentNumber;
    private LocalDate birthDay;
    private LocalDate entryDate;
    private List<String> courses;

    public StudentDto() {
    }

    public StudentDto(String firstname,
                      String lastname,
                      String tc,
                      String phoneNumber,
                      String studentNumber,
                      LocalDate birthDay,
                      LocalDate entryDate,
                      List<String> courses) {
        this.firstname = firstname;
        this.lastname=lastname;
        this.tc=tc;
        this.phoneNumber=phoneNumber;
        this.studentNumber = studentNumber;
        this.birthDay = birthDay;
        this.entryDate=entryDate;
        this.courses = courses;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTc() {
        return tc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getEntryDate(){
        return entryDate;
    }

    public List<String> getCourses() {
        return courses;
    }

}
