package com.tinderclone.core.data.repository;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tinderclone.core.domain.helpers.Resource;
import com.tinderclone.core.domain.model.User;
import com.tinderclone.core.domain.repository.ProfileRepository;
import java.util.UUID;

public class ProfileRepositoryImpl implements ProfileRepository {

    private final FirebaseFirestore db;
    private final FirebaseStorage storage;
    private static final String USERS_COLLECTION = "users";
    private static final String PROFILE_PHOTOS_PATH = "profile_photos";

    public ProfileRepositoryImpl() {
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @Override
    public LiveData<Resource<Void>> createUserProfile(User user) {
        MutableLiveData<Resource<Void>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));
        db.collection(USERS_COLLECTION).document(user.getUid()).set(user)
            .addOnSuccessListener(aVoid -> result.setValue(Resource.success(null)))
            .addOnFailureListener(e -> result.setValue(Resource.error(e.getMessage(), null)));
        return result;
    }

    @Override
    public LiveData<Resource<User>> getUserProfile(String uid) {
        MutableLiveData<Resource<User>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));
        db.collection(USERS_COLLECTION).document(uid).get()
            .addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    result.setValue(Resource.success(user));
                } else {
                    result.setValue(Resource.error("User profile not found", null));
                }
            })
            .addOnFailureListener(e -> result.setValue(Resource.error(e.getMessage(), null)));
        return result;
    }

    @Override
    public LiveData<Resource<Void>> updateUserProfile(User user) {
        MutableLiveData<Resource<Void>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));
        db.collection(USERS_COLLECTION).document(user.getUid()).set(user) // .set with merge option could be better
            .addOnSuccessListener(aVoid -> result.setValue(Resource.success(null)))
            .addOnFailureListener(e -> result.setValue(Resource.error(e.getMessage(), null)));
        return result;
    }

    @Override
    public LiveData<Resource<String>> uploadProfilePhoto(Uri imageUri, String uid) {
        MutableLiveData<Resource<String>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null));

        StorageReference storageRef = storage.getReference();
        StorageReference photoRef = storageRef.child(PROFILE_PHOTOS_PATH + "/" + uid + "/" + UUID.randomUUID().toString());

        photoRef.putFile(imageUri)
            .continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return photoRef.getDownloadUrl();
            })
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    result.setValue(Resource.success(downloadUri.toString()));
                } else {
                    result.setValue(Resource.error(task.getException().getMessage(), null));
                }
            });

        return result;
    }
}
