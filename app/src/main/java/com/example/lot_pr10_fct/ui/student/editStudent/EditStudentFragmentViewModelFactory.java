package com.example.lot_pr10_fct.ui.student.editStudent;

import com.example.lot_pr10_fct.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditStudentFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public EditStudentFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditStudentFragmentViewModel.class)) {
            return (T) new EditStudentFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
