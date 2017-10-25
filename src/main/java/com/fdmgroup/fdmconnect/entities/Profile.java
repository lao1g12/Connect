package com.fdmgroup.fdmconnect.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar startDate;
	@Column(name = "ENDDATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar endDate;
	@Column(name = "IMAGEURL")
	private String imageUrl;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "HOBBIES")
	private String hobbies;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="profile")
	private Set<Education> educations=new HashSet<Education>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="profile")
	private Set<Experience> experiences=new HashSet<Experience>();
	
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

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEducation(Set<Education> educations) {
		this.educations = educations;
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

	public Set<Education> getEducation() {
		return educations;
	}

	public void addEducation(Education education) {
		this.educations.add(education);
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}
	
	public void addExperience(Experience experience){
		this.experiences.add(experience);
	}
	
}
