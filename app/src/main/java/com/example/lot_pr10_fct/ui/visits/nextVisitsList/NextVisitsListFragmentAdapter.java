package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.local.model.Visit;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NextVisitsListFragmentAdapter extends ListAdapter<Visit, NextVisitsListFragmentAdapter.ViewHolder> {

    private NavController navController;

    protected NextVisitsListFragmentAdapter(NavController navController) {
        super(new DiffUtil.ItemCallback<Visit>() {
            @Override
            public boolean areItemsTheSame(@NonNull Visit oldItem, @NonNull Visit newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Visit oldItem, @NonNull Visit newItem) {
//                return TextUtils.equals(oldItem.getStudent(), newItem.getStudent())
                return oldItem.getStudent() == newItem.getStudent()
                        && TextUtils.equals(oldItem.getDay(), newItem.getDay())
                        && TextUtils.equals(oldItem.getBeginHour(), newItem.getBeginHour())
                        && TextUtils.equals(oldItem.getEndHour(), newItem.getEndHour())
                        && TextUtils.equals(oldItem.getObservations(), newItem.getObservations());
            }
        });

        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nextvisits_item, parent, false), navController);    }

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

        public ViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            requireViews();

            itemView.setOnClickListener(v -> {
                //LD<long> getAdapterPosition();
                navController.navigate(R.id.editVisitFragment);
            });
        }

        private void requireViews() {
            name = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblStudentName);
            company = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblStudentCompany);
            nextVisit = ViewCompat.requireViewById(itemView, R.id.nvl_card_lblNextVisit);
        }

        public void bind(Visit visit) {
            //Comprobar si el alumno tiene visitas registradas
            String next = "";
            if(true) {

            } else {
                next = "¡Visitar cuanto antes!";
            }

            name.setText(Long.toString(visit.getStudent()));
            company.setText(visit.getCompany());
            nextVisit.setText(next);
        }
    }
}
