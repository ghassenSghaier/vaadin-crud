package com.example.application.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDTO {

    private String candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    //private Collection<ApplicationDTO> applications;

    public CandidateDTO() {
    }

    public CandidateDTO(String candidateId, String firstName, String lastName, String email, String phone) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public CandidateDTO(String candidateId)
    {
        this.candidateId=candidateId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
//
//    public Collection<ApplicationDTO> getApplications() {
//        return applications;
//    }
//
//    public void setApplications(Collection<ApplicationDTO> applications) {
//        this.applications = applications;
//    }
}
