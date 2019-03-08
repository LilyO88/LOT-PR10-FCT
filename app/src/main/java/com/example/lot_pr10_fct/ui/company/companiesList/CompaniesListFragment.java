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
import com.example.lot_pr10_fct.data.local.model.Company;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CompaniesListFragment extends Fragment {

    private CompaniesListFragmentViewModel viewModel;
    private CompaniesListFragmentAdapter listAdapter;
    NavController navController;
    private FloatingActionButton fab;
    private TextView imagen;

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
        listAdapter = new CompaniesListFragmentAdapter(navController, getContext());
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
                        Company companyToDelete = listAdapter.getItem(viewHolder.getAdapterPosition());
                        viewModel.setCompanyToDelete(companyToDelete);
                        viewModel.deleteCompany(companyToDelete);
                        Snackbar.make(requireView(), "Empresa eliminada", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", view1 -> viewModel.insertCompany(viewModel.getCompanyToDelete())).show();
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

        Bundle bundle = new Bundle();
        bundle.putLong("COMPANY_ID", 0);
        fab.setOnClickListener(v -> navController.navigate(R.id.action_companiesListFragment_to_newCompanyFragment, bundle));
        imagen.setOnClickListener(v -> navController.navigate(R.id.action_companiesListFragment_to_newCompanyFragment, bundle));
    }


}
