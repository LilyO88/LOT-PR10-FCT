package com.example.lot_pr10_fct.ui.student.newStudent;

import com.example.lot_pr10_fct.data.Repository;

import androidx.lifecycle.ViewModel;

public class NewStudentFragmentViewModel extends ViewModel {

    private final Repository repository;

    private boolean stateName = true;
    private boolean stateGrade = true;
    private boolean stateStudentPhone = true;
    private boolean stateEmail = true;
    private boolean stateTutor = true;
    private boolean stateTutorPhone = true;
    private boolean stateTime = true;

    private boolean stateEmailImg = true;
    private boolean stateStudentPhoneImg = true;
    private boolean stateTutorPhoneImg = true;
    private boolean stateBeginImg = true;
    private boolean stateEndImg = true;

    public NewStudentFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public boolean isStateTutorPhoneImg() {
        return stateTutorPhoneImg;
    }

    public void setStateTutorPhoneImg(boolean stateTutorPhoneImg) {
        this.stateTutorPhoneImg = stateTutorPhoneImg;
    }

    public boolean isStateEmailImg() {
        return stateEmailImg;
    }

    public void setStateEmailImg(boolean stateEmailImg) {
        this.stateEmailImg = stateEmailImg;
    }

    public boolean isStateStudentPhoneImg() {
        return stateStudentPhoneImg;
    }

    public void setStateStudentPhoneImg(boolean stateStudentPhoneImg) {
        this.stateStudentPhoneImg = stateStudentPhoneImg;
    }

    public boolean isStateBeginImg() {
        return stateBeginImg;
    }

    public void setStateBeginImg(boolean stateBeginImg) {
        this.stateBeginImg = stateBeginImg;
    }

    public boolean isStateEndImg() {
        return stateEndImg;
    }

    public void setStateEndImg(boolean stateEndImg) {
        this.stateEndImg = stateEndImg;
    }

    public boolean isStateName() {
        return stateName;
    }

    public void setStateName(boolean stateName) {
        this.stateName = stateName;
    }

    public boolean isStateGrade() {
        return stateGrade;
    }

    public void setStateGrade(boolean stateGrade) {
        this.stateGrade = stateGrade;
    }

    public boolean isStateStudentPhone() {
        return stateStudentPhone;
    }

    public void setStateStudentPhone(boolean stateStudentPhone) {
        this.stateStudentPhone = stateStudentPhone;
    }

    public boolean isStateEmail() {
        return stateEmail;
    }

    public void setStateEmail(boolean stateEmail) {
        this.stateEmail = stateEmail;
    }

    public boolean isStateTutor() {
        return stateTutor;
    }

    public void setStateTutor(boolean stateTutor) {
        this.stateTutor = stateTutor;
    }

    public boolean isStateTutorPhone() {
        return stateTutorPhone;
    }

    public void setStateTutorPhone(boolean stateTutorPhone) {
        this.stateTutorPhone = stateTutorPhone;
    }

    public boolean isStateTime() {
        return stateTime;
    }

    public void setStateTime(boolean stateTime) {
        this.stateTime = stateTime;
    }
}
