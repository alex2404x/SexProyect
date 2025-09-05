package com.tinderclone.feature.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.tinderclone.feature.auth.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupClickListeners();
        observeViewModel();
    }

    private void setupClickListeners() {
        binding.buttonRegister.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();
            String confirmPassword = binding.editTextConfirmPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                authViewModel.register(email, password);
            }
        });

        binding.textViewGoToLogin.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment);
        });
    }

    private void observeViewModel() {
        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), resource -> {
            if (resource == null) return;
            switch (resource.status) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.buttonRegister.setEnabled(false);
                    break;
                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.buttonRegister.setEnabled(true);
                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    // Navigate to main app flow or onboarding
                    NavHostFragment.findNavController(this).navigate(com.tinderclone.app.R.id.action_global_to_onboarding_flow);
                    break;
                case ERROR:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.buttonRegister.setEnabled(true);
                    Toast.makeText(getContext(), "Registration Failed: " + resource.message, Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
