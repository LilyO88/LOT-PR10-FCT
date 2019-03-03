package com.example.lot_pr10_fct.ui.visits.newVisit;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewVisitFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public NewVisitFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewVisitFragmentViewModel.class)) {
            return (T) new NewVisitFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
