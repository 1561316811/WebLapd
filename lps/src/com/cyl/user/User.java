package com.cyl.user;

import java.sql.Date;

public class User {
	
	private String idUser;
	
	private String password;
	
	private int id;
	
	private Date birthday;
	
	private String telPhone;
	
	private String email;
	
	private String address;
	
	private String imagePath;
	
	private String question;
	
	private String answer;
	
	private Date addTime;
	
	private String workStatus;
	
	public User() {
		
	}
	
	public User(String idUser){
		this.idUser = idUser;
	}
	
	
	public User(String idUser, String password, int id, Date birthday, String telPhone, String email, String address,
			String imagePath, String question, String answer, Date addTime, String workStatus) {
		super();
		this.idUser = idUser;
		this.password = password;
		this.id = id;
		this.birthday = birthday;
		this.telPhone = telPhone;
		this.email = email;
		this.address = address;
		this.imagePath = imagePath;
		this.question = question;
		this.answer = answer;
		this.addTime = addTime;
		this.workStatus = workStatus;
	}


	public String getWorkStatus() {
		return workStatus;
	}


	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}


	public Date getAddTime() {
		return addTime;
	}



	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
