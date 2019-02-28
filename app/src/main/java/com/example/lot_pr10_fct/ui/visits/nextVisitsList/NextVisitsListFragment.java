package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_pr10_fct.R;

public class NextVisitsListFragment extends Fragment {

    private NextVisitsListFragmentViewModel mViewModel;

    public static NextVisitsListFragment newInstance() {
        return new NextVisitsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next_visits_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NextVisitsListFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
