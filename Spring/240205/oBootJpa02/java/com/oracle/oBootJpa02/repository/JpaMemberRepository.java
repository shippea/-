package com.oracle.oBootJpa02.repository;

import java.util.List;
import java.util.Optional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

import jakarta.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	
	public JpaMemberRepository (EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		
		// 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		// 회원저장
		member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
		em.persist(member);
		System.out.println("JpaMemberRepositry save member->" + member);
		return(member);
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName +'%';
		System.out.println("JpaMemberRepository findByNames pname ->" +pname);
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name ", Member.class)
									.setParameter("name", pname)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()->" + memberList.size());
		return memberList;
	}

	@Override
	public Optional<Member> findByMember(Long id) {

		Member member = em.find(Member.class, id);
		System.out.println("findByMember ->" +member);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		System.out.println("update->"+member3.getId());
		
		if(member3 != null) {
			System.out.println("Here");
			Team team = em.find(Team.class, member.getId());
			System.out.println("team -> " + team.getName());
			if (team != null) {
				team.setName(member.getTeamname());
				System.out.println("team ->" + team.getName());
				em.persist(team);
			}
			// 회원 저장
			member3.setTeam(team);
			member3.setName(member.getName());
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember member->" +member);
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}
		return;
		
		
	}

}
