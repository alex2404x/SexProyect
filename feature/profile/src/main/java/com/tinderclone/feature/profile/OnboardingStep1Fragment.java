package com.tinderclone.feature.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.tinderclone.core.domain.model.User;
import com.tinderclone.feature.profile.databinding.FragmentOnboardingStep1Binding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OnboardingStep1Fragment extends Fragment {

    private FragmentOnboardingStep1Binding binding;
    private OnboardingViewModel viewModel;
    private final Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOnboardingStep1Binding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(OnboardingViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDatePicker();
        setupNextButton();
    }

    private void setupDatePicker() {
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        binding.editTextBirthday.setOnClickListener(v -> new DatePickerDialog(getContext(), date,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        binding.editTextBirthday.setText(sdf.format(myCalendar.getTime()));
    }

    private void setupNextButton() {
        binding.buttonNext.setOnClickListener(v -> {
            if (validateInputs()) {
                updateViewModel();
                // Navigation to next step will be added here
                // NavHostFragment.findNavController(this).navigate(R.id.action_onboardingStep1_to_step2);
                 Toast.makeText(getContext(), "Next step logic pending", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs() {
        if (binding.editTextDisplayName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.editTextBirthday.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Please select your birthday", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Add age validation (e.g., must be 18+)
        return true;
    }

    private void updateViewModel() {
        User currentUserDraft = viewModel.getUserDraft().getValue();
        if (currentUserDraft != null) {
            currentUserDraft.getPublicProfile().setDisplayName(binding.editTextDisplayName.getText().toString().trim());

            int selectedId = binding.radioGroupGender.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = binding.getRoot().findViewById(selectedId);
            currentUserDraft.getPublicProfile().setGender(selectedRadioButton.getText().toString());

            // Calculate age from birthday
            // ... (logic to calculate age)
            int age = 25; // Placeholder
            currentUserDraft.getPublicProfile().setAge(age);

            viewModel.updateUserDraft(currentUserDraft);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
