package com.example.lot_pr10_fct.ui.student.studentsList;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.local.model.Student;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsListFragmentAdapter extends ListAdapter<Student, StudentsListFragmentAdapter.ViewHolder> {

    private NavController navController;

    protected StudentsListFragmentAdapter(NavController navController) {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName())
                        && TextUtils.equals(oldItem.getPhone(), newItem.getPhone())
                        && TextUtils.equals(oldItem.getEmail(), newItem.getEmail())
                        && TextUtils.equals(oldItem.getGrade(), newItem.getGrade())
                        && oldItem.getCompany() == newItem.getCompany()
                        && TextUtils.equals(oldItem.getTutorName(), newItem.getTutorName())
                        && TextUtils.equals(oldItem.getTutorPhone(), newItem.getTutorPhone())
                        && TextUtils.equals(oldItem.getHorary(), newItem.getHorary());
            }
        });

        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_studentslist_item, parent, false), navController);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected Student getItem(int position) {
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
        private TextView studentPhone;
        private TextView company;
        private TextView tutor;
        private TextView tutorPhone;

        public ViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            requireViews();

            itemView.setOnClickListener(v -> {
                //LD<long> getAdapterPosition();
                navController.navigate(R.id.editStudentFragment);
            });
        }

        private void requireViews() {
            name = ViewCompat.requireViewById(itemView, R.id.sl_card_lblStudentName);
            studentPhone = ViewCompat.requireViewById(itemView, R.id.sl_card_lblStudentTlfo);
            company = ViewCompat.requireViewById(itemView, R.id.sl_card_lblStudentCompany);
            tutor = ViewCompat.requireViewById(itemView, R.id.sl_card_lblTutorName);
            tutorPhone = ViewCompat.requireViewById(itemView, R.id.sl_card_lblTutorTlfo);
        }

        public void bind(Student student) {
            name.setText(student.getName());
            studentPhone.setText(student.getPhone());
            company.setText(student.getCompany());
            tutor.setText(student.getTutorName());
            tutorPhone.setText(student.getTutorPhone());
        }
    }
}
