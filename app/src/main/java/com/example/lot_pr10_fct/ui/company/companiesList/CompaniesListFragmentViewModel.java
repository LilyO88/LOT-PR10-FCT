package com.example.lot_pr10_fct.ui.company.companiesList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class CompaniesListFragmentViewModel extends ViewModel {

    private final Repository repository;

    public CompaniesListFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
