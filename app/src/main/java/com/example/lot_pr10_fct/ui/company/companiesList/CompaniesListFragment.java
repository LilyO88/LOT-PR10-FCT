package com.example.lot_pr10_fct.ui.company.companiesList;

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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.RepositoryImpl;
import com.example.lot_pr10_fct.data.local.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompaniesListFragment extends Fragment {

    private CompaniesListFragmentViewModel viewModel;
    private CompaniesListFragmentAdapter listAdapter;
    NavController navController;
    private FloatingActionButton fab;
    private TextView imagen;

    public static CompaniesListFragment newInstance() {
        return new CompaniesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_companies_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        viewModel = ViewModelProviders.of(this, new CompaniesListFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(CompaniesListFragmentViewModel.class);

        setupViews(requireView());
        setupRecyclerView(requireView());
        observeCompanies();
    }

    private void setupRecyclerView(View view) {
        RecyclerView lstCompanies = ViewCompat.requireViewById(view, R.id.cl_rv_companiesList);
        lstCompanies.setHasFixedSize(true);
        listAdapter = new CompaniesListFragmentAdapter(navController);
        lstCompanies.setAdapter(listAdapter);
        lstCompanies.setLayoutManager(
                new LinearLayoutManager(requireActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        viewModel.deleteCompany(listAdapter.getItem(viewHolder.getAdapterPosition()));
                    }
                });
        itemTouchHelper.attachToRecyclerView(lstCompanies);
    }

    private void observeCompanies() {
        viewModel.getCompanies().observe(getViewLifecycleOwner(), companies -> {
            listAdapter.submitList(companies);
            imagen.setVisibility(companies.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupViews(View view) {
        fab = ViewCompat.requireViewById(view, R.id.cl_fab);
        imagen = ViewCompat.requireViewById(view, R.id.cl_lblEmptyView);

        fab.setOnClickListener(v -> navController.navigate(R.id.newCompanyFragment));
        imagen.setOnClickListener(v -> navController.navigate(R.id.newCompanyFragment));
    }


}
