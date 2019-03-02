package com.example.lot_pr10_fct.ui.company.newCompany;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NewCompanyFragmentViewModel extends ViewModel {

    private final Repository repository;

    public NewCompanyFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
