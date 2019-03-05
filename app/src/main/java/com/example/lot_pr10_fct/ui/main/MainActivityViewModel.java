package com.example.lot_pr10_fct.ui.main;

import android.app.Application;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;
import com.example.lot_pr10_fct.ui.settings.SharedPreferencesStringLiveData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<String> interval;
    private final Repository repository;

    private final LiveData<List<Visit>> visits;
    private final LiveData<List<Student>> students;
    private final LiveData<List<Company>> companies;


    public MainActivityViewModel(@NonNull Application application, Repository repository) {
        super(application);

        interval = new SharedPreferencesStringLiveData(
                PreferenceManager.getDefaultSharedPreferences(application),
                application.getString(R.string.prefNumDays_key),
                application.getString(R.string.prefNumDays_defaultValue));
        this.repository = repository;
        visits = repository.queryVisits();
        students = repository.queryStudents();
        companies = repository.queryCompanies();
    }

    LiveData<String> getTxtContent() {
        return interval;
    }

    public LiveData<List<Visit>> getVisits() {
        return visits;
    }

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public LiveData<List<Company>> getCompanies() {
        return companies;
    }
}
