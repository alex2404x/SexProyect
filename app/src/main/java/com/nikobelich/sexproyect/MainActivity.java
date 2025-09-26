package com.nikobelich.sexproyect;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView profileImage;
    private TextView profileName;
    private TextView profileDistance;
    private TextView profileBio;
    private ChipGroup interestsGroup;
    private TextView statusText;

    private Profile[] profiles;
    private int currentProfileIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profileDistance = findViewById(R.id.profile_distance);
        profileBio = findViewById(R.id.profile_bio);
        interestsGroup = findViewById(R.id.profile_interests);
        statusText = findViewById(R.id.status_text);
        MaterialButton skipButton = findViewById(R.id.skip_button);
        MaterialButton likeButton = findViewById(R.id.like_button);
        MaterialButton superLikeButton = findViewById(R.id.super_like_button);

        profiles = new Profile[]{
                new Profile(
                        R.drawable.profile_gradient_luna,
                        "Luna",
                        27,
                        3,
                        "Productora de fiestas en azoteas. Busco cómplices para bailes eternos y amaneceres con mimosas.",
                        new String[]{"After parties", "Viajes relámpago", "House"}
                ),
                new Profile(
                        R.drawable.profile_gradient_valentina,
                        "Valentina",
                        30,
                        1,
                        "Diseñadora UX con debilidad por los cócteles experimentales. Sorpréndeme con tu bar secreto favorito.",
                        new String[]{"Cócteles", "Diseño", "Rooftops"}
                ),
                new Profile(
                        R.drawable.profile_gradient_dante,
                        "Dante",
                        29,
                        4,
                        "Fotógrafo nocturno. Si eres fan de las luces de neón y los planes improvisados, hagámoslo memorable.",
                        new String[]{"Neón", "Fotografía", "After hours"}
                )
        };

        showProfile(profiles[currentProfileIndex]);
        statusText.setText(getString(R.string.status_default));

        skipButton.setOnClickListener(v -> handleAction(ActionType.SKIP));
        likeButton.setOnClickListener(v -> handleAction(ActionType.LIKE));
        superLikeButton.setOnClickListener(v -> handleAction(ActionType.SUPER_LIKE));
    }

    private void handleAction(ActionType actionType) {
        Profile profile = profiles[currentProfileIndex];
        switch (actionType) {
            case SKIP:
                statusText.setText(R.string.status_skip);
                Toast.makeText(this, R.string.status_skip, Toast.LENGTH_SHORT).show();
                break;
            case LIKE:
                String likeMessage = getString(R.string.status_like, profile.name);
                statusText.setText(likeMessage);
                Toast.makeText(this, likeMessage, Toast.LENGTH_SHORT).show();
                break;
            case SUPER_LIKE:
                String superLikeMessage = getString(R.string.status_super_like, profile.name);
                statusText.setText(superLikeMessage);
                Toast.makeText(this, superLikeMessage, Toast.LENGTH_SHORT).show();
                break;
        }

        currentProfileIndex = (currentProfileIndex + 1) % profiles.length;
        showProfile(profiles[currentProfileIndex]);
    }

    private void showProfile(Profile profile) {
        profileImage.setImageResource(profile.imageRes);
        profileName.setText(getString(R.string.profile_name_age, profile.name, profile.age));
        profileDistance.setText(getString(R.string.profile_distance, profile.distanceKm));
        profileBio.setText(profile.bio);
        populateInterests(profile.interests);
    }

    private void populateInterests(String[] interests) {
        interestsGroup.removeAllViews();
        if (interests == null || interests.length == 0) {
            interestsGroup.setVisibility(View.GONE);
            return;
        }

        interestsGroup.setVisibility(View.VISIBLE);
        int backgroundColor = ContextCompat.getColor(this, R.color.chip_background);
        int textColor = ContextCompat.getColor(this, R.color.chip_text);
        int rippleColor = ContextCompat.getColor(this, R.color.chip_ripple);

        float density = getResources().getDisplayMetrics().density;
        int horizontalPadding = Math.round(12 * density);
        int verticalPadding = Math.round(4 * density);

        for (String interest : interests) {
            Chip chip = new Chip(this);
            chip.setText(interest);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setEnsureMinTouchTargetSize(false);
            chip.setEllipsize(TextUtils.TruncateAt.END);
            chip.setMaxLines(1);
            chip.setChipBackgroundColor(ColorStateList.valueOf(backgroundColor));
            chip.setTextColor(textColor);
            chip.setRippleColor(ColorStateList.valueOf(rippleColor));
            chip.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
            interestsGroup.addView(chip);
        }
    }

    private enum ActionType {
        SKIP,
        LIKE,
        SUPER_LIKE
    }

    private static class Profile {
        final int imageRes;
        final String name;
        final int age;
        final int distanceKm;
        final String bio;
        final String[] interests;

        Profile(int imageRes, String name, int age, int distanceKm, String bio, String[] interests) {
            this.imageRes = imageRes;
            this.name = name;
            this.age = age;
            this.distanceKm = distanceKm;
            this.bio = bio;
            this.interests = interests;
        }
    }
}
