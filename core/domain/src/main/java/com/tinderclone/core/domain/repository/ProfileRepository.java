package com.tinderclone.core.domain.repository;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import com.tinderclone.core.domain.helpers.Resource;
import com.tinderclone.core.domain.model.User;

public interface ProfileRepository {

    /**
     * Creates a new user profile document in Firestore after registration.
     * @param user The user object to save.
     * @return LiveData representing the result of the operation.
     */
    LiveData<Resource<Void>> createUserProfile(User user);

    /**
     * Fetches a user profile from Firestore.
     * @param uid The user ID to fetch.
     * @return LiveData containing the user profile.
     */
    LiveData<Resource<User>> getUserProfile(String uid);

    /**
     * Updates an existing user profile in Firestore.
     * @param user The user object with updated fields.
     * @return LiveData representing the result of the operation.
     */
    LiveData<Resource<Void>> updateUserProfile(User user);

    /**
     * Uploads a profile photo to Firebase Storage.
     * @param imageUri The local URI of the image file.
     * @param uid The user ID to associate the photo with.
     * @return LiveData containing the download URL of the uploaded image.
     */
    LiveData<Resource<String>> uploadProfilePhoto(Uri imageUri, String uid);
}
