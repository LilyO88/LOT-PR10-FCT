package com.example.lot_pr10_fct.ui.studentVisitsList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class StudentVisitsListFragmentViewModel extends ViewModel {

    private final Repository repository;

    public StudentVisitsListFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
