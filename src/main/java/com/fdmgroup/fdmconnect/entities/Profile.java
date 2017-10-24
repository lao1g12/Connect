package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FC_PROFILES")
public class Profile {

	@Id
	@SequenceGenerator(name = "profileid_sequence", sequenceName = "profileid_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profileid_sequence")
	@Column(name = "ID")
	private int profileId;
	@Column(name = "FIRSTNAME")
	private String firstName;
	@Column(name = "LASTNAME")
	private String lastName;
	@Column(name = "STREAM")
	private String stream;
	@Column(name = "STARTDATE")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private Calendar startDate;
	@Column(name = "ENDDATE")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private Calendar endDate;
	@Column(name = "IMAGEURL")
	private String imageUrl;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "HOBBIES")
	private String hobbies;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="profile")
	private List<Education> education;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="profile")
	private List<Experience> experience;
	
	public Profile() {}

	public Profile(String imageUrl, String description, String hobbies) {
		super();
		this.imageUrl = imageUrl;
		this.description = description;
		this.hobbies = hobbies;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
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

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void addEducation(Education education) {
		this.education.add(education);
	}

	public List<Experience> getExperience() {
		return experience;
	}

	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}
	
}
