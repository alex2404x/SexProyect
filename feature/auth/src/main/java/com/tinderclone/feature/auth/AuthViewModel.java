package com.tinderclone.feature.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.tinderclone.core.data.repository.AuthRepositoryImpl;
import com.tinderclone.core.domain.helpers.Resource;
import com.tinderclone.core.domain.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;

    // LiveData for observing authentication results
    private LiveData<Resource<FirebaseUser>> userLiveData;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        // This should be injected by a DI framework in a real-world app
        this.authRepository = new AuthRepositoryImpl();
    }

    public void login(String email, String password) {
        userLiveData = authRepository.loginWithEmail(email, password);
    }

    public void register(String email, String password) {
        userLiveData = authRepository.registerWithEmail(email, password);
    }

    public LiveData<Resource<FirebaseUser>> getUserLiveData() {
        return userLiveData;
    }

    public boolean isUserLoggedIn() {
        return authRepository.getCurrentUser() != null;
    }
}
