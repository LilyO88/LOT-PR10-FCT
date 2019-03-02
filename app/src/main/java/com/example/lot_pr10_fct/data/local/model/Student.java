package com.example.lot_pr10_fct.data.local.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "students",
        foreignKeys = @ForeignKey(entity = Company.class,
        parentColumns = "name",
        childColumns = "company",
        onUpdate = CASCADE,
        onDelete = CASCADE))
public class Student {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String phone;
    private String email;
    private String grade;
    private long company;
    private String tutorName;
    private String tutorPhone;
    private String horary;

    public Student(String name, String phone, String email, String grade, long company, String tutorName, String tutorPhone, String horary) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.company = company;
        this.tutorName = tutorName;
        this.tutorPhone = tutorPhone;
        this.horary = horary;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorPhone() {
        return tutorPhone;
    }

    public void setTutorPhone(String tutorPhone) {
        this.tutorPhone = tutorPhone;
    }

    public String getHorary() {
        return horary;
    }

    public void setHorary(String horary) {
        this.horary = horary;
    }
}
