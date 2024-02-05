package com.oracle.oBootJpaApi01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@SequenceGenerator(
		name="member_seq_gen5", // 객체 seq 
		sequenceName="member_seq_generate5", // DB seq
		initialValue=1,
		allocationSize=1
		)
@Table(name = "member5")
public class Member {
	@Id
	@GeneratedValue (
			strategy = GenerationType.SEQUENCE,
			generator = "member_seq_gen5"
			)
		
	@Column(name="member_id")
	private Long   id;
	@Column(name="userName")
	private String name	;
	private Long   sal;
	
	// 관계설정
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
	
	// 실제 column x -> buffer 용도
	@Transient
	public String teamname;

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSal() {
		return sal;
	}

	public void setSal(Long sal) {
		this.sal = sal;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
}
