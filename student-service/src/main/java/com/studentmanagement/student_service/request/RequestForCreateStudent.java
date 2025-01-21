package com.studentmanagement.student_service.request;

import java.time.LocalDate;

public class RequestForCreateStudent {

    private String firstname;
    private String lastname;
    private String tc;
    private String phoneNumber;
    private LocalDate birthday;

    public RequestForCreateStudent(String firstname,
                                   String lastname,
                                   String tc,
                                   String phoneNumber,
                                   LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tc=tc;
        this.phoneNumber=phoneNumber;
        this.birthday = birthday;
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

    public LocalDate getBirthday() {
        return birthday;
    }
}
