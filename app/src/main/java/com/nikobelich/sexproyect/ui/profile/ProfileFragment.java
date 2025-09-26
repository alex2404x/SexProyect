package com.nikobelich.sexproyect.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.DatingRepository;
import com.nikobelich.sexproyect.data.model.UserProfile;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatingRepository repository = DatingRepository.getInstance(requireContext());
        UserProfile currentUser = repository.getCurrentUser();

        ImageView avatar = view.findViewById(R.id.profileAvatar);
        TextView name = view.findViewById(R.id.profileHeaderName);
        TextView bio = view.findViewById(R.id.profileHeaderBio);
        TextView location = view.findViewById(R.id.profileHeaderLocation);
        ChipGroup interestsGroup = view.findViewById(R.id.profileInterestsGroup);
        Button editButton = view.findViewById(R.id.profileEditButton);
        Button visibilityButton = view.findViewById(R.id.profileVisibilityButton);

        avatar.setImageResource(currentUser.getPhotoRes());
        name.setText(getString(R.string.profile_name_age, currentUser.getName(), currentUser.getAge()));
        bio.setText(currentUser.getBio());
        location.setText(currentUser.getLocation());

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        interestsGroup.removeAllViews();
        for (String interest : currentUser.getInterests()) {
            Chip chip = (Chip) inflater.inflate(R.layout.item_interest_chip, interestsGroup, false);
            chip.setText(interest);
            interestsGroup.addView(chip);
        }

        editButton.setOnClickListener(v -> Toast.makeText(requireContext(), R.string.profile_edit_toast, Toast.LENGTH_SHORT).show());
        visibilityButton.setOnClickListener(v -> Toast.makeText(requireContext(), R.string.profile_visibility_toast, Toast.LENGTH_SHORT).show());
    }
}
