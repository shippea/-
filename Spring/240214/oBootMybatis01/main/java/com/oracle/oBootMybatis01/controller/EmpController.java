package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {

	private final EmpService es;

	@RequestMapping(value = "listEmp") 
	public String empList(Emp emp , Model model) {
		System.out.println("EmpController Start listEmp..." );
		// if (emp.getCurrentPage() == null ) emp.setCurrentPage("1");
		// Emp 전체 Count  15
		int totalEmp =  es.totalEmp();
		System.out.println("EmpController Start totalEmp->"+totalEmp );
		
		// Paging 작업
		Paging   page = new Paging(totalEmp, emp.getCurrentPage());
		// Parameter emp --> Page만 추가 Setting
		emp.setStart(page.getStart());   // 시작시 1
		emp.setEnd(page.getEnd());       // 시작시 10 

		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>" + listEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp" , listEmp);
		model.addAttribute("page"    , page);

		return "list";

	}
	
	@GetMapping(value = "detailEmp")
	public String detailEmp(Emp emp1 , Model model) {
		System.out.println("EmpController Start detailEmp..." );
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
	
	@GetMapping(value = "updateFormEmp")
	public String updateFormEmp(Emp emp1 , Model model) {
		System.out.println("EmpController Start updateForm..." );

		Emp emp = es.detailEmp(emp1.getEmpno());
	    System.out.println("emp.getEname()->"+emp.getEname());
	    System.out.println("emp.getHiredate()->"+emp.getHiredate());
		//System.out.println("hiredate->"+hiredate);
		// 문제 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3.해결책  : 년월일만 짤라 넣어 주어야 함 
		String hiredate = "";
		if (emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0, 10);
			emp.setHiredate(hiredate);
		}
		System.out.println("hiredate->"+hiredate);

	    
		model.addAttribute("emp",emp);
		return "updateFormEmp";

	}
	
	@PostMapping(value="updateEmp")
	public String updateEmp(Emp emp, Model model) {
        log.info("updateEmp Start...");
//      1. EmpService안에 updateEmp method 선언
//      1) parameter : Emp
//      2) Return      updateCount (int)
//
//   2. EmpDao updateEmp method 선언
////                              mapper ID   ,    Parameter
		int updateCount = es.updateEmp(emp);
		System.out.println("empController es.updateEmp updateCount-->"+updateCount);
		model.addAttribute("uptCnt",updateCount);    // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");    // Test Controller간 Data 전달
   		//return "forward:listEmp";   
   		return "redirect:listEmp";   
		  
	}
	
	@RequestMapping(value = "writeFormEmp" )
	public String writeFormEmp(Model model) {
		System.out.println("empController writeFormEmp Start...");
		// 관리자 사번 만 Get
		List<Emp> empList =  es.listManager();
		System.out.println("EmpController writeForm empList.size->"+empList.size());
		model.addAttribute("empMngList",empList); // emp Manager List
		// 1. Service , DAO --> listManager
		// 2. Mapper -> tkSelectManager
		//    1) Emp Table --> MGR 등록된 정보 Get
		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect(); 
		model.addAttribute("deptList", deptList); // dept
		System.out.println("EmpController writeForm deptList.size->"+deptList.size());
		
		return "writeFormEmp";
	}

	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp , Model model) {
		System.out.println("EmpController Start writeEmp..." );
		
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp);  
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeFormEmp";
		}
	}
	
	@RequestMapping(value = "writeFormEmp3" )
	public String writeFormEmp3(Model model) {
		System.out.println("empController writeFormEmp3 Start...");
		// 관리자 사번 만 Get
		List<Emp> empList =  es.listManager();
		System.out.println("EmpController writeFormEmp3 empList.size->"+empList.size());
		model.addAttribute("empMngList",empList); // emp Manager List
		
		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect(); 
		model.addAttribute("deptList", deptList); // dept
		System.out.println("EmpController writeFormEmp3 deptList.size->"+deptList.size());

		return "writeFormEmp3";
	}	
	
	
	// Validation시 참조
	@PostMapping(value = "writeEmp3")
	public String writeEmp3( @ModelAttribute("emp") @Valid Emp emp
			                , BindingResult result
			                , Model model
			                ) {
		System.out.println("EmpController Start writeEmp3..." );

		// Validation 오류시 Result
		if(result.hasErrors()) {
			System.out.println("EmpController writeEmp3 hasErrors... ");
			model.addAttribute("msg","BindingResult 입력 실패 확인해 보세요");
			return "forward:writeFormEmp3";
		}
		
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp); 
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeFormEmp3";
		}
		
	}

	@GetMapping(value="confirm")
	public String confirm(Emp emp1, Model model) {
		Emp emp = es.detailEmp(emp1.getEmpno());
		model.addAttribute("empno", emp1.getEmpno());
		if (emp !=null) {
			System.out.println("empController confirm 중복된 사번.. ");
			model.addAttribute("msg","중복된 사번입니다");			
			return "forward:writeFormEmp";
		} else {
			System.out.println("empController confirm 사용 가능한 사번.. ");
			model.addAttribute("msg","사용 가능한 사번입니다");
			return "forward:writeFormEmp";
		}
	}	
	
	@RequestMapping(value="deleteEmp")
	public String deleteEmp(Emp emp, Model model) {
		System.out.println("EmpController Start delete..." );
		// name -> Service, dao , mapper
		int result = es.deleteEmp(emp.getEmpno());
		return "redirect:listEmp";
	}
	
	@RequestMapping(value = "listSearch3")
	public String listSearch3(Emp emp ,  Model model) {
		// Emp 전체 Count  25
		int totalEmp =  es.condTotalEmp(emp);
		System.out.println("EmpController listSearch3 totalEmp=>" + totalEmp);
		// Paging 작업
		Paging   page = new Paging(totalEmp, emp.getCurrentPage());
		// Parameter emp --> Page만 추가 Setting
		emp.setStart(page.getStart());   // 시작시 1
		emp.setEnd(page.getEnd());       // 시작시 10 

		List<Emp> listSearchEmp = es.listSearchEmp(emp);
		System.out.println("EmpController listSearch3 listSearchEmp.size()=>" + listSearchEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp" , listSearchEmp);
		model.addAttribute("page"    , page);

		return "list";
	
	}
	
	@GetMapping(value="listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController listEmpDept Start...");
		// Service ,DAO -> listEmpDept
		// Mapper만 ->tkListEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept",listEmpDept);

		return "listEmpDept";

	}
	
}
