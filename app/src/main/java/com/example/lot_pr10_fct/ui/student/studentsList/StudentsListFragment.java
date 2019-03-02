package com.example.lot_pr10_fct.ui.student.studentsList;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_pr10_fct.R;

public class StudentsListFragment extends Fragment {

    private StudentsListFragmentViewModel mViewModel;
    private NavController navController;

    public static StudentsListFragment newInstance() {
        return new StudentsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        navController = Navigation.findNavController(requireActivity(), R.id.navHostFragment);

        return inflater.inflate(R.layout.fragment_students_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        //mViewModel
        requireActivity().findViewById(R.id.sl_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.newStudentFragment);
            }
        });
    }

//    @Override
//    public void onDetach() {
//        navController.popBackStack();
//        super.onDetach();
//    }

}
