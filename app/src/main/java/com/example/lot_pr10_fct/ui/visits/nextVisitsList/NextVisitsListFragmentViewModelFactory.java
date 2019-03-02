package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NextVisitsListFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public NextVisitsListFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NextVisitsListFragmentViewModel.class)) {
            return (T) new NextVisitsListFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
