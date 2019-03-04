package com.example.lot_pr10_fct.ui.student.studentsList;

import androidx.core.view.ViewCompat;
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
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListFragment extends Fragment {

    private StudentsListFragmentViewModel mViewModel;
    private NavController navController;
    private FloatingActionButton fab;
    private TextView imagen;

    public static StudentsListFragment newInstance() {
        return new StudentsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_students_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        fab = ViewCompat.requireViewById(requireView(), R.id.sl_fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.newVisitFragment));

        imagen = ViewCompat.requireViewById(requireView(), R.id.sl_lblEmptyView);
        imagen.setOnClickListener(v -> navController.navigate(R.id.newVisitFragment));
    }

}
