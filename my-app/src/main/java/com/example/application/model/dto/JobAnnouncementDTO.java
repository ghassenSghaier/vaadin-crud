package com.example.application.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobAnnouncementDTO {

    private Long announcementId;
    private String position;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    //private Collection<ApplicationDTO> applications;

    public JobAnnouncementDTO(Long announcementId) {
        this.announcementId = announcementId;
    }

    public JobAnnouncementDTO() {
    }

    public JobAnnouncementDTO(String position, String company, LocalDate startDate, LocalDate endDate, String description) {
        this.position = position;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Collection<ApplicationDTO> getApplications() {
//        return applications;
//    }
//
//    public void setApplications(Collection<ApplicationDTO> applications) {
//        this.applications = applications;
//    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
