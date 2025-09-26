package com.nikobelich.sexproyect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nikobelich.sexproyect.ui.matches.MatchesFragment;
import com.nikobelich.sexproyect.ui.messages.MessagesFragment;
import com.nikobelich.sexproyect.ui.profile.ProfileFragment;
import com.nikobelich.sexproyect.ui.discover.DiscoverFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_DISCOVER = "tag_discover";
    private static final String TAG_MATCHES = "tag_matches";
    private static final String TAG_MESSAGES = "tag_messages";
    private static final String TAG_PROFILE = "tag_profile";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, 0);
            return windowInsets;
        });

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_discover:
                    showFragment(TAG_DISCOVER, DiscoverFragment::new);
                    return true;
                case R.id.navigation_matches:
                    showFragment(TAG_MATCHES, MatchesFragment::new);
                    return true;
                case R.id.navigation_messages:
                    showFragment(TAG_MESSAGES, MessagesFragment::new);
                    return true;
                case R.id.navigation_profile:
                    showFragment(TAG_PROFILE, ProfileFragment::new);
                    return true;
                default:
                    return false;
            }
        });

        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.navigation_discover);
        }
    }

    private void showFragment(@NonNull String tag, @NonNull FragmentFactory factory) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        for (Fragment existing : fragmentManager.getFragments()) {
            if (existing != null) {
                transaction.hide(existing);
            }
        }

        if (fragment == null) {
            fragment = factory.create();
            transaction.add(R.id.fragment_container, fragment, tag);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
    }

    private interface FragmentFactory {
        Fragment create();
    }
}
