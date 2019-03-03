package com.example.lot_pr10_fct.ui.company.companiesList;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.local.model.Company;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CompaniesListFragmentAdapter extends ListAdapter<Company, CompaniesListFragmentAdapter.ViewHolder> {

    private final NavController navController;

    CompaniesListFragmentAdapter(NavController navController) {
        super(new DiffUtil.ItemCallback<Company>() {
            @Override
            public boolean areItemsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName())
                        && TextUtils.equals(oldItem.getCif(), newItem.getCif())
                        && TextUtils.equals(oldItem.getAddress(), newItem.getAddress())
                        && TextUtils.equals(oldItem.getPhone(), newItem.getPhone())
                        && TextUtils.equals(oldItem.getEmail(), newItem.getEmail())
                        && TextUtils.equals(oldItem.getContactName(), newItem.getContactName());
            }
        });

        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_companieslist_item, parent, false), navController);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected Company getItem(int position) {
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
        private TextView address;
        private TextView contact;
        private TextView phone;

        public ViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            requireViews();

            itemView.setOnClickListener(v -> {
                //LD<long> getAdapterPosition();
                navController.navigate(R.id.editCompanyFragment);
            });
        }

        private void requireViews() {
            name = ViewCompat.requireViewById(itemView, R.id.cl_card_lblCompanyName);
            address = ViewCompat.requireViewById(itemView, R.id.cl_card_lblCompanyAddress);
            contact = ViewCompat.requireViewById(itemView, R.id.cl_card_lblCompanyContactName);
            phone = ViewCompat.requireViewById(itemView, R.id.cl_card_lblCompanyTlfo);
        }

        void bind(Company company) {
            name.setText(company.getName());
            address.setText(company.getAddress());
            contact.setText(company.getContactName());
            phone.setText(company.getPhone());
        }

    }
}
