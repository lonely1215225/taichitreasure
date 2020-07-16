package edu.hunau.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "user_name", nullable = false, length = 32, unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "pwd_key")
	private String pwdKey;

//	@Column(name = "mobile")
//	private String mobile;
//
//	@Column(name = "create_time")
//	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdKey() {
		return pwdKey;
	}

	public void setPwdKey(String pwdKey) {
		this.pwdKey = pwdKey;
	}
}