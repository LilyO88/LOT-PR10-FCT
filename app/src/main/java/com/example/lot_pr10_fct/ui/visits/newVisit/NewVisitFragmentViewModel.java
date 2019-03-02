package com.example.lot_pr10_fct.ui.visits.newVisit;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NewVisitFragmentViewModel extends ViewModel {

    private final Repository repository;

    public NewVisitFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
