package com.nikobelich.sexproyect.ui.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.model.UserProfile;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ProfileViewHolder> {

    public interface ProfileActionListener {
        void onProfileLiked(@NonNull UserProfile profile);
        void onProfileSkipped(@NonNull UserProfile profile);
    }

    private final List<UserProfile> profiles;
    private final ProfileActionListener listener;

    public DiscoverAdapter(List<UserProfile> profiles, ProfileActionListener listener) {
        this.profiles = profiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_card, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.bind(profiles.get(position));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        private final ImageView photoView;
        private final TextView nameView;
        private final TextView bioView;
        private final TextView locationView;
        private final ChipGroup chipGroup;
        private final Button likeButton;
        private final Button skipButton;

        ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.profilePhoto);
            nameView = itemView.findViewById(R.id.profileName);
            bioView = itemView.findViewById(R.id.profileBio);
            locationView = itemView.findViewById(R.id.profileLocation);
            chipGroup = itemView.findViewById(R.id.interestsChipGroup);
            likeButton = itemView.findViewById(R.id.likeButton);
            skipButton = itemView.findViewById(R.id.skipButton);
        }

        void bind(UserProfile profile) {
            photoView.setImageResource(profile.getPhotoRes());
            nameView.setText(itemView.getContext().getString(R.string.profile_name_age, profile.getName(), profile.getAge()));
            bioView.setText(profile.getBio());
            locationView.setText(profile.getLocation());
            populateChips(profile);

            likeButton.setOnClickListener(v -> listener.onProfileLiked(profile));
            skipButton.setOnClickListener(v -> listener.onProfileSkipped(profile));
        }

        private void populateChips(UserProfile profile) {
            chipGroup.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
            for (String interest : profile.getInterests()) {
                Chip chip = (Chip) inflater.inflate(R.layout.item_interest_chip, chipGroup, false);
                chip.setText(interest);
                chipGroup.addView(chip);
            }
        }
    }
}
