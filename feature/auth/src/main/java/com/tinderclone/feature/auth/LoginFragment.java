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

import com.tinderclone.feature.auth.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
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
        binding.buttonLogin.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                authViewModel.login(email, password);
            }
        });

        binding.textViewGoToRegister.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment);
        });
    }

    private void observeViewModel() {
        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), resource -> {
            if (resource == null) return;
            switch (resource.status) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.buttonLogin.setEnabled(false);
                    break;
                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.buttonLogin.setEnabled(true);
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    // Navigate to main app flow
                    NavHostFragment.findNavController(this).navigate(com.tinderclone.app.R.id.action_global_to_main_flow);
                    break;
                case ERROR:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.buttonLogin.setEnabled(true);
                    Toast.makeText(getContext(), "Login Failed: " + resource.message, Toast.LENGTH_LONG).show();
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
