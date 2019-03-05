package com.example.lot_pr10_fct.ui.company.newCompany;

import com.example.lot_pr10_fct.data.Repository;
import com.example.lot_pr10_fct.data.local.model.Company;

import androidx.lifecycle.ViewModel;

public class NewCompanyFragmentViewModel extends ViewModel {

    private final Repository repository;

    private boolean stateName = true;
    private boolean statePhone = true;
    private boolean statePhoneImg = true;
    private boolean stateCif = true;
    private boolean stateAddress = true;
    private boolean stateAddressImg = true;
    private boolean stateEmail = true;
    private boolean stateEmailImg = true;
    private boolean stateContact = true;
//    private boolean stateUrl = true;

    public NewCompanyFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public boolean isStateName() {
        return stateName;
    }

    public void setStateName(boolean stateName) {
        this.stateName = stateName;
    }

    public boolean isStatePhone() {
        return statePhone;
    }

    public void setStatePhone(boolean statePhone) {
        this.statePhone = statePhone;
    }

    public boolean isStateCif() {
        return stateCif;
    }

    public void setStateCif(boolean stateCif) {
        this.stateCif = stateCif;
    }

    public boolean isStateAddress() {
        return stateAddress;
    }

    public void setStateAddress(boolean stateAddress) {
        this.stateAddress = stateAddress;
    }

    public boolean isStateEmail() {
        return stateEmail;
    }

    public void setStateEmail(boolean stateEmail) {
        this.stateEmail = stateEmail;
    }

    public boolean isStateContact() {
        return stateContact;
    }

    public void setStateContact(boolean stateContact) {
        this.stateContact = stateContact;
    }

/*    public boolean isStateUrl() {
        return stateUrl;
    }

    public void setStateUrl(boolean stateUrl) {
        this.stateUrl = stateUrl;
    }*/

    public boolean isStatePhoneImg() {
        return statePhoneImg;
    }

    public void setStatePhoneImg(boolean statePhoneImg) {
        this.statePhoneImg = statePhoneImg;
    }

    public boolean isStateAddressImg() {
        return stateAddressImg;
    }

    public void setStateAddressImg(boolean stateAddressImg) {
        this.stateAddressImg = stateAddressImg;
    }

    public boolean isStateEmailImg() {
        return stateEmailImg;
    }

    public void setStateEmailImg(boolean stateEmailImg) {
        this.stateEmailImg = stateEmailImg;
    }

    public void insert(Company company) {
        repository.insertCompany(company);
    }
}
