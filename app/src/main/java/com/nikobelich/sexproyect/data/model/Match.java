package com.nikobelich.sexproyect.data.model;

public class Match {

    private final UserProfile profile;
    private final String matchedAt;

    public Match(UserProfile profile, String matchedAt) {
        this.profile = profile;
        this.matchedAt = matchedAt;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public String getMatchedAt() {
        return matchedAt;
    }
}
