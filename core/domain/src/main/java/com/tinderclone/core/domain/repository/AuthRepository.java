package com.tinderclone.core.domain.repository;

import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.tinderclone.core.domain.helpers.Resource;

public interface AuthRepository {
    LiveData<Resource<FirebaseUser>> loginWithEmail(String email, String password);
    LiveData<Resource<FirebaseUser>> registerWithEmail(String email, String password);
    FirebaseUser getCurrentUser();
    void logout();
}
