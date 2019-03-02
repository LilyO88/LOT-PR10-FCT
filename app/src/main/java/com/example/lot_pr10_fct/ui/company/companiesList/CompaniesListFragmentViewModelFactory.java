package com.example.lot_pr10_fct.ui.company.companiesList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CompaniesListFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public CompaniesListFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CompaniesListFragmentViewModel.class)) {
            return (T) new CompaniesListFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
