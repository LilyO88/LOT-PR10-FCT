package com.example.lot_pr10_fct.ui.visits.editVisit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_pr10_fct.R;

public class EditVisitFragment extends Fragment {

    private EditVisitFragmentViewModel mViewModel;

    public static EditVisitFragment newInstance() {
        return new EditVisitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_visit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditVisitFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
