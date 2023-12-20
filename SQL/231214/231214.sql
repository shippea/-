/*
익명블록 (이름이 없는 PL/SQL 블록)
문법: DECLARE ~ BEGIN ~ END;
declare: 변수, 상수, 커서 선언
begin~end; : 처리할 코드
변수선언: 변수명 변수타입;
상수선언: 변수명 constant 변수타입;
:= : 변수에 값 할당 연산자
DBMS_OUTPUT: 출력에 관한 함수와 프로시져들이 있는 패키지명
PUT_LINE: 인자값을 출력하는 함수
*/

-- 선언부, 실행부 안에서 문장은 한 행마다 ;필수
-- DECLARE~BEGIN: 선언부
DECLARE
-- 변수명 다음 변수type 명시
-- table data를 변수에 받아올 때 type을 일일히 찾기 어렵기때문에
-- type에 table명.column명%를 해주면 자동으로 type을 찾아서 인식시켜줌
	vi_num NUMBER;	
--BEGIN~END: 실행부
BEGIN
-- 변수에 값을 넣을 때 :=을 해줘야 함
	vi_num := 100;
-- 출력문법
	DBMS_OUTPUT.PUT_LINE(vi_num);
-- end에서 ;처리해줘야됨
END;
-- / 해줘야 실행됨
/

-- 변수를 선언한 필요 없으면 declare도 생략가능
-- putline에 바로 문자 입력가능
BEGIN
	DBMS_OUTPUT.PUT_LINE('HELLO');
END;


DECLARE
	var1 varchar2(10);
-- 상수는 항상 초기화해서 사용해야함
	const1 constant varchar2(10) := 'HELLO';
BEGIN
	var1 := 'PLSQL';
-- 상수는 선언부에서 초기화한 값 그대로만 사용 가능 (실행부에서 값을 바꿀수 x)
	const1 := 'NOT HELLO';
	DBMS_OUTPUT.PUT_LINE(const1);
	DBMS_OUTPUT.PUT_LINE(var1);
END;

SELECT e.FIRST_NAME  FROM EMPLOYEES e;


-- 변수에 조회결과를 할당
-- 선언한 변수의 개수, type, 순서와 select할 column이 같아야됨
DECLARE 
	EMP_ID NUMBER(6,0);
-- type에 테이블명.칼럼명%TYPE을 해주면 dat를 받아올 column의 type을 자동으로 받아줌
	EMP_FN EMPLOYEES.FIRST_NAME%TYPE;
--	EMP_FN varchar2(20);
	EMP_LN VARCHAR2(25);
BEGIN
	SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME
	INTO EMP_ID, EMP_FN, EMP_LN
	FROM EMPLOYEES e
	WHERE EMPLOYEE_ID = 100;
	DBMS_OUTPUT.PUT_LINE(EMP_ID||' - '||EMP_FN||' - '||EMP_LN);
END;


/* 사원 테이블에서 201번 사원의 이름과 이메일 주소를 출력하는 익명블록 */
-- select에서 변수명을 만들어줘도 type을 자동으로 불러올 때는 table 안에만 있는 column명으로만 불러오기
DECLARE 
	EMP_NAME EMPLOYEES.FIRST_NAME%TYPE;
	EMP_EMAIL EMPLOYEES.EMAIL%TYPE;
BEGIN 
	SELECT (e.FIRST_NAME||' '||e.LAST_NAME) name, e.EMAIL
	INTO EMP_NAME, EMP_EMAIL
	FROM EMPLOYEES e
	WHERE e.EMPLOYEE_ID = 201;
	DBMS_OUTPUT.PUT_LINE(EMP_NAME||' - '||EMP_EMAIL);
END;

BEGIN
	INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, DEPARTMENT_ID, JOB_ID)
	-- employee id는 unique 값이므로 table의 seq에서 nextval로 값을 받아야 중복되는 값을 받지 않게 해줌
	VALUES (EMPLOYEES_SEQ.NEXTVAL,'Harrison', 'Ford', 'HARRIS', sysdate, 50, 'AD_PRES');
END;

/*
function: return값이 있는 호출해서 처리할 코드블록
기본문법
create [or replace] function 함수명 (매개변수리스트)
return 리턴타입
is 또는 as
변수, 상수, 커서 선언
begin
[exception]
end
*/

-- 함수명 (매개변수리스트)
CREATE OR REPLACE FUNCTION getDeptId(empId NUMBER)
-- return 타입을 지정
RETURN NUMBER
IS
-- empId를 받아와서 이에 해당하는 dept_id
-- is 뒤에 선언한 변수에는 ;를 붙이지 x
	dept_id EMPLOYEES.DEPARTMENT_ID%TYPE;
BEGIN 
	SELECT e.DEPARTMENT_ID
	INTO dept_id
	FROM EMPLOYEES e
	WHERE e.EMPLOYEE_ID = empid;
	RETURN dept_id;
END;

SELECT getDeptId(100) FROM dual;


/*
Procedure(프로시져): return문을 통해 값을 반환하지 않는함수
	(return문으로 값을 반환하는 것은 function)
IN변수: 프로시져에서 값을 받아서 사용할 목적의 변수
OUT변수: 프로시져 안에 있는 값을 호출한 쪽에 보낼 목적의 변수
	(out으로 설정한 변수는 밖에서 값을 던져줘도 받지 못함)
IN OUT변수: IN, OUT 역할을 동시에 수행하는 변수
	(값을 받거나 던질 수 있음)
*/ 

CREATE OR REPLACE PROCEDURE proc_test (
	-- 프로시져에 들어오는 값을 저장할 변수
	v_in IN NUMBER,
	-- 프로시져를 호출했을 때 호출한 쪽에 전달할 값을 저장할 변수
	v_out OUT NUMBER,
	-- 프로시져에 들어오는 값을 저장하고 호출한쪽에 전달할 값도 저장할 변수
	v_inout IN OUT NUMBER
) 
IS
BEGIN
	-- in 파리미터로 들어온 값을 출력
	DBMS_OUTPUT.PUT_LINE('in변수가 받은 값: '||v_in);
	-- in out 파라미터로 들어온 값을 출력
	DBMS_OUTPUT.PUT_LINE('in out변수가 받은 값: '||v_inout);
	-- out, inout 파라미터로 내보낼 값 
	-- 아래 익명block 선언부의 변수들이 이 값으로 바뀜
	v_out := 40;
	v_inout := 30;
END;

DECLARE
	-- 호출할 프로시져의 out 파라미터에게 전달받을 값을 저장할 변수
	v_var1 NUMBER := 1;
	-- 호출할 프로시져의 in out 파라미터에게 전달받을 값을 저장할 변수
	v_var2 NUMBER := 2;
BEGIN 
	-- 프로시져를 호출 전 선언부에서 선언한 값들을 출력
	DBMS_OUTPUT.PUT_LINE('프로시져 호출 전 값: '||v_var1||', '||v_var2);
	-- 포리시져를 호출하면
	-- 1. 10값이 IN파라미터에 전달
	-- 2. v_var1에 프로시져의 OUT 파라미터로부터 전달받은 값을 저장 (v_out := 40)
	-- 3. v_var2에 프로시져의 IN OUT 파라미터로부터 전달받은 값을 저장 (v_inout := 30)
	-- 프로시져를 호출 (파라미터 개수만큼 ()에 값을 넣어줘야 함)
	proc_test (10,v_var1,v_var2);
	-- proc_test 프로시져를 실행한 후에는 v_var1,2의 값이 프로시져의 out 변수에 의해 바뀜
	DBMS_OUTPUT.PUT_LINE('OUT변수가 내보낸 값 : '||v_var1);
	DBMS_OUTPUT.PUT_LINE('IN OUT변수가 내보낸 값 : '||v_var2);
END;

/* 함수, 프로시져 실습 */

/* # 실습1
	프로시져명: PROC_GUGUDAN
   	기능: 구구단을 출력				*/ 

CREATE OR REPLACE PROCEDURE PROC_GUGUDAN (in_num IN NUMBER)
IS 
BEGIN 
	IF in_num <2 THEN 
		DBMS_OUTPUT.PUT_LINE('숫자를 2이상으로 입력해주세요.');
		RETURN;
	ELSIF in_num >9 THEN
		DBMS_OUTPUT.PUT_LINE('숫자를 9이하로 입력해주세요.');
		RETURN;
	END IF;
	
	FOR i IN 1..9
	LOOP
		DBMS_OUTPUT.PUT_LINE(in_num||' x '||i||' = '||in_num*i);
	END LOOP;
END;

DECLARE 
BEGIN
	PROC_GUGUDAN (2);
END;


/* # 실습2
 	프로시져명: PROC_STARPRINT1
	기능: 별을 출력
  	출력형태: * 
  			**
  			***					*/

CREATE OR REPLACE PROCEDURE PROC_STARPRINT1 (
	v_num IN NUMBER )
	
IS 
BEGIN
	
	FOR j IN 1..v_num
	LOOP 
		FOR i IN 1..j
		LOOP 
		DBMS_OUTPUT.PUT ('*');
		END LOOP;
		DBMS_OUTPUT.NEW_LINE;
	END LOOP;
END;

DECLARE
BEGIN
	PROC_STARPRINT1 (6);
END;


/* # 실습3.
 	 프로시져명 : PROC_STARPRINT2
     기능 : 별을 출력한다.
     출력형태
         *
        **
       ***
      ****					*/

CREATE OR REPLACE PROCEDURE PROC_STARPRINT2 (
	v_num IN NUMBER )
IS 
BEGIN
	
	FOR k IN 1..v_num
	LOOP 	
		FOR i IN 1..(v_num-k)
		LOOP
			DBMS_OUTPUT.PUT (' ');
		END LOOP;
	
		FOR j IN 1..k
		LOOP 
			DBMS_OUTPUT.PUT ('*');
		END LOOP;
		
		DBMS_OUTPUT.NEW_LINE;

	END LOOP;
END;
 
DECLARE
BEGIN
	PROC_STARPRINT2 (10);
END;

-- 4. 프로시져명 : PROC_GETDEPTID
--    기능 : EMPLOYEE_ID를 입력하면 해당 직원의 DEPARTMENT_ID를 출력
	
CREATE OR REPLACE PROCEDURE PROC_GETDEPID (
	v_eid IN EMPLOYEES.EMPLOYEE_ID%TYPE,
	v_did OUT EMPLOYEES.DEPARTMENT_ID%TYPE)
IS 
BEGIN 
	SELECT DEPARTMENT_ID
	INTO v_did
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = v_eid;

	DBMS_OUTPUT.PUT_LINE('DEPARTMENT_ID: '||v_did);
END;

DECLARE
	v_pdid EMPLOYEES.DEPARTMENT_ID%TYPE;
BEGIN
	PROC_GETDEPID(100,v_pdid);
	DBMS_OUTPUT.PUT_LINE('DEPARTMENT_ID: '||v_pdid);
END;


-- 5. 프로시져명 : PROC_GETYOIL
--   기능 : 년도네자리, 월두자리, 일두자리를 입력하면 해당 일자의 요일을 출력

CREATE OR REPLACE PROCEDURE PROC_GETYOIL (
	p_year IN NUMBER, 
	p_month IN NUMBER,
	p_day IN NUMBER,
	p_yoil OUT varchar2)
IS 
BEGIN 
	SYSDATE 
	DBMS_OUTPUT.PUT_LINE('오늘날짜: '||p_yoil);
END;

DECLARE 
	v_year NUMBER;
	v_month NUMBER;
	v_day NUMBER;
	v_yoil varchar2(10);
BEGIN
	PROC_GETYOIL(2023,12,14,v_yoil);
END;

SELECT TO_DATE(2012) 
FROM dual;


-- 6. 함수명 : FUNC_COUNTEMP
--   기능 : DEPARTMENT_ID를 입력하면 해당 부서의 직원의 수를 출력

CREATE OR REPLACE FUNCTION FUNC_COUNTEMP (
	f_did EMPLOYEES.DEPARTMENT_ID%TYPE)
RETURN NUMBER
IS 
	f_ecount NUMBER;
BEGIN 
	SELECT count(e.EMPLOYEE_ID)
	INTO f_ecount
	FROM EMPLOYEES e
	WHERE e.DEPARTMENT_ID = f_did;
	RETURN f_ecount;
	DBMS_OUTPUT.PUT_LINE('직원수: '||f_ecount);
END;

SELECT FUNC_COUNTEMP(90)
FROM dual;


-- 7. 함수명 : FUNC_MAXMINSALARY
--   기능 : EMPLOYEE_ID 100을 입력하면 해당 직원이 소속된 부서내
--           최고급여자이름, 최고급여, 최저급여자이름, 최저급여를 출력
--           (단, 이름은 FIRST_NAME||' '||LAST_NAME)


CREATE OR REPLACE FUNCTION TEST (
	f_did EMPLOYEES.EMPLOYEE_ID%TYPE)
RETURN varchar2
IS 
	f_ecount varchar2(1000);
	f_salary varchar2(1000);
BEGIN 
	SELECT (e.FIRST_NAME||' '||e.LAST_NAME), e.SALARY
	INTO f_ecount, f_salary
FROM EMPLOYEES e
WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
							FROM EMPLOYEES e
							WHERE e.EMPLOYEE_ID = f_did)
	AND e.SALARY = (SELECT max(e.SALARY)
						FROM EMPLOYEES e 
						WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
													FROM EMPLOYEES e
													WHERE e.EMPLOYEE_ID = f_did))
	WHERE e.DEPARTMENT_ID = f_did;
	RETURN f_ecount, f_salary;
	DBMS_OUTPUT.PUT_LINE('직원수: '||f_ecount||' - '||f_salary);
END;

SELECT TEST(105)
FROM dual;

--2.
SELECT (e.FIRST_NAME||' '||e.LAST_NAME), e.SALARY
FROM EMPLOYEES e
WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
							FROM EMPLOYEES e
							WHERE e.EMPLOYEE_ID = 105)
	AND (e.SALARY = (SELECT max(e.SALARY)
						FROM EMPLOYEES e 
						WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
													FROM EMPLOYEES e
													WHERE e.EMPLOYEE_ID = 105))
			OR e.SALARY = (SELECT min(e.SALARY)
							FROM EMPLOYEES e 
							WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
														FROM EMPLOYEES e
														WHERE e.EMPLOYEE_ID = 105)));
								
SELECT e.EMPLOYEE_ID, max(e.SALARY), min(e.SALARY) 
FROM EMPLOYEES e 
WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
							FROM EMPLOYEES e
							WHERE e.EMPLOYEE_ID = 105)
GROUP BY e.EMPLOYEE_ID;
HAVING e.SALARY = max(e.SALARY);


--1.
SELECT e.DEPARTMENT_ID
FROM EMPLOYEES e
WHERE e.EMPLOYEE_ID = 100; eid100t
---------------------------------------------------
CREATE OR REPLACE FUNCTION FUNC_MAXMINSALARY (
	f_eid EMPLOYEES.EMPLOYEE_ID%TYPE)
RETURN varchar2
IS 
	f_char_set varchar2(100);
	f_char_set2 varchar2(100);
BEGIN 
	SELECT (e.FIRST_NAME||' '||e.LAST_NAME), e.SALARY
	INTO f_char_set, f_char_set2
	FROM EMPLOYEES e
	WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
								FROM EMPLOYEES e
								WHERE e.EMPLOYEE_ID = f_eid)
		AND (e.SALARY = (SELECT max(e.SALARY)
							FROM EMPLOYEES e 
							WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
														FROM EMPLOYEES e
														WHERE e.EMPLOYEE_ID = f_eid))
			OR e.SALARY = (SELECT min(e.SALARY)
							FROM EMPLOYEES e 
							WHERE e.DEPARTMENT_ID = (SELECT e.DEPARTMENT_ID
														FROM EMPLOYEES e)));
	RETURN f_char_set, f_char_set2;
	DBMS_OUTPUT.PUT_LINE(f_char_set);
	DBMS_OUTPUT.PUT_LINE(f_char_set2);
END;

SELECT FUNC_MAXMINSALARY(100)
FROM dual;

-- 8. 함수명 : FUNC_GETGRADE
--   기능 : 점수(0~100)를 입력하면 100~80 A, 79~60 B, 59~40 C, 나머지 F를 출력

CREATE OR REPLACE FUNCTION FUNC_GETGRADE (f_score NUMBER)
RETURN varchar2
IS 
	f_grade varchar2(100);
BEGIN 
	CASE WHEN f_score>100 THEN f_grade := '점수를 다시 입력해주세요'; 
		WHEN f_score>=80 THEN f_grade := 'A'; 
		WHEN f_score>=60 THEN f_grade := 'B'; 
		WHEN f_score>=40 THEN f_grade := 'C'; 
		WHEN f_score>=0 THEN f_grade := 'F'; 

		ELSE f_grade := '점수를 다시 입력해주세요';
	END CASE;
	RETURN f_grade;
	DBMS_OUTPUT.PUT_LINE(f_grade);
END;

SELECT FUNC_GETGRADE(-10) FROM dual;
