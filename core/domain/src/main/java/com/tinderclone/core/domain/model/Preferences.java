package com.tinderclone.core.domain.model;

import java.util.List;

public class Preferences {
    private List<String> preferredGenders;
    private AgeRange ageRange;
    private int maxDistanceKm;
    private List<String> interestsBoost;
    private boolean showOnlyVerified;
    private String language;

    public Preferences() {}

    // Getters and Setters
    public List<String> getPreferredGenders() { return preferredGenders; }
    public void setPreferredGenders(List<String> preferredGenders) { this.preferredGenders = preferredGenders; }
    public AgeRange getAgeRange() { return ageRange; }
    public void setAgeRange(AgeRange ageRange) { this.ageRange = ageRange; }
    public int getMaxDistanceKm() { return maxDistanceKm; }
    public void setMaxDistanceKm(int maxDistanceKm) { this.maxDistanceKm = maxDistanceKm; }
    public List<String> getInterestsBoost() { return interestsBoost; }
    public void setInterestsBoost(List<String> interestsBoost) { this.interestsBoost = interestsBoost; }
    public boolean isShowOnlyVerified() { return showOnlyVerified; }
    public void setShowOnlyVerified(boolean showOnlyVerified) { this.showOnlyVerified = showOnlyVerified; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
