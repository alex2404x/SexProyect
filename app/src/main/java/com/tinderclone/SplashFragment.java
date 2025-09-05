package com.tinderclone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.tinderclone.app.R;
import com.tinderclone.feature.auth.AuthViewModel;

public class SplashFragment extends Fragment {

    private AuthViewModel authViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // This fragment can have a simple layout if a splash logo is desired,
        // but for pure logic, returning a new View is sufficient.
        return new View(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // A short delay to simulate a splash screen effect
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (authViewModel.isUserLoggedIn()) {
                // User is logged in, navigate to the main part of the app
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_splashFragment_to_navigation_home);
            } else {
                // User is not logged in, navigate to the authentication flow
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_splashFragment_to_auth_navigation);
            }
        }, 1000); // 1-second delay
    }
}
