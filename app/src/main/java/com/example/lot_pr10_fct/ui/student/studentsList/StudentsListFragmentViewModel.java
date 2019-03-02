package com.example.lot_pr10_fct.ui.student.studentsList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class StudentsListFragmentViewModel extends ViewModel {

    private final Repository repository;

    public StudentsListFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
