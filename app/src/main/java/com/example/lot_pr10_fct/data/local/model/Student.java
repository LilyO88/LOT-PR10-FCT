package com.example.lot_pr10_fct.data.local.model;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.NO_ACTION;

@Entity(tableName = "students",
        foreignKeys = {@ForeignKey(entity = Company.class,
                        parentColumns = "name",
                        childColumns = "company",
                        onUpdate = NO_ACTION,
                        onDelete = NO_ACTION)},
        indices = {@Index(value = {"name"}, unique = true),
                @Index(value = {"company"}, unique = true),
                @Index(value = {"id"}, unique = true)})
public class Student {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String phone;
    private String email;
    private String grade;
    private String company;
    private String tutorName;
    private String tutorPhone;
    private Date begin;
    private Date end;
//    private String horary;

    public Student(String name, String phone, String email, String grade, String company, String tutorName, String tutorPhone /*String horary*/, Date begin, Date end) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.company = company;
        this.tutorName = tutorName;
        this.tutorPhone = tutorPhone;
//        this.horary = horary;
        this.begin = begin;
        this.end = end;
    }

    public Student() {
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Student && id == ((Student)obj).id && name.equals(((Student)obj).name)
                && phone.equals(((Student)obj).phone) && grade.equals(((Student)obj).grade)
                && company.equals(((Student)obj).company)
                && tutorName.equals(((Student)obj).tutorName) && tutorPhone.equals(((Student)obj).tutorPhone)
                && begin.equals(((Student)obj).begin)&& end.equals(((Student)obj).end)) {
            return true;
        }
        return false;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
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

/*    public String getHorary() {
        return horary;
    }

    public void setHorary(String horary) {
        this.horary = horary;
    }*/

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
