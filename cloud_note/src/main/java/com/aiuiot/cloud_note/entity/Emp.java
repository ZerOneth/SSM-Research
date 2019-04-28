package com.aiuiot.cloud_note.entity;

import java.io.Serializable;
/**
 * 用户演示Mybatis
 * @author aiuiot
 *
 */
public class Emp implements Serializable {
	private Integer id;		//id
	private String name;	//姓名
	private int age;		//年龄
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "EMP [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
