package com.example.lot_pr10_fct.ui.visits.editVisit;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.RepositoryImpl;
import com.example.lot_pr10_fct.data.local.AppDatabase;
import com.example.lot_pr10_fct.utils.KeyboardUtils;
import com.example.lot_pr10_fct.utils.ValidationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class EditVisitFragment extends Fragment {

    private EditVisitFragmentViewModel viewModel;
    private Spinner spStudent;
    private EditText txtDate;
    private EditText txtBegin;
    private EditText txtEnd;
    private EditText txtObservations;
    private TextView lblStudent;
    private TextView lblDate;
    private TextView lblTime;
    private TextView lblObservations;

    private ImageView imgDate;
    private ImageView imgBegin;
    private ImageView imgEnd;

    private FloatingActionButton saveFab;

    NavController navController;

    public static EditVisitFragment newInstance() {
        return new EditVisitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_visit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new EditVisitFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(EditVisitFragmentViewModel.class);
        setupViews();
        validateFields();
    }

    private void setupViews() {
        spStudent = ViewCompat.requireViewById(requireView(), R.id.ev_spStudent);
        txtDate = ViewCompat.requireViewById(requireView(), R.id.ev_txtDate);
        txtBegin = ViewCompat.requireViewById(requireView(), R.id.ev_txtBegin);
        txtEnd = ViewCompat.requireViewById(requireView(), R.id.ev_txtEnd);
        txtObservations = ViewCompat.requireViewById(requireView(), R.id.ev_txtObservations);

        lblStudent = ViewCompat.requireViewById(requireView(), R.id.ev_lblStudent);
        lblDate = ViewCompat.requireViewById(requireView(), R.id.ev_lblDate);
        lblTime = ViewCompat.requireViewById(requireView(), R.id.ev_lblTime);
        lblObservations = ViewCompat.requireViewById(requireView(), R.id.ev_lblObservations);

        imgDate = ViewCompat.requireViewById(requireView(), R.id.ev_imgDate);
        imgBegin = ViewCompat.requireViewById(requireView(), R.id.ev_imgBegin);
        imgEnd = ViewCompat.requireViewById(requireView(), R.id.ev_imgEnd);

        saveFab = ViewCompat.requireViewById(requireView(), R.id.ev_fab);

        navController = NavHostFragment.findNavController(this);

        setFocusListeners();

        //TextWatcher, check fields
        GestorTextWatcher gestorTextWatcher = new GestorTextWatcher();
        setTextWatcher(gestorTextWatcher);

        setListeners();
    }

    private void validateFields() {
        lblStudent.setEnabled(viewModel.isStateLblStudent());
        lblDate.setEnabled(viewModel.isStateLblDate());
        lblTime.setEnabled(viewModel.isStateLblTime());
        lblObservations.setEnabled(viewModel.isStateLblObservations());

        imgDate.setEnabled(viewModel.isStateImgDate());
        imgBegin.setEnabled(viewModel.isStateImgBegin());
        imgEnd.setEnabled(viewModel.isStateImgEnd());
    }

    private void setTextWatcher(GestorTextWatcher gestorTextWatcher) {
        txtDate.addTextChangedListener(gestorTextWatcher);
        txtBegin.addTextChangedListener(gestorTextWatcher);
        txtEnd.addTextChangedListener(gestorTextWatcher);
        txtObservations.addTextChangedListener(gestorTextWatcher);
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
            if (getActivity().getCurrentFocus().getId() == txtDate.getId()) {
                checkStringImg(lblDate, txtDate, imgDate);
                //Comprobar FECHAAAAAA checkDate
            } else if (getActivity().getCurrentFocus().getId() == txtBegin.getId()) {
                checkStringImg(lblTime, txtBegin, imgBegin);
                //Comprobar HORAAAA checkHour
            } else if (getActivity().getCurrentFocus().getId() == txtEnd.getId()) {
                checkStringImg(lblTime, txtEnd, imgEnd);
            } else if (getActivity().getCurrentFocus().getId() == txtObservations.getId()) {
                checkString(lblObservations, txtObservations);
            } /*else if (getActivity().getCurrentFocus().getId() == spStudent.getId()) {
                checkSpinner(lblTutorName, txtTutorName);
            }*/
        }
    }

    private void setFocusListeners() {
        spStudent.setOnFocusChangeListener((v, hasFocus) -> setBold(lblStudent, null));
//        txtDate.setOnFocusChangeListener((v, hasFocus) -> {
//            setBold(lblDate, txtDate);
//        });
        txtDate.setOnFocusChangeListener((v, hasFocus) -> {
            setBold(lblDate, txtDate);
//            showDateDialogPicker(txtDate);
        });
        txtBegin.setOnFocusChangeListener((v, hasFocus) -> {
            setBold(lblTime, txtBegin);
//            showTimeDialogPicker(txtBegin, "inicio");
        });
        txtEnd.setOnFocusChangeListener((v, hasFocus) -> {
            setBold(lblTime, txtEnd);
//            showTimeDialogPicker(txtEnd, "fin");
        });
        txtObservations.setOnFocusChangeListener((v, hasFocus) -> {
            setBold(lblObservations, txtObservations);
        });
    }

    private void setBold(TextView textView, EditText editText) {
        if(editText.hasFocus() || editText == null) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    private void setListeners() {
        txtDate.setOnClickListener(v -> showDateDialogPicker(txtDate));
        imgDate.setOnClickListener(v -> showDateDialogPicker(txtDate));
        txtBegin.setOnClickListener(v -> showTimeDialogPicker(txtBegin));
        imgBegin.setOnClickListener(v -> showTimeDialogPicker(txtBegin));
        txtEnd.setOnClickListener(v -> showTimeDialogPicker(txtEnd));
        imgEnd.setOnClickListener(v -> showTimeDialogPicker(txtEnd));
        //Faltan dialogs time

        saveFab.setOnClickListener(v -> save());
    }

    private void showDateDialogPicker(EditText editText) {
        Calendar mcurrentDate = Calendar.getInstance();
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int month = mcurrentDate.get(Calendar.MONTH);
        int year = mcurrentDate.get(Calendar.YEAR);
        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(getContext(), (view, selectedYear, selectedMonth, selectedDay)
                -> {
//            editText.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
            editText.setText(String.format ("%02d/%02d/%d", selectedDay, selectedMonth, selectedYear));
        }, year, month, day);

//        mDatePicker.setTitle("Seleccionar fecha");
        mDatePicker.show();/*
        mDatePicker.setOnDismissListener(dialog -> {
            KeyboardUtils.hideSoftKeyboard(requireActivity());
        });*/
    }

    private void showTimeDialogPicker(EditText editText) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editText.setText(String.format ("%02d:%02d", selectedHour, selectedMinute));
            }
        }, hour, minute, true);
        mTimePicker.show();
        /*mTimePicker.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(editText.getId() == txtEnd.getId() || editText.getId() == imgEnd.getId()) {
                    if(TextUtils.isEmpty(txtBegin.getText().toString())) {
                        enabledDisabledFieldImg(lblTime, txtBegin, imgBegin, false, "Introducir hora inicio");
                    }
                }
                KeyboardUtils.hideSoftKeyboard(requireActivity());
            }
        });*/
    }

    private void save() {
        if (!validateAll()) {
            Snackbar.make(requireView(), R.string.main_error_saving, Snackbar.LENGTH_LONG).show();
        } else {
//            if(getArguments() != null) {
//                database.editUser(toEditUser);
//            } else {
//                database.addUser(toEditUser);
//            }
            Snackbar.make(requireView(), R.string.main_saving, Snackbar.LENGTH_LONG).show();
            navController.navigate(R.id.nextVisitsListFragment);
        }
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void checkStringImg(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidString(editText.getText().toString()), null);
    }

    private void checkString(TextView textView, EditText editText) {
        enabledDisabledField(textView, editText, ValidationUtils.isValidString(editText.getText().toString()));
    }

    private void checkSpinner(TextView textView, Spinner spinner) {
        //TODO comprobar
        enabledDisabledSpinner(textView, (spinner != null && spinner.getSelectedItem() !=null));
    }

    private void checkAll() {
        checkSpinner(lblStudent, spStudent);
        checkStringImg(lblDate, txtDate, imgDate); //checkDate
        checkStringImg(lblTime, txtBegin, imgBegin); //checkHour
        checkStringImg(lblTime, txtEnd, imgEnd);
        checkString(lblObservations, txtObservations);
    }

    private void enabledDisabledFieldImg(TextView textView, EditText editText, ImageView imageView, boolean valid, String error) {
        if(valid) {
            editText.setError(null);
        } else {
/*            if(error != null) {
                editText.setError(error);
            } else {*/
            editText.setError(getString(R.string.main_invalid_data));
//            }
/*            if(editText.getId() == txtEnd.getId()) {
                if(!TextUtils.isEmpty(txtBegin.getText().toString()) && !TextUtils.isEmpty(txtEnd.getText().toString())) {
                    Date begin = new Date(2019, 01, 01, Integer.parseInt(txtBegin.getText().toString().substring(0, 2)), Integer.parseInt(editText.getText().toString().substring(-2)));
                    Date end = new Date(2019, 01, 01, Integer.parseInt(txtEnd.getText().toString().substring(0, 2)), Integer.parseInt(editText.getText().toString().substring(-2)));
                }
            }*/
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

    private void enabledDisabledSpinner(TextView textView, boolean valid) {
        textView.setEnabled(valid);
        selectStateView(textView, valid);
    }

    private void selectStateView(View view, boolean state) {
        if(view == spStudent) {
            viewModel.setStateLblStudent(state);
        } else if(view == txtDate) {
            viewModel.setStateLblDate(state);
            viewModel.setStateImgDate(state);
        } else if(view == txtBegin) {
            viewModel.setStateLblTime(state);
            viewModel.setStateImgBegin(state);
        }  else if(view == txtEnd) {
            viewModel.setStateLblTime(state);
            viewModel.setStateImgEnd(state);
        } else if(view == txtObservations) {
            viewModel.setStateLblObservations(state);
        }
    }

    private boolean validateAll() {
        checkAll();
        View[] array = new View[]{lblDate, lblTime, lblObservations};
        for (View view: array) {
            if(!view.isEnabled()) {
                return false;
            }
        }
        return true;
    }

}
