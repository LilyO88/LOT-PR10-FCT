package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.RepositoryImpl;
import com.example.lot_pr10_fct.data.local.AppDatabase;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NextVisitsListFragment extends Fragment {

    private NextVisitsListFragmentViewModel viewModel;
    private NavController navController;
    private FloatingActionButton fab;
    private TextView imagen;
    private NextVisitsListFragmentAdapter listAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next_visits_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(this, new NextVisitsListFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(NextVisitsListFragmentViewModel.class);

        setupViews(requireView());
        setupRecyclerView(requireView());
        observeStudents();
        observeStudentVisits();
        observeNextVisits();
    }

    private void observeNextVisits() {
        viewModel.setNextVisits();
        viewModel.getNextVisits().observe(getViewLifecycleOwner(), nextVisits -> {
            listAdapter.submitList(nextVisits);
        });
    }

    private void observeStudentVisits() {
        List<Student> students = viewModel.getStudents();
//        List<Visit> visitas = new ArrayList<>();
        for(Student student: students) {
            viewModel.getVisitsStudent(student.getId()).observe(getViewLifecycleOwner(), studentLD -> {
                Visit visita = new Visit();
                visita.setIdStudent(student.getId());
                visita.setStudent(student.getName());
                visita.setCompanyStudent(student.getCompany());
                if(studentLD.getStudent().isEmpty()) {
                    visita.setDate(null);
                    visita.setObservations(null);
                } else {
                    visita.setDate(studentLD.getDate());
                    visita.setObservations(studentLD.getObservations());
                }
//                visitas.add(visita);
                viewModel.addVisit(visita);
            });
        }
//        listAdapter.submitList(visitas);
//        viewModel.setNextVisits(visitas);
    }

    private void setupRecyclerView(View view) {
        RecyclerView lstVisits = ViewCompat.requireViewById(view, R.id.nvl_rv_visitsList);
        lstVisits.setHasFixedSize(true);
        listAdapter = new NextVisitsListFragmentAdapter(navController, viewModel, getViewLifecycleOwner());
        lstVisits.setAdapter(listAdapter);
        lstVisits.setLayoutManager(
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
                        Snackbar.make(requireView(), "No se pueden eliminar las próximas visitas", Snackbar.LENGTH_LONG).show();
                    }
                });
        itemTouchHelper.attachToRecyclerView(lstVisits);
    }

    private void setupViews(View view) {
        fab = ViewCompat.requireViewById(view, R.id.nvl_fab);
        imagen = ViewCompat.requireViewById(view, R.id.nvl_lblEmptyView);
    }

    private void observeStudents() {
        viewModel.getStudentsLiveData().observe(getViewLifecycleOwner(), students -> {

            if(students.isEmpty()) {
                imagen.setVisibility(View.VISIBLE);
            } else {
                imagen.setVisibility(View.INVISIBLE);
                viewModel.setStudents(students);
            }

            Bundle bundle = new Bundle();
            bundle.putLong("VISIT_ID", 0);
            fab.setOnClickListener(v -> {
                navigateNewVisit(students, bundle);
            });
            imagen.setOnClickListener(v -> navigateNewVisit(students, bundle));
        });
    }

    /*private void showList(List<Student> students) {
        List<Visit> nextVisits = new ArrayList<>();
        for(Student student: students) {
            Visit visit = new Visit();
            viewModel.getVisitsStudent(student.getId()).observe(getViewLifecycleOwner(), visita -> {
                if(visita == null) {
                    visit.setStudent(student.getName());
                    visit.setCompanyStudent(student.getCompany());
                    visit.setDate(null);
                    nextVisits.add(visit);
                } else {
                    visit.setStudent(student.getName());
                    visit.setCompanyStudent(student.getCompany());
                    visit.setDate(visita.getDate());
                    nextVisits.add(visit);
                }
            });
        }
        observeNextVisits(nextVisits);
    }*/

/*    private void showList(List<Student> students) {
        List<Visit> nextVisits = new ArrayList<>();
        for(Student student: students) {
            Visit visit = new Visit();
            viewModel.getVisitsStudent(student.getId()).observe(getViewLifecycleOwner(), visita -> {
                if(visita == null) {
                    visit.setStudent(student.getName());
                    visit.setCompanyStudent(student.getCompany());
                    visit.setDate(null);
                    nextVisits.add(visit);
                } else {
                    visit.setStudent(student.getName());
                    visit.setCompanyStudent(student.getCompany());
                    visit.setDate(visita.getDate());
                    nextVisits.add(visit);
                }
            });
        }
        observeNextVisits(nextVisits);
    }*/

/*    private void observeNextVisits(List<Visit> nextVis) {
        viewModel.setNextVisits(nextVis);
        viewModel.getNextVisits().observe(getViewLifecycleOwner(), nextVisits -> {
            listAdapter.submitList(nextVisits);
        });
    }*/

    private void navigateNewVisit(List<Student> students, Bundle bundle) {
        if(students.size() > 0) {
            navController.navigate(R.id.action_nextVisitsListFragment_to_newVisitFragment, bundle);
        } else {
            Snackbar.make(requireView(), "Aún no hay alumnos para recibir visitas", Snackbar.LENGTH_LONG).show();
        }
    }

}
