package com.example.lot_pr10_fct.data.local.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "visits",
        foreignKeys = @ForeignKey(
                entity = Student.class,
                parentColumns = "id",
                childColumns = "student",
                onUpdate = CASCADE,
                onDelete = CASCADE))
public class Visit {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long student;
    private String company;
    private String day;
    private String beginHour;
    private String endHour;
    private String observations;

    public Visit(long student, String company, String day, String beginHour, String endHour, String observations) {
        this.student = student;
        this.company = company;
        this.day = day;
        this.beginHour = beginHour;
        this.endHour = endHour;
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

    public long getStudent() {
        return student;
    }

    public void setStudent(long student) {
        this.student = student;
    }

    public String getDay() {
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
    }

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
}
