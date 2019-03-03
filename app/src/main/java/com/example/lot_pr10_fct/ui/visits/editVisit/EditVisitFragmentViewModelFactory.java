package com.example.lot_pr10_fct.ui.visits.editVisit;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditVisitFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public EditVisitFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditVisitFragmentViewModel.class)) {
            return (T) new EditVisitFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
