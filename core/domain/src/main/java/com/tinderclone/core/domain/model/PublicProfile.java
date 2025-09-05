package com.tinderclone.core.domain.model;

import java.util.List;
import java.util.Date;

public class PublicProfile {
    private String displayName;
    private int age;
    private String gender;
    private String bio;
    private List<String> photos;
    private Location location;
    private List<String> interests;
    private String jobTitle;
    private String education;
    private boolean verified;
    private Date lastActiveAt;

    public PublicProfile() {}

    // Getters and Setters
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public Date getLastActiveAt() { return lastActiveAt; }
    public void setLastActiveAt(Date lastActiveAt) { this.lastActiveAt = lastActiveAt; }
}
