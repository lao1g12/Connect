package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FC_EXPERIENCES")
public class Experience {
	
	@Id
	@SequenceGenerator(name="experienceid_sequence", sequenceName="experienceid_sequence",
	initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="experienceid_sequence")
	@Column(name="ID")
	private int experienceId;
	@Column(name="COMPANY")
	private String company;
	@Column(name="ROLE")
	private String role;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name = "STARTDATE")
	private Calendar startDate;
	@Column(name = "ENDDATE")
	private Calendar endDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "FC_EXPERIENCE_PROFILE")
	private Profile profile;

	public Experience() {}

	public Experience(String company, String role, String description, Calendar startDate, Calendar endDate) {
		super();
		this.company = company;
		this.role = role;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(int experienceId) {
		this.experienceId = experienceId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getStartDate() {
		return startDate;
	}
	
	public Date getStartDateFormatted(){
		Date date = this.startDate.getTime();
		return date;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Date getEndDateFormatted(){
		Date date = this.endDate.getTime();
		return date;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
