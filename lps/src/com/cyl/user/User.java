package com.cyl.user;

import java.sql.Date;

public class User {
	
	private String idUser;
	
	private String password;
	
	private Integer id;
	
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
	
	
	public User(String idUser, String password, Integer id, Date birthday, String telPhone, String email, String address,
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

	
	public User(Builder b) {
		super();
		this.idUser = b.idUser;
		this.password = b.password;
		this.id = b.id;
		this.birthday = b.birthday;
		this.telPhone = b.telPhone;
		this.email = b.email;
		this.address = b.address;
		this.imagePath = b.imagePath;
		this.question = b.question;
		this.answer = b.answer;
		this.addTime = b.addTime;
		this.workStatus = b.workStatus;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
	
	public static class Builder{
		
		private String idUser;
		
		public Builder idUser(String idUser){
			this.idUser = idUser;
			return this;
		}
		
		private String password;
		
		public Builder password(String password){
			this.password = password;
			return this;
		}
		
		private Integer id;
		
		public Builder id(Integer id){
			this.id = id;
			return this;
		}
		private Date birthday;
		public Builder birthday(Date birthday){
			this.birthday = birthday;
			return this;
		}
		private String telPhone;
		public Builder telPhone(String telPhone){
			this.telPhone = telPhone;
			return this;
		}
		private String email;
		public Builder email(String email){
			this.email = email;
			return this;
		}
		private String address;
		public Builder address(String address){
			this.address = address;
			return this;
		}
		private String imagePath;
		public Builder imagePath(String imagePath){
			this.imagePath = imagePath;
			return this;
		}
		private String question;
		public Builder question(String question){
			this.question = question;
			return this;
		}
		private String answer;
		public Builder answer(String answer){
			this.answer = answer;
			return this;
		}
		private Date addTime;
		public Builder addTime(Date addTime){
			this.addTime = addTime;
			return this;
		}
		private String workStatus;
		public Builder workStatus(String workStatus){
			this.workStatus = workStatus;
			return this;
		}
		
		public Builder(String idUser) {
			this.idUser = idUser;
		}
		
		
	}
	
	
}
