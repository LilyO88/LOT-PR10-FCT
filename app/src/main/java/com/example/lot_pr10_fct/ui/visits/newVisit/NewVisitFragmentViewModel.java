package com.example.lot_pr10_fct.ui.visits.newVisit;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NewVisitFragmentViewModel extends ViewModel {

    private final Repository repository;

    private boolean stateLblStudent = true;
    private boolean stateLblDate = true;
    private boolean stateLblTime = true;
    private boolean stateLblObservations = true;
    private boolean stateImgDate = true;
    private boolean stateImgBegin = true;
    private boolean stateImgEnd = true;

    public NewVisitFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public boolean isStateLblStudent() {
        return stateLblStudent;
    }

    public void setStateLblStudent(boolean stateLblStudent) {
        this.stateLblStudent = stateLblStudent;
    }

    public boolean isStateLblDate() {
        return stateLblDate;
    }

    public void setStateLblDate(boolean stateLblDate) {
        this.stateLblDate = stateLblDate;
    }

    public boolean isStateLblTime() {
        return stateLblTime;
    }

    public void setStateLblTime(boolean stateLblTime) {
        this.stateLblTime = stateLblTime;
    }

    public boolean isStateLblObservations() {
        return stateLblObservations;
    }

    public void setStateLblObservations(boolean stateLblObservations) {
        this.stateLblObservations = stateLblObservations;
    }

    public boolean isStateImgDate() {
        return stateImgDate;
    }

    public void setStateImgDate(boolean stateImgDate) {
        this.stateImgDate = stateImgDate;
    }

    public boolean isStateImgBegin() {
        return stateImgBegin;
    }

    public void setStateImgBegin(boolean stateImgBegin) {
        this.stateImgBegin = stateImgBegin;
    }

    public boolean isStateImgEnd() {
        return stateImgEnd;
    }

    public void setStateImgEnd(boolean stateImgEnd) {
        this.stateImgEnd = stateImgEnd;
    }
}
