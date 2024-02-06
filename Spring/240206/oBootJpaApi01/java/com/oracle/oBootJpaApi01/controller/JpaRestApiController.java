package com.oracle.oBootJpaApi01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;


//RestController = Controller + ResponseBody
//사용목적: Ajax + RestApi

@RestController
@RequiredArgsConstructor
@Slf4j
public class JpaRestApiController {
	private final MemberService memberService;
	
	// v1: member의 모든 column 정보들이 제공됨
	@PostMapping("/restApi/v1/memberSave")
	// @RequsetBody: Json(member)으로 온 것을 --> Member member Setting
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		System.out.println("JpaRestApiController /api/v1/memberSave member.getId()->" + member.getId());
		log.info("member.getName()-> {}.",member.getName());
		log.info("member.getSal()-> {}.", member.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	// v2: member의 제공하고 싶은 column 정보들만 제공 (class로 제공할 column을 parameter로 만들어 제공)
	// Good API
	@PostMapping("/restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest cMember) {
		System.out.println("JpaRestApiController /api/v2/memberSave cMember.getId()->"+cMember.getName());
		
		log.info("cMember.getName()-> {}.", cMember.getName());
		log.info("cMember.getSal()-> {}.", cMember.getSal());
		Member member = new Member();
		member.setName(cMember.getName());
		member.setSal(cMember.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
		
	}
	
	@Data
	static class CreateMemberRequest {
		@NotEmpty
		private final String name;
		private final Long sal;
	}
	 
	@Data
	@RequiredArgsConstructor
	static class CreateMemberResponse {
		private final Long id;
		
	}
	
	// Bad API
	@GetMapping ("/restApi/v1/members")
	public List<Member> memberVer1() {
		System.out.println("JpaRestApiController /restApi/v1/members start...");
		List<Member> listMember = memberService.getListAllMember();
		return listMember;
	}
	

}
