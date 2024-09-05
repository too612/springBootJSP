package com.base.app.main.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainDTO {
	
	int num;
	String name;
	String pwd;
	String email;
	String subject;
	String content;
	String ipAddr;
	String created;
	int hitCount;
}