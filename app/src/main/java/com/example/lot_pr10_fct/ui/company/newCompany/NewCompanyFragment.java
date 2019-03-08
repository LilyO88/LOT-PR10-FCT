package com.example.lot_pr10_fct.ui.company.newCompany;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.RepositoryImpl;
import com.example.lot_pr10_fct.data.local.AppDatabase;
import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.utils.KeyboardUtils;
import com.example.lot_pr10_fct.utils.ValidationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class NewCompanyFragment extends Fragment {

    private NewCompanyFragmentViewModel viewModel;
    private EditText txtName;
    private EditText txtTlfo;
    private EditText txtCif;
    private EditText txtAddress;
    private EditText txtEmail;
    private EditText txtContact;
    private EditText txtUrl;
    private TextView lblName;
    private TextView lblTlfo;
    private TextView lblCif;
    private TextView lblAddress;
    private TextView lblEmail;
    private TextView lblContact;
    private TextView lblUrl;


    private ImageView imgPhone;
    private ImageView imgAddress;
    private ImageView imgEmail;

    private FloatingActionButton saveFab;

    NavController navController;
    private long companyId;
    private boolean editMode;


/*    public static NewCompanyFragment newInstance() {
        return new NewCompanyFragment();
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getArguments());
        companyId = getArguments().getLong("COMPANY_ID");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_company, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new NewCompanyFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(NewCompanyFragmentViewModel.class);
        setupViews();
/*
        validateFields();
*/
        if(companyId != 0) {
            editMode = true;
            viewModel.getCompany(companyId).observe(getViewLifecycleOwner(), this::showCompany);
        }
    }

    private void showCompany(Company company) {
        viewModel.setCompanyCompare(company);
        txtName.setText(company.getName());
        txtTlfo.setText(company.getPhone());
        txtCif.setText(company.getCif());
        txtAddress.setText(company.getAddress());
        txtEmail.setText(company.getEmail());
        txtContact.setText(company.getContactName());
        txtUrl.setText(company.getUrl());
    }

    private void setupViews() {
        txtName = ViewCompat.requireViewById(requireView(), R.id.nc_txtName);
        txtTlfo = ViewCompat.requireViewById(requireView(), R.id.nc_txtTlfo);
        txtCif = ViewCompat.requireViewById(requireView(), R.id.nc_txtCIF);
        txtAddress = ViewCompat.requireViewById(requireView(), R.id.nc_txtAddress);
        txtEmail = ViewCompat.requireViewById(requireView(), R.id.nc_txtEmail);
        txtContact = ViewCompat.requireViewById(requireView(), R.id.nc_txtContact);
        txtUrl = ViewCompat.requireViewById(requireView(), R.id.nc_txtURL);

        lblName = ViewCompat.requireViewById(requireView(), R.id.nc_lblName);
        lblTlfo = ViewCompat.requireViewById(requireView(), R.id.nc_lblTlfo);
        lblCif = ViewCompat.requireViewById(requireView(), R.id.nc_lblCIF);
        lblAddress = ViewCompat.requireViewById(requireView(), R.id.nc_lblAddress);
        lblEmail = ViewCompat.requireViewById(requireView(), R.id.nc_lblEmail);
        lblContact = ViewCompat.requireViewById(requireView(), R.id.nc_lblContact);
        lblUrl = ViewCompat.requireViewById(requireView(), R.id.nc_lblURL);

        imgPhone = ViewCompat.requireViewById(requireView(), R.id.nc_imgTlfo);
        imgAddress = ViewCompat.requireViewById(requireView(), R.id.nc_imgAddress);
        imgEmail = ViewCompat.requireViewById(requireView(), R.id.nc_imgEmail);

        saveFab = ViewCompat.requireViewById(requireView(), R.id.nc_fab);
        navController = NavHostFragment.findNavController(this);

        setFocusListeners();

        //TextWatcher, check fields
        GestorTextWatcher gestorTextWatcher = new GestorTextWatcher();
        setTextWatcher(gestorTextWatcher);

        setListeners();
    }

    private void validateFields() {
        lblName.setEnabled(viewModel.isStateName());
        lblTlfo.setEnabled(viewModel.isStatePhone());
        lblCif.setEnabled(viewModel.isStateCif());
        lblAddress.setEnabled(viewModel.isStateAddress());
        lblEmail.setEnabled(viewModel.isStateEmail());
        lblContact.setEnabled(viewModel.isStateContact());
        lblUrl.setEnabled(viewModel.isStateName());

        imgPhone.setEnabled(viewModel.isStatePhoneImg());
        imgAddress.setEnabled(viewModel.isStateAddressImg());
        imgEmail.setEnabled(viewModel.isStateEmailImg());
    }

    private void setTextWatcher(GestorTextWatcher gestorTextWatcher) {
        txtName.addTextChangedListener(gestorTextWatcher);
        txtTlfo.addTextChangedListener(gestorTextWatcher);
        txtCif.addTextChangedListener(gestorTextWatcher);
        txtAddress.addTextChangedListener(gestorTextWatcher);
        txtEmail.addTextChangedListener(gestorTextWatcher);
        txtContact.addTextChangedListener(gestorTextWatcher);
        txtUrl.addTextChangedListener(gestorTextWatcher);
    }

    private class GestorTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkCurrentView();
        }

        @Override
        public void afterTextChanged(Editable s) {
            checkCurrentView();
        }
    }

    private void checkCurrentView() {
        if(getActivity().getCurrentFocus() != null) {
            if (getActivity().getCurrentFocus().getId() == txtName.getId()) {
                checkString(lblName, txtName);
            } else if (getActivity().getCurrentFocus().getId() == txtTlfo.getId()) {
                checkPhone();
            } else if (getActivity().getCurrentFocus().getId() == txtCif.getId()) {
                checkCif(lblCif, txtCif);
            } else if (getActivity().getCurrentFocus().getId() == txtAddress.getId()) {
                checkStringImg(lblAddress, txtAddress, imgAddress);
            } else if (getActivity().getCurrentFocus().getId() == txtEmail.getId()) {
                checkEmail(lblEmail, txtEmail, imgEmail);
            } else if (getActivity().getCurrentFocus().getId() == txtContact.getId()) {
                checkString(lblContact, txtContact);
            } else if (getActivity().getCurrentFocus().getId() == txtUrl.getId()) {
                checkString(lblUrl, txtUrl);
            }
        }
    }

    private void setFocusListeners() {
        txtName.setOnFocusChangeListener((v, hasFocus) -> setBold(lblName, txtName));
        txtTlfo.setOnFocusChangeListener((v, hasFocus) -> setBold(lblTlfo, txtTlfo));
        txtCif.setOnFocusChangeListener((v, hasFocus) -> setBold(lblCif, txtCif));
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> setBold(lblAddress, txtAddress));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setBold(lblEmail, txtEmail));
        txtContact.setOnFocusChangeListener((v, hasFocus) -> setBold(lblContact, txtContact));
        txtUrl.setOnFocusChangeListener((v, hasFocus) -> setBold(lblUrl, txtUrl));
    }

    private void setBold(TextView textView, EditText editText) {
        if(editText.hasFocus()) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    private void setListeners() {
        imgPhone.setOnClickListener(v -> callPhone());
        imgAddress.setOnClickListener(v -> searchAddress());
        imgEmail.setOnClickListener(v -> sendEmail());
        txtUrl.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                save();
                return true;
            }
            return false;
        });

        saveFab.setOnClickListener(v -> save());
    }

    private void save() {
        if (!validateAll()) {
            Snackbar.make(requireView(), R.string.main_error_saving, Snackbar.LENGTH_LONG).show();
        } else {
            Company company = new Company(txtName.getText().toString(),
                    txtTlfo.getText().toString(),
                    txtCif.getText().toString(),
                    txtAddress.getText().toString(),
                    txtEmail.getText().toString(),
                    txtContact.getText().toString(),
                    txtUrl.getText().toString());
            if(editMode) {
                company.setId(companyId);
                if(company.equals(viewModel.getCompanyCompare())){
                    Snackbar.make(requireView(), "No ha habido cambios", Snackbar.LENGTH_LONG).show();
                } else {
                    viewModel.update(company);
                    Snackbar.make(requireView(), "¡Empresa modificada con éxito!", Snackbar.LENGTH_LONG).show();
                }
            } else {
                viewModel.insert(company);
                Snackbar.make(requireView(), "¡Empresa guardada con éxito!", Snackbar.LENGTH_LONG).show();
            }

            getFragmentManager().popBackStack();
//            navController.navigate(R.id.companiesListFragment);
        }
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void checkStringImg(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidString(editText.getText().toString()));
    }

    private void checkString(TextView textView, EditText editText) {
        enabledDisabledField(textView, editText, ValidationUtils.isValidString(editText.getText().toString()));
    }

    private void checkPhone() {
        enabledDisabledFieldImg(lblTlfo, txtTlfo, imgPhone, ValidationUtils.isValidPhone(txtTlfo.getText().toString()));
    }

    private void checkEmail(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidEmail(editText.getText().toString()));
    }

    private void checkCif(TextView textView, EditText editText) {
        enabledDisabledField(textView, editText, ValidationUtils.isValidCIF(editText.getText().toString()));
    }

    private void checkAll() {
        checkString(lblName, txtName);
        checkPhone();
        checkCif(lblCif, txtCif);
        checkStringImg(lblAddress, txtAddress, imgAddress);
        checkEmail(lblEmail, txtEmail, imgEmail);
        checkString(lblContact, txtContact);
        checkString(lblUrl, txtUrl);
    }

    private void enabledDisabledFieldImg(TextView textView, EditText editText, ImageView imageView, boolean valid) {
        if(valid) {
            editText.setError(null);
        } else {
            editText.setError(getString(R.string.main_invalid_data));
        }
        imageView.setEnabled(valid);
        textView.setEnabled(valid);
        selectStateView(textView, valid);
    }

    private void enabledDisabledField(TextView textView, EditText editText, boolean valid) {
        if(valid) {
            editText.setError(null);
        } else {
            editText.setError(getString(R.string.main_invalid_data));
        }
        textView.setEnabled(valid);
        selectStateView(textView, valid);
    }

    private void selectStateView(View view, boolean state) {
        if(view == txtName) {
            viewModel.setStateName(state);
        } else if(view == txtTlfo) {
            viewModel.setStatePhone(state);
            viewModel.setStatePhoneImg(state);
        } else if(view == txtCif) {
            viewModel.setStateCif(state);
        } else if(view == txtAddress) {
            viewModel.setStateAddress(state);
            viewModel.setStateAddressImg(state);
        } else if(view == txtEmail) {
            viewModel.setStateEmail(state);
            viewModel.setStateEmailImg(state);
        } else if(view == txtContact) {
            viewModel.setStateContact(state);
        } else if(view == txtUrl) {
            viewModel.setStateUrl(state);
        }
    }

    private boolean validateAll() {
        checkAll();
        View[] array = new View[]{lblName, lblTlfo, lblCif, lblAddress, lblEmail, lblContact, lblUrl};
        for (View view: array) {
            if(!view.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void sendEmail() {
        String email = txtEmail.getText().toString();
        Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("mailto:%s", email)));
        startActivity(sendEmailIntent);
    }

    private void callPhone() {
        String phone = txtTlfo.getText().toString();
        Intent callPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", phone)));
        startActivity(callPhoneIntent);
    }

    private void searchAddress() {
        String address = txtAddress.getText().toString();
        Intent searchAddressIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:0,0?q=%s", address)));
        startActivity(searchAddressIntent);
    }

    @Override
    public void onDetach() {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
        super.onDetach();
    }
}
