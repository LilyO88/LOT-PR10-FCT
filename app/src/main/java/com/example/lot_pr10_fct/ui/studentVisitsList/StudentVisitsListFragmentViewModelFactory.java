package com.example.lot_pr10_fct.ui.studentVisitsList;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StudentVisitsListFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public StudentVisitsListFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StudentVisitsListFragmentViewModel.class)) {
            return (T) new StudentVisitsListFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
