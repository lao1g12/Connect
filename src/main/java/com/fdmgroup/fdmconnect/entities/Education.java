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
@Table(name="FC_EDUCATIONS")
public class Education {
	
	@Id
	@SequenceGenerator(name="educationid_sequence", sequenceName="educationid_sequence",
	initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="educationid_sequence")
	@Column(name="ID")
	private int educationId;
	@Column(name="INSTITUTION")
	private String institution;
	@Column(name="QUALIFICATION")
	private String qualification;
	@Column(name="FURTHERINFO")
	private String furtherInfo;
	@Column(name = "STARTDATE")
	private Calendar startDate;
	@Column(name = "ENDDATE")
	private Calendar endDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "FC_EDUCATION_PROFILE")
	private Profile profile;

	
	
	public Education() {}

	public Education(String institution, String qualification, String furtherInfo, Calendar startDate,
			Calendar endDate) {
		super();
		this.institution = institution;
		this.qualification = qualification;
		this.furtherInfo = furtherInfo;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getEducationId() {
		return educationId;
	}

	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getFurtherInfo() {
		return furtherInfo;
	}

	public void setFurtherInfo(String furtherInfo) {
		this.furtherInfo = furtherInfo;
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
