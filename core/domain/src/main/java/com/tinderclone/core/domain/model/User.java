package com.tinderclone.core.domain.model;

import com.google.firebase.firestore.DocumentId;

public class User {
    @DocumentId
    private String uid;

    private PublicProfile publicProfile;
    private Preferences preferences;
    private Meta meta;

    public User() {}

    // Getters and Setters
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public PublicProfile getPublicProfile() { return publicProfile; }
    public void setPublicProfile(PublicProfile publicProfile) { this.publicProfile = publicProfile; }
    public Preferences getPreferences() { return preferences; }
    public void setPreferences(Preferences preferences) { this.preferences = preferences; }
    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }
}
