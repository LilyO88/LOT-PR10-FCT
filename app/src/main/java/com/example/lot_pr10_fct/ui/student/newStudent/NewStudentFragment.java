package com.example.lot_pr10_fct.ui.student.newStudent;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_pr10_fct.R;

public class NewStudentFragment extends Fragment {

    private NewStudentFragmentViewModel mViewModel;

    public static NewStudentFragment newInstance() {
        return new NewStudentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_student, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewStudentFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
