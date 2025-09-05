package com.tinderclone.core.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tinderclone.core.domain.helpers.Resource;
import com.tinderclone.core.domain.repository.AuthRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthRepositoryImpl implements AuthRepository {

    private final FirebaseAuth firebaseAuth;

    @Inject
    public AuthRepositoryImpl() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public LiveData<Resource<FirebaseUser>> loginWithEmail(String email, String password) {
        MutableLiveData<Resource<FirebaseUser>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        liveData.setValue(Resource.success(task.getResult().getUser()));
                    } else {
                        liveData.setValue(Resource.error(task.getException().getMessage(), null));
                    }
                });
        return liveData;
    }

    @Override
    public LiveData<Resource<FirebaseUser>> registerWithEmail(String email, String password) {
        MutableLiveData<Resource<FirebaseUser>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        liveData.setValue(Resource.success(task.getResult().getUser()));
                    } else {
                        live_data.setValue(Resource.error(task.getException().getMessage(), null));
                    }
                });
        return liveData;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void logout() {
        firebaseAuth.signOut();
    }
}
