package com.example.lot_pr10_fct.data;

import android.os.AsyncTask;

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
        return visitDao.queryVisits();
    }

    @Override
    public LiveData<List<Visit>> queryStudentVisits(long studentId) {
        return visitDao.queryStudentVisits(studentId);
    }

    @Override
    public LiveData<Visit> queryVisit(long visitId) {
        return visitDao.queryVisit(visitId);
    }

    @Override
    public void insertVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitDao.insert(visit));
    }

    @Override
    public void updateVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitDao.update(visit));
    }

    @Override
    public void deleteVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitDao.delete(visit));
    }

    //Student
    @Override
    public LiveData<List<Student>> queryStudents() {
        return studentDao.queryStudents();
    }

    @Override
    public LiveData<Student> queryStudent(long studentId) {
        return studentDao.queryStudent(studentId);
    }

    @Override
    public void insertStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.insert(student));
    }

    @Override
    public void updateStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.update(student));
    }

    @Override
    public void deleteStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.delete(student));
    }

    //Company
    @Override
    public LiveData<Company> queryCompany(long companyId) {
        return companyDao.queryCompany(companyId);
    }

    @Override
    public LiveData<List<Company>> queryCompanies() {
        return companyDao.queryCompanies();
    }

    @Override
    public void insertCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> companyDao.insert(company));
    }

    @Override
    public void updateCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> companyDao.update(company));
    }

    @Override
    public void deleteCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> companyDao.delete(company));
    }
}
