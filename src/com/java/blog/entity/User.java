/**
 *ʱ�䣺2014-4-18����10:07:11
 *userʵ����
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.entity;

public class User {
    
	//ʹ����id   int ����
	private int userid;
	//�˺�
	private String account;
	//����
	private String password;
	//��ʵ����
	private String name;
	//�Ա�
	private String gender;
	//ͷ��
	private String imageUrl;
	//����
	private int age;
	//����
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
