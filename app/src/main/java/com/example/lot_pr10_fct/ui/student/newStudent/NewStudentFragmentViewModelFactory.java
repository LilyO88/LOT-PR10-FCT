package com.example.lot_pr10_fct.ui.student.newStudent;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewStudentFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public NewStudentFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewStudentFragmentViewModel.class)) {
            return (T) new NewStudentFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
