package com.example.lot_pr10_fct.ui.student.newStudent;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lot_pr10_fct.R;
import com.example.lot_pr10_fct.data.RepositoryImpl;
import com.example.lot_pr10_fct.data.local.AppDatabase;
import com.example.lot_pr10_fct.data.local.model.Company;
import com.example.lot_pr10_fct.data.local.model.Student;
import com.example.lot_pr10_fct.utils.KeyboardUtils;
import com.example.lot_pr10_fct.utils.ValidationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class NewStudentFragment extends Fragment {

    private NewStudentFragmentViewModel viewModel;

    private EditText txtName;
    private EditText txtGrade;
    private EditText txtStudentPhone;
    private EditText txtEmail;
    private Spinner spCompany;
    private EditText txtTutorName;
    private EditText txtTutorPhone;
    private EditText txtBegin;
    private EditText txtEnd;
    private TextView lblName;
    private TextView lblGrade;
    private TextView lblStudentPhone;
    private TextView lblEmail;
//    private TextView lblCompany;
    private TextView lblTutorName;
    private TextView lblTutorPhone;
    private TextView lblTime;
    private ImageView imgStudentPhone;
    private ImageView imgEmail;
    private ImageView imgTutorPhone;
    private ImageView imgBegin;
    private ImageView imgEnd;

    private FloatingActionButton saveFab;

    NavController navController;

    private long studentId;
    private boolean editMode;

    public static NewStudentFragment newInstance() {
        return new NewStudentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getArguments());
        studentId = getArguments().getLong("STUDENT_ID");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_student, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new NewStudentFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).visitDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).studentDao()
                , AppDatabase.getInstance(requireContext().getApplicationContext()).companyDao())))
                .get(NewStudentFragmentViewModel.class);
        setupViews();
//        validateFields();

        if(studentId != 0) {
            editMode = true;
            viewModel.getStudent(studentId).observe(getViewLifecycleOwner(), student -> {
                showStudent(student);
            });
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void showStudent(Student student) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        int positionSpinnerCompany = spCompany.getSelectedItemPosition();
        viewModel.setStudentCompare(student);
        txtName.setText(student.getName());
        txtStudentPhone.setText(student.getPhone());
        txtGrade.setText(student.getGrade());
        txtEmail.setText(student.getEmail());
        spCompany.setSelection(positionSpinnerCompany);
        txtTutorName.setText(student.getTutorName());
        txtTutorPhone.setText(student.getTutorPhone());
        txtBegin.setText(formatter.format(student.getBegin().getTime()));
        txtEnd.setText(formatter.format(student.getEnd()));
    }

    private void setupViews() {
        txtName = ViewCompat.requireViewById(requireView(), R.id.ne_txtName);
        txtGrade = ViewCompat.requireViewById(requireView(), R.id.ne_txtCurso);
        txtStudentPhone = ViewCompat.requireViewById(requireView(), R.id.ne_txtStudentPhone);
        txtEmail = ViewCompat.requireViewById(requireView(), R.id.ne_txtEmail);
        spCompany = ViewCompat.requireViewById(requireView(), R.id.ne_spCompany);
        txtTutorName = ViewCompat.requireViewById(requireView(), R.id.ne_txtTutorName);
        txtTutorPhone = ViewCompat.requireViewById(requireView(), R.id.ne_txtTutorTlfo);
        txtBegin = ViewCompat.requireViewById(requireView(), R.id.ne_txtBegin);
        txtEnd = ViewCompat.requireViewById(requireView(), R.id.ne_txtEnd);

        lblName = ViewCompat.requireViewById(requireView(), R.id.ne_lblName);
        lblGrade = ViewCompat.requireViewById(requireView(), R.id.ne_lblCurso);
        lblStudentPhone = ViewCompat.requireViewById(requireView(), R.id.ne_lblStudentPhone);
        lblEmail = ViewCompat.requireViewById(requireView(), R.id.ne_lblEmail);
//        lblCompany = ViewCompat.requireViewById(requireView(), R.id.ne_lblCompany);
        lblTutorName = ViewCompat.requireViewById(requireView(), R.id.ne_lblTutorName);
        lblTutorPhone = ViewCompat.requireViewById(requireView(), R.id.ne_lblTutorTlfo);
        lblTime = ViewCompat.requireViewById(requireView(), R.id.ne_lblTime);

        imgStudentPhone = ViewCompat.requireViewById(requireView(), R.id.ne_imgStudentPhone);
        imgEmail = ViewCompat.requireViewById(requireView(), R.id.ne_imgEmail);
        imgTutorPhone = ViewCompat.requireViewById(requireView(), R.id.ne_imgTutorTlfo);
        imgBegin = ViewCompat.requireViewById(requireView(), R.id.ne_imgBegin);
        imgEnd = ViewCompat.requireViewById(requireView(), R.id.ne_imgEnd);

        saveFab =  ViewCompat.requireViewById(requireView(), R.id.ne_fab);

        navController = NavHostFragment.findNavController(this);

        setupSpinner();
        setFocusListeners();

        //TextWatcher, check fields
        GestorTextWatcher gestorTextWatcher = new GestorTextWatcher();
        setTextWatcher(gestorTextWatcher);

        setListeners();
    }

    private void setupSpinner() {
        viewModel.getCompanies().observe(getViewLifecycleOwner(), companies -> {
            ArrayList<String> listArraySpinner = new ArrayList<>();
            for (Company company: companies) {
                listArraySpinner.add(company.getName());
            }
            String[] arraySpinner = new String[listArraySpinner.size()];
            listArraySpinner.toArray(arraySpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(),
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCompany.setAdapter(adapter);
        });
    }

    private void validateFields() {
        lblName.setEnabled(viewModel.isStateName());
        lblGrade.setEnabled(viewModel.isStateGrade());
        lblStudentPhone.setEnabled(viewModel.isStateStudentPhone());
        lblEmail.setEnabled(viewModel.isStateEmail());
        lblTutorName.setEnabled(viewModel.isStateTutor());
        lblTutorPhone.setEnabled(viewModel.isStateTutorPhone());
        lblTime.setEnabled(viewModel.isStateTime());

        imgStudentPhone.setEnabled(viewModel.isStateStudentPhoneImg());
        imgEmail.setEnabled(viewModel.isStateEmailImg());
        imgTutorPhone.setEnabled(viewModel.isStateTutorPhoneImg());
        imgBegin.setEnabled(viewModel.isStateBeginImg());
        imgEnd.setEnabled(viewModel.isStateEndImg());
    }

    private void setTextWatcher(GestorTextWatcher gestorTextWatcher) {
        txtName.addTextChangedListener(gestorTextWatcher);
        txtGrade.addTextChangedListener(gestorTextWatcher);
        txtStudentPhone.addTextChangedListener(gestorTextWatcher);
        txtEmail.addTextChangedListener(gestorTextWatcher);
        txtTutorName.addTextChangedListener(gestorTextWatcher);
        txtTutorPhone.addTextChangedListener(gestorTextWatcher);
        txtBegin.addTextChangedListener(gestorTextWatcher);
        txtEnd.addTextChangedListener(gestorTextWatcher);
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
            } else if (getActivity().getCurrentFocus().getId() == txtGrade.getId()) {
                checkString(lblGrade, txtGrade);
            } else if (getActivity().getCurrentFocus().getId() == txtStudentPhone.getId()) {
                checkPhone(lblStudentPhone, txtStudentPhone, imgStudentPhone);
            } else if (getActivity().getCurrentFocus().getId() == txtEmail.getId()) {
                checkEmail(lblEmail, txtEmail, imgEmail);
            } else if (getActivity().getCurrentFocus().getId() == txtTutorName.getId()) {
                checkString(lblTutorName, txtTutorName);
            } else if (getActivity().getCurrentFocus().getId() == txtTutorPhone.getId()) {
                checkPhone(lblTutorPhone, txtTutorPhone, imgTutorPhone);
            } else if (getActivity().getCurrentFocus().getId() == txtBegin.getId()) {
                checkStringImg(lblTime, txtBegin, imgBegin);
            } else if (getActivity().getCurrentFocus().getId() == txtEnd.getId()) {
                checkStringImg(lblTime, txtEnd, imgEnd);
            }
        }
    }

    private void setFocusListeners() {
        txtName.setOnFocusChangeListener((v, hasFocus) -> setBold(lblName, txtName));
        txtGrade.setOnFocusChangeListener((v, hasFocus) -> setBold(lblGrade, txtGrade));
        txtStudentPhone.setOnFocusChangeListener((v, hasFocus) -> setBold(lblStudentPhone, txtStudentPhone));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setBold(lblEmail, txtEmail));
        txtTutorName.setOnFocusChangeListener((v, hasFocus) -> setBold(lblTutorName, txtTutorName));
        txtTutorPhone.setOnFocusChangeListener((v, hasFocus) -> setBold(lblTutorPhone, txtTutorPhone));
        txtBegin.setOnFocusChangeListener((v, hasFocus) -> setBold(lblTime, txtBegin));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setBold(lblTime, txtEnd));
    }

    private void setBold(TextView textView, EditText editText) {
        if(editText.hasFocus()) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }

    private void setListeners() {
        imgStudentPhone.setOnClickListener(v -> callPhone(txtStudentPhone));
        imgEmail.setOnClickListener(v -> sendEmail());
        imgTutorPhone.setOnClickListener(v -> callPhone(txtTutorPhone));
        imgBegin.setOnClickListener(v -> showTimeDialogPicker(txtBegin));
        imgEnd.setOnClickListener(v -> showTimeDialogPicker(txtEnd));

        saveFab.setOnClickListener(v -> save());
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
    }

    private void save() {
        if (!validateAll()) {
            Snackbar.make(requireView(), R.string.main_error_saving_student, Snackbar.LENGTH_LONG).show();
        } else {
            Date now = new Date();
            Date dateBegin = new Date(now.getYear(), now.getMonth(), now.getDay(), Integer.parseInt(txtBegin.getText().toString().substring(0,2)), Integer.parseInt(txtBegin.getText().toString().substring(3)));
            Date dateEnd = new Date(now.getYear(), now.getMonth(), now.getDay(), Integer.parseInt(txtEnd.getText().toString().substring(0,2)), Integer.parseInt(txtEnd.getText().toString().substring(3)));

            Student student = new Student(txtName.getText().toString(),
                txtStudentPhone.getText().toString(),
                txtEmail.getText().toString(),
                txtGrade.getText().toString(),
                spCompany.getSelectedItem().toString(),
                txtTutorName.getText().toString(),
                txtTutorPhone.getText().toString(),
                    dateBegin,
                    dateEnd);
            if(editMode) {
                student.setId(studentId);
                if(student.equals(viewModel.getStudentCompare())){
                    Snackbar.make(requireView(), "No ha habido cambios", Snackbar.LENGTH_LONG).show();
                } else {
                    viewModel.update(student);
                    Snackbar.make(requireView(), "¡Estudiante modificado con éxito!", Snackbar.LENGTH_LONG).show();
                }
            } else {
                viewModel.insert(student);
                Snackbar.make(requireView(), "¡Estudiante guardado con éxito!", Snackbar.LENGTH_LONG).show();
            }

            getFragmentManager().popBackStack();
        }
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void checkStringImg(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidString(editText.getText().toString()));
    }

    private void checkString(TextView textView, EditText editText) {
        enabledDisabledField(textView, editText, ValidationUtils.isValidString(editText.getText().toString()));
    }

    private void checkPhone(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidPhone(editText.getText().toString()));
    }

    private void checkEmail(TextView textView, EditText editText, ImageView imageView) {
        enabledDisabledFieldImg(textView, editText, imageView, ValidationUtils.isValidEmail(editText.getText().toString()));
    }

    private void checkAll() {
        checkString(lblName, txtName);
        checkString(lblGrade, txtGrade);
        checkPhone(lblStudentPhone, txtStudentPhone, imgStudentPhone);
        checkEmail(lblEmail, txtEmail, imgEmail);
        checkString(lblTutorName, txtTutorName);
        checkPhone(lblTutorPhone, txtTutorPhone, imgTutorPhone);
        checkStringImg(lblTime, txtBegin, imgBegin);
        checkStringImg(lblTime, txtEnd, imgEnd);
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
        } else if(view == txtGrade) {
            viewModel.setStateGrade(state);
        } else if(view == txtStudentPhone) {
            viewModel.setStateStudentPhone(state);
            viewModel.setStateStudentPhoneImg(state);
        }  else if(view == txtEmail) {
            viewModel.setStateEmail(state);
            viewModel.setStateEmailImg(state);
        } else if(view == txtTutorName) {
            viewModel.setStateTutor(state);
        }  else if(view == txtTutorPhone) {
            viewModel.setStateTutorPhone(state);
            viewModel.setStateTutorPhoneImg(state);
        }  else if(view == txtBegin) {
            viewModel.setStateTime(state);
            viewModel.setStateBeginImg(state);
        }  else if(view == txtEnd) {
            viewModel.setStateTime(state);
            viewModel.setStateEndImg(state);
        }
    }

    private boolean validateAll() {
        checkAll();
        View[] array = new View[]{lblName, lblGrade, lblStudentPhone, lblEmail, lblTutorName, lblTutorPhone, lblTime};
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

    private void callPhone(EditText editText) {
        String phone = editText.getText().toString();
        Intent callPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", phone)));
        startActivity(callPhoneIntent);
    }

    @Override
    public void onDetach() {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
        super.onDetach();
    }
}
