package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Emp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpDao ed;
	
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl start total...");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl tottalEmp totEmpCnt -> "+totEmpCnt);
		return totEmpCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		empList =ed.listEmp(emp);
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpServiceImpl detailEmp start...");
		
		Emp emp = null;
		emp = ed.detailEmp(empno);
		return emp;
		
	}

	@Override
	public Emp updateEmp(int empno) {
		System.out.println("EmpServiceImpl updateEmp start...");
		Emp emp = null;
		
		emp = ed.updateEmp(empno);
		return emp;
	}

}
