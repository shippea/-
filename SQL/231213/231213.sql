/* # 실습1. 직원의 풀네임(first_name+last_name)을 내림차순 정렬조회 */

SELECT e.FIRST_NAME||' '||e.LAST_NAME "풀네임"
FROM EMPLOYEES e
ORDER BY 풀네임 DESC;

SELECT concat(e.FIRST_NAME,concat(' ',e.LAST_NAME)) "풀네임"
FROM EMPLOYEES e
ORDER BY 풀네임 DESC;

/* # 실습2. 직원 중에서 2005년 이후 입사자의 수를 조회 */

SELECT count(e.EMPLOYEE_ID)
FROM EMPLOYEES e
WHERE e.HIRE_DATE >= '2005-01-01';


/* # 실습3. 연봉이 전체평균연봉보다 높은 직원의 모든 정보를 연봉이 많은 순으로 조회 (연봉=salary*12) */
-- avg 할때는 nvl로 null값을 제외해줘야됨

SELECT * 
FROM EMPLOYEES e
WHERE e.SALARY*12 >= (SELECT avg(nvl(e.SALARY*12,0)) FROM EMPLOYEES e )
ORDER BY e.SALARY*12 DESC; 

/* # 실습4. 모든 직원의 월급, 커미션, 월급+커미션을 월급+커미션이 많은 순으로 조회 */

SELECT e.SALARY, e.COMMISSION_PCT, e.SALARY*(1+e.COMMISSION_PCT)
FROM EMPLOYEES e
WHERE e.COMMISSION_PCT IS NOT NULL 
ORDER BY e.SALARY*(1+e.COMMISSION_PCT) DESC;

/* # 실습5. 부서별로 연봉을 가장 많이 받는 직원의 정보를 조회 */

SELECT d.DEPARTMENT_NAME, e.*
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND (d.DEPARTMENT_NAME, e.SALARY) IN (
		SELECT d.DEPARTMENT_NAME, max(e.SALARY)
		FROM EMPLOYEES e,DEPARTMENTS d
		WHERE e.DEPARTMENT_ID=d.DEPARTMENT_ID
		GROUP BY d.DEPARTMENT_NAME);

/* # 실습6. JOB_ID가 SA_REP인 직원들 중에서 입사일이 가장 빠른 직원의 정보를 조회 */

SELECT *
FROM EMPLOYEES e
WHERE e.JOB_ID = 'SA_REP'
	AND (e.JOB_ID, e.HIRE_DATE) IN (
		SELECT e.JOB_ID, min(e.HIRE_DATE)
		FROM EMPLOYEES e
		GROUP BY (e.JOB_ID));
	
/* # 실습7. MANAGER_ID가 149인 직원들의 직원아이디, 직원풀네임, 월급을 조회 */

SELECT e.EMPLOYEE_ID, e.FIRST_NAME||' '||e.LAST_NAME "EMP_NAME", e.SALARY 
FROM EMPLOYEES e
WHERE e.MANAGER_ID = '149';

/* # 실습8. JOB_ID가 S로 시작하는 직원 중 최저월급을 받는 직원의 정보를 조회 */

SELECT *
FROM EMPLOYEES e
WHERE e.JOB_ID LIKE 'S%'
	AND e.SALARY = (SELECT min(e.SALARY) FROM EMPLOYEES e);

/* # 실습9. FISRT_NAME이나 LAST_NAME이 5문자 이하인 직원의 정보를 조회 */

SELECT *
FROM EMPLOYEES e
WHERE LENGTH(e.FIRST_NAME ) <=5
	OR LENGTH (e.LAST_NAME) <=5;

/* # 실습10. 짝수년도에 고용된 직원 중에서 COMMINSION_PCT가 있는 직원의 정보를 조회 */

SELECT *
FROM EMPLOYEES e
WHERE MOD(TO_CHAR(e.HIRE_DATE,'yyyy'),2)=0
	AND e.COMMISSION_PCT IS NOT NULL;
			 
/* # 실습11. 월급이 10000이상이면 '고소득자', 10000미만 5000이상이면 '일반소득자' 5000미만이면 '저소득자'로 직원의 정보를 조회 */
	
-- 풀이
SELECT e.*,
	CASE WHEN e.SALARY >= 10000 THEN '고소득자'
		WHEN e.SALARY >= 5000 AND e.SALARY < 10000 THEN '일반소득자'
		WHEN e.SALARY < 5000 THEN '저소득자'
		ELSE '기초수급자'
	END "소득분류"
FROM EMPLOYEES e;

-- self
SELECT e1.*, e2.IncomeClass
FROM EMPLOYEES e1, (SELECT e.EMPLOYEE_ID,
	CASE WHEN e.SALARY >=10000 THEN '고소득자'
		WHEN e.SALARY >=5000 AND e.SALARY<10000 THEN '일반소득자'
		WHEN e.SALARY<5000 THEN '저소득자'
		END AS IncomeClass
FROM EMPLOYEES e) e2
WHERE e1.EMPLOYEE_ID = e2.EMPLOYEE_ID ;


/* # 실습12. 월급이 2000달러에서 3000달러 사이 (양쪽 다 포함)인 직원의 정보를 월급 많은 순으로 조회 */

SELECT *
FROM EMPLOYEES e
WHERE e.SALARY >= 2000
	AND e.SALARY <=3000
ORDER BY e.SALARY DESC ;

/* # 실습13. 매니져가 없는 부서의 부서명을 오름차순으로 조회 */

SELECT d.DEPARTMENT_NAME 
FROM DEPARTMENTS d
WHERE d.MANAGER_ID IS NULL
ORDER BY d.DEPARTMENT_NAME ASC;

/* # 실습14. 매니져의 아이디가 201~205인 부서의 부서명을 오름차순 조회 */

SELECT d.DEPARTMENT_NAME 
FROM DEPARTMENTS d
WHERE d.MANAGER_ID BETWEEN '201' AND '205'
ORDER BY d.DEPARTMENT_NAME ASC;

/* # 실습15. 부서명에 'a'가 포함되는 부서의 정보를 조회 */

SELECT *
FROM DEPARTMENTS d
WHERE d.DEPARTMENT_NAME LIKE '%a%';

/* # 실습16. 부서명이 'P'로 시작하고 's'로 끝나는 부서의 정보를 조회 */

SELECT *
FROM DEPARTMENTS d
WHERE d.DEPARTMENT_NAME LIKE 'P%s';

/* # 실습17. 직원들의 JOB_ID를 12자리에 맞춰 오른쪽으로 정렬해서 조회 */

SELECT LPAD(e.JOB_ID,12,' ')
FROM EMPLOYEES e;

/* # 실습18. 직원들의 JOB_ID를 AC는 ACC로 ST는 STT로 변경해서 조회 */

SELECT REPLACE(REPLACE(e.JOB_ID,'AC','ACC'),'ST','STT')
FROM EMPLOYEES e;

/* # 실습19. 직원들의 근무개월수, 근무주수, 근무일수를 조회 */

SELECT e.EMPLOYEE_ID, floor(MONTHS_BETWEEN(SYSDATE,e.HIRE_DATE)), 
						floor(MONTHS_BETWEEN(SYSDATE,e.HIRE_DATE)*(365/12/7)), 
							floor(MONTHS_BETWEEN(SYSDATE,e.HIRE_DATE)*(365/12))
FROM EMPLOYEES e;

/* # 실습20. 직원들의 직무시작일과 직무종료일을 '0000년 00월 00일 00시 00분 00초'로 조회 */
-- 
SELECT TO_DATE(jh.START_DATE), TO_DATE(jh.END_DATE)
FROM JOB_HISTORY jh;

/* # 실습21. 직원들의 직무시작일은 한달 전으로 직무종료일은 한달 후로 변경해 조회 */

SELECT ADD_MONTHS(TO_DATE(jh.START_DATE),-1), ADD_MONTHS(TO_DATE(jh.END_DATE),1)
FROM JOB_HISTORY jh;


/* # 실습22. 직원들의 직무종료일이 포함된 달의 마지막 일자를 조회 */

SELECT LAST_DAY(jh.END_DATE)
FROM JOB_HISTORY jh;

/* # 실습23. JOB_ID가  IT_PROG 또는 AC_ACCOUNT 또는 AC_MGR인 것에 대해
 		      IT_PROG이면 "정보부", AC_ACCOUNT이면 "회계부", AC_MGR이면 "관리부"로 조회 */

-- 풀이

SELECT decode (e.JOB_ID,
		'IT_PROG', '정보부', 
		'AC_ACCOUNT', '회계부',
		'AC_MGR', '관리부') "부서 이름"
FROM EMPLOYEES e;

-- self
SELECT j.JOB_TITLE, j.JOB_ID,
	CASE WHEN j.JOB_ID='IT_PROG' THEN '정보부'
		 WHEN j.JOB_ID='AC_ACCOUNT' THEN '회계부'
		 WHEN j.JOB_ID='AC_MGR' THEN '관리부'
	END AS 부서이름
FROM JOBS j;

/* # 실습24. 부서별로 소속직원의수, 직원의 월급합계를 조회 */

SELECT d.DEPARTMENT_ID, sum(e.EMPLOYEE_ID), sum(e.SALARY)
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY ( d.DEPARTMENT_ID);

/* # 실습25. 부서별로 부서아이디, 최대급여와 최소급여의 차를 조회 */

SELECT d.DEPARTMENT_NAME, d.DEPARTMENT_ID, max(e.SALARY)-MIN(e.SALARY) 
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
GROUP BY (d.DEPARTMENT_NAME, d.DEPARTMENT_ID);

/* # 실습26. 부서별로 직원의 수가 5이상인 부서의 부서아이디, 직원수를 조회 */
-- having에서는 집계함수 사용가능

SELECT d.DEPARTMENT_ID, count(e.EMPLOYEE_ID)
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY (d.DEPARTMENT_ID)
HAVING count(e.EMPLOYEE_ID)>=5;

/* # 실습27. 전체 직원의 평균월급보다 부서의 평균월급이 높은 부서의 부서아이디, 평균월급을 조회 */

-- 풀이

SELECT e.DEPARTMENT_ID, avg(nvl(e.SALARY,0))
FROM EMPLOYEES e
GROUP BY e.DEPARTMENT_ID 
HAVING avg(nvl(e.SALARY,0)) > (SELECT avg(nvl(e.SALARY,0)) FROM EMPLOYEES e);

-- 최종

SELECT e.DEPARTMENT_ID, avg(nvl(e.SALARY,0))
FROM EMPLOYEES e 
WHERE e.DEPARTMENT_ID IN (SELECT dep_avgt.did
							FROM 
								(SELECT avg(e.SALARY) emp_avg
								FROM EMPLOYEES e) emp_avgt,
								(SELECT d.DEPARTMENT_ID did, avg(e.SALARY) dep_avg
								FROM DEPARTMENTS d, EMPLOYEES e
								WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID
								GROUP BY d.DEPARTMENT_ID) dep_avgt
							WHERE dep_avgt.dep_avg > emp_avgt.emp_avg)	
GROUP BY e.DEPARTMENT_ID ; 

--3. 평균월급이 더 많은 부서의 id 조회
SELECT dep_avgt.did
FROM 
	(SELECT avg(e.SALARY) emp_avg
		FROM EMPLOYEES e) emp_avgt,
	(SELECT d.DEPARTMENT_ID did, avg(e.SALARY) dep_avg
		FROM DEPARTMENTS d, EMPLOYEES e
		WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID
		GROUP BY d.DEPARTMENT_ID) dep_avgt
WHERE dep_avgt.dep_avg > emp_avgt.emp_avg; more_avgt

--2. 부서별 평균월급
SELECT d.DEPARTMENT_ID did, avg(e.SALARY) dep_avg
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID; dep_avgt

--1. 전체 직원의 평균월급
SELECT avg(e.SALARY) emp_avg
FROM EMPLOYEES e; emp_avgt

/* # 실습28. 직무아이디, 직무별 소속직원의 수, 부서아이디, 부서별 소속직원의 수를 조회 */

SELECT e.JOB_ID, e.DEPARTMENT_ID, COUNT(e.EMPLOYEE_ID) 
FROM EMPLOYEES e
GROUP BY e.JOB_ID, e.DEPARTMENT_ID;


------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

/* # 실습1. 직원 아이디가 100인 직원과 같은 부서에 근무하는 
				 직원들의 직원아이디, 직원명, 부서아이디, 부서명을 조회 */

SELECT e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME", e.DEPARTMENT_ID, d.DEPARTMENT_NAME 
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND e.EMPLOYEE_ID = '100';
	
/* # 실습2. 부서명이 R로 시작하는 부서에 근무하는 직원들의
 		 			직원 아이디, 직원명, 부서아이디, 부서명을 조회 */

SELECT e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME", e.EMPLOYEE_ID, d.DEPARTMENT_NAME 
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
	AND d.DEPARTMENT_NAME LIKE 'R%';

/* # 실습3. 직무아이디가 IT_PROG인 직원들 중 최소월급을 받는 사람과
 				최대월급을 받는 사람의 직원아이디, 직원명, 부서명, 월급을 조회 */

SELECT *
FROM EMPLOYEES e
WHERE e.JOB_ID = 'IT_PROG'
	AND (e.SALARY = (SELECT max(e.SALARY)  FROM EMPLOYEES e WHERE e.JOB_ID = 'IT_PROG')
		OR e.SALARY = (SELECT min(e.SALARY)  FROM EMPLOYEES e WHERE e.JOB_ID = 'IT_PROG'));

/* # 실습4. 모든 직원의 직원아이디, 직원명, 부서명, 커미션이포함된월급을 조회
 							   (단, 커미션퍼센트가 NULL인 경우는 제외) */

SELECT e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME", d.DEPARTMENT_NAME, e.SALARY*(1+e.COMMISSION_PCT)
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND e.COMMISSION_PCT IS NOT NULL;
	
/* # 실습5. 커미션퍼센트가 NULL인 직원들이 근무하는 부서별로 부서아이디, 부서명, 부서직원들의 
	 			커미션이 포함된 월급의 합계를 조회 (커미션퍼센트를 null인 경우는 0.1로 함) */

SELECT d.DEPARTMENT_NAME, e.DEPARTMENT_ID, 
	sum(e.SALARY*(1+
	CASE WHEN e.COMMISSION_PCT IS NULL THEN 0.1 ELSE e.COMMISSION_PCT END)) "월급"
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID
	AND e.DEPARTMENT_ID IN (
		SELECT e.DEPARTMENT_ID 
		FROM EMPLOYEES e
		WHERE e.COMMISSION_PCT IS NULL)
GROUP BY d.DEPARTMENT_NAME, e.DEPARTMENT_ID;
	
/* # 실습6. 최대급여와 최소급여의 차가 가장 큰 직무를 수행하는 직원들의
 				직무아이디, 직무명, 직원아이디, 직원명을 조회 */
-- jobs tabled에서 max min salary 사용해보기!


--self2
SELECT j.JOB_ID, j.JOB_TITLE, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME"
FROM JOBS j, EMPLOYEES e
WHERE j.JOB_ID = e.JOB_ID 
	AND j.MAX_SALARY-j.MIN_SALARY = (SELECT max(j.MAX_SALARY-j.MIN_SALARY)
										FROM JOBS j);			

--1. 최대급여 - 최소급여 차가 가장 큰 값을 구하기
SELECT max(j.MAX_SALARY-j.MIN_SALARY)
FROM JOBS j;


-- self

SELECT e.JOB_ID, j.JOB_TITLE, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME"
FROM EMPLOYEES e, JOBS j
WHERE e.JOB_ID = j.JOB_ID 
	AND e.JOB_ID = (SELECT t1.jid
					FROM (SELECT e.JOB_ID jid, max(e.SALARY)-min(e.SALARY) 
							FROM EMPLOYEES e 
							GROUP BY e.JOB_ID
							ORDER BY max(e.SALARY)-min(e.SALARY) DESC) t1
					WHERE rownum=1);


--2. t2 차이가 최대인 값과 직무아이디구하기
SELECT t1.jid
FROM (SELECT e.JOB_ID jid, max(e.SALARY)-min(e.SALARY) AS gap FROM EMPLOYEES e GROUP BY e.JOB_ID ORDER BY max(e.SALARY)-min(e.SALARY) DESC) t1
WHERE rownum=1
GROUP BY t1.jid;

--1. t1 / jobid별로 최대월급-최소월급차이 구하기
SELECT j.JOB_ID, j.JOB_TITLE, e.EMPLOYEE_ID, e.FIRST_NAME, max(e.SALARY)-min(e.SALARY) AS gap 
FROM EMPLOYEES e, JOBS j
WHERE e.JOB_ID = j.JOB_ID 
GROUP BY ROLLUP (j.JOB_ID, j.JOB_TITLE, e.EMPLOYEE_ID, e.FIRST_NAME);

/* # 실습7. 7. 직무수행시간(END_DATE-START_DATE)이 가장 길었던 직무를 수행했던 부서에
					근무하는 직원들의 직무아이디, 직무명, 부서명, 직원아이디, 직원명을 조회 */

SELECT e.JOB_ID, j.JOB_TITLE, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME"
FROM JOB_HISTORY jh, JOBS j, DEPARTMENTS d, EMPLOYEES e 
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
	AND e.JOB_ID = j.JOB_ID
	AND jh.EMPLOYEE_ID = e.EMPLOYEE_ID 
	AND MONTHS_BETWEEN(jh.END_DATE, jh.START_DATE) 
		= (SELECT max(MONTHS_BETWEEN(jh.END_DATE,jh.START_DATE))
			FROM JOB_HISTORY jh) ;

/* # 실습8. 시애틀(Seattle)에 있는 부서에 근무하는 모든 직원들의
								부서아이디, 부서명, 직원아이디, 직원명을 조회 */

SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME"
FROM EMPLOYEES e, DEPARTMENTS d
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID 
	AND d.DEPARTMENT_ID IN (SELECT d.DEPARTMENT_ID
								FROM DEPARTMENTS d, LOCATIONS l 
								WHERE d.LOCATION_ID = l.LOCATION_ID 
									AND l.CITY = 'Seattle');	
								
-- 1. 시애틀에 있는 부서의 아이디 조회
SELECT d.DEPARTMENT_ID
FROM DEPARTMENTS d, LOCATIONS l 
WHERE d.LOCATION_ID = l.LOCATION_ID 
	AND l.CITY = 'Seattle';
	
/* # 실습9. 유럽(Europe)에 있는 도시들에 있는 모든 부서에 근무하는 직원들의
							도시명, 부서아이디, 부서명, 직원아이디, 직원명을 조회 */

SELECT l.CITY, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, e.EMPLOYEE_ID, (e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME"
FROM LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
WHERE l.LOCATION_ID = d.LOCATION_ID 
	AND e.DEPARTMENT_ID = d.DEPARTMENT_ID 
	AND l.CITY IN (SELECT l.CITY 
					FROM LOCATIONS l, COUNTRIES c, REGIONS r
					WHERE r.REGION_ID = c.REGION_ID 
						AND c.COUNTRY_ID = l.COUNTRY_ID 	
						AND r.REGION_NAME = 'Europe');	

--1. location, countrie, region 조인으로 유럽의 도시 조회
SELECT l.CITY 
FROM LOCATIONS l, COUNTRIES c, REGIONS r
WHERE r.REGION_ID = c.REGION_ID 
	AND c.COUNTRY_ID = l.COUNTRY_ID 	
	AND r.REGION_NAME = 'Europe'; eu_city
	
/* # 실습10. 아시아(Asia)에 위치하고 있는 부서들 중 직원수가 가장 많은 부서의
									도시명, 부서아이디, 부서명, 직원수를 조회 */
-- rownum으로 한번 다시 풀어보기! - where절에서 사용
-- 집계함수 having에 넣기 (실습26 참고)
	
-- self2
SELECT *
FROM (SELECT e.DEPARTMENT_ID, l.CITY, d.DEPARTMENT_NAME, count(e.EMPLOYEE_ID) 
		FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
		WHERE r.REGION_NAME = 'Americas'
			AND r.REGION_ID = c.REGION_ID 
			AND c.COUNTRY_ID = l.COUNTRY_ID 
			AND l.LOCATION_ID = d.LOCATION_ID
			AND d.DEPARTMENT_ID = e.DEPARTMENT_ID
		GROUP BY e.DEPARTMENT_ID, l.CITY, d.DEPARTMENT_NAME
		ORDER BY count(e.EMPLOYEE_ID) desc) 
WHERE rownum=1; 
	
-- self
SELECT l.CITY, d.DEPARTMENT_ID, d.DEPARTMENT_NAME, count(e.EMPLOYEE_ID)
FROM LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
WHERE l.LOCATION_ID = d.LOCATION_ID 
	AND d.DEPARTMENT_ID = e.DEPARTMENT_ID 
	AND d.DEPARTMENT_ID IN (SELECT as_didt.did
							FROM 
								(SELECT numt.did did
									FROM 
										(SELECT max(numt.emp_num) max_num
											FROM 
												(SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
													FROM EMPLOYEES e, DEPARTMENTS d
													WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
													GROUP BY d.DEPARTMENT_ID) numt)	maxt,
												(SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
													FROM EMPLOYEES e, DEPARTMENTS d
													WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
													GROUP BY d.DEPARTMENT_ID) numt
											WHERE numt.emp_num = maxt.max_num) max_didt,
								(SELECT d.DEPARTMENT_ID did
									FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d
									WHERE r.REGION_NAME = 'Americas'
										AND r.REGION_ID = c.REGION_ID 
										AND c.COUNTRY_ID = l.COUNTRY_ID 
										AND l.LOCATION_ID = d.LOCATION_ID) as_didt
							WHERE max_didt.did = as_didt.did)
							GROUP BY l.CITY, d.DEPARTMENT_ID, d.DEPARTMENT_NAME;

--5. 1 table에서 4table 조건을 만족하는 부서 id를 조회
SELECT as_didt.did
FROM 
	(SELECT numt.did did
		FROM 
			(SELECT max(numt.emp_num) max_num
				FROM (SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
						FROM EMPLOYEES e, DEPARTMENTS d
						WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
						GROUP BY d.DEPARTMENT_ID) numt)	maxt,
			(SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
				FROM EMPLOYEES e, DEPARTMENTS d
				WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
				GROUP BY d.DEPARTMENT_ID) numt
		WHERE numt.emp_num = maxt.max_num) max_didt,
	(SELECT d.DEPARTMENT_ID did
		FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d
		WHERE r.REGION_ID = c.REGION_ID 
			AND c.COUNTRY_ID = l.COUNTRY_ID 
			AND l.LOCATION_ID = d.LOCATION_ID) as_didt
WHERE max_didt.did = as_didt.did;


--4.직원수가 max인 값을 부서별 직원수값 table과 비교하여 max인 값을 가지는 부서id 조회
SELECT numt.did did
FROM 
	(SELECT max(numt.emp_num) max_num
		FROM (SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
				FROM EMPLOYEES e, DEPARTMENTS d
				WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
				GROUP BY d.DEPARTMENT_ID) numt)	maxt,
	(SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
		FROM EMPLOYEES e, DEPARTMENTS d
		WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 	
		GROUP BY d.DEPARTMENT_ID) numt
WHERE numt.emp_num = maxt.max_num; max_didt

--3. 직원수가 max인 값 조회
SELECT max(numt.emp_num) max_num
FROM 
	(SELECT d.DEPARTMENT_ID did, count(e.EMPLOYEE_ID) emp_num
		FROM EMPLOYEES e, DEPARTMENTS d
		WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
		GROUP BY d.DEPARTMENT_ID) numt; maxt

--2. 부서별 직원수 값 조회
SELECT d.DEPARTMENT_ID, count(e.EMPLOYEE_ID) emp_num
FROM EMPLOYEES e, DEPARTMENTS d
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
GROUP BY d.DEPARTMENT_ID; numt

--1. region, countrie, location, department 조인으로 asia에 위치하는 부서 조회
SELECT e.DEPARTMENT_ID, l.CITY, d.DEPARTMENT_NAME, count(e.EMPLOYEE_ID) 
FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
WHERE r.REGION_NAME = 'Americas'
	AND r.REGION_ID = c.REGION_ID 
	AND c.COUNTRY_ID = l.COUNTRY_ID 
	AND l.LOCATION_ID = d.LOCATION_ID
	AND d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY e.DEPARTMENT_ID, l.CITY, d.DEPARTMENT_NAME; ast
	
	
/* # 실습11. 아메리카(Americas)에 위치하고 있는 부서들 중 직원수가 가장 많은 부서의
--    	 평균월급보다 평균월급이 높은 부서들의 부서아이디, 부서명, 직원수, 평균월급을 조회 */
	
SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0))
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
HAVING avg(nvl(e.SALARY,0)) > (SELECT emp_countt.sal_avg
								FROM 
									(SELECT e.DEPARTMENT_ID, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0)) sal_avg
										FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
										WHERE r.REGION_NAME = 'Americas'
											AND r.REGION_ID = c.REGION_ID 
											AND c.COUNTRY_ID = l.COUNTRY_ID 
											AND l.LOCATION_ID = d.LOCATION_ID
											AND d.DEPARTMENT_ID = e.DEPARTMENT_ID
										GROUP BY e.DEPARTMENT_ID
										ORDER BY count(e.EMPLOYEE_ID) DESC) emp_countt
								WHERE rownum=1);

--3. 직원수 최대인 부서의 평균월급
SELECT emp_countt.sal_avg
FROM 
	(SELECT e.DEPARTMENT_ID, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0)) sal_avg
		FROM DEPARTMENTS d, EMPLOYEES e
		WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
			AND d.DEPARTMENT_ID <100
		GROUP BY e.DEPARTMENT_ID
		ORDER BY count(e.EMPLOYEE_ID) DESC) emp_countt
WHERE rownum=1;
														
------														
SELECT emp_countt.sal_avg
FROM (SELECT e.DEPARTMENT_ID did, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0)) sal_avg
		FROM EMPLOYEES e, (SELECT d.DEPARTMENT_ID did
							FROM DEPARTMENTS d
							WHERE d.DEPARTMENT_ID <100) am_didt
		WHERE e.DEPARTMENT_ID = am_didt.did
		GROUP BY e.DEPARTMENT_ID
		ORDER BY count(e.EMPLOYEE_ID) DESC) emp_countt
WHERE rownum =1; max_count_didt

--2. 부서별 직원수, 평균월급

SELECT e.DEPARTMENT_ID, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0))
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
	AND d.DEPARTMENT_ID <100
GROUP BY e.DEPARTMENT_ID;
ORDER BY count(e.EMPLOYEE_ID) DESC;													
										
------

SELECT e.DEPARTMENT_ID, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0))
FROM EMPLOYEES e, (SELECT d.DEPARTMENT_ID did
							FROM DEPARTMENTS d
							WHERE d.DEPARTMENT_ID <100) am_didt
WHERE e.DEPARTMENT_ID = am_didt.did
GROUP BY e.DEPARTMENT_ID
ORDER BY count(e.EMPLOYEE_ID) DESC;

--1. 아메리카에 위치하는 부서 찾기

SELECT d.DEPARTMENT_ID
FROM DEPARTMENTS d
WHERE d.DEPARTMENT_ID <100;					
							
SELECT e.DEPARTMENT_ID, count(e.EMPLOYEE_ID), avg(nvl(e.SALARY,0))
FROM REGIONS r, COUNTRIES c, LOCATIONS l, DEPARTMENTS d, EMPLOYEES e
WHERE r.REGION_NAME = 'Americas'
	AND r.REGION_ID = c.REGION_ID 
	AND c.COUNTRY_ID = l.COUNTRY_ID 
	AND l.LOCATION_ID = d.LOCATION_ID
	AND d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY e.DEPARTMENT_ID
ORDER BY count(e.EMPLOYEE_ID) DESC; 

	
	
/* # 실습12.  레젼명(REGION_NAME), 국가명, 도시명, 부서명, 직원수, 부서평균월급, 순위를 조회
								 (단, 부서평균월급이 높은순으로 정렬. 순위는 부서평균월급순) */
-- rownum 사용 -> 12 만들고 10번고쳐보기!
-- 게시판 paging query 검색
(e.FIRST_NAME||' '||e.LAST_NAME) "EMP_NAME";
SELECT * FROM EMPLOYEES e;
SELECT * FROM DEPARTMENTS d;
SELECT * FROM JOBS j;
SELECT * FROM JOB_HISTORY jh;
SELECT * FROM LOCATIONS l;
SELECT * FROM COUNTRIES c;
SELECT * FROM REGIONS r;


SELECT r.REGION_NAME, c.COUNTRY_NAME, l.CITY, dep_count_empt.dn, dep_count_empt.count_emp, 
		dep_count_empt.avg_sal, RANK() over(ORDER BY dep_count_empt.avg_sal DESC) "Rank"
FROM REGIONS r, COUNTRIES c, LOCATIONS l,
	(SELECT d.DEPARTMENT_NAME dn, d.LOCATION_ID lid, count(e.DEPARTMENT_ID) count_emp, avg(nvl(e.SALARY,0)) avg_sal
		FROM DEPARTMENTS d, EMPLOYEES e
		WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
		GROUP BY d.DEPARTMENT_NAME, d.LOCATION_ID) dep_count_empt
WHERE r.REGION_ID = c.REGION_ID 
	AND c.COUNTRY_ID = l.COUNTRY_ID
	AND l.LOCATION_ID = dep_count_empt.lid;

--1 
SELECT d.DEPARTMENT_NAME, d.LOCATION_ID, d.DEPARTMENT_ID, count(e.DEPARTMENT_ID) count_emp, avg(nvl(e.SALARY,0)) avg_sal
FROM DEPARTMENTS d, EMPLOYEES e
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID 
GROUP BY d.DEPARTMENT_NAME, d.LOCATION_ID, d.DEPARTMENT_ID; dep_count_empt


	