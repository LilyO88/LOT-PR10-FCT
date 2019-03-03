package com.example.lot_pr10_fct.ui.company.newCompany;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewCompanyFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public NewCompanyFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewCompanyFragmentViewModel.class)) {
            return (T) new NewCompanyFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
