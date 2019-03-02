package com.example.lot_pr10_fct.ui.visits.editVisit;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class EditVisitFragmentViewModel extends ViewModel {

    private final Repository repository;

    public EditVisitFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
