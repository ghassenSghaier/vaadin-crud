package com.example.application.model.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="candidate")
public class Candidate  implements Serializable {

    @Id
    @NotNull
    @Column(name="candidate_id",length=9)
    private String id;
    @NotEmpty
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    @Email
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;

    public Candidate(String cin, String firstName, String lastname, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id=cin;
    }
    public Candidate()
    {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    public Collection<Application> getApplications() {
//        return applications;
//    }

//    public void setApplications(Collection<Application> applications) {
//        this.applications = applications;
//    }

}
