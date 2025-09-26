package com.nikobelich.sexproyect.data.model;

import java.util.Collections;
import java.util.List;

public class UserProfile {

    private final int id;
    private final String name;
    private final int age;
    private final String location;
    private final String bio;
    private final List<String> interests;
    private final int photoRes;

    public UserProfile(int id, String name, int age, String location, String bio, List<String> interests, int photoRes) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.location = location;
        this.bio = bio;
        this.interests = interests;
        this.photoRes = photoRes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getInterests() {
        return Collections.unmodifiableList(interests);
    }

    public int getPhotoRes() {
        return photoRes;
    }
}
