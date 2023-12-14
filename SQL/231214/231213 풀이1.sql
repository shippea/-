-- [쿼리실습 1213]

-- 1. 모든 직원의 풀네임(FIRST_NAME+LAST_NAME)을 내림차순 정렬 조회
SELECT FIRST_NAME||' '||LAST_NAME "풀네임"
FROM EMPLOYEES
ORDER BY "풀네임" DESC;

-- 2. 모든 직원 중에서 2005년 이후 입사자의 수를 조회
SELECT COUNT(*) "2005년 이후 입사자 수"
FROM EMPLOYEES
WHERE HIRE_DATE >= '2005.01.01';

-- 3. 연봉이 전체평균연봉보다 높은 직원의 정보를 연봉이 많은순으로 조회 (연봉=SALARY_12)_
SELECT *
FROM EMPLOYEES E1
WHERE SALARY > (SELECT AVG(NVL(E2.SALARY,0)) FROM EMPLOYEES E2)
_ORDER BY E1.SALARY_12 DESC;

-- 4. 모든 직원의 월급, 커미션, 월급+커미션을 월급+커미션이 많은순으로 조회
SELECT SALARY "월급", E1.SALARY_E1.COMMISSION_PCT "커미션", E1.SALARY_(1+E1.COMMISSION_PCT) "월급+커미션"
FROM EMPLOYEES E1
WHERE E1.SALARY > (SELECT AVG(E2.SALARY) FROM EMPLOYEES E2)
AND E1.COMMISSION_PCT IS NOT NULL
ORDER BY "월급+커미션" DESC;

-- 5. 부서별로 연봉을 가장 많이 받는 직원의 정보를 조회
SELECT *
FROM EMPLOYEES E1
WHERE (E1.DEPARTMENT_ID, E1.SALARY) IN (
SELECT E2.DEPARTMENT_ID, MAX(E2.SALARY)
FROM EMPLOYEES E2
GROUP BY E2.DEPARTMENT_ID);

-- 6. JOB_ID가 SA_REP인 직원들 중에서 입사일이 가장 빠른 직원의 정보를 조회
SELECT *
FROM EMPLOYEES E1
WHERE HIRE_DATE = (
SELECT MIN(E2.HIRE_DATE)
FROM EMPLOYEES E2
WHERE E2.JOB_ID = 'SA_REP');

-- 7. MANAGER_ID가 149인 직원들의 직원아이디, 직원풀네임, 월급을 조회
SELECT EMPLOYEE_ID "직원아이디", FIRST_NAME||' '||LAST_NAME "풀네임", SALARY "월급"
FROM EMPLOYEES
WHERE MANAGER_ID = 149;

-- 8. JOB_ID가 S로 시작하는 직원 중 최저월급을 받는 직원의 정보를 조회
SELECT *
FROM EMPLOYEES E1
WHERE E1.SALARY = (
SELECT MIN(E2.SALARY)
FROM EMPLOYEES E2
WHERE E2.JOB_ID LIKE 'S%');

-- 9. FIRST_NAME이나 LAST_NAME이 5문자 이하인 직원의 정보를 조회
SELECT *
FROM EMPLOYEES
WHERE LENGTH(FIRST_NAME)<=5 OR LENGTH(LAST_NAME)<=5;

-- 10. 짝수년도에 고용된 직원 중에서 COMMISSION_PCT가 있는 직원의 정보를 조회
SELECT *
FROM EMPLOYEES
WHERE MOD(TO_CHAR(HIRE_DATE,'yyyy'), 2) = 0
AND COMMISSION_PCT IS NOT NULL;

-- 11. 월급이 10000이상이면 '고소득자', 10000미만 5000이상이면 '일반소득자'
--       5000미만이면 '저소득자'로 직원의 정보를 조회
SELECT E.*, CASE WHEN SALARY>=10000 THEN '고소득자'
WHEN SALARY<10000 AND SALARY>=5000 THEN '일반소득자'
WHEN SALARY>5000 THEN '저소득자'
ELSE '미분류'
END "소득분류"
FROM EMPLOYEES E;

-- 12. 월급이 2000달러에서 3000달러 사이(양쪽 다 포함)인 직원의 정보를 월급 많은순으로 조회
SELECT *
FROM EMPLOYEES
WHERE SALARY BETWEEN 2000 AND 3000
ORDER BY SALARY DESC;

-- 13. 매니져가 없는 부서의 부서명을 오름차순으로 조회
SELECT DEPARTMENT_NAME "부서명"
FROM DEPARTMENTS
WHERE MANAGER_ID IS NULL
ORDER BY DEPARTMENT_NAME;

-- 14. 매니져의 아이디가 201~205인 부서의 부서명을 오름차순 조회
SELECT DEPARTMENT_NAME "부서명"
FROM DEPARTMENTS
WHERE MANAGER_ID BETWEEN 201 AND 205
ORDER BY DEPARTMENT_NAME;

-- 15. 부서명에 'a'가 포함되는 부서의 정보를 조회
SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME LIKE '%a%';

-- 16. 부서명이 'P'로 시작하고 's'로 끝나는 부서의 정보를 조회
SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME LIKE 'P%s';

-- 17. 직원들의 JOB_ID를 12자리에 맞춰 오른쪽으로 정렬해서 조회
SELECT LPAD(JOB_ID, 12, ' ')
FROM EMPLOYEES;

-- 18. 직원들의 JOB_ID를 AC는 ACC로 ST는 STT로 변경해서 조회
SELECT REPLACE(REPLACE(JOB_ID, 'AC', 'ACC'), 'ST', 'STT')
FROM EMPLOYEES;

-- 19. 직원들의 근무개월수, 근무주수, 근무일수를 조회
SELECT TO_NUMBER(SYSDATE-HIRE_DATE)/30 "근무개월수",
TO_NUMBER(SYSDATE-HIRE_DATE)/7 "근무주수",
TO_NUMBER(SYSDATE-HIRE_DATE) "근무일수"
FROM EMPLOYEES;

-- 20. 직원들의 직무시작일과 직무종료일을 '0000년 00월 00일 00시 00분 00초'로 조회
SELECT TO_CHAR(START_DATE, 'YYYY')||'년 '||
TO_CHAR(START_DATE, 'MM')||'월 '||
TO_CHAR(START_DATE, 'DD')||'일 '||
TO_CHAR(START_DATE, 'HH24')||'시 '||
TO_CHAR(START_DATE, 'MI')||'분 '||
TO_CHAR(START_DATE, 'SS')||'초' "직무시작일",
TO_CHAR(END_DATE, 'YYYY')||'년 '||
TO_CHAR(END_DATE, 'MM')||'월 '||
TO_CHAR(END_DATE, 'DD')||'일 '||
TO_CHAR(END_DATE, 'HH24')||'시 '||
TO_CHAR(END_DATE, 'MI')||'분 '||
TO_CHAR(END_DATE, 'SS')||'초' "직무종료일"
FROM JOB_HISTORY;

-- 21. 직원들의 직무시작일은 한달 전으로 직무종료일은 한달 후로 변경해 조회
SELECT ADD_MONTHS(START_DATE, -1) "직무시작일 한달 전",
ADD_MONTHS(END_DATE, 1) "직무종료일 한달 후"
FROM JOB_HISTORY;

-- 22. 직원들의 직무종료일이 포함된 달의 마지막 일자를 조회
SELECT LAST_DAY(END_DATE) "직무종료월의 말일"
FROM JOB_HISTORY;

-- 23. JOB_ID가  IT_PROG 또는 AC_ACCOUNT 또는 AC_MGR인 것에 대해
--       IT_PROG이면 "정보부", AC_ACCOUNT이면 "회계부", AC_MGR이면 "관리부"로 조회
SELECT DECODE (JOB_ID, 'IT_PROG', '정보부',
'AC_ACCOUNT', '회계부',
'AC_MGR', '관리부')
FROM EMPLOYEES;

-- 24. 부서별로 소속직원수, 직원들의 월급합계를 조회
SELECT DEPARTMENT_ID "부서아이디", SUM(DEPARTMENT_ID) "소속직원수", SUM(SALARY) "직원월급합계"
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;

-- 25. 부서별로 부서아이디, 최대급여, 최소급여, 최대급여와 최소급여의 차를 조회
SELECT DEPARTMENT_ID "부서아이디", MAX(SALARY) "최대급여", MIN(SALARY) "최소급여"
, MAX(SALARY)-MIN(SALARY) "최대최소급여차"
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;

-- 26. 부서별로 직원의 수가 5이상인 부서의 부서아이디, 직원수를 조회
SELECT DEPARTMENT_ID "부서아이디", COUNT(EMPLOYEE_ID) "직원수"
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID
HAVING COUNT(EMPLOYEE_ID)>=5;

-- 27. 전체 직원의 평균월급보다 부서의 평균월급이 높은 부서의 부서아이디, 부서평균월급을 조회
SELECT DEPARTMENT_ID "부서아이디", AVG(SALARY) "부서평균월급"
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID
HAVING AVG(SALARY) > (SELECT AVG(SALARY) FROM EMPLOYEES);

-- 28. 직무아이디, 직무별 소속직원의 수, 부서아이디, 부서별 소속직원의 수를 조회
SELECT JOB_ID "직무아이디", DEPARTMENT_ID "부서아이디", SUM(EMPLOYEE_ID) "소속직원수"
FROM EMPLOYEES
GROUP BY GROUPING SETS(JOB_ID, DEPARTMENT_ID);