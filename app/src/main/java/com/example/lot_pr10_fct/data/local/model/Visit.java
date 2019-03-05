package com.example.lot_pr10_fct.data.local.model;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "visits",
        foreignKeys = {@ForeignKey(
                entity = Student.class,
                parentColumns = "name",
                childColumns = "student",
                onUpdate = CASCADE,
                onDelete = CASCADE),
                @ForeignKey(entity = Student.class,
                parentColumns = "id",
                childColumns = "idStudent",
                onUpdate = CASCADE,
                onDelete = CASCADE)})
public class Visit {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long idStudent;
    private String student;
    private String company;
    private Date date;
//    private String day;
//    private String beginHour;
//    private String endHour;
    private String observations;

    public Visit(long idStudent, String student, String company, Date date, /*String day, String beginHour, String endHour,*/ String observations) {
        this.idStudent = idStudent;
        this.student = student;
        this.company = company;
        this.date = date;
//        this.day = day;
//        this.beginHour = beginHour;
//        this.endHour = endHour;
        this.observations = observations;
    }

    public Visit() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

/*    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }*/

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }
}
