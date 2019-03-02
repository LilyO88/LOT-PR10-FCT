package com.example.lot_pr10_fct.ui.visits.newVisit;

import androidx.lifecycle.ViewModelProviders;

import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.lot_pr10_fct.R;

public class NewVisitFragment extends Fragment {

    private NewVisitFragmentViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_visit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel



//        Spinner spinner = requireActivity().findViewById(R.id.nv_spStudent);
//        String[] arraySpinner = new String[] {
//                "1", "2", "3", "4", "5", "6", "7"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(),
//                android.R.layout.simple_spinner_item, arraySpinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        //cerrar teclado
        super.onDetach();
    }
}
