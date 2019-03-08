package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NextVisitsListFragmentAdapter extends ListAdapter<Visit, NextVisitsListFragmentAdapter.ViewHolder> {

    private NavController navController;
    private NextVisitsListFragmentViewModel viewModel;
    private LifecycleOwner viewLifecycleOwner;

    protected NextVisitsListFragmentAdapter(NavController navController, NextVisitsListFragmentViewModel viewModel, LifecycleOwner viewLifecycleOwner) {
        super(new DiffUtil.ItemCallback<Visit>() {
            @Override
            public boolean areItemsTheSame(@NonNull Visit oldItem, @NonNull Visit newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Visit oldItem, @NonNull Visit newItem) {
//                return TextUtils.equals(oldItem.getStudent(), newItem.getStudent())
                return oldItem.getStudent() == newItem.getStudent()
//                        && TextUtils.equals(oldItem.getDay(), newItem.getDay())
//                        && TextUtils.equals(oldItem.getBeginHour(), newItem.getBeginHour())
//                        && TextUtils.equals(oldItem.getEndHour(), newItem.getEndHour())
                        && oldItem.getDate().equals(newItem.getDate())
                        && TextUtils.equals(oldItem.getObservations(), newItem.getObservations());
            }
        });

        this.navController = navController;
        this.viewModel = viewModel;
        this.viewLifecycleOwner = viewLifecycleOwner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nextvisits_item, parent, false), navController, viewModel, viewLifecycleOwner);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected Visit getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView company;
        private TextView nextVisit;
        NextVisitsListFragmentViewModel viewModel;
        LifecycleOwner viewLifecycleOwner;
        List<Student> students;
        List<Visit> visits;

        public ViewHolder(@NonNull View itemView, NavController navController, NextVisitsListFragmentViewModel viewModel, LifecycleOwner viewLifecycleOwner) {
            super(itemView);
            requireViews();
            this.viewModel = viewModel;
            this.viewLifecycleOwner = viewLifecycleOwner;

            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putLong("VISIT_ID", getItem(getAdapterPosition()).getId());
                navController.navigate(R.id.action_nextVisitsListFragment_to_newVisitFragment, bundle);
            });
/*            observeVisits();
            observeStudents();*/
        }
/*
        private void observeStudents() {
            viewModel.getStudentsLiveData().observe(viewLifecycleOwner, studentsO -> {
                students = studentsO;
            });
        }*/

        private void requireViews() {
            name = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblStudentName);
            company = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblStudentCompany);
            nextVisit = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblNextVisit);
        }

        public void bind(Visit visit) {
            String nkj;
            if(visit.getDate() == null) {
                setFields(visit, "¡Visitar cuánto antes!");
            } else {
                nkj = addDays(visit.getDate(), 15);
                setFields(visit, nkj);
            }
        }

        private void setFields(Visit visit, String nextVisitR) {
            name.setText(visit.getStudent());
            company.setText(visit.getCompanyStudent());
            nextVisit.setText(nextVisitR);
        }

        private String addDays(Date fecha, int dias){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DAY_OF_YEAR, dias);
            return formatter.format(calendar.getTime());
        }
    }
}
