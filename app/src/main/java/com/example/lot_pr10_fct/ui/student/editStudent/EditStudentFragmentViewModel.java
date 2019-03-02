package com.example.lot_pr10_fct.ui.student.editStudent;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class EditStudentFragmentViewModel extends ViewModel {

    private final Repository repository;

    public EditStudentFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
