package com.example.lot_pr10_fct.ui.visits.nextVisitsList;

import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.data.local.model.Visit;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class NextVisitsListFragmentViewModel extends ViewModel {

    private final Repository repository;
    private LiveData<List<Student>> studentsLiveData;
    private List<Student> students = new ArrayList<>();
    private MutableLiveData<List<Visit>> nextVisits = new MutableLiveData<>();
    private List<Visit> visitas = new ArrayList<>();

    NextVisitsListFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    //LDStudents
    public LiveData<List<Student>> getStudentsLiveData() {
        if(studentsLiveData == null) {
            studentsLiveData = repository.queryStudents();
        }
        return studentsLiveData;
    }

    //ListStudents
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //Visita del student
    public LiveData<Visit> getVisitsStudent(long studentId) {
        return repository.queryLastVisitsStudent(studentId);
    }

    //LDLista visitas
    public LiveData<List<Visit>> getNextVisits() {
        return nextVisits;
    }

    //Introducir próximas visitas de los estudiantes
    public void setNextVisits() {
        nextVisits.postValue(visitas);
    }

    //Introducir una visita (la consultada)
    public void addVisit(Visit visit) {
        visitas.add(visit);
    }
}
