package com.example.lot_pr10_fct.ui.company.editCompany;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditCompanyFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public EditCompanyFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditCompanyFragmentViewModel.class)) {
            return (T) new EditCompanyFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
