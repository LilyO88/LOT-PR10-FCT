package com.example.lot_pr10_fct.ui.company.companiesList;

import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CompaniesListFragmentViewModel extends ViewModel {

    private final Repository repository;

    private final LiveData<List<Company>> companies;

    public CompaniesListFragmentViewModel(Repository repository) {
        this.repository = repository;
        companies = repository.queryCompanies();
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }

    public void deleteCompany(Company company) {
        repository.deleteCompany(company);
    }
}
