package com.example.lot_pr10_fct.ui.company.editCompany;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class EditCompanyFragmentViewModel extends ViewModel {

    private final Repository repository;

    public EditCompanyFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
