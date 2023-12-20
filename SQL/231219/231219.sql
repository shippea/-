/*
	레코드 (RECORD)
- 여러 타입을 묶은 하나의 타입 
- 일반적으로 테이블의 하나의 행의 모든 컬럼의 타입을 묶은 타입
- 커서형, 사용자정의형, 테이블형
- 레코드타입 선언법
	TYPE 레코드타입명 IS RECORD (			// 1
		필드명 필드타입,
		필드명 필드타입,
		...
	);
	
*/

-- 1. 사용자정의형 레코드: type을 임의로 지정하는 레코드
DECLARE
	-- 임의로 지정한 변수들의 모든 타입을 하나로 묶어줌
	TYPE TYPE_REC_DEPT IS RECORD(					-- 1 // 변수, 변수타입 설정
		DEPARTMENT_ID DEPARTMENTS.DEPARTMENT_ID%TYPE,
		DEPARTMENT_NAME DEPARTMENTS.DEPARTMENT_NAME%TYPE,
		MANAGER_ID DEPARTMENTS.MANAGER_ID%TYPE,
		LOCATION_ID DEPARTMENTS.LOCATION_ID%TYPE
	);
	-- 위에 선언한 타입을 가지는 레코드변수를 만듬
	REC_DEPT TYPE_REC_DEPT;							-- 2 // 레코드변수 생성
BEGIN
	-- 레코드변수의 변수들 값을 설정				 			-- 3 // 레코드의 변수 값 설정
	REC_DEPT.DEPARTMENT_ID := 300;
	REC_DEPT.DEPARTMENT_NAME := 'FOREIGN SALES';
	REC_DEPT.MANAGER_ID := NULL;
	REC_DEPT.LOCATION_ID := 1700;
	-- 레코드와 테이블의 column 개수, 타입, 순서가 다 일치하면 레코드 변수 자체를 넣을 수 있음
	INSERT INTO DEPARTMENTS VALUES REC_DEPT;		-- 4 // 테이블에 레코드 값 넣기
END;

-- 2. 테이블형 레코드: 테이블의 모든 타입을 자동으로 받아오는 레코드
DECLARE 
	-- department 테이블의 한 행에 대응하는 모든 타입을 하나로 묶어줌
	REC_DEPT DEPARTMENTS%ROWTYPE;
BEGIN
	REC_DEPT.DEPARTMENT_ID := 400;
	REC_DEPT.DEPARTMENT_NAME := 'FOREIGN SALES';
	REC_DEPT.MANAGER_ID := NULL;
	REC_DEPT.LOCATION_ID := 1700;
	INSERT INTO DEPARTMENTS VALUES REC_DEPT;
END;

-- 3. 커서형 레코드: 커서로부터 받아온 타입들을 가지는 레코드
DECLARE
	CURSOR CUR_DEPT IS(
	SELECT * FROM DEPARTMENTS);
	REC_DEPT CUR_DEPT%ROWTYPE;
BEGIN
	REC_DEPT.DEPARTMENT_ID := 500;
	REC_DEPT.DEPARTMENT_NAME := 'JAPANESE SALES';
	REC_DEPT.MANAGER_ID := NULL;
	REC_DEPT.LOCATION_ID := 1700;
	INSERT INTO DEPARTMENTS VALUES REC_DEPT;
END;

-- 4. 한 행 자체를 update하기
DECLARE
	REC_DEPT DEPARTMENTS%ROWTYPE;
BEGIN
	REC_DEPT.DEPARTMENT_ID := 600;
	REC_DEPT.DEPARTMENT_NAME := 'AMERICAN SALES';
	REC_DEPT.MANAGER_ID := NULL;
	REC_DEPT.LOCATION_ID := 1700;
	UPDATE DEPARTMENTS
	-- 테이블의 모든 타입을 rowtype해서 받아온 레코드는
	-- 테이블의 한 행을 통째로 바꿀 수 있음
	SET ROW = REC_DEPT
	WHERE DEPARTMENT_ID IN (500);
END;

-- 5. 중첩레코드: 레코드 내에 레코드가 있는 형태
DECLARE
	TYPE TYPE_REC_DEPT IS RECORD(
		DEPARTMENT_ID DEPARTMENTS.DEPARTMENT_ID%TYPE
	);
	TYPE TYPE_REC_EMP IS RECORD(
		EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE,
		-- 위에 선언한 타입을 가지는 레코드변수 (중첩레코드 상태)
		DEPT_REC TYPE_REC_DEPT
	);
	REC_EMP TYPE_REC_EMP;
BEGIN
	REC_EMP.EMPLOYEE_ID := 100;
	REC_EMP.DEPT_REC.DEPARTMENT_ID := 60;
	DBMS_OUTPUT.PUT_LINE(REC_EMP.EMPLOYEE_ID||' '||REC_EMP.DEPT_REC.DEPARTMENT_ID);
END;


-- 레코드 실습

/*
 	 # 실습1.
	JOBS 태이블의 모든 컬럼을 가지는 사용자정의 레코드를 만들어서 모든 행을 출력
	1) 사용자정의 레코드
	2) 테이블레코드
*/

SELECT *
FROM JOBS;

-- 1) 사용자정의 레코드
DECLARE
	TYPE USER_RECORD IS RECORD(
		j_id JOBS.JOB_ID%TYPE,
		j_title JOBS.JOB_TITLE%TYPE,
		min_sal JOBS.MIN_SALARY%TYPE,
		max_sal JOBS.MAX_SALARY%TYPE
	);
	JOB_REC USER_RECORD;
	CURSOR JOB_CUR IS
	SELECT * FROM JOBS;
BEGIN
	OPEN JOB_CUR;
	LOOP
		FETCH JOB_CUR INTO JOB_REC.j_id, JOB_REC.j_title, 
							JOB_REC.min_sal, JOB_REC.max_sal;
		EXIT WHEN JOB_CUR%NOTFOUND;
		DBMS_OUTPUT.PUT_LINE(JOB_REC.j_id||', '||
							JOB_REC.j_title||', '||
							JOB_REC.min_sal||', '||
							JOB_REC.max_sal);
	END LOOP;
	CLOSE JOB_CUR;
END;


-- 2) 테이블레코드

DECLARE
--	JOB_REC JOBS%ROWTYPE;
	CURSOR JOB_CUR IS
	SELECT * FROM JOBS;
BEGIN
	-- for에 record 사용 시 타입을 지정안해도 in의 cursor 타입들 받아옴 
	FOR JOB_REC IN JOB_CUR()
	LOOP
		DBMS_OUTPUT.PUT_LINE(JOB_REC.JOB_ID||', '||
							JOB_REC.JOB_TITLE||', '||
							JOB_REC.MIN_SALARY||', '||
							JOB_REC.MAX_SALARY);
	END LOOP;
END;


/*
	 # 실습2
	부서 ID가 50인 직원들의 부서ID, 부서명, 직원 ID, 직원풀네임을 출력
	1) 사용자 정의 레코드
	2) 커서형 레코드
*/

SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) 
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
	AND d.DEPARTMENT_ID = 50;
	
-- 1) 사용자정의 레코드 (중첩레코드)
DECLARE
	TYPE USER_DEPT_RECORD IS RECORD(
		DEPARTMENT_ID DEPARTMENTS.DEPARTMENT_ID%TYPE,
		DEPARTMENT_NAME DEPARTMENTS.DEPARTMENT_NAME%TYPE
	);
	DEPT_REC USER_DEPT_RECORD;
	TYPE USER_EMP_REOCRD IS RECORD(
		EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE,
		EMPLOYEE_NAME VARCHAR2(100),
		DEPT_REC USER_DEPT_RECORD
	);
	EMP_REC USER_EMP_REOCRD; 
	CURSOR CUR_DATA
	IS
	SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) 
	FROM EMPLOYEES e, DEPARTMENTS d
	WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
		AND d.DEPARTMENT_ID = 50;
BEGIN
	OPEN CUR_DATA;
	LOOP
		FETCH CUR_DATA INTO EMP_REC.DEPT_REC.DEPARTMENT_ID, EMP_REC.DEPT_REC.DEPARTMENT_NAME, 
							EMP_REC.EMPLOYEE_ID, EMP_REC.EMPLOYEE_NAME;
		EXIT WHEN CUR_DATA%NOTFOUND;
		DBMS_OUTPUT.PUT_LINE(EMP_REC.DEPT_REC.DEPARTMENT_ID||', '||
							EMP_REC.DEPT_REC.DEPARTMENT_NAME||', '||
							EMP_REC.EMPLOYEE_ID||', '||
							EMP_REC.EMPLOYEE_NAME);	
	END LOOP;
	CLOSE CUR_DATA;
END;


-- 2) 사용자정의 레코드 (단일레코드)
DECLARE
	TYPE TYPE_USER_CUR IS RECORD(
		DEPARTMENT_ID DEPARTMENTS.DEPARTMENT_ID%TYPE,
		DEPARTMENT_NAME DEPARTMENTS.DEPARTMENT_NAME%TYPE,
		EMPLOYEE_ID EMPLOYEES.EMPLOYEE_ID%TYPE,
		EMPLOYEE_NAME VARCHAR2(100)
	);
	USER_CUR TYPE_USER_CUR;
	CURSOR CUR_DATA
	IS
	SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) 
	FROM EMPLOYEES e, DEPARTMENTS d
	WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
		AND d.DEPARTMENT_ID = 50;
BEGIN
	OPEN CUR_DATA;
	LOOP
		FETCH CUR_DATA INTO USER_CUR.DEPARTMENT_ID, USER_CUR.DEPARTMENT_NAME, 
							USER_CUR.EMPLOYEE_ID, USER_CUR.EMPLOYEE_NAME;
		EXIT WHEN CUR_DATA%NOTFOUND;
		DBMS_OUTPUT.PUT_LINE(USER_CUR.DEPARTMENT_ID||', '||
							USER_CUR.DEPARTMENT_NAME||', '||
							USER_CUR.EMPLOYEE_ID||', '||
							USER_CUR.EMPLOYEE_NAME);
	END LOOP;
	CLOSE CUR_DATA;
END;


-- 3) 커서형 레코드
DECLARE 
	CURSOR CUR_DATA (C_DEPT_ID DEPARTMENTS.DEPARTMENT_ID%TYPE) 
	IS
	SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) EMP_NAME
	FROM EMPLOYEES e, DEPARTMENTS d
	WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
		AND d.DEPARTMENT_ID = C_DEPT_ID;
	DATA_REC CUR_DATA%ROWTYPE;
BEGIN
	-- data를 받아올 cursor의 column type들을 자동으로 받아옴 -> record의 type을 따로 명시하지 않아도 됨
	FOR DATA_REC IN CUR_DATA(50)
	LOOP
		DBMS_OUTPUT.PUT_LINE(DATA_REC.DEPARTMENT_ID||', '||
							DATA_REC.DEPARTMENT_NAME||', '||
							DATA_REC.EMPLOYEE_ID||', '||
							DATA_REC.EMP_NAME);
	END LOOP;
END;



/*
	컬렉션 (COLLECTION)
- 여러 레코드들의 모음
- 연관배열 (ASSOCIATIVE ARRAY), VARRAY, 중첩테이블
- 컬렉션 타입에 따라 특성들이 조금씩 다름

*/

--	 1. 연관배열
-- 키: 문자형 또는 PLS_INTEGER
-- 크기를 제한하지 x
-- 생성자를 사용하지 x (:=으로 값 대입)
-- TYPE (컬렉션 변수명) IS TABLE OF (반환결과타입) INDEX BY (키 타입)

DECLARE
	TYPE TYPE_AA1 IS TABLE OF VARCHAR2(100) INDEX BY PLS_INTEGER;
	V_AA1 TYPE_AA1;
	TYPE TYPE_AA2 IS TABLE OF VARCHAR2(100) INDEX BY VARCHAR2(100);
	V_AA2 TYPE_AA2;
BEGIN
	-- java: index가 0부터 시작, []에 넣어서 사용
	-- 데이터베이스: index가 1부터 시작, ()에 넣어서 사용
	V_AA1(1) := '값1';
	V_AA1(2) := '값2';
	DBMS_OUTPUT.PUT_LINE(V_AA1(1));
	DBMS_OUTPUT.PUT_LINE(V_AA1(2));
	V_AA2('이름') := '홍길동';
	V_AA2('이메일') := 'HONG@HONG.COM';
	DBMS_OUTPUT.PUT_LINE(V_AA2('이름'));
	DBMS_OUTPUT.PUT_LINE(V_AA2('이메일'));
END;


--	 2. VARRAY (java의 배열과 가장 비슷함)
-- 정수 index만 가능
-- 크기를 정해놓고 사용
-- 초기화 해야 사용할 수 있음
-- 생성자 사용
-- TYPE (컬렉션 변수명) IS VARRAY(크기) OF (반환결과타입) 
-- (중요!) 테이블 컬럼의 타입으로 사용가능 ****** 

DECLARE 
	-- VARRAY 크기는 선언부에서 type 선언시에만 지정 가능하므로 프로시져 변수를 넣어줄 수 없음
	--	→ 프로시져 변수는 실행부에서만 불러올 수 있기 때문에 변수를 못받음 넉넉한 크기로 임의지정 할 수 밖에 없음
	TYPE TYPE_VA IS varray(5) OF varchar2(20);
	V_VA TYPE_VA;
	V_CNT NUMBER := 0;
BEGIN
	-- 생성자를 통해서 VARRAY 생성
	V_VA := TYPE_VA('가','나','다','라','마');
	LOOP
		-- 데이터베이스 index는 1부터 시작이므로
		V_CNT := V_CNT+1;
		IF V_CNT>5 THEN 
			EXIT;
		END IF;
		DBMS_OUTPUT.PUT_LINE(V_VA(V_CNT));
	END LOOP;
END;

-- 	3. 중첩 테이블 (NESTED TABLE)
-- 정수 index만 가능
-- 크기에 제한이 x
-- 생성자 사용
-- 다른 컬렉션에 비해 활용도가 높아 가장 많이 쓰임
-- (중요!) 테이블 컬럼의 타입으로 사용가능 ******
-- TYPE (컬렉션 변수명) IS TABLE OF (반환결과타입)
	
DECLARE 
	TYPE TYPE_TBL IS TABLE OF VARCHAR2(20);
	V_TBL TYPE_TBL;
	V_CNT NUMBER := 0;
BEGIN
	V_TBL := TYPE_TBL('홍길동', '유관순','이순신');
	V_TBL.EXTEND;
	V_TBL(4) := '장보고';
	V_CNT := V_TBL.count;
	FOR I IN 1..V_CNT
	LOOP
		DBMS_OUTPUT.PUT_LINE(V_TBL(I));
	END LOOP;
END;


/*
	컬렉션 메소드
- DELETE: 요소 제거
		(VARRAY에서는 요소 전체만 제거 가능 -> 중간 요소만 제거 불가능)
- TRIM: 마지막 요소 제거
- EXTEND: 마지막 요소 뒤에 요소를 추가할 공간 확보
		(VARRAY는 type선언에서 정한 길이를 넘는 extend 할 수 없음)
- EXIST: 요소가 존재하면 TRUE
- FIRST: 첫 번째 요소의 index
- LAST: 마지막 요소의 index
- COUNT: 요소의 수
- LIMIT: 허용가능한 요소의 최대 수
		(크기를 지정하는 ARRAYS만 값 존재, 나머지는 NULL)
- PRIOR: 이전 index
- NEXT: 다음 index 

*/

DECLARE
	-- 연관배열
	TYPE TYPE_AA IS TABLE OF VARCHAR2(20) INDEX BY PLS_INTEGER;
	V_AA TYPE_AA;
	-- VARRAY
	TYPE TYPE_VA IS VARRAY(5) OF VARCHAR2(20);
	V_VA TYPE_VA;
	-- 중첩테이블
	TYPE TYPE_TBL IS TABLE OF VARCHAR2(20);
	V_TBL TYPE_TBL;
	-- 카운트 변수
	V_CNT NUMBER := 0;
BEGIN
	V_AA(1) := 'A';
	V_AA(2) := 'B';
	V_AA(3) := 'C';
	V_AA(4) := 'D';
	V_AA(5) := 'E';
	V_AA.DELETE(5);
	V_CNT := V_AA.COUNT;
	FOR I IN V_AA.FIRST..V_CNT
	LOOP
		DBMS_OUTPUT.PUT_LINE(V_AA(I));	
	END LOOP;
	V_VA := TYPE_VA('한국','일본','미국','프랑스','독일');
	V_TBL := TYPE_TBL('가','나','다','라','마');
	V_TBL.EXTEND;
	-- 중첩테이블은 생성자를 통해 값을 줄 때 index 길이가 정해져있어
	-- 		추가로 값을 넣기 위해서는 공간을 늘려주고 값을 넣어야 들어감
	V_TBL(6) := '바';
	V_CNT := V_TBL.COUNT;
	FOR I IN 1..V_CNT
	LOOP
		DBMS_OUTPUT.PUT_LINE(V_TBL(I));
	END LOOP;
	DBMS_OUTPUT.PUT_LINE(V_VA.PRIOR(V_VA.LAST));
	DBMS_OUTPUT.PUT_LINE(V_VA.NEXT(V_VA.FIRST));
END;

/*
사용자 정의 데이터 타입 (USER DEFINED DATA TYPE)
자주 사용하는 데이터 타입을 미리 정의해 놓고 사용
컬렉션 중 VARRAY, 중첩테이블, OBJECT 타입은 사용자 정의 데이터 타입으로 쓸 수 있음
	(연관배열은 생성자로 값을 못 정하기 떄문에 사용할 수 x)
타입별 선언법
	1. VARRAY: CREATE OR REPLACE TYPE 타입명 IS VARRAY(크기) OF 요소타입;
	2. 중첩테이블: CREATE OF REPLACE TYPE 타입명 IS TABLE OF 요소타입;
	3. OBJECT: CREATE OF REPLACE TYPE 타입명 IS OBJECT(
											멤버1 멤버1타입;
											멤버2 멤버2타입;
											생성자
											멤버프로시져,
											멤버함수,
											...
											);
*/

-- ARRAY 사용자정의 데이터타입 생성
CREATE OR REPLACE TYPE TYPE_VARRAY IS VARRAY(3) OF VARCHAR2(20);
-- 중첩테이블 사용자정의 데이터타입 생성
CREATE OR REPLACE TYPE TYPE_TABLE IS TABLE OF VARCHAR2(20);
-- OBJECT 사용자정의 데이터타입 생성
CREATE OR REPLACE TYPE TYPE_EMPOBJ IS OBJECT(
	EMP_ID NUMBER,
	EMP_NAME VARCHAR2(20),
	EMP_VA TYPE_VARRAY,
	EMP_TBL TYPE_TABLE
);

DECLARE 
	EMP TYPE_EMPOBJ;
BEGIN
	EMP := TYPE_EMPOBJ(
		1,
		'홍길동',
		TYPE_VARRAY('사과','딸기','복숭아'),
		TYPE_TABLE('이순신','강감찬','유관순')
	);
	DBMS_OUTPUT.PUT_LINE(EMP.EMP_ID);
	DBMS_OUTPUT.PUT_LINE(EMP.EMP_NAME);
	DBMS_OUTPUT.PUT_LINE(EMP.EMP_VA(1));
	DBMS_OUTPUT.PUT_LINE(EMP.EMP_TBL(1));
END;


-- 컬렉션 실습
/*
	 # 실습1. 연관배열
	EMPLOYEES 테이블의 직원 풀네임을 키로 하고 행을 값으로 하는
	연관배열을 만들어서 출력해보자

*/

SELECT (e.FIRST_NAME||' '||e.LAST_NAME), e.* 
FROM EMPLOYEES e
WHERE (e.FIRST_NAME||' '||e.LAST_NAME) = 'Ellen Abel';


DECLARE
	TYPE TYPE_EMP_AA IS TABLE OF EMPLOYEES%ROWTYPE INDEX BY VARCHAR2(100);
	EMP_AA TYPE_EMP_AA;
	CURSOR CUR_EMP_DATA IS
	SELECT *
	FROM EMPLOYEES e;
	V_EMP_NAME VARCHAR2(100);
BEGIN
	FOR REC_EMP IN CUR_EMP_DATA
	LOOP
		V_EMP_NAME := (REC_EMP.FIRST_NAME||' '||REC_EMP.LAST_NAME);
		EMP_AA(V_EMP_NAME) := REC_EMP;
		DBMS_OUTPUT.PUT_LINE(EMP_AA(V_EMP_NAME).EMPLOYEE_ID);
	END LOOP;
END;

-- 선생님 코드
DECLARE
	-- TYPE_AA라는 타입을 선언, TYPE_AA는 연관배열
	-- 연관배열은 키(PLS_INTEGER 또는 문자타입),값
	-- 키 : VARCHAR2(50)
    -- 값 : EMPLOYEES%ROWTYPE (즉, EMPLOYEES테이블의 한 행이 값)
	TYPE TYPE_AA IS TABLE OF EMPLOYEES%ROWTYPE
		INDEX BY VARCHAR2(50);
	-- 만들어진 연관배열타입의 변수
	EMP_AA TYPE_AA;
	-- 각 행들을 반복시키기 위한 커서
	CURSOR CUR_EMP IS (SELECT * FROM EMPLOYEES);
	-- 풀네임 문자열을 저장할 변수 선언
	FULLNAME VARCHAR2(50);
BEGIN
	-- 커서에서 한 행씩 인출(FETCH)해서 한 행의 데이터를 EACHROW변수에 저장
	-- EACHROW는 FOR의 인덱스변수이므로 명시적으로 선언하지 않아도 선언이 된다.
	FOR EACHROW IN CUR_EMP
	LOOP
		FULLNAME := EACHROW.FIRST_NAME||' '||EACHROW.LAST_NAME;
		-- 연관배열에 키/값을 저장
		EMP_AA(FULLNAME) := EACHROW;
		-- 출력
		DBMS_OUTPUT.PUT_LINE(
			EMP_AA(FULLNAME).EMPLOYEE_ID||' '||
			FULLNAME
		);
	END LOOP;
END;

/*
	 # 실습2. VARRAY
	모든 부서명을 갖는 VARRAY를 생성해서 출력해보자

*/


CREATE OR REPLACE PROCEDURE PROC_DEPT_CNT (
	P_CNT OUT NUMBER)
IS 
BEGIN 
	SELECT count(*)
	INTO P_CNT
	FROM DEPARTMENTS d;
END;


DECLARE 
	CURSOR CUR_DEPT_NAME IS
	SELECT d.DEPARTMENT_NAME
	FROM DEPARTMENTS d;
	TYPE TYPE_DEPT_VA IS VARRAY(100) OF DEPARTMENTS.DEPARTMENT_NAME%TYPE;
	DEPT_NAME_VA TYPE_DEPT_VA;
	V_NUM PLS_INTEGER := 1;
BEGIN
	-- ''로 1개를 미리 extend한 생성자를 넣어줌
	DEPT_NAME_VA := TYPE_DEPT_VA('');
	FOR REC_DEPT_NAME IN CUR_DEPT_NAME
	LOOP
		DEPT_NAME_VA(V_NUM) := REC_DEPT_NAME.DEPARTMENT_NAME;
		DBMS_OUTPUT.PUT_LINE(DEPT_NAME_VA(V_NUM));
		V_NUM := V_NUM+1;
		DEPT_NAME_VA.EXTEND;
	END LOOP;
END;

-- 선생님 코드
DECLARE
	-- VARRAY타입 TYPE_VA 선언
	TYPE TYPE_VA IS VARRAY(100)
		OF DEPARTMENTS.DEPARTMENT_NAME%TYPE;
	-- TYPE_VA타입의 변수 V_VA 선언
	V_VA TYPE_VA;
BEGIN
	-- 비어있는 VARRAY 생성
	V_VA := TYPE_VA();
	-- 익명커서에서 각 행을 EACHROW에 저장
	FOR EACHROW IN (SELECT * FROM DEPARTMENTS)
	LOOP
		-- VARRAY 공간 추가
		V_VA.EXTEND;
		-- 추가된 공간에 부서명 저장
		V_VA(V_VA.LAST) := EACHROW.DEPARTMENT_NAME;
		-- 출력
		DBMS_OUTPUT.PUT_LINE(V_VA(V_VA.LAST));
	END LOOP;
END;



/*
	 # 실습3. 중첩테이블
	모든 직원들의 정보를 갖는 중첩테이블을 생성해서
	직원 ID, 직원풀네임, 월급을 출력해보자

*/

DECLARE
	TYPE TYPE_EMP_TBL IS TABLE OF EMPLOYEES%ROWTYPE;
	V_EMP_TBL TYPE_EMP_TBL;
	V_NUM PLS_INTEGER := 1;
	CURSOR CUR_EMP IS
	SELECT *
	FROM EMPLOYEES e;
BEGIN
	-- VARRAY은 '' 필요, 중첩테이블은 ''없어도됨
	V_EMP_TBL := TYPE_EMP_TBL();
	FOR REC_EMP_DATA IN CUR_EMP
	LOOP
		V_EMP_TBL.EXTEND;
		V_EMP_TBL(V_NUM) := REC_EMP_DATA;
		V_NUM := V_NUM+1;
	END LOOP;
END;

-- 선생님 코드
DECLARE
	-- 중첩테이블 타입 TYPE_TBL 선언
	TYPE TYPE_TBL IS TABLE OF EMPLOYEES%ROWTYPE;
	-- TYPE_TBL타입의 변수 V_TBL 선언
	V_TBL TYPE_TBL;
BEGIN
	-- 비어있는 중첩테이블 생성 -> 배열이 0개이기 때문에 extend부터
	V_TBL := TYPE_TBL();
	-- 익명커서에서 한 행씩 EACHROW에 저장
	FOR EACHROW IN (SELECT * FROM EMPLOYEES)
	LOOP
		-- 중첩테이블에 공간하나 추가
		V_TBL.EXTEND;
		-- 추가된 공간에 각 행 저장
		V_TBL(V_TBL.LAST) := EACHROW;
		-- 출력
		DBMS_OUTPUT.PUT_LINE(
			V_TBL(V_TBL.LAST).EMPLOYEE_ID||' '||
			V_TBL(V_TBL.LAST).FIRST_NAME||' '||
			V_TBL(V_TBL.LAST).LAST_NAME||' '||
			V_TBL(V_TBL.LAST).SALARY
		);
	END LOOP;
END;
