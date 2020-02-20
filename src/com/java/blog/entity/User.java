/**
 *时间：2014-4-18上午10:07:11
 *user实体类
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.entity;

public class User {

	//使用者id   int 类型
	private int userid;
	//账号
	private String account;
	//密码
	private String password;
	//真实姓名
	private String name;
	//性别
	private String gender;
	//头像
	private String imageUrl;
	//年龄
	private int age;
	//积分
	private int integrals;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIntegrals() {
		return integrals;
	}

	public void setIntegrals(int integrals) {
		this.integrals = integrals;
	}
}
