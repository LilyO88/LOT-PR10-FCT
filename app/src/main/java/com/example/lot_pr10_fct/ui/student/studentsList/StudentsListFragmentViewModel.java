package com.example.lot_pr10_fct.ui.student.studentsList;

import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class StudentsListFragmentViewModel extends ViewModel {

    private final Repository repository;
    private Student studentToDelete;
    private final LiveData<List<Company>> companies;
    private final LiveData<List<Student>> students;

    public StudentsListFragmentViewModel(Repository repository) {
        this.repository = repository;
        companies = repository.queryCompanies();
        students = repository.queryStudents();
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public Student getStudentToDelete() {
        return studentToDelete;
    }

    public void setStudentToDelete(Student studentToDelete) {
        this.studentToDelete = studentToDelete;
    }

    public void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }

    public void insertStudent(Student student) {
        repository.insertStudent(student);
    }
}
