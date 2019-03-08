package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class NextVisitsListFragmentViewModel extends ViewModel {

    private final Repository repository;
    private final LiveData<List<Student>> studentsLiveData;
//    private MutableLiveData<List<Student>> studentsLiveData = new MutableLiveData<>();
//    private final LiveData<List<Visit>> visits;
    private MutableLiveData<List<Visit>> nextVisits = new MutableLiveData<>();

    NextVisitsListFragmentViewModel(Repository repository) {
        this.repository = repository;
        studentsLiveData = repository.queryStudents();
//        visits = repository.queryVisits();
    }

    public LiveData<List<Student>> getStudentsLiveData() {
        return studentsLiveData;
    }

//    public LiveData<List<Visit>> getVisits() {
//        return visits;
//    }

    public LiveData<List<Visit>> getNextVisits() {
        return nextVisits;
    }

    public void setNextVisits(List<Visit> nextVis) {
        nextVisits.postValue(nextVis);
    }

    public LiveData<Visit> getVisitsStudent(long studentId) {
        return repository.queryLastVisitsStudent(studentId);
    }

}
