package com.example.lot_pr10_fct.ui.student.studentsList;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.lot_pr10_fct.data.local.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class StudentsListFragment extends Fragment {

    private StudentsListFragmentViewModel mViewModel;
    private NavController navController;
    private FloatingActionButton fab;
    private TextView imagen;
    private StudentsListFragmentViewModel viewModel;
    private StudentsListFragmentAdapter listAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_students_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        viewModel = ViewModelProviders.of(this, new StudentsListFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(StudentsListFragmentViewModel.class);

        setupViews(requireView());
        setupRecyclerView(requireView());
        observeStudents();
    }

    private void setupRecyclerView(View view) {
        RecyclerView lstStudents = ViewCompat.requireViewById(view, R.id.sl_rv_studentsList);
        lstStudents.setHasFixedSize(true);
        listAdapter = new StudentsListFragmentAdapter(navController, getContext());
        lstStudents.setAdapter(listAdapter);
        lstStudents.setLayoutManager(
                new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.main_lists_columns)));
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
                        Student studentToDelete = listAdapter.getItem(viewHolder.getAdapterPosition());
                        viewModel.setStudentToDelete(studentToDelete);
                        viewModel.deleteStudent(studentToDelete);
                        Snackbar.make(requireView(), "Estudiante eliminado", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", view1 -> viewModel.insertStudent(viewModel.getStudentToDelete())).show();
                    }
                });
        itemTouchHelper.attachToRecyclerView(lstStudents);
    }

    private void observeStudents() {
        viewModel.getStudents().observe(getViewLifecycleOwner(), students -> {
            listAdapter.submitList(students);
            imagen.setVisibility(students.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupViews(View view) {
        fab = ViewCompat.requireViewById(view, R.id.sl_fab);
        imagen = ViewCompat.requireViewById(view, R.id.sl_lblEmptyView);

        Bundle bundle = new Bundle();
        bundle.putLong("STUDENT_ID", 0);
        observeCompanies(bundle);
    }

    private void observeCompanies(Bundle bundle) {
        viewModel.getCompanies().observe(getViewLifecycleOwner(), companies -> {
            fab.setOnClickListener(v -> {
                navigateNewStudent(companies, bundle);
            });
            imagen.setOnClickListener(v -> navigateNewStudent(companies, bundle));
        });
    }

    private void navigateNewStudent(List<Company> companies, Bundle bundle) {
        if(companies.size() > 0) {
            navController.navigate(R.id.action_studentsListFragment_to_newStudentFragment, bundle);
        } else {
            Snackbar.make(requireView(), "Aún no hay empresas que asignar a los estudiantes", Snackbar.LENGTH_LONG).show();
        }
    }
}
