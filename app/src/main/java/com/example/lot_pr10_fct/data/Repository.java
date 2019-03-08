package com.example.lot_pr10_fct.data;

import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {

    //    Visit
    LiveData<List<Visit>> queryVisits();
    LiveData<List<Visit>> queryStudentVisits(long studentId);
    LiveData<Visit> queryVisit(long visitId);
    LiveData<Visit> queryLastVisitsStudent(long studentId);
    void insertVisit(Visit visit);
    void updateVisit(Visit visit);
    void deleteVisit(Visit visit);

    //    Student
    LiveData<List<Student>> queryStudents();
    LiveData<Student> queryStudent(long studentId);
    void insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);

    //    Company
    LiveData<Company> queryCompany(long companyId);
    LiveData<List<Company>> queryCompanies();
    void insertCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Company company);
}
