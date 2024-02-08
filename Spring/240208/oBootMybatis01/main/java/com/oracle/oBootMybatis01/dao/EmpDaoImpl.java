package com.oracle.oBootMybatis01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	//my batis 연동
	private final SqlSession session;
	@Override
	public int totalEmp() {
		int totEmpCount =0 ;
		System.out.println("EmpDaoImpl start total...");
		try {
			totEmpCount = session.selectOne("com.oracle.oBootMybatis01.EmpMapper.empTotal");  //Emp.xml과연결!!!
			System.out.println("EmpDaoImpltotalEmp totEmpCount-> "+ totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpltotalEmp totEmpCount Exception ->" + e.getMessage());
		}
		return totEmpCount;
	}
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		
		try {
			//								MAP ID    PARAMETERS
			empList =session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()->"+empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()"+e.getMessage());
		}
		return empList;
	}
	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpDaoImpl detail start...");
		Emp emp = new Emp();
		
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname ->" + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception ->" + e.getMessage());
		}
 		return emp;
	}
	@Override
	public Emp updateEmp(int empno) {
		System.out.println("EmpDaoImpl update start...");
		Emp emp = new Emp();
		
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl update getEname ->" + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception ->" + e.getMessage());
		}
		
		return emp;
	}


}
