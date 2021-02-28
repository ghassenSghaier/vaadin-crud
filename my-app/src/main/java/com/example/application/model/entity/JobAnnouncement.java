package com.example.application.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="job_annonoucement")
public class JobAnnouncement implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="announcement_id")
	private Long id;

	@Column(name="company_name")
	private String company;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="announ_start_date")
	private LocalDate startDate;

	@Column(name="announ_end_date")
	private LocalDate endDate;

	@Column(name="announ_description")
	private String description;

	public JobAnnouncement(String company, LocalDate startDate, LocalDate endDate, String description) {
		this.company = company;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public JobAnnouncement() {

	}

	public JobAnnouncement(Long id, String company, String jobTitle, LocalDate startDate, LocalDate endDate, String description) {
		this.id = id;
		this.company = company;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;

	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

//	public Collection<Application> getApplications() {
//		return applications;
//	}
//
//	public void setApplications(Collection<Application> applications) {
//		this.applications = applications;
//	}
}
