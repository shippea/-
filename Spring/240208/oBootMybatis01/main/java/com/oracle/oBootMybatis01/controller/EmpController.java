package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {

	private final EmpService es;
	
	@RequestMapping("listEmp")
	public String empList(Emp emp,Model model) {
		System.out.println("EmpController start listEmp...");
		//if (emp.getCurrentPage()==null) emp.setCurrentPage("1");
		//Emp전체 Count 15
		int totalEmp =es.totalEmp();
		System.out.println("EmpController start  totalEmp-> "+totalEmp);
		
		// Paging 작업
		Paging page = new Paging(totalEmp, emp.getCurrentPage());
		// parameter emp --> page만 추가 setting
		emp.setStart(page.getStart());   //시작시1
		emp.setEnd(page.getEnd());       //시작시10
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController List listEmp.size()->"+listEmp.size());

		model.addAttribute("totalEmp",totalEmp);
		model.addAttribute("listEmp",listEmp);
		model.addAttribute("page",page);
		
		return "list";
	}
	
	@GetMapping(value="detailEmp")
	public String detailEmp(Emp emp1, Model model) {
		System.out.println("EmpController start detailEmp...");
		
		// # 실습1
//		1. EmpService안에 detailEmp method 선언
//		   1) parameter : empno
//		   2) Return      Emp
//
//		2. EmpDao   detailEmp method 선언 
////		                    mapper ID   ,    Parameter
//		emp = session.selectOne("tkEmpSelOne",    empno);
//		System.out.println("emp->"+emp1);
		
		
		
		Emp emp = es.detailEmp(emp1.getEmpno());
		model.addAttribute("emp",emp);
		return "detailEmp";
	}
	
	@GetMapping(value="updateFormEmp")
	public String updateFormEmp(Emp emp1, Model model) {
		System.out.println("EmpController start updateFormEmp..");
		
		Emp emp = es.detailEmp(emp1.getEmpno());
		System.out.println("emp.getEname() ->" + emp.getEname());
		System.out.println("emp.getHiredate() ->" + emp.getHiredate());
		
		// 문제 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생 (view에 값이 출력안됨)
		// 3.해결책  : 년월일만 짤라 넣어 주어야 함
		
		String hiredate="";
		if (emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0,10);
			emp.setHiredate(hiredate);
		}
		System.out.println("hiredate ->" + hiredate);
		
		model.addAttribute("emp", emp);
		return "updateFormEmp";
	}
	
	@PostMapping(value="updateEmp")
	public String updateEmp(Emp emp1, Model model) {
		System.out.println("EmpController start updateEmp...");
		
		// # 실습2
		
//      1. EmpService안에 updateEmp method 선언
//      1) parameter : Emp
//      2) Return      updateCount (int)
//
//   2. EmpDao updateEmp method 선언
////                              mapper ID   ,    Parameter
//   updateCount = session.update("tkEmpUpdate",emp);
		
		System.out.println("emp1 sal -> " + emp1.getSal());
		Emp emp = es.updateEmp(emp1.getEmpno());
		System.out.println("emp empno ->" + emp.getEmpno());
		System.out.println("emp sal -> " + emp.getSal());
		
		model.addAttribute("emp", emp);
		return "redirect:/detailEmp";
	}
	
}
