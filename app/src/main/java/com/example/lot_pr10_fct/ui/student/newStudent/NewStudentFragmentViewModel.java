package com.example.lot_pr10_fct.ui.student.newStudent;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NewStudentFragmentViewModel extends ViewModel {

    private final Repository repository;

    public NewStudentFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
