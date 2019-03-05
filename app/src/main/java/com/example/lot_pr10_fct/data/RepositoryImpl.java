package com.example.lot_pr10_fct.data;

import com.example.lot_pr10_fct.data.local.CompanyDao;
import com.example.lot_pr10_fct.data.local.StudentDao;
import com.example.lot_pr10_fct.data.local.VisitDao;
import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    private final VisitDao visitDao;
    private final StudentDao studentDao;
    private final CompanyDao companyDao;

    public RepositoryImpl(VisitDao visitDao, StudentDao studentDao, CompanyDao companyDao) {
        this.visitDao = visitDao;
        this.studentDao = studentDao;
        this.companyDao = companyDao;
    }

    //Visit
    @Override
    public LiveData<List<Visit>> queryVisits() {
        return null;
    }

    @Override
    public LiveData<List<Visit>> queryStudentVisits(long studentId) {
        return null;
    }

    @Override
    public LiveData<Visit> queryVisit(long visitId) {
        return null;
    }

    @Override
    public void insertVisit(Visit visit) {

    }

    @Override
    public void updateVisit(Visit visit) {

    }

    @Override
    public void deleteVisit(Visit visit) {

    }

    //Student
    @Override
    public LiveData<List<Student>> queryStudents() {
        return null;
    }

    @Override
    public LiveData<Student> queryStudent(long studentId) {
        return null;
    }

    @Override
    public void insertStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }

    //Company
    @Override
    public LiveData<Company> queryCompany(long companyId) {
        return null;
    }

    @Override
    public LiveData<List<Company>> queryCompanies() {
        return null;
    }

    @Override
    public void insertCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteSCompany(Company company) {

    }
}
