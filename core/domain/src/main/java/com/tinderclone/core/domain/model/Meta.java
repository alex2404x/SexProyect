package com.tinderclone.core.domain.model;

import java.util.Date;

public class Meta {
    private Date createdAt;
    private Date updatedAt;
    private String banStatus;
    private int reportCount;

    public Meta() {}

    // Getters and Setters
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public String getBanStatus() { return banStatus; }
    public void setBanStatus(String banStatus) { this.banStatus = banStatus; }
    public int getReportCount() { return reportCount; }
    public void setReportCount(int reportCount) { this.reportCount = reportCount; }
}
