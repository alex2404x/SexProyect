package com.tinderclone.feature.profile;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.tinderclone.core.data.repository.ProfileRepositoryImpl;
import com.tinderclone.core.domain.model.PublicProfile;
import com.tinderclone.core.domain.model.User;
import com.tinderclone.core.domain.repository.ProfileRepository;
import com.tinderclone.core.domain.helpers.Resource;

import java.util.ArrayList;
import java.util.Date;

public class OnboardingViewModel extends AndroidViewModel {

    private final ProfileRepository profileRepository;
    private final MutableLiveData<User> userDraftLiveData = new MutableLiveData<>();

    public OnboardingViewModel(@NonNull Application application) {
        super(application);
        this.profileRepository = new ProfileRepositoryImpl();
        initUserDraft();
    }

    private void initUserDraft() {
        User user = new User();
        // Set UID from current Firebase user
        String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : null;
        user.setUid(uid);

        // Initialize nested objects to avoid NullPointerExceptions
        user.setPublicProfile(new PublicProfile());
        user.getPublicProfile().setPhotos(new ArrayList<>());
        user.getPublicProfile().setInterests(new ArrayList<>());

        // Initialize other fields if necessary

        userDraftLiveData.setValue(user);
    }

    public LiveData<User> getUserDraft() {
        return userDraftLiveData;
    }

    public void updateUserDraft(User user) {
        userDraftLiveData.setValue(user);
    }

    public LiveData<Resource<Void>> saveUserProfile() {
        User finalUser = userDraftLiveData.getValue();
        if (finalUser != null) {
            // Set any final metadata before saving
            if (finalUser.getMeta() == null) {
                finalUser.setMeta(new com.tinderclone.core.domain.model.Meta());
            }
            finalUser.getMeta().setCreatedAt(new Date());
            finalUser.getMeta().setUpdatedAt(new Date());
        }
        return profileRepository.createUserProfile(finalUser);
    }
}
