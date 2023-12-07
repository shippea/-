DROP TABLE table5;

CREATE TABLE table5(
	col1 char(1),
	col2 varchar2(20),
	col3 varchar2(30)
);

CREATE UNIQUE INDEX idx_table5
ON table5 (col1); 


CREATE UNIQUE INDEX idx2_table5
ON table5 (col2, col3);

CREATE OR REPLACE synonym syn_table5
FOR table5;

CREATE SEQUENCE seq_table5
INCREMENT BY 1
START WITH 1;

SELECT seq_table5.nextval FROM dual;

SELECT seq_table5.currval FROM dual;

DROP TABLE table6;

CREATE TABLE table6(
	userid NUMBER PRIMARY KEY,
	name varchar2(20),
	age number(3)
)
PARTITION BY range(age)
(
	PARTITION age_under_12 VALUES less than (12) TABLESPACE myts,
	PARTITION age_under_19 VALUES less than (19) TABLESPACE myts,
	PARTITION age_under_21 VALUES less than (21) TABLESPACE myts
);


/* 1. 직원테이블에서 직원의 아이디, 이름 급여(salary)를 조회 */
SELECT employee_id, emp_name, salary
-- select *는 모든 데이터를 조회
FROM EMPLOYEES;

/* 2. 직원 테이블에서 이름이 D로 시작하는 직원의 아이디, 이름, 급여를 조회 */
SELECT employee_id, emp_name, salary
FROM EMPLOYEES
WHERE emp_name LIKE 'D%';

/* 3. 직원테이블에서 이름이 D로 시작하는 직원의 아이디, 이름, 급여를 급여가 많은 순으로 조회 */
SELECT employee_id, emp_name, salary
FROM EMPLOYEES
WHERE emp_name LIKE 'D%'
ORDER BY salary DESC;
-- 많은 순으로 하려면 DESC (기본 defalut값은 ASC 오름차순)

/* 4. 직원테이블에서 이름이 D로 시작하는 직원의 아이디, 이름, 급여를 
 * 이름 순으로 ASC 정렬하고 급여가 많은 순으로 정렬해서 조회 */
SELECT employee_id, emp_name, salary
FROM EMPLOYEES
WHERE emp_name LIKE 'D%'
ORDER BY emp_name ASC, salary DESC;
-- 다중정렬 시 ,을 사용하여 순서대로 정렬조건을 입력

/* 5. 직원테이블에서 JOB_ID가 AD_VP인 직원들의 데이터를 연봉순으로 조회 */
SELECT employee_id, emp_name, salary, job_id
FROM EMPLOYEES
WHERE job_id = 'AD_VP'
ORDER BY salary DESC;

/* 6. 모든 직원의 연봉을 10% 상향해서 모든 직원들의 데이터를 연봉 많은 순으로 조회*/
SELECT employee_id, emp_name, salary "연봉", salary*1.1 "10% 인상된 연봉"
-- data 옆에 ""로 이름을 다르게 설정해서 볼 수 있음
FROM EMPLOYEES
ORDER BY salary*1.1 DESC;

/* 7. 직원의 이름보다 이메일주소가 더 긴 문자로 시작하는 모든 직원의 데이터를 조회 */
SELECT *
FROM EMPLOYEES
WHERE length(emp_name) < length(email);

/* 8. 직원 중에서 2005년 이후 고용된 직원들의 이름과 고용일을 조회*/
SELECT emp_name, hire_date
FROM EMPLOYEES
WHERE hire_date >= '2005-01-01';
-- 문자열 data이므로 ''에 넣어줘야 함

/* 9. 직원 중에서 2004년 이나 2006년에 입사한 직원들의 이름과 고용일을 조회 */
SELECT emp_name, hire_date
FROM EMPLOYEES 
WHERE hire_date BETWEEN '2004-01-01' AND '2004-12-31'
	OR hire_date BETWEEN '2006-01-01' AND '2006-12-31'
-- or뒤에 and를 쓴 것은 한 덩어리로 인식

/* 10. 직원 중에서 부서 아이디가 20,30,40,70,110인 직원들의 이름과 부서아이디를 조회 */
SELECT emp_name, department_id
FROM EMPLOYEES e
WHERE department_id = ANY (20,30,40.70.110);
-- department_id in (20,30,40,70,110) // 주로 in을 많이 씀

/* 11. 직원 커미션포인트가 있는 직원들의 급여와 커미션이 포함된 급여를 조회 */
SELECT salary "기본 급여", salary*(1+commission_pct) "커미션 포함 급여"
FROM EMPLOYEES e
WHERE commission_pct IS NOT NULL;


/* 12. 직원 중에서 1년 연봉(salary*12)이 8000 이상이면 고액연봉자
 	   6000 이상 8000미만이면 일반연봉자, 6000미만이면 저액연봉자로 조회 */
SELECT emp_name, salary*12,
	CASE WHEN salary*12 >=80000 THEN '고액연봉자'
		 WHEN salary*12 >=60000 AND salary*12 <=80000 THEN '일반연봉자'
		 ELSE '저액연봉자'
	--   when salary*12 <60000 then '저액연봉자'
	END 
FROM EMPLOYEES e;


/*
Join: 두 개 이상의 테이블의 데이터를 하나의 결과로 만들기 위해 PK와 FK의 관계를 따져 합침 (PK: 기본키, FK: 참조키)
Inner Join(Cartesian Join): 한 테이블에 PK와 다른 테이블의 FK가 같다는 조건 하에 합치는 것 
*/

SELECT * FROM DEPARTMENTS d;
SELECT * FROM EMPLOYEES e;

/* 13. 직원의 이름과 부서명을 조회 */

-- ORCALE Join 문법
SELECT e.EMP_NAME, d.DEPARTMENT_NAME
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID  = e.DEPARTMENT_ID;

-- ANSI Join 문법 -> 다른 oracle에서 공용으로 사용할 수 있는 문법
SELECT e.EMP_NAME, d.DEPARTMENT_NAME
FROM DEPARTMENTS d
	INNER JOIN EMPLOYEES e
	ON d.DEPARTMENT_ID  = e.DEPARTMENT_ID;

/* 14. 각 직원의 이름, 직무 아이디, 직무명, 최대급여와 최저급여의 차를 조회 */
SELECT e.emp_name, e.job_id, j.job_title, j.max_salary-j.min_salary
FROM EMPLOYEES e, JOBS j
WHERE e.job_id = j.job_id;

/* 15. 2006년에 일을 한 직원의 이름과 작업시작일, 작업종료일을 조회 */
SELECT * FROM JOB_HISTORY jh;
SELECT * FROM EMPLOYEES e;

SELECT e.EMP_NAME, jh.START_DATE, jh.END_DATE
FROM EMPLOYEES e, JOB_HISTORY jh
WHERE e.EMPLOYEE_ID = jh.EMPLOYEE_ID
-- 중복되지 않는 값을 join으로 설정해야 됨 (job_id도 같이 가지지만 중복되는 값이므로 join에 설정x)
	AND (jh.START_DATE < '2007-01-01'
			AND jh.END_DATE >= '2006-01-01');

/* 16. 이름이 D로 시작하는 직원 중 JOB_ID가 IT_PROG인 직원들의 직원아이디와 이름, 직업명을 조회 */
SELECT * FROM EMPLOYEES e;
SELECT * FROM JOBS j;

SELECT e.EMPLOYEE_ID, e.EMP_NAME, JOB_TITLE
FROM EMPLOYEES e, JOBS j
WHERE e.JOB_ID = j.JOB_ID
	AND e.EMP_NAME  LIKE 'D%'
	AND e.JOB_ID = 'IT_PROG';

/* 17. 고객 중에서 국적이 미국(United States of America)인 고객들의 이름과 국가명. 국가코드를 조회 */
SELECT * FROM CUSTOMERS cu;
SELECT * FROM COUNTRIES co;

-- SELECT COUNT(*) // 행 개수를 한번에 알 수 있음
-- configure 값을 조절하면 한번에 보여주는 결과값의 행의 개수를 조절할 수 있음

SELECT cu.CUST_NAME, co.COUNTRY_NAME, co.COUNTRY_ISO_CODE
FROM CUSTOMERS cu, COUNTRIES co
WHERE cu.COUNTRY_ID = co.COUNTRY_ID 
	AND co.COUNTRY_NAME = 'United States of America';

/* rowid, rownum: 오라클이 기본 제공하는 행에 대한 아이디와 번호 */
SELECT rowid, rownum, emp_name
FROM EMPLOYEES e
WHERE rownum<=100;
-- 임의의 개수만큼 가져오고 싶을 때 rownum을 설정하면 됨

/* subquery: 쿼리문 내에 있는 쿼리 */

SELECT e.EMPLOYEE_ID, e.EMP_NAME 
FROM EMPLOYEES e
WHERE SALARY >= (SELECT SALARY FROM EMPLOYEES WHERE EMPLOYEE_ID='198');


/* 18. 직원 중 부서 아이디가 20 또는 50인 직원들의 아이디와 이름, 부서아이디를 조회 (subquery 사용) */

SELECT e.EMPLOYEE_ID, e.EMP_NAME, e.DEPARTMENT_ID 
FROM EMPLOYEES e 
WHERE e.EMPLOYEE_ID IN (
	SELECT e.EMPLOYEE_ID 
	FROM EMPLOYEES e 
	WHERE e.DEPARTMENT_ID IN (20,50)
	);
-- ORA-01427: single-row subquery returns more than one row 
-- 받을 수 있는 결과값은 1개인데 나오는 결과값은 여러개일 경우 오류발생
-- 하위 query의 결과값이 여러개이므로 1개만 받을 수 있는 =이 아닌 in으로 묶어서 받음

/* 19. 직원 중 부서아이디가 20 또는 50인 직원들의 아이디, 이름, 부서아이디, 부서명을 조회 (subquery 사용) */

SELECT e.EMPLOYEE_ID, e.EMP_NAME, e.DEPARTMENT_ID, d.DEPARTMENT_NAME
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND e.EMPLOYEE_ID IN (
	SELECT e.EMPLOYEE_ID 
	FROM EMPLOYEES e 
	WHERE e.DEPARTMENT_ID IN (20,50)
	);

/* 1:다 관계 (학생:과목, 과목:교수)
 * 학생, 과목, 교수 테이블
 * 학생: 아이디, 이름
 * 과목: 과목아이디, 과목명
 * 교수: 교수아이디, 교수명
 */
DROP TABLE student;

CREATE TABLE student (
	sid NUMBER PRIMARY KEY,
	sname varchar2(100) NOT null
);

CREATE SEQUENCE seq_student;

INSERT INTO STUDENT s VALUES (seq_student.nextval, '홍길동');
INSERT INTO STUDENT s VALUES (seq_student.nextval, '강감찬');
INSERT INTO STUDENT s VALUES (seq_student.nextval, '유관순');
SELECT * FROM STUDENT s;

CREATE TABLE subject(
	subid NUMBER PRIMARY KEY,
	subname varchar2(100) NOT NULL,
	sid NUMBER, 
		CONSTRAINT fk_student_sid FOREIGN key(sid)
		REFERENCES student(sid)
);

CREATE SEQUENCE seq_subject;

INSERT INTO SUBJECT s VALUES (seq_subject.nextval, '영어',1);
INSERT INTO SUBJECT s VALUES (seq_subject.nextval, '수학',2);
INSERT INTO SUBJECT s VALUES (seq_subject.nextval, '수학',1);
INSERT INTO SUBJECT s VALUES (seq_subject.nextval, '영어',2);
SELECT * FROM SUBJECT s;
-- 학생:과목 = 1:다

CREATE TABLE professor(
	pid NUMBER PRIMARY KEY,
	pname varchar2(100) NOT NULL,
	subid NUMBER, 
		CONSTRAINT fk_subject_subid FOREIGN key(subid)
		REFERENCES subject(subid)
);

CREATE SEQUENCE seq_professor;

INSERT INTO PROFESSOR p VALUES (seq_professor.nextval, '교수1',1);
INSERT INTO PROFESSOR p VALUES (seq_professor.nextval, '교수2',2);
INSERT INTO PROFESSOR p VALUES (seq_professor.nextval, '교수3',2);
SELECT * FROM PROFESSOR p;
-- 과목:교수 = 1:다

/* 20. 학생들이 수강하는 과목을 조회 (학생명, 과목명) */

SELECT stu.SNAME, sub.SUBNAME 
FROM STUDENT stu, SUBJECT sub
WHERE stu.SID = sub.SID;


/* 21. 학생들이 수강하는 과목의 교수님들을 조회 (학생명, 과목명, 교수명) */

SELECT * FROM STUDENT stu;
SELECT * FROM SUBJECT sub;
SELECT * FROM PROFESSOR p;

SELECT stu.SNAME, sub.SUBNAME, p.PNAME 
FROM STUDENT stu, SUBJECT sub, PROFESSOR p 
WHERE stu.SID = sub.SID
	AND sub.SUBID = p.SUBID;


/* 다:다 관계 -> query가 만들어지지 않기 때문에 중간에 key테이블을 별도로 만들어서
 * 				1:다, 1:다 관계로 바꿔줘야 함
 * 예) 대학원생, 지도교수
 * - 1명의 대학원생이 여러 지도 교수가 있음
 * - 1명의 지도교수가 여러 대학원생을 지도함
 */

CREATE TABLE gstudent(
	gsid NUMBER PRIMARY KEY,
	gsname varchar2(100) NOT NULL
--	rpid NUMBER, 
--		CONSTRAINT fk_subject_rpid FOREIGN key(rpid)
--		REFERENCES subject(rpid)
);

CREATE SEQUENCE seq_gstudent;

INSERT INTO gstudent VALUES (seq_gstudent.nextval, '홍길동');
INSERT INTO gstudent VALUES (seq_gstudent.nextval, '강감찬');
INSERT INTO gstudent VALUES (seq_gstudent.nextval, '유관순');

DROP TABLE RPROFESSOR;
DROP SEQUENCE seq_rprofessor;

CREATE TABLE rprofessor(
	rpid NUMBER PRIMARY KEY,
	rpname varchar2(100) NOT NULL
--	gsid NUMBER, 
--		CONSTRAINT fk_subject_gsid FOREIGN key(gsid)
--		REFERENCES subject(gsid)
);

CREATE SEQUENCE seq_rprofessor;

INSERT INTO rprofessor VALUES (seq_rprofessor.nextval, '교수1');
INSERT INTO rprofessor VALUES (seq_rprofessor.nextval, '교수2');
INSERT INTO rprofessor VALUES (seq_rprofessor.nextval, '교수3');

-- 다:다 관계 해소용 키테이블 (1:다, 1:다로 만들어줌)
-- gsid, rpid number query를 각각의 테이블에 넣으면 기준이 없이
-- 서로 참조하기 때문에 만들어지지 x -> 따로 키테이블을 만들고 그곳에 참조코드를 넣어줌
CREATE TABLE gs_rp_key (
	gsid NUMBER,
		CONSTRAINT fk_subject_gsid FOREIGN key(gsid)
		REFERENCES gstudent(gsid),
		-- gstudent:gs_rp_key = 1:다
	rpid NUMBER,
		CONSTRAINT fk_subject_rpid FOREIGN key(rpid)
		REFERENCES rprofessor(rpid)
		-- rprofessor:gs_rp_key = 1:다
);

INSERT INTO gs_rp_key values(1,1);
INSERT INTO gs_rp_key values(1,2);
INSERT INTO gs_rp_key values(1,3);
INSERT INTO gs_rp_key values(2,1);
INSERT INTO gs_rp_key values(2,2);
INSERT INTO gs_rp_key values(2,3);

SELECT * FROM GSTUDENT g;
SELECT * FROM RPROFESSOR r;

SELECT rp.RPNAME 
FROM RPROFESSOR rp
WHERE rp.rpid in(
	SELECT gr.rpid
	FROM GSTUDENT g, gs_rp_key gr
	WHERE g.gsid = gr.gsid
		AND g.gsid =1);
	