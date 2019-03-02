package com.example.lot_pr10_fct.ui.main;

import android.app.Application;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.ui.settings.SharedPreferencesStringLiveData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<String> interval;
    private final Repository repository;


    public MainActivityViewModel(@NonNull Application application, Repository repository) {
        super(application);
        
        this.repository = repository;
        interval = new SharedPreferencesStringLiveData(
                PreferenceManager.getDefaultSharedPreferences(application),
                application.getString(R.string.prefNumDays_key),
                application.getString(R.string.prefNumDays_defaultValue));
    }

    LiveData<String> getTxtContent() {
        return interval;
    }

}
