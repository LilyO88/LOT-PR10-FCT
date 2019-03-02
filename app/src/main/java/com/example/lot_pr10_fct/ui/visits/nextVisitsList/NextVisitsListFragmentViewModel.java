package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NextVisitsListFragmentViewModel extends ViewModel {

    private final Repository repository;

    NextVisitsListFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
