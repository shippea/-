package com.oracle.oBootMybatis01.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member3")
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	private Long id;
	private String name;
	private String password;
	// data에 값이 없으면 sysdate를 기본으로 값을 제공
	@Column (nullable = false, columnDefinition = "date default sysdate")
	private Date reg_date = new Date();
	

}
