/* count 합계 */

-- 전체 출력
SELECT count(*) FROM EMPLOYEES e;

-- ()안의 column만 count -> 107개 count
SELECT count(EMPLOYEE_ID) 
FROM EMPLOYEES e;

-- 106개 count -> null은 생략됨
SELECT count(DEPARTMENT_ID) 
FROM EMPLOYEES el;

------------------------------------------------
/* distinct: department_id가 같은 것은 하나로 카운트 */


-- 11개 count (null 미포함)
SELECT count(DISTINCT DEPARTMENT_ID) 
FROM EMPLOYEES e;

-- 12개 count (null포함)
SELECT DISTINCT (DEPARTMENT_ID) 
FROM EMPLOYEES e;

----------------------------------------
/* sum 합계, avg 평균, min 최소, max 최대 */


SELECT sum(SALARY), AVG(SALARY), MIN(SALARY), MAX(SALARY)  
FROM EMPLOYEES e;

--------------------------------------------------------------------
/* roll-up, CUBE, GROUPING SETS (grouping한 것의 소계, 전체합계 구할 때) */

CREATE TABLE 월별매출(
	상품id varchar2(5),
	월 varchar2(10),
	회사 varchar2(10),
	매출액 NUMBER
);

INSERT INTO 월별매출 VALUES ('P001','2019.10','삼성',15000);
INSERT INTO 월별매출 VALUES ('P001','2019.11','삼성',25000);
INSERT INTO 월별매출 VALUES ('P002','2019.10','LG',10000);
INSERT INTO 월별매출 VALUES ('P002','2019.11','LG',20000);
INSERT INTO 월별매출 VALUES ('P003','2019.10','애플',15000);
INSERT INTO 월별매출 VALUES ('P003','2019.11','애플',10000);

UPDATE 월별매출 SET 상품id = 'P003'
WHERE 회사 = '애플'
	AND 매출액 = 10000;

COMMIT; 

SELECT *
FROM 월별매출;

-- groupby: 1차적으로 조회된 행들을 그룹핑 (where 뒤, orderby 앞에 위치)
-- having: 그룹핑된 집합들에 대한 조건 (groupby절 뒤에 위치)

-- 집계함수()안에 있는 column만 결과를 나타내므로 select의 행 개수와 달라 error 발생
SELECT 상품id, sum(매출액) 
FROM 월별매출;

-- 집계함수()안에 없는 column은 group by에 넣어주어야 error 해결
SELECT 상품id, sum(매출액) 
FROM 월별매출
GROUP BY 상품id;

-- having: gropu by된 집합에서 다시 한 번 조건을 걸음
SELECT 상품id, sum(매출액) 
FROM 월별매출
GROUP BY 상품id
HAVING 상품id='P001';

-- roll-up: 처음 언급한 group 상품id를 기준으로 group에 속한 행들의 데이터, group의 소계, 전체합계 출력
SELECT 상품id, 월, sum(매출액) 
FROM 월별매출
GROUP BY ROLLUP(상품id,월);

-- 처음 언급한 group이 월이므로 위와 다르게 월group에 속한 행들의 데이터, 월별 합계 (group의 소계), 전체합계 출력
SELECT 상품id, 월, sum(매출액) 
FROM 월별매출
GROUP BY ROLLUP(월,상품id);

-- cube: roll-up보다 더 자세한 소계, 합계를 도출 (맨 앞에 언급한 group뿐만 아니라 뒤에 group의 소계도 출력)
SELECT 상품id, 월, sum(매출액) 
FROM 월별매출
GROUP BY CUBE(상품id,월);

-- grouping sets: 기준 column 각각에 대해 그룹핑하고 각각의 소계를 출력
SELECT 상품id, 월, sum(매출액) 
FROM 월별매출
GROUP BY GROUPING SETS(상품id,월);

-- 각 grouping 한 것에 이름을 만들어줌

SELECT
	CASE GROUPING(상품id) WHEN 1 THEN '모둔 상품id' ELSE 상품id END AS 상품id,
	CASE GROUPING(월) WHEN 1 THEN '모든 월' ELSE 월 END AS 월,
	sum(매출액) AS 매출액
FROM 월별매출
GROUP BY ROLLUP(상품id,월);


-- grouping sets에서 ()묶인 것을 하나로 인식, 정렬 우선순위가 먼저 주어짐
SELECT
	CASE GROUPING(상품id) WHEN 1 THEN '모둔 상품id' ELSE 상품id END AS 상품id,
	CASE GROUPING(월) WHEN 1 THEN '모든 월' ELSE 월 END AS 월,
	CASE GROUPING(회사) WHEN 1 THEN '모든 회사' ELSE 회사 END AS 회사,
	sum(매출액) AS 매출액
FROM 월별매출
GROUP BY GROUPING SETS(회사,(상품id,월));

----------------------------------------------------------------------------
/* 집합연산 UNION(합집합), UNION ALL(합집합+교집합), INTERSECT(교집합), MINUS(차집합)*/


CREATE TABLE a (num number);
CREATE TABLE b (num number);
INSERT INTO a values(10);
INSERT INTO a values(20);
INSERT INTO a values(30);
INSERT INTO b values(10);
INSERT INTO b values(20);
INSERT INTO b values(40);

SELECT * FROM a 
UNION 
SELECT * FROM b;	-- 10 20 30 40

SELECT * FROM a
UNION ALL
SELECT * FROM b;	-- 10 20 30 10 20 40

SELECT * FROM a
INTERSECT
SELECT * FROM b;	-- 10 20

SELECT * FROM a
MINUS 
SELECT * FROM b;	-- 30 (a-b의 값만 도출)


------------------------------------------------------------------
/* join: 논리적, 물리적으로 2개 이상의 테이블이나 서브쿼리를 연결하여 결과를 조회 */

-- equi join: 두 테이블에서 where 조건을 만족하는 같은 값을 갖는 컬럼을 기준으로 조인
SELECT a.EMPLOYEE_ID, a.EMP_NAME, a.DEPARTMENT_ID, b.DEPARTMENT_NAME 
FROM EMPLOYEES a, DEPARTMENTS b
WHERE a.DEPARTMENT_ID = b.DEPARTMENT_ID;

-- semi join: 서브쿼리의 결과에 해당하는 메인쿼리의 컬럼들을 조회
-- anti join: semi join의 반대 (exists > not exists, in > not in)

-- exists()안에 서브쿼리의 조건을 만족하는 컬럼들을 조회 (행단위)
SELECT DEPARTMENT_ID, DEPARTMENT_NAME
FROM DEPARTMENTS a
WHERE exists(
	SELECT *
	FROM EMPLOYEES b
	WHERE a.DEPARTMENT_ID = b.DEPARTMENT_ID
	AND 
	b.salary>3000
)
ORDER BY a.DEPARTMENT_NAME;

-- in은 exists와 달리 컬럼단위
SELECT DEPARTMENT_ID, DEPARTMENT_NAME
FROM DEPARTMENTS a
WHERE a.DEPARTMENT_ID IN (
	SELECT b.department_id
	FROM EMPLOYEES b
	WHERE b.salary>3000
)
ORDER BY a.DEPARTMENT_NAME;

-- self join: 물리적으로 하나의 테이블을 논리적으로 두개로 보고 join
SELECT *
FROM EMPLOYEES e1, EMPLOYEES e2
WHERE
	e1.MANAGER_ID = e2.EMPLOYEE_ID
	AND 
	e1.MANAGER_ID = 100; 

-- 위 self join select의 결과값과 같음
SELECT *
FROM EMPLOYEES e
WHERE e.MANAGER_ID = 100;

-- 외부join: 양쪽 또는 한 쪽에 매칭되는 데이터가 없어도 모두 조회
--			left outer join, right outer join, full outer join

-- a.num = b.num 인 것만 출력
SELECT *
FROM a, b
WHERE a.num = b.num;

-- right outer join: 오른쪽 +가 붙은 곳에 대응하는 값이 없어도 왼쪽은 다 나오도록
-- -> a.num = b.num과 b.num에 없고 a.num에만 있는 것도 출력
SELECT a.NUM anum, b.NUM bnum
FROM A, B 
WHERE a.NUM = b.NUM(+);

-- left outer join: 왼쪽 +가 붙은 곳에 대응하는 값이 없어도 오른쪽은 다 나오도록
-- -> a.num = b.num과 a.num에 없고 b.num에만 있는 것도 출력
SELECT a.NUM anum, b.NUM bnum
FROM A, B 
WHERE a.NUM(+) = b.NUM;

-- cartasian join: join 조건이 없는 join (각 table의 행 개수가 곱해짐)
SELECT count(*) FROM EMPLOYEES e;					-- 107
SELECT count(*) FROM DEPARTMENTS d;					-- 27
SELECT COUNT(*) FROM EMPLOYEES e, DEPARTMENTS d;	-- 2889 (107*27)


------------------------------------------------------------------
/* ANSI join: DBMS를 가리지 않는 문법 (모든 DB에서 공용으로 사용가능한 문법) */

-- inner join
SELECT *
FROM EMPLOYEES e
	INNER JOIN DEPARTMENTS d
	ON e.DEPARTMENT_ID  = d.DEPARTMENT_ID ;

-- outer join 
-- left outer join: WHERE a.NUM = b.NUM(+)와 동일
--				from table이 왼쪽 join table이 오른쪽 -> a,b 위치 바뀌면 결과도 반대
SELECT *
FROM A 
	LEFT OUTER JOIN B 
	ON a.NUM  = b.NUM;

-- right outer join: WHERE a.NUM(+) = b.NUM와 동일
SELECT *
FROM A 
	RIGHT OUTER JOIN B 
	ON a.num = b.NUM ;

-- full outer join: 겹치지 않는 각 table에 없는 값들까지 다 보여줌 (=합집합)
-- 	(WHERE a.NUM(+) = b.NUM(+)와 같은 느낌 but 양쪽다 +는 사용할 수 없는 문법
SELECT *
FROM A 
	FULL OUTER JOIN B 
	ON a.num = b.num;

-- cartasian join의 ANSI 문법
SELECT *
FROM EMPLOYEES 
CROSS JOIN DEPARTMENTS ;

--------------------------------------------------------------------
/* subquery: 쿼리문 내에 있는 쿼리 (select, from, where 어디에나 사용 가능) */

-- where에 subquery를 넣어준 경우
SELECT *
FROM EMPLOYEES e
WHERE e.DEPARTMENT_ID IN
	(SELECT DEPARTMENT_ID FROM DEPARTMENTS d);

-- select에 subquery를 넣어준 경우 (부서명이 마케팅인 행만 조회 -> 마케팅 마케팅...)
SELECT (
	SELECT department_name 
	FROM DEPARTMENTS d
	WHERE department_name='마케팅'
)
FROM EMPLOYEES e;

-- from에 subquery를 넣어준 경우
SELECT department_id "50이하의 부서아이디"
FROM (
	SELECT department_id
	FROM DEPARTMENTS 
	WHERE department_id<50
);


---------------------------------------
/* # 실습1. 미국에 거주하는 고객의 정보를 조회 */

-- 일반 join시 contry 정보까지 가져옴 -> 실습결과에 맞지 x
SELECT *
FROM CUSTOMERS cus, COUNTRIES con
WHERE cus.COUNTRY_ID  = con.COUNTRY_ID;

-- semi join을 사용해야 고객의 정보만 가져올 수 있음
SELECT *
FROM CUSTOMERS cus
WHERE cus.COUNTRY_ID IN
	(SELECT COUNTRY_ID FROM COUNTRIES con);

--------------------------------------------------------------------------
/* # 실습2. 서르비젼(country / country_subregion)별로 서브리젼명과 고객의 수를 조회 */

SELECT con.COUNTRY_SUBREGION "나라이름", count(cus.CUST_NAME) "고객 수"
FROM CUSTOMERS cus, COUNTRIES con
WHERE cus.COUNTRY_ID = con.COUNTRY_ID
GROUP BY con.COUNTRY_SUBREGION;


-------------------------------------
/* # 실습3. 서브리젼(country /country_subregion)별로 서브리젼명과 남자, 여자고객의 수를 조회 */

SELECT * FROM EMPLOYEES e;
SELECT * FROM DEPARTMENTS d;
SELECT * FROM COUNTRIES con;
SELECT * FROM CUSTOMERS cus;


-- from에 sub-query 사용 -> join을 어떤식으로 해줘야하나

SELECT COUNTRY_SUBREGION, count(COUNTRY_ID) "고객 수"
FROM (
	SELECT cus.COUNTRY_ID
	FROM CUSTOMERS cus, COUNTRIES con
	WHERE cus.COUNTRY_ID = con.COUNTRY_ID
		AND cus.CUST_GENDER ='M'
), COUNTRIES con;
--WHERE m.cus.COUNTRY_ID = con.COUNTRY_ID
GROUP BY COUNTRY_SUBREGION ;





( SELECT con.COUNTRY_SUBREGION
	FROM CUSTOMERS cus, COUNTRIES con
	WHERE cus.COUNTRY_ID = con.COUNTRY_ID);

FROM CUSTOMERS cus, COUNTRIES con
WHERE cus.COUNTRY_ID = con.COUNTRY_ID
GROUP BY (con.COUNTRY_SUBREGION, cus.CUST_GENDER );


GROUP BY ROLLUP (con.COUNTRY_SUBREGION, cus.CUST_GENDER);

SELECT con.COUNTRY_SUBREGION "나라이름", cus.CUST_GENDER "성별", count(*) "고객 수"
FROM CUSTOMERS cus, COUNTRIES con
WHERE cus.COUNTRY_ID = con.COUNTRY_ID
GROUP BY ROLLUP (con.COUNTRY_SUBREGION, cus.CUST_GENDER)
HAVING cus.CUST_GENDER ='M';


---------------------------------------------------------------------------------------
SELECT con.COUNTRY_SUBREGION "나라이름", cus.CUST_GENDER "성별", count(cus.CUST_ID) "고객 수"
FROM CUSTOMERS cus, COUNTRIES con, 
WHERE cus.COUNTRY_ID = con.COUNTRY_ID
	AND(cus.cust_ID IN (SELECT cus.CUST_ID FROM CUSTOMERS cus WHERE cus.CUST_GENDER  = 'M')
		)
GROUP BY con.COUNTRY_SUBREGION, cus.CUST_GENDER;
------------------------------------------------------------------------------------------
SELECT COUNT (
SELECT count(cus.CUST_ID)
FROM CUSTOMERS cus
WHERE cus.CUST_GENDER  = 'M')
FROM CUSTOMERS cus;

SELECT count(*)
FROM CUSTOMERS cus
WHERE cus.CUST_GENDER = 'F'


-- 완성 --
SELECT 
	CASE GROUPING(con.COUNTRY_REGION) WHEN 1 THEN '전체' ELSE con.COUNTRY_REGION END AS 나라이름, 
	CASE GROUPING(cus.CUST_GENDER) WHEN 1 THEN '총 합' ELSE cus.CUST_GENDER END AS 성별,
	count(cus.CUST_GENDER) AS "고객 수"
FROM CUSTOMERS cus, COUNTRIES con
WHERE cus.COUNTRY_ID = con.COUNTRY_ID
GROUP BY ROLLUP (con.COUNTRY_REGION, cus.CUST_GENDER);

count(*) "고객 수"
con.COUNTRY_SUBREGION "나라이름", 
cus.CUST_GENDER "성별", count(*) "고객 수"


/* # 실습4. JOB_ID가 SH_ClERK인 직원들의 평균 연봉보다 많이 받는 직원들의 직원아이디, 직원명 조회 */

SELECT e1.EMPLOYEE_ID, e1.EMP_NAME
FROM EMPLOYEES e1, EMPLOYEES e2
WHERE e1.EMPLOYEE_ID = e2.EMPLOYEE_ID
	AND e1.SALARY*12 > (SELECT avg(e2.SALARY*12) FROM EMPLOYEES e2 WHERE e2.JOB_ID = 'SH_CLERK');


/* # 실습5. 부서별로 최소연봉을 받는 직원과 최대연봉을 받는 직원의 직원아이디, 직원명, 연봉, 부서명 조회(salary는 월급여, 연봉=salary*12)*/

SELECT d.DEPARTMENT_NAME "부서명", e.EMPLOYEE_ID "사원 ID", e.EMP_NAME "사원이름", e.SALARY*12 "연봉"
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND ((e.DEPARTMENT_ID,e.SALARY*12) IN (SELECT e2.DEPARTMENT_ID, min(e2.SALARY*12) FROM EMPLOYEES e2 GROUP BY e2.department_id ) 
	OR (e.DEPARTMENT_ID,e.SALARY*12) IN (SELECT e2.DEPARTMENT_ID, max(e2.SALARY*12) FROM EMPLOYEES e2 GROUP BY e2.department_id )) 
ORDER BY d.DEPARTMENT_NAME;


/* # 실습6. 매니저가 없는 부서의 상위부서에 소속된 직원들 중 부서내 최고연봉을 받는 직원들의 부서명, 연봉을 조회 */
SELECT * FROM EMPLOYEES e;
SELECT * FROM DEPARTMENTS d;

--------------------------------------------------------
SELECT d.DEPARTMENT_NAME, e.EMP_NAME, e.SALARY*12
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND (e.DEPARTMENT_ID,e.SALARY*12) IN (
		SELECT e.DEPARTMENT_ID, max(e.SALARY*12) 
		FROM EMPLOYEES e 
		WHERE e.DEPARTMENT_ID IN (
			SELECT e.DEPARTMENT_ID 
			FROM EMPLOYEES e, DEPARTMENTS d
			WHERE e.DEPARTMENT_ID = d.PARENT_ID
			AND d.PARENT_ID IN (
				SELECT d.PARENT_ID 
				FROM DEPARTMENTS d
				WHERE d.MANAGER_ID IS NULL))
		GROUP BY e.DEPARTMENT_ID);
--------------------------------------------------------
		
		
	
	SELECT e.DEPARTMENT_ID 
	FROM EMPLOYEES e, DEPARTMENTS d
	WHERE e.DEPARTMENT_ID = d.PARENT_ID
		AND d.PARENT_ID IN (
			SELECT d.PARENT_ID 
			FROM DEPARTMENTS d
			WHERE d.MANAGER_ID IS NULL);
	)
	  
	
AND e.EMPLOYEE_ID  IN (SELECT DEPARTMENT_ID,);

	SELECT e.DEPARTMENT_ID 
	FROM EMPLOYEES e, DEPARTMENTS d
	WHERE e.DEPARTMENT_ID = d.PARENT_ID
		AND d.PARENT_ID IN (
			SELECT d.PARENT_ID 
			FROM DEPARTMENTS d
			WHERE d.MANAGER_ID IS NULL);
	

SELECT d.PARENT_ID 
FROM DEPARTMENTS d
WHERE d.MANAGER_ID IS NULL;

		
/* # 실습7. 모든 직원들의 부서명, 직원명, 직무명, 직무수행일자를 조회 */

SELECT * FROM EMPLOYEES e WHERE e.EMPLOYEE_ID ='101' ORDER BY e.EMP_NAME ;
SELECT * FROM DEPARTMENTS d;
SELECT * FROM JOBS j;
SELECT * FROM JOB_HISTORY jh WHERE jh.EMPLOYEE_ID ='200';

-- Neena Kochhar (101), Jonathon Taylor (176), Jennifer Whalen (200)
-- job_id가 변동된 애들임 -> 110개 나오는 이유
-- distinct 안먹힘

SELECT DISTINCT (e.EMP_NAME), d.DEPARTMENT_NAME, j.JOB_TITLE, jh.START_DATE 
FROM EMPLOYEES e, DEPARTMENTS d, JOBS j, JOB_HISTORY jh
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID (+)
	AND e.JOB_ID = j.JOB_ID (+)
	AND e.EMPLOYEE_ID = jh.EMPLOYEE_ID (+)
ORDER BY e.EMP_NAME ;

SELECT DISTINCT (e.EMPLOYEE_ID)
FROM EMPLOYEES e, JOB_HISTORY jh
WHERE e.EMPLOYEE_ID = jh.EMPLOYEE_ID (+);

SELECT e.EMP_NAME,  jh.START_DATE, e.EMPLOYEE_ID 
FROM EMPLOYEES e, JOB_HISTORY jh
WHERE e.EMPLOYEE_ID = jh.EMPLOYEE_ID (+)
ORDER BY e.EMP_NAME  DESC  ;


-- left 4번써보기