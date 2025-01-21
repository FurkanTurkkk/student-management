package com.studentmanagement.student_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Document
public class Student {

    @Id
    private String id;

    private static final AtomicInteger counter=new AtomicInteger(0);
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String tc;
    private String phoneNumber;
    private LocalDate birthDay;
    private LocalDate entryDay;
    private List<String> courses;

    public Student() {
    }

    public Student(String firstName, String lastName,String tc,String phoneNumber, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc=tc;
        this.phoneNumber=phoneNumber;
        this.birthDay = birthDay;
        this.studentNumber=generateStudentNumber();
        this.entryDay=LocalDate.now();
        this.courses=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public LocalDate getEntryDay() {
        return entryDay;
    }

    public List<String> getCourses() {
        return courses;
    }

    private String generateStudentNumber(){
        int year= Year.now().getValue();
        int uniqueNumber=counter.incrementAndGet();
        return String.format("%d-STU-%04d",year,uniqueNumber);
    }
}
