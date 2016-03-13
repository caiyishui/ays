package com.tz.ays.entity.result;
//登入返回实体类

public class ResultUserLogin extends ResultCallback{
	private String name;
	private Integer userType;
	private String lastLoginTime;
	private Integer score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	

}
