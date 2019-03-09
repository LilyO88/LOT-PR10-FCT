package com.example.lot_pr10_fct.data.local.model;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.NO_ACTION;

@Entity(tableName = "visits",
        foreignKeys = {@ForeignKey(entity = Student.class,
                        parentColumns = "id",
                        childColumns = "idStudent",
                        onUpdate = NO_ACTION,
                        onDelete = NO_ACTION)},
        indices = {@Index(value = {"idStudent"})})
public class Visit {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long idStudent;
    private String student;
    private String companyStudent;
    private Date date;
    private String begin;
    private String end;
    private String observations;

    public Visit(long idStudent, String student, String companyStudent, Date date, String begin, String end, String observations) {
        this.idStudent = idStudent;
        this.student = student;
        this.companyStudent = companyStudent;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.observations = observations;
    }

    public Visit() {
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCompanyStudent() {
        return companyStudent;
    }

    public void setCompanyStudent(String companyStudent) {
        this.companyStudent = companyStudent;
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
