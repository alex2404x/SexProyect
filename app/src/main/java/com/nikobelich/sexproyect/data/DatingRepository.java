package com.nikobelich.sexproyect.data;

import android.content.Context;

import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.model.Match;
import com.nikobelich.sexproyect.data.model.MessageThread;
import com.nikobelich.sexproyect.data.model.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatingRepository {

    private static DatingRepository instance;

    private final List<UserProfile> discoveryProfiles = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();
    private final List<MessageThread> conversations = new ArrayList<>();
    private UserProfile currentUser;

    private DatingRepository(Context context) {
        seedData(context);
    }

    public static synchronized DatingRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DatingRepository(context.getApplicationContext());
        }
        return instance;
    }

    public List<UserProfile> getDiscoveryProfiles() {
        return Collections.unmodifiableList(discoveryProfiles);
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public List<MessageThread> getConversations() {
        return Collections.unmodifiableList(conversations);
    }

    public UserProfile getCurrentUser() {
        return currentUser;
    }

    private void seedData(Context context) {
        UserProfile emilia = new UserProfile(
                1,
                "Emilia",
                28,
                context.getString(R.string.profile_location_chapala),
                context.getString(R.string.profile_bio_emilia),
                Arrays.asList("Brunch", "Yoga", "Fotografía"),
                R.drawable.illustration_emilia
        );

        UserProfile alex = new UserProfile(
                2,
                "Alex",
                31,
                context.getString(R.string.profile_location_zapopan),
                context.getString(R.string.profile_bio_alex),
                Arrays.asList("Ciclismo", "Cafés", "Festivales"),
                R.drawable.illustration_alex
        );

        UserProfile sofia = new UserProfile(
                3,
                "Sofía",
                26,
                context.getString(R.string.profile_location_guadalajara),
                context.getString(R.string.profile_bio_sofia),
                Arrays.asList("Música en vivo", "Tatuajes", "Series"),
                R.drawable.illustration_sofia
        );

        UserProfile camila = new UserProfile(
                4,
                "Camila",
                29,
                context.getString(R.string.profile_location_tlaquepaque),
                context.getString(R.string.profile_bio_camila),
                Arrays.asList("Road trips", "Cocina", "Mascotas"),
                R.drawable.illustration_camila
        );

        UserProfile lucia = new UserProfile(
                5,
                "Lucía",
                27,
                context.getString(R.string.profile_location_tonala),
                context.getString(R.string.profile_bio_lucia),
                Arrays.asList("Crossfit", "Tecnología", "Museos"),
                R.drawable.illustration_lucia
        );

        discoveryProfiles.add(emilia);
        discoveryProfiles.add(alex);
        discoveryProfiles.add(sofia);
        discoveryProfiles.add(camila);
        discoveryProfiles.add(lucia);

        matches.add(new Match(emilia, context.getString(R.string.matched_hours_ago, 2)));
        matches.add(new Match(sofia, context.getString(R.string.matched_hours_ago, 5)));
        matches.add(new Match(camila, context.getString(R.string.matched_day_ago)));

        conversations.add(new MessageThread(
                matches.get(0),
                context.getString(R.string.message_thread_emilia),
                context.getString(R.string.time_today, "20:14"),
                true
        ));
        conversations.add(new MessageThread(
                matches.get(1),
                context.getString(R.string.message_thread_sofia),
                context.getString(R.string.time_today, "18:02"),
                false
        ));
        conversations.add(new MessageThread(
                matches.get(2),
                context.getString(R.string.message_thread_camila),
                context.getString(R.string.time_yesterday, "23:15"),
                false
        ));

        currentUser = new UserProfile(
                999,
                "Valeria",
                30,
                context.getString(R.string.profile_location_guadalajara),
                context.getString(R.string.profile_bio_valeria),
                Arrays.asList("Cocteles de autor", "Street art", "Mercados locales"),
                R.drawable.illustration_valeria
        );
    }
}
